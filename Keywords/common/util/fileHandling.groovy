package common.util

import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration;
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.util.KeywordUtil
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import groovy.json.*;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import common.util.aribakeywords as Ariba

import org.bouncycastle.operator.KeyWrapper
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions

public class fileHandling {

	//static Session session=null;					//Stores the session object
	static String ftpHost = "";						//Stores the linux hostname used for selenium grid
	static String ftpUserName = "cqrobotuser";		//Stores the username
	static String ftpPassword = "cqrobotuser";		//Stores the password
	static int ftpPort = 22;						//Stores the sftp port number
	static List<String> ipAddress = new ArrayList();//Stores the VM IPs
	static def list = ["unconfirmed", ".tmp", ".crdownload"];	//Stores the List of temp file
	//static def downloadLocation = null;						//Stores the download location
	static File sharedLocation = new File("/nfs/katalonqa");	//Stores Shared Location between S4 & Grid


	/**
	 * Set download location for Source File and Get Connection for Remote Location
	 * @return
	 */
	static private Map<String, Object> getConnection() {
		Map<String, Object> obj = new HashMap<String, Object>();
		String downloadLocation = null;
		Session session = null;
		String os = "linux";
		String gridHostName = DriverFactory.getRemoteWebDriverServerUrl();
		if (gridHostName != null) {
			//			if (session == null) {
			if(sharedLocation.exists()) {
				//downloadLocation = "/nfs/katalonqa";
				downloadLocation = sharedLocation.getAbsolutePath();
			} else {
				ftpHost = gridHostName.split("/")[2].split(":")[0];
				KeywordUtil.logInfo("GridHostName:"+ftpHost);

				//Creates a Jsch object and disables the hostkey checking
				JSch jsch = new JSch();
				java.util.Properties config = new java.util.Properties();
				config.put("StrictHostKeyChecking", "no");

				//Gets a session and configures the session and connects to server
				session = jsch.getSession(ftpUserName, ftpHost, ftpPort);
				session.setConfig(config);
				session.setPassword(ftpPassword);
				session.connect();
				KeywordUtil.logInfo("Connection to '"+ftpHost+"' Established: "+String.valueOf(session.isConnected()));
				downloadLocation = "/home/"+ftpUserName+"/katalonqa";
				//getRemoteIPs(session);
			}
			//			}
		} else {
			os = System.getProperty("os.name").toLowerCase();
			Process proc = Runtime.getRuntime().exec("whoami");
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String whoami = stdInput.readLine();
			stdInput.close();
			proc.destroyForcibly();
			if (os.contains("windows")) {
				downloadLocation = "C:\\Users\\"+whoami.split("\\\\")[1]+"\\Downloads";
			} else if (os.contains("linux")) {
				downloadLocation = "/home/"+whoami+"/Downloads";
			} else if (os.contains("mac")) {
				downloadLocation = "/Users/"+whoami+"/Downloads";
			} else throw new IllegalArgumentException("Please Contact Katalon Core Team to get support for OS '${os}'.");
		}

		KeywordUtil.logInfo("Download Location:"+downloadLocation);
		obj.put("downloadLocation", downloadLocation);
		obj.put("session", session);
		obj.put("os", os);
		return obj;
	}

	/**
	 * Execute Shell Command on Remote (Unix) Location
	 * @param command
	 * @return
	 */
	static private String executeCommand(Session session, String command) {
		Channel channel=null;			//Stores the channel object
		String result="";				//Stores the last result of execution
		byte[] tmp=new byte[1024];		//Stores byte char from output console
		boolean flag = true;

		//Opens a channel for communication
		channel = session.openChannel("exec");

		//sending command
		KeywordUtil.logInfo("Command:"+command);
		((ChannelExec)channel).setCommand(command);

		channel.setOutputStream(null);
		channel.setInputStream(null);

		InputStream ins = channel.getInputStream();
		OutputStream out=channel.getOutputStream();
		((ChannelExec)channel).setErrStream(System.err, true);
		((ChannelExec) channel).setPty(true);
		channel.connect();

		while(true){
			while(ins.available()>0){

				int i=ins.read(tmp, 0, 1024);
				if(i<0)break;

				result+=new String(tmp,0,i).trim();
				if(result.contains("password") && flag) {
					out.write((ftpPassword+"\n").getBytes());
					out.flush();
					KeywordUtil.logInfo("exiting password");
					flag = false;
				}
			}
			if(channel.isClosed()){
				KeywordUtil.logInfo("exit-status: "+channel.getExitStatus());
				break;
			}
		}
		ins.close();
		out.close();
		channel.disconnect();
		return result
	}

