<%-- 
    Document   : login
    Created on : 09/06/2017, 17:48:41
    Author     : gustavo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estiloLogin.css" rel="stylesheet" type="text/css">
        <link href="css/estiloForms.css" rel="stylesheet" type="text/css">
        <title>Login</title>
    </head>
    <body>

        <section id="login">           

            <div id="center_form">
                
                <img src="img/logo.JPG" alt="gabinete virtual" title="gabinete virtual">

                <form method="POST" action="UsuarioControl">

                    <input type="hidden" name="op" value="AUTENTICAR">
                    <label for="usuario" class="l">Usuário:</label>

                    <input type="text" id="usuario" name="usuario" 
                           class="q input_texto" required>

                    <label for="senha" class="l">Senha:</label>
                    <input type="password" id="senha" class="q input_texto"
                           name="senha" required>

                    <div id="acoes">
                        <input type="submit" value="Login" class="action">
                        <input type="reset" value="Limpar" class="action">
                    </div>

                </form>


                <%
                    if (request.getAttribute("msg_erro") != null) {
                        String msg = (String) request.getAttribute("msg_erro");
                %>
                <p class="login_error"><%= msg%></p><!--Mostra mensagem de erro se usuário não existir -->
                <%
                    }
                %>
            </div>
            <p id="dev">Desenvolvimento: Alunos do Instituto Federal do Rio Grande do Sul </p>
        </section>
    </body>
</html>
