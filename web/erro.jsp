<%-- 
    Document   : erro
    Created on : 06/05/2016, 08:56:07
    Author     : DTI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gabinete Virtual</title>
    </head>
    <body>
        <h1>Erro!!!</h1>
        <hr>
        <p><%= request.getAttribute("msg_erro")%></p>
        <a href="javascript:window.history.go(-1)">Voltar</a>
    </body>
</html>
