<?xml version="1.0" encoding="UTF-8"?>
<WebElementEntity>
   <description></description>
   <name>createFormTemplate</name>
   <tag></tag>
   <elementGuidId>4406206b-9efd-40db-be47-5f26cb2b6ec3</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>tag</name>
      <type>Main</type>
      <value>html</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>dir</name>
      <type>Main</type>
      <value>LTR</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>awmouseup</name>
      <type>Main</type>
      <value>true</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>text</name>
      <type>Main</type>
      <value>










Ariba Spend Management



window.testClientContext = {};
/*
   This file is depreciated with the changes in component inspector.
   There are still some DOM functions that only is still used by ARWDialogBox.
*/
var uagent = navigator.userAgent;
var appver = navigator.appVersion.substring(0,1);

function showTip(ctrl, tiptext, debug) {
    if (debug) {
        if (event.altKey) {
            showIt(tiptext);
        }
    }
    else {
		// in IE all we need to do is set the &quot;title&quot; attribute
		ctrl.title = tiptext;
	}
}

function showIt(s) {
	var p;
	p = window.open(&quot;&quot;,&quot;showdebug&quot;,&quot;toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=1,width=500,height=600&quot;);
	p.document.open(&quot;text/html&quot;, &quot;replace&quot;);
	var i;
	var ss;
	var k;
	k = 0;
	p.document.write(&quot;&lt;html>&lt;head>\n&lt;title>Group/Field Debug&lt;/title>\n&lt;style>\n&lt;!--\n.body {\ncolor: #000000;\nfont-size: 8pt;\nfont-weight: normal;\nfont-family: Verdana, Arial, Helvetica, sans-serif;\n}\n-->\n&lt;/style>&lt;/head>\n&lt;body class='body'>\n&quot;);
        ss = &quot;&quot;;
	for(i = 0; i &lt;= s.length; i++) {
		if (s.charAt(i) == '-') {
			k++;
		}
		else {
			k = 0;
		}
		if (k &lt; 20) {
			ss += s.charAt(i);
			if (s.charAt(i) == '\n')
				ss += &quot;&lt;br>&quot;;
		}
	}
	p.document.write(ss);
	p.document.write(&quot;\n&lt;/body>&lt;/html>\n&quot;);
}

function findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf(&quot;?&quot;))>0&amp;&amp;parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&amp;&amp;d.all) x=d.all[n]; for (i=0;!x&amp;&amp;i&lt;d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&amp;&amp;d.layers&amp;&amp;i&lt;d.layers.length;i++) x=findObj(n,d.layers[i].document);
  if(!x &amp;&amp; d.getElementById) x=d.getElementById(n); return x;
}

