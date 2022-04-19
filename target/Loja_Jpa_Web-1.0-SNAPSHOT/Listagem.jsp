
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String listaHTML = request.getParameter("lista");
    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Listagem de Clientes</title>
        <link href="css/style-list.css" rel="stylesheet" />
    </head>
    <body>
        <div id="title">
            <a href="index.html">Tela Principal</a>
            <form action="ClienteSrv?acao=listagem-busca" method="POST">
                <input type="text" name="nomeBusca" id="nomeBusca" placeholder="Digite o nome do cliente..." />
                <input type="submit" value="Buscar" id="btnBusca" />
            </form>
        </div>
        <main id="container">
            <%=listaHTML%>
        </main>
    </body>
</html>
