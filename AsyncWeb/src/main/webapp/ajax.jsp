<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%@include file="commons/pageCommon.jsp" %>
<html>

<head>
    <%@include file="commons/headCommon.jsp" %>
    <title>${applicationScope.urlMappingConstants.getTitle(PageNames.AJAX_FROM_SCRATCH)}</title>
</head>

<header>
    <%@include file="commons/headerCommon.jsp" %>
</header>
<body onload="setInterval('startRequest()' ,1000)">
<FORM name="ajax" action="#">
    <input type="button" value="Submit" onclick="submitForm();">
    <input type="text" value="" style="width: 100%;padding: 16px;" name="dyn">
</FORM>
<br>
<FORM name="ajax2" action="#">
    <input type="text" id="theName" value="" size="20" onblur="submitForm2();">
    <input type="text" id="status" value="" size="20" disabled>

</FORM>
<br>
<div id="results">
</div>
<footer>
    <%@include file="commons/footerCommon.jsp" %>

    <script>
        let req = null;

        function submitForm() {
            if (window.XMLHttpRequest)
                req = new XMLHttpRequest();
            else if (window.ActiveXObject)
                req = new ActiveXObject(Microsoft.XMLHTTP);
            req.onreadystatechange = handleReq;
            req.open("GET", "simpleResponse.txt?t=" + new Date().getTime(), true);
            req.send(null);
        }

        function handleReq() {
            if (req.readyState == 4)
                if (req.status == 200)
                    document.ajax.dyn.value = "Received:" + req.responseText;
                else
                    document.ajax.dyn.value = "Error code " + req.status;
        }

        // second

        let req2 = null;

        function submitForm2() {
            if (window.XMLHttpRequest)
                req2 = new XMLHttpRequest();
            else if (window.ActiveXObject)
                req2 = new ActiveXObject(Microsoft.XMLHTTP);
            req2.onreadystatechange = handleStateChange;
            let yourvalue = document.getElementById("theName").value;
            url = "ServletGetUser" + "?uName=" + yourvalue + "&timeStamp=" + new Date().getTime();
            req2.open("GET", url, true);
            req2.send(null);
        }

        function handleStateChange() {
            if (req2.readyState == 4 && req2.status == 200) {
                let xmlvalue = req2.responseText;
                document.getElementById("status").value = xmlvalue;
            }
        }

        // 3
        let xmlHttp;

        function startRequest() {
            createXMLHttpRequest();
            xmlHttp.onreadystatechange = handleStateChange3;
            xmlHttp.open("GET", "innerHTML.txt", true);
            xmlHttp.send(null);
        }

        function createXMLHttpRequest() {
            if (window.ActiveXObject)
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            else if (window.XMLHttpRequest)
                xmlHttp = new XMLHttpRequest();
        }

        function handleStateChange3() {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                document.getElementById("results").innerHTML =
                    xmlHttp.responseText;
        }

        // var xmlDoc = xmlHttp.responseXML;
        // var states = xmlDoc.getElementsByTagName("state");
        // for (var i = 0; i < states.length; i++) {
        //     currentState = states[i];
        //     out = out + "\n- " + currentState.childNodes[0].nodeValue;
        //     Or
        //     out = out + "\n- " + currentState.firstChild.nodeValue;
        // }
    </script>
</footer>
</body>
</html>