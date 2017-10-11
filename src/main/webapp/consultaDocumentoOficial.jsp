<%-- 
    Document   : consultaDocumentoOficial
    Created on : 28/06/2017, 09:14:41
    Author     : Cassiano
--%>

<%@page import="br.edu.ifrs.modelo.bean.DocumentoOficial"%>
<%@page import="br.edu.ifrs.modelo.bean.DocumentoSolicitado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/estiloGeral.css" rel="stylesheet" type="text/css">
        <link href="css/estiloForms.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisa de Documentos Oficiais</title>
    </head>
    <body>
        <%@include  file="html/VerificarSessao.jsp"%>
        <%Usuario usu = (Usuario) session.getAttribute("usuario");

            if (!usu.getPerfil().getNome().equals("outros")) {

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
                    <h1>Pesquisa de Documentos Oficiais</h1>
                    <hr>      
                    <br>
                    <a href="consultarDocumento.jsp">Voltar</a> 
                </div>
                <!--Formulário para filtro-->
                <div id="center_form_busca">

                    <a href="index.jsp"><img src="img/home.png" title="ir para início"
                                             alt="ir para início" height="20"></a>                   

                    <table>
                        <tr>
                            <th>Número da Solicitação</th>
                            <th>Ano</th>                              
                            <th>Data da Emissão</th>
                            <th>Tipo do Documento</th>
                            <th>Detalhes</th>
                            <th>Link</th>
                        </tr>

                        <% //Busca da solicitação de documentos oficiais
                            DocumentoOficial[] documentos
                                    = (request.getAttribute("documentos") != null)
                                    ? (DocumentoOficial[]) request.getAttribute("documentos")
                                    : new DocumentoOficial[0];

                            //se não tiver solicitações
                            if (documentos.length == 0) {//se não existir nada

                        %>
                        <tr>
                            <td colspan="6">Não foram encontrados dados para a consulta.</td>
                        </tr>
                        <%                        } else {

                            for (int i = 0; i < documentos.length; i++) {
                        %>

                        <tr>
                            <td><%=documentos[i].getNumero()%></td> 
                            <td><%=documentos[i].getAno()%></td>  
                            <td><%=documentos[i].getDataEmissao()%></td>                               
                            <td><%=documentos[i].getTipo()%></td>

                            <td>
                                <!--Buscar objeto da solicitação do banco de dados-->                                      
                                <a href="DocumentoSolicitacaoControl?id=<%=documentos[i].getNumero()%>&AMP;op=buscarSolicitacaoDocumento">
                                    <img src="img/detalhes.png" title="Visualizar detalhes da solicitação"
                                         alt="Ver detalhes" height="20"></a>
                            </td> 

                            <td>
                                <a href="pdf/documento<%=documentos[i].getNumero()%>.pdf">Download</a>
                            </td> 

                        </tr>
                        <%      }
                            }
                        %>
                    </table>

                </div>
            </section>
        </div>
    </body>
</html>
