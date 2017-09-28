<%-- 
    Document   : header
    Created on : 29/06/2017, 19:04:35
    Author     : gustavo
--%>
<%@page import="br.edu.ifrs.modelo.bean.Usuario"%>
<header>
    <img src="img/logo_ifrs.png" alt="logo ifrs" height="75">
    <h1>Gabinete Virtual</h1>
    <ul class="list-acoes">
        <li>Usuário: <%= usu.getNome()%>
            <%="[" + usu.getMatricula() + "]"%></li>
        <%if (!usu.getNome().equals("Novo usuário")) {%>
        <li>Perfil: <%=usu.getPerfil().getNome()%></li>
        <li><a href="meus_dados.jsp"><img src="img/folder.png" alt="folder">Meus Dados</a></li>
        <li><a href="UsuarioControl?op=SAIR"><img src="img/close.png" alt="close">Sair</a></li>  
        <%}%>
    </ul>
</header>