	/**
	 * Get Remote IP Address for all VMs attached to (Swarm) Grid
	 * @return
	 */
	static private getRemoteIPs(Session session) {
		String command = "sudo docker inspect selenium_private"
		String result = executeCommand(session, command);
		String[] results = result.split("\\n");
		for (String line : results) {
			if (line.contains("\"IP\":")) {
				line = line.replace("\"IP\": \"", "").trim();
				ipAddress.add(line.subSequence(0, line.length()-1));
			}
		}
		KeywordUtil.logInfo("ipAddress:"+ipAddress);
	}

	/**
	 * Get the List of Files in Remote Location
	 * @return
	 */
	static private String[] getRemoteFileName(Session session, String downloadLocation) {
		String command = "ls -1 " + downloadLocation;
		String result = executeCommand(session, command);
		String[] files = result.split("\\n").collect {return it.toString().trim()};
		return files;
	}

	/**
	 * Wait for Download to complete
	 * @param listOfFiles
	 * @param fileName
	 * @return
	 */
	static private Object waitForFileDownload(Session session, String downloadLocation, String os, Object[] listOfFiles, String fileName, int retry = 50, int delay = 5) {
		Object selectedFile = null;
		Object[] file = null;
		boolean flag = true;
		while (flag && (retry-- > 0)) {
			int i = 0;
			file = session == null ? ((new File(downloadLocation)).listFiles() - listOfFiles) : (getRemoteFileName(session, downloadLocation) - listOfFiles);
			KeywordUtil.logInfo("DIFF-Local:Retry:${retry}:List:${listOfFiles.size()}->${file}");
			for (;i<file.size();i++) {
				if(list.any { file[i].toString().toLowerCase().contains(it) }) {
					WebUI.delay(delay);
					break;
				}
				if(file[i].toString().contains(fileName.split("\\.")[0])) {
					flag = false;
					selectedFile = file[i];
				}
			}
			WebUI.delay(delay);
		}
		if (flag) throw new IllegalArgumentException("Max retry counter reached, Please contact Automation SPOC");
		if (os.contains("linux"))
			selectedFile = selectedFile.toString().replaceAll("\\ ", "\\\\ ").replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
		if (selectedFile.toString().startsWith("'")) selectedFile = selectedFile.toString().substring(1, selectedFile.toString().length()-1);
		KeywordUtil.logInfo("Selected File:${selectedFile}");
		return selectedFile;
	}

	/**
	 * Get Test Case Name as Prefix
	 * @return: String
	 */
	@Keyword
	static private String getPrefix() {
		String tc = RunConfiguration.getExecutionSourceName();
		String tempTC = tc.split("([0-9]|_)[a-zA-Z]")[0];
		if (tc.indexOf("_",tempTC.length()) != tempTC.length() && tc.length() > tempTC.length() + 1) {
			tempTC =  tc.substring(0, tempTC.length()+1);
		}
		return "Auto" + tempTC + "_";
	}

