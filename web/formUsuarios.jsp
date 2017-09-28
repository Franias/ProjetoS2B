<%-- 
    Document   : formUsuarios
    Created on : 11/05/2016, 08:27:48
    Author     : DTI
--%>

<%@page import="br.edu.ifrs.modelo.bean.Setor"%>
<%@page import="br.edu.ifrs.modelo.bean.Perfil"%>
<%@page import="br.edu.ifrs.modelo.bean.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gabinete Virtual</title>
        <link href="css/estiloForms.css" rel="stylesheet" type="text/css">
        <link href="css/estiloGeral.css" rel="stylesheet" type="text/css">
        <script src="js/validacoes.js" type="text/javascript"></script>
    </head>
    <body>
        <%
            Usuario usu = new Usuario();
            boolean test = request.getSession().getAttribute("usuario") != null;
            if (test) {
                usu = (Usuario) request.getSession().getAttribute("usuario");
            }else{
                usu = new Usuario();
            }
        %>
        <div class="containner">
            
            <%@include file="html/header.jsp"%>

            <%@include file="html/menu.jsp"%>
            
            <section id="principal">
                <div class="title">
                    <h1>Cadastro de usuários<%=(test)
                                ? " - Alteração de Dados" : " - Cadastro"%></h1>
                    <hr>
                </div>
                <div id="center_form_cad">
                    <form action="UsuarioControl" method="post" onsubmit="return validarUsuario();">
                        <fieldset>
                            <input type="hidden" name="op" id="op" value="<%= (usu.getCpf().equals("") ? "INSERIR" : "ATUALIZAR")%>">

                            <label for="cpf" class="l_cad">CPF: </label>
                            <input type="text" name="cpf" id="cpf" class="q_cad input_texto"
                                   size="20" value="<%= usu.getCpf()%>" <%= (usu.getCpf().equals("") ? "" : "readonly=\"true\"")%> required>

                            <label for="matricula" class="l_cad">Matrícula: </label>
                            <input type="text" name="matricula" class="q_cad input_texto"
                                   id="matricula" value="<%= usu.getMatricula()%>" size="10">

                            <label for="nome" class="l_cad">* Nome: </label>
                            <input type="text" name="nome" class="q_cad input_texto"
                                   id="nome" size="50" value="<%= usu.getNome()%>" required>

                            <label for="sexo" class="l_cad">* Sexo: </label>
                            <div class="q_cad">
                                <input type="radio" name="sexo" id="feminino" 
                                       value="F" <%= (usu.getSexo().equals("F") ? "checked" : "")%> required><label for="feminino">Feminino</label>
                                <input type="radio" name="sexo" id="masculino" 
                                       value="M" <%= (usu.getSexo().equals("M") ? "checked" : "")%>><label for="masculino">Masculino</label>
                            </div>
                            <label for="endereco" class="l_cad">Endereço: </label>
                            <textarea name="endereco" class="q_cad input_texto"
                                      id="endereco" rows="5" cols="50"><%= usu.getEndereco()%></textarea>

                            <label for="email" class="l_cad">Email: </label>
                            <input type="text" name="email" class="q_cad input_texto"
                                   id="email" value="<%= usu.getEmail()%>" size="30">

                            <label for="telefone" class="l_cad">telefone: </label>
                            <input type="text" name="telefone" class="q_cad input_texto"
                                   id="telefone"  value="<%= usu.getTelefoneResidencial()%>" size="20"><br>

                            <label for="perfil" class="l_cad">* Perfil: </label>
                            <select name="perfil" id="perfil" class="q_cad select">
                                <%
                                    Perfil[] perfis = (request.getAttribute("perfis") == null ? new Perfil[0] : (Perfil[]) request.getAttribute("perfis"));
                                    for (int i = 0; i < perfis.length; i++) {
                                %>
                                <option value="<%= perfis[i].getId()%>" <%= (perfis[i].getId() == usu.getPerfil().getId() ? "selected" : "")%> ><%= perfis[i].getNome()%></option>
                                <%
                                    }
                                %>
                            </select>

                            <label for="setor" class="l_cad">* Setor: </label>
                            <select name="setor" id="setor" class="q_cad select">
                                <%
                                    Setor[] setores = (request.getAttribute("setores") == null ? new Setor[0] : (Setor[]) request.getAttribute("setores"));
                                    for (int i = 0; i < setores.length; i++) {
                                %>
                                <option value="<%= setores[i].getId()%>" <%= (setores[i].getId() == usu.getSetor().getId() ? "selected" : "")%> ><%= setores[i].getNome()%></option>
                                <%
                                    }
                                %>
                            </select>

                            <label for="username" class="l_cad">* Username: </label>
                            <input type="text" name="username" class="q_cad input_texto"
                                   id="username" value="<%= usu.getUsername()%>" size="30">

                            <%
                                if (usu.getCpf().equals("")) {
                            %>
                            <label for="senha" class="l_cad">* Senha: </label>
                            <input type="password" name="senha" class="q_cad input_texto"
                                   id="senha" size="20">

                            <label for="confsenha" class="l_cad">Confimar Senha: </label>
                            <input type="password" class="q_cad"
                                   name="confsenha" id="confsenha" size="20">
                            <%
                                }
                            %>

                            <label for="situacao" class="l_cad">* Situação: </label>
                            <div class="q_cad">
                                <input type="radio" name="situacao" id="sitativo" value="A" <%= (usu.getSituacao().equals("A") ? "checked" : "")%>><label for="sitativo">Ativo</label>
                                <input type="radio" name="situacao" id="sitinativo" value="I" <%= (usu.getSituacao().equals("I") ? "checked" : "")%>><label for="sitinativo">Inativo</label>
                            </div>
                            <div id="acoes">
                                <input type="submit" value="Enviar" class="action">
                                <input type="reset" value="Limpar" class="action">
                            </div>
                        </fieldset>
                    </form>
                    <p>Campos com asterisco são obrigatórios</p>
                    <a href="UsuarioControl?op=LISTA">Voltar</a>
                </div>
            </section>
        </div>
    </body>
</html>
