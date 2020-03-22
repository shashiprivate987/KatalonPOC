<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>SOAP_Test</name>
   <tag></tag>
   <elementGuidId>241b79c1-2a68-4084-989f-a2fde034d615</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Basic ZmdhcmliYWludDphcmliYWFyaWJhMDA=</value>
   </httpHeaderProperties>
   <restRequestMethod></restRequestMethod>
   <restUrl></restUrl>
   <serviceType>SOAP</serviceType>
   <soapBody>&lt;soapenv:Envelope xmlns:soapenv=&quot;http://schemas.xmlsoap.org/soap/envelope/&quot; xmlns:urn=&quot;urn:Ariba:Sourcing:vrealm_50479&quot;>
   &lt;soapenv:Header>
      &lt;urn:Headers>
         &lt;!--You may enter the following 2 items in any order-->
         &lt;!--Optional:-->
         &lt;urn:variant>?&lt;/urn:variant>
         &lt;!--Optional:-->
         &lt;urn:partition>?&lt;/urn:partition>
      &lt;/urn:Headers>
   &lt;/soapenv:Header>
   &lt;soapenv:Body>
      &lt;urn:ContractHeaderExportRequest partition=&quot;?&quot; variant=&quot;?&quot;>
         &lt;!--Optional:-->
         &lt;urn:WSContractHeaderExportInputBean_Item>
            &lt;!--Optional:-->
            &lt;urn:item>
               &lt;!--You may enter the following 6 items in any order-->
               &lt;urn:ClassName>ariba.collaborate.contracts.ContractWorkspace&lt;/urn:ClassName>
               &lt;!--Optional:-->
               
               &lt;!--Optional:-->
               &lt;urn:WorkspaceIds>CW4268&lt;/urn:WorkspaceIds>
            &lt;/urn:item>
         &lt;/urn:WSContractHeaderExportInputBean_Item>
      &lt;/urn:ContractHeaderExportRequest>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceFunction>ContractHeaderExportOperation</soapServiceFunction>
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
   <wsdlAddress>https://s1-2-eu.ariba.com/Sourcing/soap/ZikaInnovation-T/ContractHeaderExport?wsdl&amp;validate=false</wsdlAddress>
</WebServiceRequestEntity>
