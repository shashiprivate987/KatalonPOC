<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>oAuthGenerator</name>
   <tag></tag>
   <elementGuidId>3afa9e6c-6374-4220-8118-df5d7d18b442</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;contentType&quot;: &quot;application/x-www-form-urlencoded&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;,
  &quot;parameters&quot;: [
    {
      &quot;name&quot;: &quot;uniq_attr1&quot;,
      &quot;value&quot;: &quot;PasswordAdapter1&quot;
    },
    {
      &quot;name&quot;: &quot;uniq_attr2&quot;,
      &quot;value&quot;: &quot;s4All-13&quot;
    },
    {
      &quot;name&quot;: &quot;user_name&quot;,
      &quot;value&quot;: &quot;shwetabg&quot;
    },
    {
      &quot;name&quot;: &quot;locale&quot;,
      &quot;value&quot;: &quot;en_US&quot;
    },
    {
      &quot;name&quot;: &quot;display_name&quot;,
      &quot;value&quot;: &quot;shwetabg&quot;
    },
    {
      &quot;name&quot;: &quot;custom_attributes&quot;,
      &quot;value&quot;: &quot;{\&quot;permissions\&quot;:\&quot;35184372088831\&quot;,anId\u003d\&quot;AN71000004342\&quot;}&quot;
    },
    {
      &quot;name&quot;: &quot;scope&quot;,
      &quot;value&quot;: &quot;&quot;
    }
  ]
}</httpBodyContent>
   <httpBodyType>x-www-form-urlencoded</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/x-www-form-urlencoded</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Basic c291cmNpbmctMmxvLWNsaWVudDpwcml2YXRlc291cmNpbmcybG8x</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${URL}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.oAuthURL</defaultValue>
      <description></description>
      <id>fdba3fd1-21fd-4de8-8e6e-ad4551fba921</id>
      <masked>false</masked>
      <name>URL</name>
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



WS.verifyResponseStatusCode(response, 200)

assertThat(response.getStatusCode()).isEqualTo(200)


def respJson = response.getResponseText()

JsonSlurper slurper = new JsonSlurper()

Map parsedJson = slurper.parseText(respJson)

GlobalVariable.token = parsedJson.get(&quot;access_token&quot;)
                   
println(&quot;AccessToken:&quot;+GlobalVariable.token)
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
