<%-- 
    Document   : meus_dados
    Created on : 13/06/2017, 16:01:52
    Author     : 062013
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estiloGeral.css" rel="stylesheet" type="text/css">
        <title>Meus Dados</title>
    </head>
    <body>
        <%@include  file="html/VerificarSessao.jsp"%>
        <!--Pega usuário logado na sessão autal -->
        <%Usuario usu = (Usuario) session.getAttribute("usuario");%>
        <div class="containner">
            
            <%@include file="html/header.jsp"%>

            <%@include file="html/menu.jsp"%>
            <!--Mostrando os dados do usuário -->
            <section id="principal">
                <div class="title">
                    <h1>Meus Dados</h1>
                    <hr>
                    <a href="index.jsp"><img src="img/home.png" title="ir para início"
                                             alt="ir para início" height="20"></a>
                    <br>
                    <fieldset class="dados_user_page">
                        <legend>Usuário</legend>
                        <span>Número de matrícula:</span><span><%= usu.getMatricula()%></span>
                        <br><br>
                        <span>Nome:</span><span><%= usu.getNome()%></span> 
                        <br><br>
                        <span>Sexo:</span><span><%= usu.getSexo()%></span> 
                    </fieldset>
                    <br>
                    <fieldset class="dados_user_page">
                        <legend>Contato</legend>
                        <span>Endereço:</span><span><%= usu.getEndereco()%></span>
                        <br><br>
                        <span>Email:</span><span><%= usu.getEmail()%></span> 
                        <br><br>
                        <span>Telefone Celular:</span><span><%= usu.getTelefoneCelular()%></span> 
                        <br><br>
                        <span>Telefone Residencial:</span><span><%= usu.getTelefoneResidencial()%></span> 
                        <br><br>
                        <span>Telefone Proficional:</span><span><%= usu.getTelefoneProfissional()%></span> 
                    </fieldset>
                    <br>
                    <fieldset class="dados_user_page">
                        <legend>Outros Dados</legend>
                        <span>Situação:</span><span><%= usu.getSituacao()%></span>
                        <br><br>
                        <span>Nome de usuário:</span><span><%= usu.getUsername()%></span> 
                        <br><br>
                        <span>Observações:</span><span><%= usu.getObservacoes()%></span> 
                    </fieldset>
                    <br>
                    <a class="action_link"
                       href="UsuarioControl?op=CONSATUALIZAR&cpf=<%= usu.getCpf()%>">Atualizar</a>&nbsp;                  
                </div>
            </section>
        </div>
    </body>
</html>