	/**
	 * Download a File
	 * @param to: Clicking on Object File download operation should start
	 * @param fileName: Expected File Name
	 */
	@Keyword
	static public File downloadFile(TestObject to, String fileName, int retry = 50, int delay = 5) {
		Object file = null;
		boolean flag = true;
		Object[] listOfFiles = null;

		cleanup(fileName);
		Map<String, Object> obj = getConnection();
		String downloadLocation = obj.get("downloadLocation");
		Session session = obj.get("session");
		String os = obj.get("os");
		if (session == null) {
			File folder = new File(downloadLocation);
			listOfFiles = folder.listFiles();
		} else {
			listOfFiles = getRemoteFileName(session, downloadLocation);
		}
		WebUI.click(to);
		WebUI.delay(5);
		WebUI.takeScreenshot();

		file = waitForFileDownload(session, downloadLocation, os, listOfFiles, fileName, retry, delay);
		if (session == null && !sharedLocation.exists()) {
			//def target = RunConfiguration.getProjectDir() + File.separator + "Downloads" + File.separator + fileName;
			def target = RunConfiguration.getLogFolderPath() + File.separator + fileName;
			File f1 = new File(target);
			f1.getParentFile().mkdirs();
			Files.copy(file.toPath(), f1.toPath());
		} else {
			fileTransfer(session, downloadLocation, file.toString(), fileName);
			String tempName = file.toString();
			tempName = tempName.substring(0, tempName.indexOf(".")) + "*";
			executeCommand(session, "rm -rf " + downloadLocation + "/" + tempName);
			if (session != null) {
				downloadLocation = null;
				session.disconnect();
				session = null;
			}
		}
		return new File(RunConfiguration.getLogFolderPath() + "/" + fileName);
	}

	/**
	 * Responsible for File Transfer either get (from Grid) or put (to Grid)
	 * @param fileName
	 * @param dupFileName
	 */
	static private fileTransfer(Session session, String downloadLocation, String fileName, String dupFileName = "get") {
		int counter = 20;
		if (dupFileName.equals("put")) {	//PUT - From execution node to server
			if (sharedLocation.exists()) {
				File source = new File(RunConfiguration.getProjectDir() + File.separator + fileName);
				File dest = new File(downloadLocation + File.separator + getPrefix() + source.getName());
				while (dest.exists() && counter-->0) {
					Thread.sleep(1000);
				}
				Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
				KeywordUtil.logInfo("File copied Successfully:"+downloadLocation+"/"+source.getName());
			} else {
				boolean flag = true;
				//Opens a channel for communication
				Channel channel = session.openChannel("sftp");
				channel.connect();
				ChannelSftp channelsftp = (ChannelSftp) channel;
				File file = new File(RunConfiguration.getProjectDir() + File.separator + fileName);
				fileName = file.getName();
				//Sets the local and remote current directories
				channelsftp.cd(downloadLocation);
				channelsftp.lcd(file.getParent());
				while (flag && counter-->0) {
					try {
						channelsftp.ls(downloadLocation + File.separator + fileName);
					} catch (SftpException e) {
						if (e.id == channelsftp.SSH_FX_NO_SUCH_FILE) {
							flag = false;
						}
					}
				}
				//Place the file to Linux Server
				channelsftp.put(fileName, getPrefix()+fileName);
				KeywordUtil.logInfo("File copied Successfully to Linux Server: "+ftpHost+":"+downloadLocation+"/"+getPrefix()+fileName);
				//Quits the channel and disconnects from the server
				channelsftp.exit();
				channelsftp.disconnect();
			}
		} else {	//GET - From server to execution node
			//Opens a channel for communication
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp channelsftp = (ChannelSftp) channel;
			//KeywordUtil.logInfo("FileName:"+RunConfiguration.getProjectDir() + "/Downloads/" + dupFileName)
			//File file = new File(RunConfiguration.getProjectDir() + File.separator + "Downloads" + File.separator + dupFileName);
			KeywordUtil.logInfo("FileName:"+RunConfiguration.getLogFolderPath() + "/" + dupFileName)
			File file = new File(RunConfiguration.getLogFolderPath() + "/" + dupFileName);
			file.getParentFile().mkdirs();
			int createIndex = 3;
			boolean createFlag = file.createNewFile();
			while(!createFlag && createIndex-->0) createFlag = file.createNewFile();
			OutputStream os = new FileOutputStream(file, false);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			channelsftp.get(downloadLocation+"/"+fileName,bos);
			KeywordUtil.logInfo("File copied Successfully to Executor: "+file);
			//Quits the channel and disconnects from the server
			channelsftp.exit();
			channelsftp.disconnect();
		}
	}

