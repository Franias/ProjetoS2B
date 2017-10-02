<%-- 
    Document   : menu
    Created on : 05/07/2017, 14:34:32
    Author     : gustavo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar">

    <ul class="listaMenu">
        <!--Menu Drop down-->
        <li class="lista_default"><a href="index.jsp">Home</a></li>

        <li class="lista_default"><a href="#">Usuários</a>
            <ul class="dropdown-content">

                <li >
                    <a class="menu_lista_curta" href="PerfilControl?op=LISTA">Listar Perfis</a>
                </li>

                <li ><a class="menu_lista_curta" href="UsuarioControl?op=LISTA">Listar Usuários</a></li>

            </ul>
        </li>

         <Li class="lista_default"><a href="#">Documento Oficiais</a>
            <ul class="dropdown-content">
                <!--Se for logado como servidor mostra opção abaixo com base no filtro -->
                <%if (usu.getPerfil().getNome().equals("servidor")) {%>
                <li ><a class="menu_lista_grande" href="formSolicitacao.jsp">Solicitar um documento oficial</a></li>
                <li><a class="menu_lista_grande" href="pesquisaSolicitacaoDocumentos.jsp">Pesquisa de solicitacao de documentos</a></li>
                    <%} else if (usu.getPerfil().getNome().equals("gabinete")) {%>
                <li><a class="menu_lista_grande" href="pesquisaSolicitacaoDocumentos.jsp">Pesquisa de solicitacao de documentos</a></li>
                    <%} else if (usu.getPerfil().getNome().equals("outros")) {%>
                <li><a class="menu_lista_grande" href="consultarDocumento.jsp">Pesquisa de documentos oficiais</a></li>
                    <%}%>
            </ul>
        </li>

        <li class="lista_default"><a href="#">Eventos</a></li>
        <li class="lista_default"><a href="#">Chamadas</a></li>

    </ul>
</nav>
            
            
            
