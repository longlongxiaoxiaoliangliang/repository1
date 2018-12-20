<%--
  Created by IntelliJ IDEA.
  User: long1
  Date: 2018/10/29
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="0" style="margin-top:4px; margin-left: 18px">
    <tr>
        <td><a href="#"  onclick="downloadfile();">数据导出</a></td>
    </tr>
</table>
<script>
    function downloadfile(){
        window.location.href="/entry/upload";
    }
</script>
</body>
</html>
