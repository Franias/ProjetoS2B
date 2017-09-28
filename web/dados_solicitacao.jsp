<%-- 
    Document   : meus_dados
    Created on : 13/06/2017, 16:01:52
    Author     : 062013
--%>

<%@page import="br.edu.ifrs.modelo.bean.DocumentoSolicitado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estiloGeral.css" rel="stylesheet" type="text/css">
        <title>Dados da solicitação</title>
    </head>
    <body>
        <%@include  file="html/VerificarSessao.jsp"%>
        <%Usuario usu = (Usuario) session.getAttribute("usuario");%>
        <div class="containner">
            
            <%@include file="html/header.jsp"%>

            <%@include file="html/menu.jsp"%>

            <% boolean teste = request.getAttribute("sol") != null;

                DocumentoSolicitado documentoSolicitado
                        = (teste) ? (DocumentoSolicitado) request.getAttribute("sol") : new DocumentoSolicitado();%>

            <section id="principal">
                <div class="title">
                    <h1>Dados da solicitação</h1>
                    <hr>
                    <br>
                    <fieldset class="dados_user_page">
                        <legend>Solicitação</legend>
                        <span>Objetivo:</span><span><%= documentoSolicitado.getObjetivoDocumento()%></span>
                        <br><br>
                        <span>Data da solicitação:</span><span><%= documentoSolicitado.getDataSolicitacao()%></span> 
                        <br><br>
                        <span>Data do documento:</span><span><%= documentoSolicitado.getDataDocumento()%></span> 
                        <br><br>
                        <span>Conteudo:</span><span><%= documentoSolicitado.getConteudoDocumento()%></span> 
                        <br><br>
                        <span>Situação:</span><span><%= documentoSolicitado.getSituacao()%></span> 
                        <br><br>
                        <span>Anexos:</span><span><%= documentoSolicitado.getAnexos()%></span> 
                        <br><br>
                        <span>Nome do Servidor Solicitante:</span><span><%= documentoSolicitado.getServidorSolicitante().getNome()%></span> 
                        <br><br>
                    </fieldset>
                    <br>
                    <a class="action_link"
                       href="javascript:window.history.go(-1)">Voltar</a>                 
                </div>
            </section>
        </div>
    </body>
</html>
