package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Date;
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

public class UpdateAccountServlet extends HttpServlet {
    
    private final String ERROR_PAGE = "error.html";
    private final String SEARCH_ACCOUNT_PAGE = "searchAccount.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        //--- Check if User's session exited.
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("USER_INFORMATION") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        //--- Set a default value for URL.
        String url = ERROR_PAGE;
        
        //--- Get the necessary parameters.
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        boolean gender = Boolean.parseBoolean(request.getParameter("gender")) ;
        String phone = request.getParameter("phone");
        boolean use = (request.getParameter("use") != null);
        int roleInSystem = Integer.parseInt(request.getParameter("roleInSystem"));
        
        try {
            AccountDTO dto = new AccountDTO(account, password, lastName, 
                    firstName, birthday, gender, phone, use, roleInSystem);
            AccountDAO dao = new AccountDAO();
            int result = dao.update(dto);
            if(result > 0){
                url = "DispatchServlet"
                        + "?btnAction=SearchAccount"
                        + "&txtSearchValue=" + URLEncoder.encode(lastName + " " + firstName, "UTF-8");
            }
        } catch (ClassNotFoundException ex) {
            String message = ex.getMessage();
            log("DeleteAccountServlt _ ClassNotFound " + message);
        } catch (SQLException ex) {
            String message = ex.getMessage();
            log("DeleteAccountServlt _ SQL " + message);
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
