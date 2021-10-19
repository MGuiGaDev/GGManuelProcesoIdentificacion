/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Manuel Guillén Gallardo
 */
@WebServlet(name = "Menu", urlPatterns = {"/menu"})
public class Menu extends HttpServlet {

    private final String nombreUsuario = "Joaquina Chamorro";
    private final String password = "1111";
    private Cookie cookieAcceso = new Cookie("ultimoAcceso", "");
    private String ultimoAcceso = null;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("pedido") != null) {
            request.setAttribute("u", "<h2 class=\"ultimaVisita\">Bienvenida a tu menú</h2>");
            request.getRequestDispatcher("JSP/menu.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("JSP/login.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StringBuilder mensaje = new StringBuilder();

        String url = "JSP/menu.jsp";

        if (request.getParameter("nombreUsuario").length() == 0 || request.getParameter("pass").length() == 0) {
            mensaje.append("<p class=\"error\">Debe completar todos los campos.<p>");
            url = "JSP/login.jsp";
        } else if (!request.getParameter("nombreUsuario").equals(nombreUsuario)) {
            mensaje.append("<p class=\"error\">Nombre de usuario incorrecto.<p>");
            url = "JSP/login.jsp";
        } else if (!request.getParameter("pass").equals(password)) {
            mensaje.append("<p class=\"error\">Contraseña incorrecta.<p>");
            url = "JSP/login.jsp";
        } else {
            if (request.getParameter("recordarme") != null) {
                Cookie datosUsuario = new Cookie("datosUsuario", (URLEncoder.encode(request.getParameter("nombreUsuario"), "UTF-8")));
                datosUsuario.setMaxAge(3600 * 24);
                response.addCookie(datosUsuario);
                /*request.getSession().setAttribute("nombreUsuario", request.getParameter("nombreUsuario"));*/
            }
            if (ultimoAcceso == null) {
                ultimoAcceso = "<h2 class=\"ultimaVisita\">Bienvenida a tu menú</h2>";
                ultimoAcceso += "<h2 class=\"ultimaVisita\">Esta es tu primera visita.</h2>";
            } else {
                ultimoAcceso = "<h2 class=\"ultimaVisita\">Bienvenida a tu menú</h2>";
                ultimoAcceso += "<h2 class=\"ultimaVisita\">Tu última visita fue el " + URLDecoder.decode(cookieAcceso.getValue(), "UTF-8");
            }
            StringBuilder ultimoAcc = new StringBuilder();
            ultimoAcc.setLength(0);
            ultimoAcc.append(LocalDateTime.now().getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es", "ES")))
                    .append(" ")
                    .append(LocalDateTime.now().getDayOfMonth())
                    .append(" de ")
                    .append(LocalDateTime.now().getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")))
                    .append(" de ")
                    .append(LocalDateTime.now().getYear())
                    .append(" a las ")
                    .append(LocalDateTime.now().getHour())
                    .append(":");
            if (LocalDateTime.now().getMinute() < 10) {
                ultimoAcc.append("0");
                ultimoAcc.append(LocalDateTime.now().getMinute());
            } else {
                ultimoAcc.append(LocalDateTime.now().getMinute());
            }
            ultimoAcc.append(" horas</h2>");
            cookieAcceso.setValue(URLEncoder.encode(ultimoAcc.toString(), "UTF-8"));
            cookieAcceso.setMaxAge(3600 * 24);
            response.addCookie(cookieAcceso);
            request.setAttribute("u", ultimoAcceso);
            request.getSession().setAttribute("nombreUsuario", nombreUsuario);
        }
        request.setAttribute("mensaje", mensaje.toString());
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
