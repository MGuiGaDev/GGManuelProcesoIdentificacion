![cabecera](imagenes/cabecera.PNG)


<hr style="height: 5px; background: #24292F;">

# Contenidos

1. [Introducción](#introduccion)
2. [Herramientas](#herramientas)
3. [Diagrama de flujo](#diagrama)
4. [Documentos](#documentos)
5. [Interconexiones]( #interconexiones)
6. [Estado de los archivos](#estado)

<hr style="height: 5px; background: #24292F;"><a name="introduccion"></a>

## NOMBRE DE USUARIO: Joaquina Chamorro
## CONTRASEÑA: 1111

# 1. Introducción

Programa que gestiona el proceso de identificación de un usuario conforme a los datos: ``nombre de usuario`` y ``contraseña``. Para llegar a tener una idea del alcance de un proceso de login, contamos con capas más profundas que solo serán accesibles si el usuario se ha logueado con éxito.

Un concepto relacionado: DASHBOARD

> En su forma más simple, un *dashboard* es una pantalla en su aplicación que muestra información. Normalmente, un tablero proporciona al usuario una descripción general global, con acceso a los datos, funciones y controles más importantes. En realidad, un panel de control a menudo se convierte en una especie de página de inicio, especialmente para usuarios avanzados.
>
> Tomado de [www.justinmind.com](https://www.justinmind.com/blog/dashboard-design-best-practices-ux-ui/#:~:text=At%20its%20simplest%2C%20a%20dashboard,homepage%2C%20especially%20for%20power%20users.)


<hr style="height: 5px; background: #24292F;"><a name="herramientas"></a>

# 2. Herramientas

![(imagenesReadme/java.svg)](https://img.shields.io/badge/code-Java-informational?style=flat&logo=java&logoColor=white&color=2bbc8a)  ![(imagenesReadme/javascript.svg)](https://img.shields.io/badge/code-JavaScript-informational?style=flat&logo=javascript&logoColor=white&color=2bbc8a) ![(imagenesReadme/css3.svg)](https://img.shields.io/badge/code-CSS3-informational?style=flat&logo=css3&logoColor=white&color=2bbc8a)  ![(imagenesReadme/html5.svg)](https://img.shields.io/badge/code-HTML5-informational?style=flat&logo=html5&logoColor=white&color=2bbc8a)  ![(imagenesReadme/apachenetbeanside.svg)](https://img.shields.io/badge/ide-NetBeans-informational?style=flat&logo=apachenetbeanside&logoColor=white&color=2bbc8a) ![(imagenesReadme/apachetomcat.svg)](https://img.shields.io/badge/server-TomCat-informational?style=flat&logo=apachetomcat&logoColor=white&color=2bbc8a)

<hr style="height: 5px; background: #24292F;"><a name="diagrama"></a>



# 3. Diagrama de flujo



![diagrama](imagenes/diagrama.png)






<hr style="height: 5px; background: #24292F;"><a name="documentos"></a>

# 4. Documentos

## Vistas

- ``inicio.jsp`` - contiene formulario para realizar login y al mismo tiempo sirve de vista para comunicar si ha habido algún error.
- ``menu.jsp`` - vista de dashboard de usuario una vez logueado.
- ``pedidos.jsp`` - vista de una de las posibilidades dentro del menú de dashboard.

### Controladores

- ``Controlador.java (name = "Controlador" urlPatterns="/login")`` - En nuestro caso, es responsable de tres acciones: 
  - Controlar el direccionamiento directo a la vista ``login.jsp``.
  - Obtener los datos del usuario y mostrarlos en ``login.jsp`` , en caso de que este seleccione la opción ser recordado.
  - Hacer efectiva la opción ``cerrar sesión`` disponible en las vistas ``menu.jsp`` y ``pedidos.jsp``.
- ``Menu.java (name = "Menu" urlPatterns="/menu")`` - En nuestro caso, es responsable de tres acciones:
  - Controlar el acceso a la vista ``menu.jsp``. En caso de existir errores en el proceso de login, no permitirá dicho acceso.
  - Producir mensajes informativos en la vista ``login.jsp`` en caso de haber algún error al introducir los campos.
  - Controlar el flujo de datos en la aplicación entre la vista ``login.jsp`` y la vista ``menu.jsp``. Generando aquellos datos que sean requeridos para las diferentes vistas.
- ``Pedidos.java (name = "Pedidos" urlPatterns="/pedidos")`` - En nuestro caso, es responsable de dos acciones:
  - Comunicar la vista ``menu.jsp`` con la vista ``pedidos.jsp``.
  - Producir los datos de usuario y de pedidos que van a mostrarse en la vista ``pedidos.jsp``.


<hr style="height: 5px; background: #24292F;"><a name="interconexiones"></a>

# 5. Interconexiones

El control y gestión sobre el recorrido del flujo de datos presenta las siguientes características:

- ``inicio.jsp`` - contiene el formulario de inicio de sesión de usuario. Este será enviado al Servlet ``Menu.java``.

*Formulario*

```html
<form action="menu" method="post">
```

- ``Menu.java`` - Contiene y genera los datos que serán utilizados por el resto de controladores y vistas, dependiendo del éxito del inicio de sesión por parte del usuario.

*Parámetros globales*

```java
/**
* Parámetro con el valor de nombre de usuario correcto.
*/
private final String nombreUsuario = "Joaquina Chamorro";
/**
* Parámetro con el valor de contraseña de usuario correcta.
*/
private final String password = "1111";
/**
* Parámetro que almacenará el momento en el que esta se produce cada inicio de sesión.
*/
private String ultimoAcceso = null;
/**
* Cookie que almacenará con cada inicio de sesión el momento en el que esta se produce, mediante el parámetro "ultimoAcceso".
*/
private Cookie cookieAcceso = new Cookie("ultimoAcceso", "");
```

*Parámetros locales*

```java
//DENTRO DE DOPOST()
/**
* Atributo que contiene el mensaje que se mostrará en las vistas, dependiendo del éxito del inicio de sesión.
*/
StringBuilder mensaje = new StringBuilder();
/**
* Atributo que contiene la URL a la que será redireccionado el usuario. Esta cambiará a "JSP/login.jsp" en caso de no iniciar sesión correctamente.
*/
String url = "JSP/menu.jsp";
```

*Atributos de petición*

```java
//DENTRO DE DOPOST()
/**
* Atributo que contiene la fecha de último acceso. Tomará su valor del parámetro "ultimoAcceso".
* Requerido en "menu.jsp"
*/
request.setAttribute("u", ultimoAcceso);
/**
* Atributo que contiene el mensaje que se mostrará en las vistas, dependiendo del éxito del inicio de sesión.
* Requerido en: "login.jsp" y "menu.jsp"
*/
request.setAttribute("mensaje", mensaje.toString());
/**
* Parámetro que direcciona el flujo de la aplicación.
*/
request.getRequestDispatcher(url).forward(request, response);
```

*Atributos de sesión*

```java
/**
* Atributo que almacena el nombre de usuario.
* Requerido en "pedidos.jsp"
*/
request.getSession().setAttribute("nombreUsuario", nombreUsuario);
```

*Validaciones*

```java
// CAMPOS VACÍOS Y MENSAJE DE ERROR
if (request.getParameter("nombreUsuario").length() == 0 || request.getParameter("pass").length() == 0) ) {
    //Mensaje de error y cambio en URL
}..
// NOMBRE DE USUARIO INCORRECTO
else if (!request.getParameter("nombreUsuario").equals(nombreUsuario)) {
    //Mensaje de error y cambio en URL
}...
// CONTRASEÑA INCORRECTA
else if (!request.getParameter("pass").equals(password)) {
	//Mensaje de error y cambio en URL
}...           
```

- ``Controlador.java`` - Recoge el nombre de usuario y lo imprime en el formulario (en caso de estar seleccionado el ``checkbox`` "recordarme") y cierra sesión.

*Nombre de usuario*

```java
//MÉTODO DOPOST()
Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie co : cookies) {
                if (co.getName().equals("datosUsuario")) {
                    request.setAttribute("nombreUsuario", URLDecoder.decode(co.getValue(), "utf8"));
                    co.setValue("");
                    co.setMaxAge(0);
                    response.addCookie(co);
                }
            }
        }
```

*Cierre de sesión*

```java
//MÉTODO DOPOST()
request.getSession().invalidate();
```

- ``Pedidos.java`` - De este archivo destacaremos la creación de atributos de sesión para ser transferidos a la vista ``pedidos.jsp``.

*Creación de atributo de sesión*

```java
//MÉTODO DOPOST()
String pedidosU = "<ul><li>Edredón metálico [1]</li><li>Felpudo de cristal [1]</li><li>Cenicero de papel [2]</li>";
request.getSession().setAttribute("pedidosUsuario", pedidosU);
```

<hr style="height: 5px; background: #24292F;"><a name="estado"></a>

