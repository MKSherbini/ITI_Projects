<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%@include file="commons/pageCommon.jsp" %>
<html>

<head>
    <%@include file="commons/headCommon.jsp" %>
    <title>${applicationScope.urlMappingConstants.getTitle(PageNames.CHAT)}</title>
</head>

<header>
    <%@include file="commons/headerCommon.jsp" %>
</header>

<body class="text-white" onload="setInterval('getMsgs()' ,1000)" style="background-color: #00183c">
<div id="myAlert" hidden class="alert alert-danger" role="alert">
    This is a primary alert—check it out!
</div>
<div class="row">
    <div class="mb-3 col-md-4">
        <label for="Name" class="form-label">Name</label>
        <input type="text" class="form-control" id="Name" aria-describedby="emailHelp">
    </div>
    <div class="mb-3 col-md-4">
        <label for="Message" class="form-label">Message</label>
        <input type="text" class="form-control" id="Message">
    </div>
    <input type="button" class="btn btn-primary" onclick="sendMsg();" value="Send"/>
</div>
<br>
<table border="1" style="width: 100%;padding: 16px;" class="table table-striped">
    <tbody id="tableBody">

    </tbody>
</table>
<footer>
    <%@include file="commons/footerCommon.jsp" %>
    <script>
        let alertIt = function (str) {
            if (typeof (alertIt.myAlert) === 'undefined') {
                alertIt.myAlert = document.getElementById("myAlert");
            }
            alertIt.myAlert.innerHTML = str;
            alertIt.myAlert.hidden = false;
            window.scrollTo(0, 0);
            setTimeout(function () {
                alertIt.myAlert.hidden = true;
            }, 2000);
        }

        // let req = null;

        function sendMsg() {
            let msg = jQuery("#Message").val();
            let name = jQuery("#Name").val();
            if (!msg || !name) {
                alertIt("Fill both fields");
                return;
            }
            $.post("ServletAddMsg", {
                username: name,
                msg: msg
            });

            // if (window.XMLHttpRequest)
            //     req = new XMLHttpRequest();
            // else if (window.ActiveXObject)
            //     req = new ActiveXObject(Microsoft.XMLHTTP);
            // req.open("GET", "ServletAddMsg?username=" + name + "&msg=" + msg);
            // req.send(null);
        }


        // let req2 = null;

        function getMsgs() {
            $.get("ServletGetMsgs", ajaxCallBack);
            // if (window.XMLHttpRequest)
            //     req2 = new XMLHttpRequest();
            // else if (window.ActiveXObject)
            //     req2 = new ActiveXObject(Microsoft.XMLHTTP);
            // req2.onreadystatechange = fetch;
            // req2.open("GET", "ServletGetMsgs");
            // req2.send(null);
        }

        function ajaxCallBack(responseTxt, statusTxt, xhr) {
            if (statusTxt == "success") {
                let jsonObj = JSON.parse(responseTxt);
                let html = " <tr> <th> UserName</th><th> Message</th></tr>";
                for (let i = 0; i < jsonObj["messages"].length; i++) {
                    let obj = jsonObj["messages"][i];
                    console.log(obj);
                    html += "<tr>\n" +
                        "        <td>" + obj["userName"] + "</td>\n" +
                        "        <td> " + obj["msg"] + "</td>\n" +
                        "    </tr>";
                }
                $("#tableBody").html(html);
            }
        }

        // function fetch() {
        //     if (req2.readyState == 4 && req2.status == 200) {
        //         let jsonObj = JSON.parse(req2.responseText);
        //         let html = " <tr> <th> UserName</th><th> Message</th></tr>";
        //         for (let i = 0; i < jsonObj["messages"].length; i++) {
        //             let obj = jsonObj["messages"][i];
        //             console.log(obj);
        //             html += "<tr>\n" +
        //                 "        <td>" + obj["userName"] + "</td>\n" +
        //                 "        <td> " + obj["msg"] + "</td>\n" +
        //                 "    </tr>";
        //         }
        //         $("#tableBody").html(html);
        //     }
        // }


    </script>

</footer>
</body>
</html>