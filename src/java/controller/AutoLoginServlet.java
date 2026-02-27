package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AccountDAO;
import model.AccountDTO;

public class AutoLoginServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String HOME_PAGE = "homepage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = LOGIN_PAGE;
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                System.out.println("Cookie existed");
                String account = null;
                String password = null;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("AUTO_LOGIN")) {
                        String[] parts = cookie.getValue().split(":");
                        account = parts[0];
                        password = parts[1];
                        System.out.println(account);
                        System.out.println(password);
                        break;
                    }
                }
                AccountDAO dao = new AccountDAO();
                AccountDTO dto = dao.checkLogin(account, password);
                if (dto != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("USER_INFORMATION", dto);
                    url = HOME_PAGE;
                }
            } else {
                System.out.println("Cookie didn't existed");
            }
        } catch (ClassNotFoundException ex) {
            String message = ex.getMessage();
            log("AtoLoginServlet _ ClassNotFound " + message);
        } catch (SQLException ex) {
            String message = ex.getMessage();
            log("AtoLoginServlet _ SQL " + message);
        } finally {
            response.sendRedirect(url);
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
