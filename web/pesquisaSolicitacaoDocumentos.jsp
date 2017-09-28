<%--
    Document   : consultaDocumentosOficiais
    Created on : 08/06/2017, 12:58:59
    Author     : gustavo
--%>

<%@page import="br.edu.ifrs.modelo.bean.DocumentoSolicitado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/estiloGeral.css" rel="stylesheet" type="text/css">
        <link href="css/estiloForms.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisa de Solicitações de Documentos Oficiais</title>
    </head>
    <body>
        <%@include  file="html/VerificarSessao.jsp"%>
        <%Usuario usu = (Usuario) session.getAttribute("usuario");

            //testando filtro de usuário
            boolean testUser = !usu.getPerfil().getNome().equals("servidor")
                    && !usu.getPerfil().getNome().equals("gabinete");

            if (testUser) {

                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);

            }

            %>
        <div class="containner">

            <%@include file="html/header.jsp"%>

            <%@include file="html/menu.jsp"%>

            <section id="principal">
                <div class="title">
                    <h1>Pesquisa de Solicitações de Documentos Oficiais</h1>
                    <hr>
                </div>
                <!--Formulário para filtro-->
                <div id="center_form_busca">
                    <!--Pesquisa de documentos-->
                    <%@include file="html/form_solicitacao_documentos.jsp"%>

                </div>
                <div class="title" >
                    <a href="index.jsp">
                        <img src="img/home.png" title="ir para início"
                             alt="ir para início" height="20"></a>
                    <hr>
                </div>
            </section>
        </div>
    </body>
</html>
