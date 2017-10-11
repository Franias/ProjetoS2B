<%--
    Document   : form_documentos
    Created on : 23/06/2017, 10:50:46
    Author     : Cassiano
--%>

<%@page import="br.edu.ifrs.modelo.bean.DocumentoSolicitado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/validacaoSolicitacao.js" type="text/javascript"></script>
        <link href="css/estiloGeral.css" rel="stylesheet" type="text/css">
        <link href="css/estiloForms.css" rel="stylesheet" type="text/css">
        <title>Emissão de documentos oficiais</title>
    </head>
    <body>
        <%@include  file="html/VerificarSessao.jsp"%>
        <%            boolean test = request.getAttribute("sol") != null ? true : false;
            //documento solicitado
            DocumentoSolicitado documentoSolicitado = (test) ? (DocumentoSolicitado) request.getAttribute("sol") : new DocumentoSolicitado();
            //pegar cpf do servidor solicitante
            Usuario usu = (Usuario) session.getAttribute("usuario");
            

            //se não estiver logado como gabinete volta para index
            if (!usu.getPerfil().getNome().equals("gabinete")) {

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
                    <h1>Formulário emissão de documentos oficiais</h1>
                    <hr>
                    <!--Campos não fazem parte do formulário-->
                    <span class="spans_form">Número da Solicitação do documento: <%=documentoSolicitado.getId()%></span>
                    
                    <span class="spans_form">Objetivo do documento: <%=documentoSolicitado.getObjetivoDocumento()%></span>
                    
                    <span class="spans_form">Servidor Solicitante: <%=documentoSolicitado.getServidorSolicitante().getNome()%></span>
                
                </div>
                <div id="center_form_cad">

                    <form action="DocumentoOficialControl" method="post">
                        <fieldset>

                            <!--Id da solicitação-->
                            <input type="hidden" name="numero" value="<%=documentoSolicitado.getId()%>">                        

                            <input type="hidden" name="op" value="emissaoDocumentoOficial">
                            <!--CPF do usuário logado-->
                            <input type="hidden" name="cpf" value="<%=usu.getCpf()%>">    
                            
                            <input type="hidden" name="ano" value="2017">                           

                            <!-- Serão copiados da solicitação -->
                            <label for="conteudo" class="l_cad">*Conteúdo</label>
                            <input type="text" id="conteudo" class="q_cad input_texto" 
                                   value="<%=documentoSolicitado.getConteudoDocumento()%>"
                                   name="conteudo" size="30" required>

                            <label for="anexos" class="l_cad">Anexos</label>
                            <input type="text" id="anexos" value="<%=documentoSolicitado.getAnexos()%>"
                                   class="q_cad input_texto"
                                   name="anexos" size="30">

                            <!--Tipo do documento-->
                            <label for="tipo" id="tipo" class="l_cad">*Tipo de documento:</label>
                            <select class="q_cad" id="tipo" name="tipo">
                                <option value="Portaria">Portaria</option>
                                <option value="Servico">Ordem de Serviço</option>
                                <option value="Resolucao">Resolução</option>
                                <option value="Oficio">Ofício</option>
                                <option value="Memorando">Memorando</option>
                            </select>

                            <label for="ano" class="l_cad">Ano: </label>                         
                            <input type="text" id="ano" value="2017" disabled
                                   maxlength="4" size="22" class="q_cad">

                            <div id="acoes">
                                <input type="submit" value="Enviar" class="action">
                                <input type="reset" value="Limpar" class="action">
                            </div>
                        </fieldset>
                    </form>

                    <p>Campos com asterisco são obrigatórios</p>

                    <a href="DocumentoSolicitacaoControl?op=buscar">Voltar</a>

                </div>
            </section>
        </div>
    </body>
</html>
