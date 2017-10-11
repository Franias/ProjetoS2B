<%-- 
    Document   : formSolicitacao
    Created on : 08/06/2017, 13:12:22
    Author     : gustavo
--%>

<%@page import="br.edu.ifrs.modelo.bean.DocumentoSolicitado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/validacaoDocumento.js" type="text/javascript"></script>
        <link href="css/estiloGeral.css" rel="stylesheet" type="text/css">
        <link href="css/estiloForms.css" rel="stylesheet" type="text/css">
        <title>Solicitação de documentos oficiais</title>
    </head>
    <body>
        <%@include  file="html/VerificarSessao.jsp"%>
        <%            //variável para teste
            boolean test = request.getAttribute("sol") != null ? true : false;
            //documento solicitado necessário
            DocumentoSolicitado documentoSolicitado = (test) ? (DocumentoSolicitado) request.getAttribute("sol") : new DocumentoSolicitado();
            //pegar cpf do servidor solicitante
            Usuario usu = (Usuario) session.getAttribute("usuario");
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
                <div class="title">
                    <h1>Formulário de solicitação de documentos oficiais <%=(test)
                                ? "- Alteração de Dados" : "- Cadastro"%></h1>
                    <hr>
                </div>
                <div id="center_form_cad">
                    <!-- onsubmit="return validarSolicitacao();" -->     

                    <form action="DocumentoSolicitacaoControl" method="post" onsubmit="return validarFormularioSolicitacao()">
                        <fieldset>

                            <input type="hidden" name="op" value="<%=(test)
                                    ? "alterarSolicitacaoDocumento" : "solicitaEmissaoDocumento"%>">

                            <input type="hidden" name="cpf" value="<%=usu.getCpf()%>">

                            <!--Email do usuário para notificação-->
                            <input type="hidden" name="email" value="<%=usu.getEmail()%>">

                            <!--Para alterar solicitação -->                  
                            <input type="hidden" name="id" value="<%=documentoSolicitado.getId()%>">


                            <label for="objetivo" class="l_cad">* Objetivo do Documento:</label>

                            <input type="text" id="objetivo" class="q_cad input_texto" size="45"
                                   name="objetivo" value="<%=documentoSolicitado.getObjetivoDocumento()%>" required>


                            <label for="dataDocumento" class="l_cad">* Data do Documento: </label>

                            <div class="q_cad">
                                <select name="ano" id="ano">
                                    <option value="2017">2017</option>                 
                                </select>

                                <select name="mes" id="mes">
                                    <option value="01">janeiro</option>
                                    <option value="02">fevereiro</option>
                                    <option value="03">março</option>
                                    <option value="04">abril</option>
                                    <option value="05">maio</option>
                                    <option value="06">junho</option>
                                    <option value="07">julho</option>
                                    <option value="08">agosto</option>
                                    <option value="09">setembro</option>
                                    <option value="10">outubro</option>
                                    <option value="11">novembro</option>
                                    <option value="12">dezembro</option>
                                </select>

                                <select name="dia" id="dia">
                                    <%for (int i = 1; i <= 31; i++) {%>
                                    <option value="<%=i%>"><%=i%></option> 
                                    <%}%>
                                </select>
                            </div>

                            <label for="conteudoDocumento" class="l_cad">* Conteúdo do Documento: </label>
                            <input type="text" id="conteudoDocumento" size="45"
                                   name="conteudoDocumento"  class="q_cad input_texto"
                                   value="<%=documentoSolicitado.getConteudoDocumento()%>" required>

                            <label for="anexos" class="l_cad">Anexos: </label>
                            <input type="text" id="anexos" class="q_cad input_texto" size="45"
                                   name="anexos" value="<%=documentoSolicitado.getAnexos()%>">

                            <div id="acoes">
                                <input type="submit" value="Enviar" class="action">
                                <input type="reset" value="Limpar" class="action">
                            </div>
                        </fieldset>

                    </form>

                    <p>Campos com asterisco são obrigatórios</p>

                    <a href="index.jsp">Voltar</a>
                </div>
            </section>
        </div>
    </body>
</html>
