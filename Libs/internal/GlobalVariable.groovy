package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object baseURL
     
    /**
     * <p></p>
     */
    public static Object realm
     
    /**
     * <p></p>
     */
    public static Object AUTH_SERVER
     
    /**
     * <p></p>
     */
    public static Object access_token
     
    /**
     * <p></p>
     */
    public static Object search_pattern
     
    /**
     * <p></p>
     */
    public static Object isTeamMember
     
    /**
     * <p></p>
     */
    public static Object Strict
     
    /**
     * <p></p>
     */
    public static Object TestDataFile
     
    /**
     * <p></p>
     */
    public static Object token
     
    /**
     * <p></p>
     */
    public static Object oAuthURL
     
    /**
     * <p></p>
     */
    public static Object devfarmURL
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += RunConfiguration.getOverridingParameters()
    
            baseURL = selectedVariables['baseURL']
            realm = selectedVariables['realm']
            AUTH_SERVER = selectedVariables['AUTH_SERVER']
            access_token = selectedVariables['access_token']
            search_pattern = selectedVariables['search_pattern']
            isTeamMember = selectedVariables['isTeamMember']
            Strict = selectedVariables['Strict']
            TestDataFile = selectedVariables['TestDataFile']
            token = selectedVariables['token']
            oAuthURL = selectedVariables['oAuthURL']
            devfarmURL = selectedVariables['devfarmURL']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
