<%-- 
    Document   : inicio.jsp
    Created on : 17-oct-2021, 9:13:03
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
            <%= (request.getAttribute("mensaje")) != null ? request.getAttribute("mensaje") : ""%>
            <form action="menu" method="post" class="formulario">
                <div class="f-head">
                    <h2>LOG IN</h2>
                </div>
                <label for="nombreUsuario">Nombre de usuario:</label>
                <div>
                    <input type="text" id="nombreUsuario" name="nombreUsuario" value="${nombreUsuario}" class="input-field"/>
                </div>
                <br>
                <label for="pass">Contraseña:</label>
                <div class="paraPass">
                    <input type="password" id="pass" name="pass" class="input-field "/>
                    <i id="icono" class="fa fa-eye-slash"></i>
                </div>


                <div class="f-footer">
                    <input type="checkbox" id="recordarme" name="recordarme" />
                    <label for="recordarme">Recordarme</label><br>
                </div>
                <div class="cont-b">
                    <button type="submit" class="btn-envio">ENVIAR</button>
                </div>
            </form>
        </main>
        <%@include file="../INCLUDES/footer.inc" %>
        <script>
            let pass = document.getElementById('pass');
            let mostrar = document.getElementById('icono');

            mostrar.onclick = function () {
                if (mostrar.className === "fa fa-eye") {
                    pass.setAttribute('type', 'password');
                    mostrar.className = 'fa fa-eye-slash';
                } else {
                    pass.setAttribute('type', 'text');
                    mostrar.className = 'fa fa-eye';
                }
            }
        </script>
    </body>
</html>
