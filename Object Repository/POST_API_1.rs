<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>POST_API_1</name>
   <tag></tag>
   <elementGuidId>beae6d96-93c7-499c-b0cb-6f5cc6c001a2</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;title\&quot; : \&quot;API_Test1\&quot;,\n  \&quot;description\&quot; : \&quot;Create Me desc\&quot;,\n  \&quot;templateId\&quot; : \&quot;SYS0052\&quot;,\n  \&quot;commodities\&quot; : [ {\n    \&quot;uniqueName\&quot; : \&quot;56\&quot;,\n    \&quot;domain\&quot; : \&quot;unspsc\&quot;,\n    \&quot;name\&quot; : \&quot;Furniture and Furnishings\&quot;\n  }, {\n    \&quot;uniqueName\&quot; : \&quot;76\&quot;,\n    \&quot;domain\&quot; : \&quot;unspsc\&quot;,\n    \&quot;name\&quot; : \&quot;Industrial Cleaning Services\&quot;\n  } ],\n  \&quot;regions\&quot; : [ {\n    \&quot;uniqueName\&quot; : \&quot;APAC\&quot;,\n    \&quot;name\&quot; : \&quot;Asia Pacific\&quot;\n  }, {\n    \&quot;uniqueName\&quot; : \&quot;CA\&quot;,\n    \&quot;name\&quot; : \&quot;California\&quot;\n  } ],\n  \&quot;supplier\&quot; : {\n    \&quot;name\&quot; : \&quot;Apex Corporation\&quot;,\n    \&quot;systemID\&quot; : \&quot;sid509\&quot;,\n    \&quot;smVendorID\&quot; : null,\n    \&quot;organizationIDs\&quot; : [ {\n      \&quot;domain\&quot; : \&quot;internalsystemid\&quot;,\n      \&quot;value\&quot; : \&quot;1009\&quot;\n    }, {\n      \&quot;domain\&quot; : \&quot;duns\&quot;,\n      \&quot;value\&quot; : \&quot;700000080\&quot;\n    }, {\n      \&quot;domain\&quot; : \&quot;networkid\&quot;,\n      \&quot;value\&quot; : \&quot;an70000000080\&quot;\n    }, {\n      \&quot;domain\&quot; : \&quot;oracle\&quot;,\n      \&quot;value\&quot; : \&quot;4009\&quot;\n    }, {\n      \&quot;domain\&quot; : \&quot;psoft\&quot;,\n      \&quot;value\&quot; : \&quot;share:usa0000022\&quot;\n    }, {\n      \&quot;domain\&quot; : \&quot;sap\&quot;,\n      \&quot;value\&quot; : \&quot;0000001050\&quot;\n    }, {\n      \&quot;domain\&quot; : \&quot;buyersystemid\&quot;,\n      \&quot;value\&quot; : \&quot;sid509\&quot;\n    }, {\n      \&quot;domain\&quot; : \&quot;sourcingnetworkid\&quot;,\n      \&quot;value\&quot; : \&quot;an70000000080\&quot;\n    } ],\n    \&quot;address\&quot; : {\n      \&quot;name\&quot; : \&quot;\&quot;,\n      \&quot;uniqueName\&quot; : \&quot;sid509\&quot;,\n      \&quot;phone\&quot; : \&quot;\&quot;,\n      \&quot;fax\&quot; : \&quot;\&quot;,\n      \&quot;lines\&quot; : [ \&quot;753 Apex Avenue\&quot; ],\n      \&quot;city\&quot; : \&quot;Marlborough\&quot;,\n      \&quot;state\&quot; : \&quot;MA\&quot;,\n      \&quot;postalCode\&quot; : \&quot;1752\&quot;,\n      \&quot;country\&quot; : \&quot;US\&quot;\n    }\n  },\n  \&quot;contractStatus\&quot; : \&quot;Draft\&quot;,\n  \&quot;expirattionTermType\&quot; : \&quot;Fixed\&quot;,\n  \&quot;effectiveDate\&quot; : \&quot;2018-04-24T00:00:00.000+0000\&quot;,\n  \&quot;expirationDate\&quot; : null,\n  \n  \&quot;contractAmount\&quot; : {\n    \&quot;amount\&quot; : 1000,\n    \&quot;currency\&quot; : \&quot;USD\&quot;\n  }\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
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
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://svcmachss.ariba.com/Sourcing/private/contractsnxg/workspace?user=customersupportadmin&amp;realm=s4All-12&amp;passwordAdapter=PasswordAdapter1</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
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
