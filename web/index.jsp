<%-- 
    Document   : index
    Created on : 10/06/2017, 10:55:44
    Author     : gustavo
--%>

<%@page import="br.edu.ifrs.modelo.bean.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Bem vindo!</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/estiloGeral.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <%@include file="html/VerificarSessao.jsp"%>
        
        <%
            Usuario usu = (Usuario) session.getAttribute("usuario");
        %>
        <div class="containner">
            
            <%@include file="html/header.jsp"%>

            <%@include file="html/menu.jsp"%>

            <section id="principal">
                <div class="center_data">
                    <fieldset class="dados_user">
                        <legend>Meus dados</legend>
                        <strong>Informações</strong>
                        <p>Bem vindo Sr(a). <%= usu.getNome()%></p>
                        <br><br>
                        <hr>
                        <p>Selecione uma das opções acima no menu!</p>
                    </fieldset>
                </div>
                <br><br><br>
            </section>
        </div>
    </body>
</html>
