<%--
    Document   : notificacao
    Created on : 22/06/2017, 12:11:46
    Author     : gustavo e eduarda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/estiloGeral.css" rel="stylesheet" type="text/css">
        <link href="css/estiloForms.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notificação</title>
    </head>
    <body>
        <%@include  file="html/VerificarSessao.jsp"%>
        <%Usuario usu = (Usuario) session.getAttribute("usuario");
            //se não estiver logado como servidor volta para index
            if (!usu.getPerfil().getNome().equals("servidor")) {

                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);

            }

            %>
        <div class="containner">

            <%@include file="html/header.jsp"%>

            <%@include file="html/menu.jsp"%>

            <section id="principal">
                <!--Tela de notificação de e-mail-->
                <%
                    boolean teste = (request.getAttribute("erro_email") != null);
                    String msg = (teste) ? (String) request.getAttribute("erro_email") : "";%>

                <div class="title">

                    <h1>Notificação de gabinete</h1>
                    <hr>      
                    <br>
                    <p>Seus dados foram gravados com sucesso.</p>
                    <br>

                    <%if (!teste) {%>
                    <p>Sua solicitação foi enviada para o email do Gabinete.</p>
                    <%} else {%>
                    <p><%=msg%></p>
                    <br>
                    <%}%>
                    <a href="index.jsp"><img src="img/home.png" title="ir para início"
                                             alt="ir para início" height="20"></a> 
                    <br>
                    <br>
                    <hr>  
                    <br>
                </div>

            </section>
        </div>
    </body>
</html>
