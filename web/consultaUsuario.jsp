<%-- 
    Document   : consultaUsuario
    Created on : 05/05/2017, 09:04:20
    Author     : DAP
--%>

<%@page import="br.edu.ifrs.modelo.bean.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estiloGeral.css" rel="stylesheet" type="text/css">
        <link href="css/estiloForms.css" rel="stylesheet" type="text/css">
        <title>Gabinete</title>
    </head>
    <body>
        <%@include  file="html/VerificarSessao.jsp"%>
        <%Usuario usu = (Usuario) session.getAttribute("usuario");%>
        <div class="containner">
            
            <%@include file="html/header.jsp"%>

            <%@include file="html/menu.jsp"%>

            <section id="principal">
                <div class="title">
                    <h1>Pesquisa de Usuários</h1>
                    <hr>      
                </div>
                <!--Formulário para filtro-->
                <div id="center_form_busca">

                    <form action="UsuarioControl">
                        <input type="hidden" name="op" value="CONSULTA">

                        <label for="nome">Nome: </label>
                        <input type="text" id="nome" name="nome" class="input_texto"
                               size="20">

                        <div id="acoes2">
                            <input type="submit" value="Pesquisar" class="action">
                            <input type="reset" value="Limpar" class="action">
                        </div>

                    </form>    

                    <a href="index.jsp"><img src="img/home.png" title="ir para início"
                                             alt="ir para início" height="20"></a>
                    
                    <a href="UsuarioControl?op=NOVO"><img src="img/lapis.png" title="Novo usuário"
                                             alt="novo usuário" height="18"></a>
                    
                    <!--Formulário da tabela -->
                    <form action="UsuarioControl" method="POST">          
                        <table>
                            <tr>
                                <th>Opções</th>
                                <th>CPF</th>
                                <th>Nome</th>
                                <th>Sexo</th>
                                <th>Matrícula</th>
                                <th>Situação</th>
                            </tr>
                            <%
                                Usuario[] usus = (Usuario[]) request.getAttribute("usuarios");

                                for (int i = 0; i < usus.length; i++) {
                            %>
                            <tr>
                                <td><input type="radio" id="cpf" name="cpf" value="<%=usus[i].getCpf()%>" required></td>
                                <td><%= usus[i].getCpf()%></td>
                                <td><%= usus[i].getNome()%></td>
                                <td><%= usus[i].getSexo()%></td>
                                <td><%= usus[i].getMatricula()%></td>
                                <td><%= usus[i].getSituacao()%></td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                        <label for="op">Ações</label>
                        <select id="op" name="op" class="select">
                            <option value="CONSATUALIZAR">Alterar</option>
                            <option value="EXCLUIR">Excluir</option>
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