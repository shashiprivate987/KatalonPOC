<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>oauth_token_validation_success</name>
   <tag></tag>
   <elementGuidId>5099b5b6-7760-4ef0-866d-e4122cb7a8e6</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\&quot;accessToken\&quot;: \&quot;$GlobalVariable.token\&quot;&quot;,
  &quot;contentType&quot;: &quot;text/plain&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>${url}/owb/ui/dashboard?user=shwetabg&amp;realm=s4All-13&amp;sessionId=1631988245&amp;accessToken=${accTkn}&amp;rfxId=123456&amp;locale=en_US</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.token</defaultValue>
      <description></description>
      <id>30cf7553-8ea3-4c85-8457-c7212e83aadf</id>
      <masked>false</masked>
      <name>accTkn</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.devfarmURL</defaultValue>
      <description></description>
      <id>d83001a4-62f3-4cbc-992c-cfb2960230c7</id>
      <masked>false</masked>
      <name>url</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()


println(&quot;AccessToken:&quot;+GlobalVariable.token)

//def localtoken = GlobalVariable.token
//def localdevurl = GlobalVariable.devfarmURL
//
//println &quot;localtoken -- >&quot;+localtoken
//println &quot;local URL -- >&quot;+localdevurl
//
////def localtoken = GlobalVariable.token
////
////println &quot;local access token&quot;+localtoken


WS.verifyResponseStatusCode(response, 200)

assertThat(response.getStatusCode()).isEqualTo(200)</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