	/**
	 * Mimic Drop File behavior
	 * @param to: Object which will receive Drop File
	 * @param fileName: File Name along with Relative Path
	 */
	@Keyword
	static String dragAndDropFile (TestObject to, String fileName) {
		WebElement target = WebUI.findWebElement(to);
		WebDriver driver = ((org.openqa.selenium.remote.RemoteWebElement)target).getWrappedDriver();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, 30);

		String JS_DROP_FILE =
				"var target = arguments[0]," +
				"    offsetX = arguments[1]," +
				"    offsetY = arguments[2]," +
				"    document = target.ownerDocument || document," +
				"    window = document.defaultView || window;" +
				"" +
				"var input = document.createElement('INPUT');" +
				"input.type = 'file';" +
				"input.style.display = 'none';" +
				"input.onchange = function () {" +
				"  var rect = target.getBoundingClientRect()," +
				"      x = rect.left + (offsetX || (rect.width >> 1))," +
				"      y = rect.top + (offsetY || (rect.height >> 1))," +
				"      dataTransfer = { files: this.files };" +
				"" +
				"  ['dragenter', 'dragover', 'drop'].forEach(function (name) {" +
				"    var evt = document.createEvent('MouseEvent');" +
				"    evt.initMouseEvent(name, !0, !0, window, 0, 0, 0, x, y, !1, !1, !1, !1, 0, null);" +
				"    evt.dataTransfer = dataTransfer;" +
				"    target.dispatchEvent(evt);" +
				"  });" +
				"" +
				"  setTimeout(function () { document.body.removeChild(input); }, 25);" +
				"};" +
				"document.body.appendChild(input);" +
				"return input;";

		WebElement input =  (WebElement)jse.executeScript(JS_DROP_FILE, target, 0, 0);
		File file = new File(RunConfiguration.getProjectDir() + File.separator + fileName);
		String filePath = "";
		if(!file.exists())
			throw new WebDriverException("File not found: " + file);
		Map<String, Object> obj = getConnection();
		String downloadLocation = obj.get("downloadLocation");
		Session session = obj.get("session");
		//filePath = session == null ? sharedLocation.exists() ? "/home/seluser/Downloads/" + getPrefix() + file.getName() : file.getAbsolutePath() : "/home/seluser/Downloads/" + getPrefix() + file.getName();
		if (session == null) {
			if (sharedLocation.exists()) filePath = "/home/seluser/Downloads/" + getPrefix() + file.getName();
			else {
				//def tempFile = RunConfiguration.getProjectDir() + File.separator + "Downloads" + File.separator + getPrefix() + file.getName();
				def tempFile = RunConfiguration.getLogFolderPath() + "/" + getPrefix() + file.getName();
				File f1 = new File(tempFile);
				f1.getParentFile().mkdirs();
				if (f1.exists()) cleanup(f1.getName());
				Files.copy(file.toPath(), f1.toPath());
				filePath = f1.getAbsolutePath();
			}
		} else filePath = "/home/seluser/Downloads/" + getPrefix() + file.getName();

		KeywordUtil.logInfo("File Path for Drop File:"+filePath);
		if(filePath.contains("/seluser/")) fileTransfer(session, downloadLocation, fileName,"put");
		input.sendKeys(filePath);
		wait.until(ExpectedConditions.stalenessOf(input));
		if(session != null) {
			String tempName = new File(filePath).getName();
			tempName = tempName.substring(0, tempName.indexOf(".")) + "*";
			executeCommand(session, "rm -rf " + downloadLocation + "/" + tempName);
			downloadLocation = null;
			session.disconnect();
			session = null;
		}
		return getPrefix() + file.getName();
	}

	/**
	 * Delete the File from Project Download Folder if exists
	 * @param fileName: Delete File Name if present in Download Folder.
	 */
	private static void cleanup(String fileName) {
		//File f = new File(RunConfiguration.getProjectDir() + "/Downloads/" + fileName);
		File f = new File(RunConfiguration.getLogFolderPath() + "/" + fileName);
		KeywordUtil.logInfo("Cleanup for '"+f.getAbsolutePath()+"': "+f.exists());
		if (f.exists()) {
			boolean status = f.delete();
			if (!status) {
				KeywordUtil.markWarning("Unable to delete File:"+fileName);
			}
		}
	}
}
