<%-- 
    Document   : consultaPerfil
    Created on : 01/06/2016, 08:34:25
    Author     : DTI
--%>

<%@page import="br.edu.ifrs.modelo.bean.Perfil"%>
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
                    <h1>Lista de Perfis</h1>
                    <hr>
                </div>

                <!--Formulário para filtro-->
                <div id="center_form_busca">

                    <form action="PerfilControl">
                        <input type="hidden" name="op" value="CONSULTA">

                        <label for="nome">Nome: </label>
                        <input type="text" id="nome" class="input_texto"
                               name="nome" size="20">

                        <div id="acoes2">
                            <input type="submit" value="Pesquisar" class="action">
                            <input type="reset" value="Limpar" class="action">
                        </div>

                    </form>

                    <a href="index.jsp"><img src="img/home.png" title="ir para início"
                                             alt="ir para início" height="20"></a>

                    <a href="#"><img src="img/addUser.png" title="Novo perfil"
                                     alt="novo perfil" height="18"></a>
                    <!--Formulário da tabela -->
                    <form action="PerfilControl" method="POST"> 
                        <table border="1" cellpadding="1" cellspacing="0">
                            <tr>
                                <th>ID</th>
                                <th>Número</th>
                                <th>Situação</th>
                                <th>Ações</th>
                            </tr>
                            <%                Perfil[] per = (Perfil[]) request.getAttribute("perfis");

                                for (int i = 0; i < per.length; i++) {
                            %>
                            <tr>
                                <td><input type="radio" id="id" name="id" value="<%=per[i].getId()%>" required></td>
                                <td><%= per[i].getId()%></td>
                                <td><%= per[i].getNome()%></td>
                               <td><%= per[i].getSituacao()%></td>
                            </tr>
                            <%
                                }
                            %>
                        </table>

                        <label for="id">Ações</label>
                        <select id="op" name="op" class="select">
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
