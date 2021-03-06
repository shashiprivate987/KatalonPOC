<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>getTaskUsingGET</name>
   <tag></tag>
   <elementGuidId>56a050a8-0fe5-4fb2-a2a6-4ecb6ca3aa5b</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;contentType&quot;: &quot;application/x-www-form-urlencoded&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;,
  &quot;parameters&quot;: [
    {
      &quot;name&quot;: &quot;scope&quot;,
      &quot;value&quot;: &quot;&quot;
    },
    {
      &quot;name&quot;: &quot;uniq_attr1&quot;,
      &quot;value&quot;: &quot;PasswordAdapter1&quot;
    },
    {
      &quot;name&quot;: &quot;uniq_attr2&quot;,
      &quot;value&quot;: &quot;${realm_name}&quot;
    },
    {
      &quot;name&quot;: &quot;user_name&quot;,
      &quot;value&quot;: &quot;${userid}&quot;
    },
    {
      &quot;name&quot;: &quot;locale&quot;,
      &quot;value&quot;: &quot;en_US&quot;
    },
    {
      &quot;name&quot;: &quot;display_name&quot;,
      &quot;value&quot;: &quot;tom Aikman&quot;
    },
    {
      &quot;name&quot;: &quot;email&quot;,
      &quot;value&quot;: &quot;&quot;
    },
    {
      &quot;name&quot;: &quot;organization&quot;,
      &quot;value&quot;: &quot;&quot;
    },
    {
      &quot;name&quot;: &quot;custom_attributes&quot;,
      &quot;value&quot;: &quot;{\&quot;ru_realm\&quot; : \&quot;${realm_name}\&quot;,\&quot;realmId\&quot; : \&quot;${realm_id}\&quot;,\&quot;anId\&quot; : \&quot;${anid}\&quot;,\&quot;ccd\&quot; : \&quot;ccc\&quot;,\&quot;ip\&quot; : \&quot;10.163.16.4\&quot;,\&quot;timeZone\&quot; : \&quot;America/Los_Angeles\&quot;,\&quot;ru_id\&quot; : \&quot;${userid}\&quot;,\&quot;cb_session_timeout_secs\&quot; : \&quot;1800\&quot;,\&quot;system\&quot; : \&quot;ACM\&quot;,\&quot;permissions\&quot; : \&quot;35219280363279\&quot;,\&quot;cb_jsession_id\&quot; : \&quot;3CC4FA0AA4CF6EE86F54A0A4C8248CB5.Node10app1348lab1\&quot;,\&quot;cb_user_front_door\&quot; : \&quot;${}\&quot;,\&quot;ru_pwa\&quot; : \&quot;PasswordAdapter1\&quot;,\&quot;cb_session_id\&quot; : \&quot;ikreu4\&quot;}&quot;
    }
  ]
}</httpBodyContent>
   <httpBodyType>x-www-form-urlencoded</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>${BearToken}</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>apikey</name>
      <type>Main</type>
      <value>3db1ecf617bc4bd0b83d276c781529ea</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>${baseURL}/Sourcing/v1/entity.svc/Task/${TaskID}?realm=${TestRealm}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.BEARERTOKEN</defaultValue>
      <description></description>
      <id>fafb33d8-ae7e-4cbe-a480-3f74b127a371</id>
      <masked>false</masked>
      <name>BearToken</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.baseURL</defaultValue>
      <description></description>
      <id>212ebb38-b344-4845-bab3-813f634db0a4</id>
      <masked>false</masked>
      <name>baseURL</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.TaskID</defaultValue>
      <description></description>
      <id>56bcc199-0fc3-4684-a553-43418fe7dffb</id>
      <masked>false</masked>
      <name>TaskID</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.TESTREALM</defaultValue>
      <description></description>
      <id>9fbb5c81-6021-48c0-a0b2-be6be82c8452</id>
      <masked>false</masked>
      <name>TestRealm</name>
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
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
