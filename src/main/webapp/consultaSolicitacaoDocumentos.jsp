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
        <title>Consulta de Solicitações de Documentos Oficiais</title>
    </head>
    <body>
        <!--Usuário da sessão autal-->
        <%@include  file="html/VerificarSessao.jsp"%>
        <%Usuario usu = (Usuario) session.getAttribute("usuario");

            //testando filtro de usuário
            boolean testGabinete = usu.getPerfil().getNome().equals("gabinete");

            if (!testGabinete && !usu.getPerfil().getNome().equals("servidor")) {

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

                    <h1>Consulta de Solicitações de Documentos Oficiais</h1>
                    <hr>      
                    <br>
                    <a href="pesquisaSolicitacaoDocumentos.jsp">Voltar</a>   

                    <!--Formulário da tabela -->

                    <form action="DocumentoSolicitacaoControl" method="POST">
                        <div id="consultaDocumentos">
                            <table>
                                <tr>
                                    <th>Opções</th>
                                    <th>Número</th>
                                    <th>Nome do Solicitante</th>                              
                                    <th>Data da Solicitação</th>
                                    <th>Situação</th>
                                    <th>Objetivo</th>
                                    <th>Conteudo</th>
                                    <th colspan="2">Ações</th>
                                </tr>

                                <% //Busca da solicitação de documentos oficiais
                                    DocumentoSolicitado[] documentos
                                            = (request.getAttribute("documentos") != null)
                                            ? (DocumentoSolicitado[]) request.getAttribute("documentos")
                                            : new DocumentoSolicitado[0];
                                    //se não tiver solicitações
                                    if (documentos.length == 0) {//se não existir nada

                                %>
                                <tr>
                                    <td colspan="9">Não foram encontrados dados para a consulta.</td>
                                </tr>

                                <%                            } else {
                                %>


                                <%//imprime as solicitações
                                    for (int i = 0; i < documentos.length; i++) {

                                        boolean situ = documentos[i].getSituacao().equals("Concluida");

                                %>

                                <tr>
                                    <td><input type="radio" id="id" name="id" 
                                               value="<%=documentos[i].getId()%>" required></td>

                                    <td><%=documentos[i].getId()%></td>
                                    <td><%=documentos[i].getServidorSolicitante().getNome()%></td>     
                                    <td><%=documentos[i].getDataSolicitacao()%></td>
                                    <td><%=documentos[i].getSituacao()%></td>
                                    <td><%=documentos[i].getObjetivoDocumento()%></td>
                                    <td><%=documentos[i].getConteudoDocumento()%></td>
                                    <!--Testanto se já foi solicitado-->
                                    <td <%=(!situ && testGabinete) ? "cospan='2'" : ""%>>
                                        <a href="DocumentoSolicitacaoControl?id=<%=documentos[i].getId()%>&AMP;op=buscarSolicitacaoDocumento">
                                            <img src="img/detalhes.png" title="Visualizar detalhes"
                                                 alt="Ver detalhes" height="20"></a>
                                    </td> 
                                    <!--Link 2 para o cadastro de documentos oficiais-->
                                    <%if (!situ && testGabinete) {%>
                                    <td>
                                        <!--Buscar objeto da solicitação do banco de dados-->                                      
                                        <a href="DocumentoSolicitacaoControl?id=<%=documentos[i].getId()%>&AMP;op=emitirDocumento">

                                            <img src="img/documento.png" title="Emitir documento"
                                                 alt="emitir documento" height="20">
                                        </a>
                                    </td>   
                                    <%}%>

                                </tr>
                                <%}//fecha for
                                    }//fecha else
                                %>
                            </table>
                        </div>
                        <label for="op">Ações</label>
                        <!--Caixa de seleção-->
                        <select id="op" name="op" class="select">
                            <option value="consultarSolicitacaoDocumento">Alterar</option>
                        </select>
                        <div id="acoes2">
                            <input type="submit" value="Submeter" class="action">
                            <input type="reset" value="Cancelar" class="action">
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </body>
</html>
