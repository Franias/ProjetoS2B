<%-- 
    Document   : consultarDocumento
    Created on : 23/06/2017, 10:43:02
    Author     : Cassiano
--%>

<!DOCTYPE html>
<html>
    <head>
        <link href="css/estiloGeral.css" rel="stylesheet" type="text/css">
        <link href="css/estiloForms.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisa de Solicitações de Documentos Oficiais</title>
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
                </div>
                <!--Formulário para filtro-->
                <div id="center_form_busca">
                    <!--Pesquisa de documentos-->
                    <!--Formulário para pesquisa dos documentos, puxado por arquivo externo-->
                    <%@include file="html/form_documentos.jsp"%>

                </div>
                <div class="title" >
                    <a href="index.jsp">
                        <img src="img/home.png" title="ir para início"
                             alt="ir para início" height="20"></a>
                    <hr>
                </div>
            </section>
        </div>
    </body>
</html>
