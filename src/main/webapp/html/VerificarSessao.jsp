<%-- 
    Document   : VerificarSessao
    Created on : 10/06/2017, 11:29:55
    Author     : gustavo
--%>

<%@page import="br.edu.ifrs.modelo.bean.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--Verificar Sessão -->
<%  //verifica sessão
    if (session.getAttribute("usuario") != null) {
        Usuario usu = (Usuario) session.getAttribute("usuario");
%>
<%//se o usuário não estiver logado retorna para a página de login
    } else {
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
%>