function showHideLayers() { //v6.0
  var i,p,v,obj,args=showHideLayers.arguments;
  for (i=0; i&lt;(args.length-2); i+=3) if ((obj=findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
    obj.visibility=v; }
}

function setElementsVisibility (targetName, newDisplay)
{
    // Should consider keeing array of hidden elements
    // (from hideIntersectingSelects)
    // and using that in here rather than scanning all
    var allElements = document.all
    var index = 0;
    for (index = allElements.length - 1;index > -1; index--) {
        var currentElement = allElements[index];
        if (currentElement.tagName == targetName) {
            currentElement.style.visibility = newDisplay;
        }
    }
    return false;
}

function hideIntersectingSelects (divObject)
    {
        var allElements = document.all
        var index = 0;
        var divObjectTop = absoluteTop(divObject);
        var divObjectBottom = divObjectTop + divObject.offsetHeight;
        var divObjectLeft = absoluteLeft(divObject);
        var divObjectRight = divObjectLeft + divObject.offsetWidth;
        for (index = allElements.length - 1;index > -1; index--) {
            var currentElement = allElements[index];
            if (currentElement.tagName == 'SELECT' &amp;&amp; currentElement.id != &quot;awexempt&quot;) {
                if (doesIntersect(divObjectTop, divObjectBottom, divObjectLeft, divObjectRight, currentElement)) {
                    currentElement.style.visibility = 'hidden';
                }
            }
        }
        return false;
    }



function doesIntersect (divObjectTop, divObjectBottom, divObjectLeft, divObjectRight, targetElement)
    {
        var doesIntersect = false;
        var targetElementTop = absoluteTop(targetElement);
        if (divObjectBottom >= targetElementTop) {
            var targetElementBottom = targetElementTop + targetElement.offsetHeight;
            if (divObjectTop &lt;= targetElementBottom) {
                var targetElementLeft = absoluteLeft(targetElement);
                if (divObjectRight >= targetElementLeft) {
                    var targetElementRight = targetElementLeft + targetElement.offsetWidth;
                    if (divObjectLeft &lt;= targetElementRight) {
                        doesIntersect = true;
                    }
                }
            }
        }
        return doesIntersect;
    }



function absoluteTop (element)
{
    var absoluteTop = element.offsetTop;
    var parentElement = element.offsetParent;
    while (parentElement != null) {
        absoluteTop += parentElement.offsetTop;
        parentElement = parentElement.offsetParent;
    }
    return absoluteTop;
}

function absoluteLeft (element)
{
    var absoluteLeft = element.offsetLeft;
    var parentElement = element.offsetParent;
    while (parentElement != null) {
        absoluteLeft += parentElement.offsetLeft;
        parentElement = parentElement.offsetParent;
    }
    return absoluteLeft;
}

#katalon{font-family:monospace;font-size:13px;background-color:rgba(0,0,0,.7);position:fixed;top:0;left:0;right:0;display:block;z-index:999999999;line-height: normal} #katalon div{padding:0;margin:0;color:#fff;} #katalon kbd{display:inline-block;padding:3px 5px;font:13px Consolas,&quot;Liberation Mono&quot;,Menlo,Courier,monospace;line-height:10px;color:#555;vertical-align:middle;background-color:#fcfcfc;border:1px solid #ccc;border-bottom-color:#bbb;border-radius:3px;box-shadow:inset 0 -1px 0 #bbb;font-weight: bold} div#katalon-elementInfoDiv {color: lightblue; padding: 5px}    var AWSessionTime;              
    var AWSessionWarningInterval;   
    var AWSessionTimeoutUrl;        
    var AWSessionExpTime;           
    var AWSessionWarningTime;       
    var AWSessionKeepAliveEnabled = false;
    var AWSessionTimeout;                   
    var AWSessionKeepAliveTimeout;          
    var AWSessionKeepAliveUpdateTimeout;    
    var AWSessionTimeoutDisabled = false;
    var AWShowSessionKeepAliveDiv = false;
    var AWSessionExpirationDiv;
    var AWSessionKeepAliveTime = false;
    var Input = ariba.Input;
    var Event = ariba.Event;
    function awInitSessionTimeout (sessionWarningInterval, sessionTime, sessionTimeoutUrl)
    {
        AWSessionTime = sessionTime;
        AWSessionTimeoutUrl = sessionTimeoutUrl;
        AWSessionWarningInterval = sessionWarningInterval;
        ariba.Event.registerRefreshCallback(awStartInactivityTimer);
        ariba.Event.registerBehaviors({
            TO : {
                click : function (elm, evt) {
                    awExtendSession();
                    return false;
                },
                focusin : function (elm, evt) {
                    $(&quot;body&quot;).attr('role', 'application');
                },
                focusout : function (elm, evt) {
                    $(&quot;body&quot;).removeAttr('role');
                },
                keypress : function (elm, evt) {
                    if(Event.keyCode(evt) == Input.KeyCodeEnter || Event.keyCode(evt) == Input.KeyCodeSpaceBar) {
                    awExtendSession();
                    return false;
                    }
                }
            },
            TOA : {
                click : function (elm, evt) {
                    ariba.Input.uncoverDocument();
                    ariba.Widgets.hideDialogDiv();
                    awSetLocation(AWSessionTimeoutUrl);
                    return false;
                },
                focusin : function (elm, evt) {
                    $(&quot;body&quot;).attr('role', 'application');
                },
                focusout : function (elm, evt) {
                    $(&quot;body&quot;).removeAttr('role');
                },
                keypress : function (elm, evt) {
                    if(Event.keyCode(evt) == Input.KeyCodeEnter || Event.keyCode(evt) == Input.KeyCodeSpaceBar) {
                    ariba.Input.uncoverDocument();
                    ariba.Widgets.hideDialogDiv();
                    awSetLocation(AWSessionTimeoutUrl);
                    return false;
                    }
                }
            }
        });
    }
    function awInitSessionKeepAlive ()
    {
        if (!AWSessionKeepAliveEnabled) {
            AWSessionKeepAliveEnabled = true;
            awStartKeepAliveTimer();
        }
    }
    function awStartInactivityTimer ()
    {
        if (oSessionModal.active || AWSessionTimeoutDisabled) {
            return;
        }
        awClearInactivityTimer();
        var sessionWarningTime = AWSessionTime - AWSessionWarningInterval;
        AWSessionTimeout = setTimeout(function () {
            oSessionModal.show();    
        }, sessionWarningTime);
        var currentTime = (new Date()).getTime();
        AWSessionExpTime = currentTime + AWSessionTime;
        AWSessionWarningTime = currentTime + sessionWarningTime;
    }
    function awClearInactivityTimer ()
    {
        if (AWSessionTimeout)
        {
            clearTimeout(AWSessionTimeout);
            AWSessionTimeout = null;
        }
    }
    var AWSessionExpirationCountdownDiv;
    var AWSessionTimeoutCountdown;
    if (!window.ariba) {
        var ariba = {};
    }
    ariba.SimpleModalManager = function (sId, fnOnShow)
    {
        if (ariba.ModalWindow &amp;&amp; ariba.ModalWindow.isModalInProgress()) {
            return;
        }        
        AWSessionExpirationDiv = ariba.Dom.getElementById(&quot;AWSessionExpirationDiv&quot;);
        if (ariba.ModalWindow &amp;&amp; ariba.ModalWindow.isModalInProgress()) {
            return;
        }        
        AWSessionExpirationDiv = ariba.Dom.getElementById(&quot;AWSessionExpirationDiv&quot;);
        this._id = sId;
        this._cell = null;
        this._button = null;
        this._node = ariba.Dom.getElementById(sId + &quot;Div&quot;);
        this._fnOnShow = fnOnShow;
        this._timeoutCount = 0;
        this.active = false;
    };
    ariba.SimpleModalManager.prototype = {
        buttonCellFocus: function ()
        {
            if (this._cell &amp;&amp; this._cell.focus) {
                this._cell.focus();
            }
        },
        buttonFocus: function ()
        {
            this._button.focus();
        },
        lazyLoad: function ()
        {
            this.active = true;
            if (ariba.Refresh.childrenNeedLoading(this._node)) {
                ariba.Refresh.loadLazyChildren(this._node);
            }
        },
        show: function ()
        {
            if (!this._cell) {
                this._cell = ariba.Dom.getElementById(
                        this._id + &quot;ButtonCell&quot;);
                if (!this._cell) {
                    ariba.Debug.log('SimpleModalManager timeout count = ' +
                            this._timeoutCount);
                    this._timeoutCount++;
                    if (10 &lt; this._timeoutCount) {
                        alert(&quot;Unable to load content. Please refresh and &quot; +
                                &quot;try again.&quot;);
                    }
                    else {
                        if (1 === this._timeoutCount) {
                            this.lazyLoad();
                        }
                        return setTimeout(
                                ariba.Util.bind(this.show, this), 500);
                    }
                }
                this._button = ariba.Util.isAW5() ? ariba.Dom.findChild(this._cell, &quot;TD&quot;) :
                                                    ariba.Dom.findChild(this._cell, &quot;BUTTON&quot;);
            }
            if (ariba.ModalWindow &amp;&amp; ariba.ModalWindow.isModalInProgress()) {
                return;
            }
            window.focus();
            this._node.awPreCloseDialogFunc =
                    ariba.Widgets.enablePage.bind(ariba.Widgets);
            ariba.Widgets.showDialogDiv(
                    this._node, ariba.Widgets.disablePage.bind(
                            ariba.Widgets));
            if (!ariba.Util.isAW5()) {
                ariba.Widgets.hideActiveMenu();
                ariba.Widgets.disablePage(&quot;000&quot;);
                ariba.Input.coverDocument(50, 30);
            }
            if (this._fnOnShow) {
                this._fnOnShow();
            }
            this.buttonFocus();
        }
    };
    var oSessionModal = new ariba.SimpleModalManager(
            'AWSessionExpiration', function () {
        var currentTime = (new Date()).getTime();
        AWSessionExpirationCountdownDiv = ariba.Dom.getElementById(
                &quot;AWSessionExpirationTime&quot;);
        AWSessionTimeoutCountdown = m2s(AWSessionExpTime - currentTime);
        awSessionExpirationCountdown();
    });
    function awSessionExpirationCountdown ()
    {
        if (AWSessionTimeoutCountdown &lt; 1) {
            awSessionDoLogout();
        }
        else {
            AWSessionExpirationCountdownDiv.innerHTML = ariba.Util.isAW5() ?
                AWSessionTimeoutCountdown : ariba.Util.toHHMMSS(AWSessionTimeoutCountdown);
            AWSessionTimeout = setTimeout(awSessionExpirationCountdown, 1000);
            AWSessionTimeoutCountdown--;
        }
    }
    function awSessionDoLogout ()
    {
        if (window.AWTab &amp;&amp; AWTab.areMultipleTabsLocked()) {
            AWTab.doLogout();
        }
        else {
            awSetLocation(AWSessionTimeoutUrl);
        }
    }
    function awSetLocation (url)
    {
        location.href=url;
    }
    function awExtendSession ()
    {
        if (window.AWTab) {
            AWTab.callFunctionInTab('awExtendSessionNoTab');
        }
        awExtendSessionNoTab();
    }
    function awExtendSessionNoTab ()
    {
        oSessionModal.active = false;
        if (!AWSessionKeepAliveEnabled) {
            awInitiateKeepAliveRequest(AWStandaloneSessionKeepAliveUrl);
        }
        if (oSessionModal) {
            if (!ariba.Util.isAW5()) {
                ariba.Input.uncoverDocument();
            }
            ariba.Widgets.hideDialogDiv();
        }
        awStartInactivityTimer();
    }
    function awStartKeepAliveTimer ()
    {
        if (AWSessionKeepAliveTimeout) {
            alert(&quot;existing keep alive timer&quot;);
        }
        else {
            var d = new Date();
            var currentTime = d.getTime();
            if (!AWSessionKeepAliveTime) {
                ariba.Debug.log(&quot;No keep alive time found.\n  Initiate keep alive.&quot;);
                AWSessionKeepAliveTime = -1;
            }
            else {
                AWSessionKeepAliveTime = parseInt(AWSessionKeepAliveTime);
            }
            if (AWSessionKeepAliveTime &lt; currentTime) {
                ariba.Debug.log(&quot;keep alive passed ... initiating keep alive&quot;);
                ariba.Event.registerUpdateCompleteCallback(awInitiateAuthKeepAlive);
                return;
            }
            else {
                var timeout = AWSessionKeepAliveTime - currentTime;
                AWSessionKeepAliveTimeout = setTimeout(awInitiateAuthKeepAlive, timeout);
            }
        }
    }
    var AWCurrentKeepAliveTime;
    function awKeepAliveTimeUpdateTimer ()
    {
        if (AWSessionKeepAliveTime == AWCurrentKeepAliveTime) {
            AWSessionKeepAliveUpdateTimeout =
                setTimeout(awKeepAliveTimeUpdateTimer, 1000);
        }
        else {
            clearTimeout(AWSessionKeepAliveTimeout);
            AWSessionKeepAliveTimeout = null;
            awCompleteKeepAliveRequest();
            awStartKeepAliveTimer();
            awDebugSessionManagement();
        }
    }
    function awInitiateAuthKeepAlive ()
    {
        if (AWSessionKeepAliveDebugTimeout) {
            clearTimeout(AWSessionKeepAliveDebugTimeout);
        }
        AWCurrentKeepAliveTime = AWSessionKeepAliveTime;
        awInitiateKeepAliveRequest(AWSessionKeepAliveUrl);
        AWSessionKeepAliveUpdateTimeout = setTimeout(awKeepAliveTimeUpdateTimer, 1000);
    }
    var AWSessionKeepAliveDebugTimeout;
    function awDebugSessionManagement ()
    {
        var d = new Date();
        var currentTime = d.getTime();
        var msg = &quot;&lt;a href='#' onmousedown='oSessionModal.show();'>(timeout)&lt;/a>&quot;+
                  &quot;Session timeout: &quot; + m2s(AWSessionExpTime - currentTime) + &quot; seconds&quot;+
                  &quot; / warning: &quot; + m2s(AWSessionWarningTime - currentTime) + &quot; seconds&lt;br/>&quot;;
        if (AWSessionKeepAliveEnabled) {
            if (AWSessionKeepAliveTime) {
                AWSessionKeepAliveTime = parseInt(AWSessionKeepAliveTime);
                var timeout = AWSessionKeepAliveTime - currentTime;
            }
            msg += &quot;&lt;a href='#' onmousedown='awInitiateAuthKeepAlive();'>(keepalive)&lt;/a>&quot;+
                   &quot;Next keep alive: &quot; + new Date(AWSessionKeepAliveTime) +
                   &quot; Keep alive timeout: &quot; + m2s(timeout);
        } else {
            msg +=&quot;Session Keep Alive disabled.  Using app server session management.&quot;;
        }
        var iframeDiv = ariba.Dom.getElementById(&quot;AWSessionManagementDebugDiv&quot;);
        if (!iframeDiv) {
            ariba.Debug.log(&quot;AWSessionManagementDebugDiv not available.&quot;);
            return;
        }
        iframeDiv.innerHTML = msg;
        AWSessionKeepAliveDebugTimeout = setTimeout(awDebugSessionManagement, 1000);
    }
    function awUpdateClientKeepAlive (sessionKeepAliveTime)
    {
        try {
            parent.parent.AWSessionKeepAliveTime = sessionKeepAliveTime;
        }
        catch (e) {
        }
    }
    function awUpdateKeepAliveTime (sessionKeepAliveTime)
    {
        AWSessionKeepAliveTime = sessionKeepAliveTime;
        awInitSessionKeepAlive();
    }
    function m2s (m)
    {
        return (m-m%1000)/1000;
    }
    function awCompleteKeepAliveRequest ()
    {
        var iframeDiv = ariba.Dom.getElementById(&quot;AWSessionKeepAliveDiv&quot;);
        if (iframeDiv) {
            $(iframeDiv).removeClass('is-block').addClass('is-dnone');
        }
    }
    function awInitiateKeepAliveRequest (url)
    {
        var iframeDiv = ariba.Dom.getElementById(&quot;AWSessionKeepAliveDiv&quot;);
        if (!iframeDiv) {
            iframeDiv = document.createElement(&quot;div&quot;);
            iframeDiv.id = &quot;AWSessionKeepAliveDiv&quot;;
            if (!AWShowSessionKeepAliveDiv) {
                $(iframeDiv).addClass('is-dnone');
            }
            ariba.Dom.appendToBody(iframeDiv);
            awInitiateKeepAliveRequest = function (url)
            {
                ariba.Debug.log(&quot;keep alive initiated: &quot; + url);
                var height = AWShowSessionKeepAliveDiv ? 
                        'leg-w-300 w-awsession-req-iframe-keep' : ' leg-h-0 leg-w-0 w-awsession-req-iframe-nokeep';
                $(iframeDiv).removeClass('is-dnone').addClass('is-block');
                iframeDiv.innerHTML = '&lt;iframe src=&quot;' + url + '&quot;' +
                        ' id=&quot;AWSessionManagement&quot; name=&quot;AWSessionManagement&quot;'
                        + ' class=&quot;leg-brdr-0 w-awsession-req-iframe-border' + height + '&quot;>&lt;/iframe>';
            };
            awInitiateKeepAliveRequest(url);
        }
    }

AWSessionTimeoutDisabled = false;
var AWSessionKeepAliveTime;
var AWSessionKeepAliveUrl = 'https://svcdev10ss.ariba.com/Sourcing/Main/ad/keepAlive/SSOActions';

ariba.Event.registerHandler(&quot;_$cndhd&quot;, &quot;onRefreshRequestComplete&quot;);

if (!window.ariba) ariba= parent.ariba || {}; ariba.awCurrWindow = parent;
if (window.name == &quot;AWRefreshFrame&quot;) {
ariba_IR = true;
RJS = function (inc, sync, isGS, f) { if (inc) parent.ariba.Refresh.RSS(sync, isGS, f.toString()); }
}if (window.ariba_IR &amp;&amp; ariba &amp;&amp; ariba.Request) {
ariba.Request.requestNotInProgress();
}























var ENABLE_COMMUNITY = true;




var SCREEN_RESOLUTION_WIDTH = 1280;
var IN_SITU_COLLAPSED_BY_USER = false;









RJS(true, true, false, function(){
ariba.Debug.AWDebugJSUrl = '/s4/ariba/ui/aribaweb/AWJSDebugWin.js';
ariba.Request.AWDebugEnabled = true;
ariba.Request.AWJSDebugEnabled = false;
ariba.Request.AWShowRequestFrame = false;
ariba.Refresh.AWShowHistoryFrame = false;
ariba.Input.AWAutomationTestModeEnabled = true;
ariba.Refresh.AWMarkRefreshRegions = false;
});













RJS(true, true, false, function(){ariba.Request.initParams('6','VHzTCYW9','/Sourcing/Main/aw?awh=r&amp;awssk=VHzTCYW9&amp;realm=s4All-2','/Sourcing/Main/ad/ping?u=h3m07Lkc&amp;realm=s4All-2','/Sourcing/Main/ad/progressCheck?u=h3m07Lkc&amp;realm=s4All-2','https://svcdev10ss.ariba.com/Sourcing/Main/aw',true,null,null,
null,'/Sourcing/Main/aw?awh=b&amp;awssk=VHzTCYW9','/Sourcing/Main/aw?awh=f&amp;awssk=VHzTCYW9',6000,'Please enable window popup for this site to use this function.');});















































RJS(true, true, false, function(){
ariba.Refresh.checkParentFrame(false);
});






RJS(true, false, true, function(){
window.testClientContext = {};
});


TA..  








Test Automation











Test Central ACM






Test Central 









Test Central Buyer









Test Central ASN Buyer









Test Central ASN Supplier









Test Central ASN Discovery









Test Central ASN Sourcing









Validation





End Test







Run a CSV





Document a Selenium Zip







Test Context









Reset Context



Save Context






















Available objects











ariba.ui.aribaweb.util.AWCommunityContext







ariba.app.integrate.test.SessionValidator







ariba.ui.aribaweb.core.AWErrorManager$AWNewErrorManager









RJS(true, false, false, function(){
ariba.Datatable.registerNonScrollTableId(&quot;_yyyn0c&quot;, false, false,true,  false);
});


















if (ariba_IR) ariba.Dom.removeRelocatedCopy('awTestPane');










RJS(true, true, false, function(){
ariba.Dom.setBodyClass(&quot;&quot;);
ariba.Dom.ApplicationType = &quot;ACM&quot;;

});













































Back













Alert ()





















Analytical Report


Compound Report


Content Document


Contract Clause


Contract Request (Procurement)


Contract Request (Sales)


Contract Workspace (Internal)


Contract Workspace (Procurement)


Contract Workspace (Sales)


Event


Excel Template


Knowledge Project


Preferred Supplier Management Project


Sourcing Project


Sourcing Request


Supplier Disqualification Project


Supplier Performance Management Project


Supplier Qualification Management Project


Supplier Qualification Project


Supplier Registration Project


Supplier Request Project


Supplier Segmentation Project


Supplier Workspace


Suppliers and Customers


More...




if (ariba_IR) ariba.Dom.removeRelocatedCopy('Search');


























RJS(true, false, false, function(){var rppId = '_mz1q7d';
});






















Help










Help Center






New UI Tour








Events






Contact Us


if (ariba_IR) ariba.Dom.removeRelocatedCopy('help');










Max Olson








Logout


 Preferences







Change reporting preferences


Change password


Change secret question


Delegate authority


Update profile


Change default locale and currency


Change notification preferences


Change Desktop File Sync


Change dashboard preferences


Download sourcing user info






 Theme




Dark Theme





Light Theme



Black Theme



if (ariba_IR) ariba.Dom.removeRelocatedCopy('Preferences');

























































RJS(true, false, false, function(){
ariba.Widgets.updateTOC(false);
});















RJS(true, false, false, function(){
ariba.Widgets.updateDialogWrapperClass(false);
});






Form Templates














Overview








Documents








Team







History



















Dismiss above warning for currently affected files in all projects



Do nothing and continue warn me about the issue
if (ariba_IR) ariba.Dom.removeRelocatedCopy('HandleDFSDirectoryErrorMenu');




























Form Templates




Show Details


Actions
 























 Show / Hide Columns







Name









Owner









Status







Modified By







Last Modified







Document Type









if (ariba_IR) ariba.Dom.removeRelocatedCopy('_twr65b');



























Name





RJS(true, true, false, function(){ariba.Refresh.loadJSFile(&quot;/s4/21388/ariba/resource/en_US/scripts/Fields.js&quot;);
});


Owner





Status




 



































FormTemplate1234






Loading...


if (ariba_IR) ariba.Dom.removeRelocatedCopy('Doc1464458608464834');














Max Olson















Active










 



























FormTemplate123






Loading...


if (ariba_IR) ariba.Dom.removeRelocatedCopy('Doc1464443846016511');














Max Olson















Active










 



























This is formTemplate Title 260718






Loading...


if (ariba_IR) ariba.Dom.removeRelocatedCopy('Doc1464428622828390');














Max Olson















Active










 



























FormTemplate260718






Loading...


if (ariba_IR) ariba.Dom.removeRelocatedCopy('Doc14612551671654912');














Max Olson















Active










 

















RJS(true, false, false, function(){
ariba.Datatable.registerScrollTable(&quot;_un_hxd&quot;,  false,
true, false,&quot;_uuqbx&quot;);
});




























RJS(true, false, false, function(){
ariba.Datatable.updateScrollTable(&quot;_un_hxd&quot;, true, &quot;_vhh8vd&quot; ,
&quot;0&quot;, &quot;0&quot;, &quot;_qw4hsc&quot;, &quot;_egkbm&quot;, &quot;_t$tq5c&quot;, &quot;_di2dbb&quot;,
&quot;&quot;, &quot;_cnffcb&quot;, 4);
});

















if (ariba_IR) ariba.Dom.removeRelocatedCopy('MyMenu');















domainObject:Workspace,activity:View





RJS(true, true, false, function(){
ariba.Widgets.clearDialog();
});



RJS(true, false, false, function(){
ariba.Input.allowSelectFirstText();
});
































Max Olson (molson) | Buyer Organization | UI18410007










Security Disclosure


Privacy Statement

Cookie Statement

Participant Terms









© 1996 - 2018 Ariba Inc. All Rights Reserved












United Kingdom (Freephone): 0800 358 3556


Europe: +44 20 7187 4144; Asia: +65 6311 4746


All other locations, call +1 412 222 6153 or



if (ariba_IR) ariba.Dom.removeRelocatedCopy('FL_Menu');









RJS(true, false, false, function(){
ariba.Widgets.initAW6Footer('w-footer-id');
});













Search


 Export



Form Templates

 Import



Form Templates







 Create





Folder









Analytical Report








Compound Report





















Form Template
 Download


Documents









RJS(true, false, false, function(){ariba.Widgets.hideBubble()
});












Loading...







Loading...
























































Loading content ...













































AW Options...



AQL Queries:
5


FULL PAGE REFRESH!: 














RJS(true, false, false, function(){
ariba.Debug.updateComponentInspector('0DA30FF9C24F8302C376AFE8FB1A1D10.UI18410007',
'_4fjidb',
'_5a6oeb',
( false || false || false ));
});






Loading...


if (ariba_IR) ariba.Dom.removeRelocatedCopy('awDebugPane');













RJS(true, true, false, function(){ariba.Refresh.loadJSFile(&quot;/s4/21388/ariba/ui/AWSessionManagement.js&quot;);
});


RJS(true, false, false, function(){

awInitSessionTimeout(60000, 1800000, '/Sourcing/Main/ad/initiateSessionTimeout/SSOActions?u=h3m07Lkc&amp;realm=s4All-2');

});



RJS(true, false, true, function(){
AWSessionTimeoutDisabled = false;
var AWSessionKeepAliveTime;
var AWSessionKeepAliveUrl = 'https://svcdev10ss.ariba.com/Sourcing/Main/ad/keepAlive/SSOActions';
});



RJS(true, false, false, function(){
awUpdateKeepAliveTime(1532674560606);
});








Loading...






Loading...









RJS(true, false, false, function(){
// initiate the polling
ariba.Request.setupPoll(true, 60,
'awpoll',
'awpollupdate',
false);

});


















RJS(true, false, false, function(){

ariba.Request.AWPollCallback = null;

});



























ariba.Input.registerActiveElementId();



RJS(true, false, true, function(){
ariba.Event.registerHandler(&quot;_$cndhd&quot;, &quot;onRefreshRequestComplete&quot;);
});



RJS(true, false, false, function(){ariba.Dom.setPageScroll(0,0);ariba.Input.postLoadFocusOnActiveElement();
});





ariba.Refresh.completeRequest(1,2,ariba_IR,false);



















page:ariba/collaborate/projectui/PIHome,app:ariba.asm.appbase.ASMApplication








Updating...





/html[1]</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath</name>
      <type>Main</type>
      <value>/html[1]</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>awname</name>
      <type>Main</type>
      <value>CreateFormTemplatePopupMenuItem:createNewFormTemplateAction</value>
   </webElementProperties>
</WebElementEntity>
