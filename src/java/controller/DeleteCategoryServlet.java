package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AccountDAO;
import model.CategoryDAO;

public class DeleteCategoryServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";
    
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
        
        //--- Get the neccessary paramters.
        String typeId = request.getParameter("typeId");
        String lastSearchValue = request.getParameter("lastSearchValue");
        
        try {
            //--- Call DAO.
            CategoryDAO dao = new CategoryDAO();
            int result = dao.delete(typeId);
            System.out.println(result);
            if (result > 0) {
                if (lastSearchValue.isEmpty()) {
                    url = "DispatchServlet?"
                            + "btnAction=ListAllCategories";
                } else {
                    url = "DispatchServlet?"
                            + "btnAction=SearchCategory"
                            + "&txtSearchValue=" 
                            + URLEncoder.encode(lastSearchValue, "UTF-8");
                }
            }
        } catch (ClassNotFoundException ex) {
            String message = ex.getMessage();
            log("DeleteCategoryServlet _ ClassNotFound " + message);
        } catch (SQLException ex) {
            String message = ex.getMessage();
            log("DeleteCategoryServlet _ SQL " + message);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
