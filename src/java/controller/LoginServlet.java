package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AccountDAO;
import model.AccountDTO;

public class LoginServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String HOME_PAGE = "homepage.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //--- Set a default value for URL.
        String url = LOGIN_PAGE;
        
        //--- Get the necessary parameters.
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        
        try {
            //--- Call DAO.
            AccountDAO dao = new AccountDAO();
            dao.checkLogin(username, password);
            AccountDTO dto = dao.checkLogin(username, password);
            if (dto != null) {
                //--- HttpSession for storing user's information.
                HttpSession session = request.getSession();
                session.setAttribute("USER_INFORMATION", dto);
                
                //--- Create Cookie to Client side for AUTO_LOGIN_SERVLET.
                Cookie cookie = new Cookie("AUTO_LOGIN", 
                        dto.getAccount() + ":" + dto.getPass());
                cookie.setMaxAge(60 * 60 * 24 * 30); //--- Set cookie for 1 month
                response.addCookie(cookie);
                
                //--- Set URL.
                url = HOME_PAGE;
            }
        } catch (ClassNotFoundException ex) {
            String message = ex.getMessage();
            log("LoginServlet _ ClassNotFound " + message);
        } catch (SQLException ex) {
            String message = ex.getMessage();
            log("LoginServlet _ SQL " + message);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
