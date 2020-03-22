<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>oauth_token_validation_failure_invalid_accesstoken</name>
   <tag></tag>
   <elementGuidId>3cbdf495-6e7d-461e-bdec-e827cff708b8</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;&quot;,
  &quot;contentType&quot;: &quot;text/plain&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Accept</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>${url}/owb/ui/dashboard?user=shwetabg&amp;realm=s4All-13&amp;sessionId=1631988245&amp;accessToken=${accTkn}vfvhyh&amp;rfxId=123456&amp;locale=en_US</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.token</defaultValue>
      <description></description>
      <id>ddc3585d-bcdb-4ed6-96c2-2ca3a24fa415</id>
      <masked>false</masked>
      <name>accTkn</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.devfarmURL</defaultValue>
      <description></description>
      <id>ea14c8a6-d3cf-4f03-8122-4cb4e6a07421</id>
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

println(&quot;Global AccessToken:&quot;+GlobalVariable.token)

WS.verifyResponseStatusCode(response, 401)

assertThat(response.getStatusCode()).isEqualTo(401)

//if(WS.verifyResponseStatusCode(response, 200))
//	assertThat(response.getStatusCode()).isEqualTo(200)
//else if(WS.verifyResponseStatusCode(response, 401))
//	assertThat(response.getStatusCode()).isEqualTo(401)
//else
//	assertThat(response.getStatusCode()).isEqualTo(400)


</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
