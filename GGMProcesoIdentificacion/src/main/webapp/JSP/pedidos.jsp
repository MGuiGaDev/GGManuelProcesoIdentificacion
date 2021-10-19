<%-- 
    Document   : pedidos
    Created on : 17-oct-2021, 20:01:02
    Author     : Manuel Guillén Gallardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../INCLUDES/metas.inc" %>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css'>
        <style><%@include file="../STYLE/login.css"%></style>
        <style><%@include file="../STYLE/estilo.css"%></style>
        <link href="https://fonts.googleapis.com/css2?family=Prompt:wght@200&family=Permanent+Marker&display=swap"
              rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="shortcut icon" href="imagenes/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
              integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <title>EJERCICIO LOGIN</title>
    </head>
    <body>
        <%@include file="../INCLUDES/cabecera.inc" %>
        <main>
            <div>
                <h2>Pedidos de <%= (request.getSession().getAttribute("nombreUsuario")==null?"":(request.getSession().getAttribute("nombreUsuario"))) %></h2>
                <div style="padding: 20px;">
                    <%= (request.getSession().getAttribute("pedidosUsuario")==null?"": request.getSession().getAttribute("pedidosUsuario")) %>
                </div>
            </div>
            
            <div class="opciones">
                <form action="login" method="post">
                    <button type="submit" class="btn-envio">CERRAR SESIÓN</button>
                </form>
                <form action="menu" method="get">
                    <input type="hidden" name="pedido" id="pedido" value="pedido" />
                    <button type="submit" class="btn-envio">VOLVER A MENÚ</button>
                </form>
            </div>
            
        </main>
        <%@include file="../INCLUDES/footer.inc" %>
    </body>
</html>
