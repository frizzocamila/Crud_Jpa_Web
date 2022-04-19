<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String acao = request.getParameter("acao");
        
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String idade = request.getParameter("idade");
        String credito = request.getParameter("credito");
        
        if (id == null) {
            nome = "";
            idade = "";
            credito = "";
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cadastro de Clientes</title>
        <link href="css/style-form.css" rel="stylesheet" />
    </head>
    <body>
        <h2>Cadastrar Cliente:</h2>
        <form action="ClienteSrv" method="POST">
            <input type="hidden" name="acao" value="<%=acao%>">
            
            <input type="hidden" name="id" value="<%=id%>">
            
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" value="<%=nome%>">

            <label for="idade">Idade:</label>
            <input type="number" id="idade" name="idade" min="0" value="<%=idade%>">

            <label for="credito">Crédito:</label>
            <input type="number" id="credito" name="credito" step="0.01" min="0" value="<%=credito%>">

            <div>
                <input id="adc" type="submit" value="Cadastrar">
                <input id="limp" type="reset" value="Limpar">
            </div>
        </form>
    </body>
</html>
