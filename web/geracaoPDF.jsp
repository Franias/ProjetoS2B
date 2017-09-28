<%-- 
    Document   : geracaoPDF
    Created on : 30/06/2017, 12:42:50
    Author     : gustavo e eduarda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/estiloGeral.css" rel="stylesheet" type="text/css">
        <link href="css/estiloForms.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Emissão foi cadastrada com sucesso</title>
    </head>   
    <body>
        <%@include  file="html/VerificarSessao.jsp"%>
        <%            boolean teste = request.getAttribute("link") != null;

            Usuario usu = (Usuario) session.getAttribute("usuario");

            if (!usu.getPerfil().getNome().equals("gabinete")) {

                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);

            }

            String link = (teste) ? (String) request.getAttribute("link") : "";

            %>
        <div class="containner">

            <%@include file="html/header.jsp"%>

            <%@include file="html/menu.jsp"%>

            <section id="principal">

                <div class="title">

                    <h1>Resultado da Emissão</h1>
                    <hr>      
                    <br>
                    <p>O documento foi emitido com sucesso.</p>
                    <br>
                    <%if (teste) {%>
                    <p>Um pdf com o documento foi gerado e disponibilizado nesse 
                        <a href="<%=link%>">link</a>
                    <p>
                        <%} else {
                            //erro no PDF
                            String msg = (request.getAttribute("msg_erro") != null
                                    ? (String) request.getAttribute("msg_erro") : "");

                        %>
                        <br>                    
                    <p><%=msg%></p>
                    <br>
                    <%

                        }%>    
                    <a href="index.jsp">
                        <img src="img/home.png" title="ir para início"
                             alt="ir para início" height="20">
                    </a> 
                    <br>
                    <br>
                    <hr>  
                    <br>

                </div>
            </section>
        </div>
    </body>
</html>