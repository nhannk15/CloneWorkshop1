package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AccountDAO;
import model.AccountDTO;

public class ListAllAccountsServlet extends HttpServlet {

    private final String LIST_ALL_ACCOUNTS_PAGE = "listAllAccounts.jsp";
    
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
        String url = LIST_ALL_ACCOUNTS_PAGE;
        try {
            //--- Call DAO.
            AccountDAO dao = new AccountDAO();
            dao.listAll();
            List<AccountDTO> result = dao.getAccounts();
            if(result != null){
                request.setAttribute("LIST_RESULT", result);
            }
        } catch (ClassNotFoundException ex) {
            String message = ex.getMessage();
            log("ListAllAccountsServlet _ ClassNotFound " + message);
        } catch (SQLException ex) {
            String message = ex.getMessage();
            log("ListAllAccountsServlet _ SQL " + message);
        }
        finally {
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
