package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AccountDAO;
import model.AccountDTO;

public class LoadUpdateAccountServlet extends HttpServlet {
    
    private final String UPDATE_ACCOUNT_PAGE = "updateAccount.jsp";
    private final String ERROR_PAGE = "error.html";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {       
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession checkSession = request.getSession(false);
        if (checkSession == null || checkSession.getAttribute("USER_INFORMATION") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        //--- Set a default value for URL.
        String url = ERROR_PAGE;
        //--- Get the neccessary parameters.
        String account = request.getParameter("txtAccount");
        
        try {
            //--- Call DAO.
            AccountDAO dao = new AccountDAO();
            AccountDTO dto = dao.getObjectById(account);
            if(dto != null){
                request.setAttribute("ACCOUNT_INFO", dto);
                url = UPDATE_ACCOUNT_PAGE;
            }
        } catch (ClassNotFoundException ex) {
            String message = ex.getMessage();
            log("LoadUpdateAccountServlet _ ClassNotFound " + message);
        } catch (SQLException ex) {
            String message = ex.getMessage();
            log("LoadUpdateAccountServlet _ SQL " + message);
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
