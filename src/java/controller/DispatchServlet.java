package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DispatchServlet extends HttpServlet {
    
    //--- Accounts Controller.   
    private final String LOGIN_PAGE = "login.jsp";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_ACCOUNT_CONTROLLER = "SearchAccountServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String LOGOUT_CONTROLLER = "LogoutController";
    private final String LIST_ALL_ACCOUNTS_CONTROLLER = "ListAllAccountsServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
    private final String LOAD_UPDATE_ACCOUNT_CONTROLLER = "LoadUpdateAccountServlet";
    private final String AUTO_LOGIN_CONTROLLER = "AutoLoginServlet";
    private final String ADD_NEW_ACCOUNT_CONTROLLER = "AddNewAccountServlet";
    
    //--- Category Controller.
    private final String LIST_ALL_CATEGORIES = "ListAllCategoriesServlet";
    private final String DELETE_CATEGORY_CONTROLLER = "DeleteCategoryServlet";
    private final String SEARCH_CATEGORY_CONTROLLER = "SearchCategoryServlet";
    private final String ADD_NEW_CATEGORY_CONTROLLER = "AddNewCategoryServlet";
    private final String LOAD_UPDATE_CATEGORY_CONTROLLER = "LoadUpdateCategoryServlet";
    private final String UPDATE_CATEGORY_CONTROLLER = "UpdateCategoryServlet";
    
    //--- Product Controller.
    private final String LIST_ALL_PRODUCTS_CONTROLLER = "ListAllProductsServlet";  
    private final String FILTER_PRODUCT_BY_CATEGORY_CONTROLLER = "FilterProductByCategoryServlet";
    private final String SEARCH_PRODUCT_USING_NAME = "SearchProductUsingNameServlet";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        //--- Set a defaul value for URL.
        String url = LOGIN_PAGE;
        
        //--- Get the necessary parameters.
        String btnAction = request.getParameter("btnAction");
        
        try {
            //--- Mapping the function Servlet.
            if (btnAction == null) {
                //--- Using Cookies to check AutoLogin.
                url = AUTO_LOGIN_CONTROLLER;
            } else if (btnAction.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (btnAction.equals("SearchAccount")) {
                url = SEARCH_ACCOUNT_CONTROLLER;
            } else if (btnAction.equals("DeleteAccount")) {
                url = DELETE_ACCOUNT_CONTROLLER;
            } else if (btnAction.equals("Logout")) {
                url = LOGOUT_CONTROLLER;
            } else if (btnAction.equals("List All Accounts")) {
                url = LIST_ALL_ACCOUNTS_CONTROLLER;
            } else if (btnAction.equals("LoadUpdateAccount")) {
                url = LOAD_UPDATE_ACCOUNT_CONTROLLER;
            } else if (btnAction.equals("UpdateAccount")) {
                url = UPDATE_ACCOUNT_CONTROLLER;
            } else if (btnAction.equals("ListAllProducts")) {
                url = LIST_ALL_PRODUCTS_CONTROLLER;
            } else if (btnAction.equals("AddNewAccount")) {
                url = ADD_NEW_ACCOUNT_CONTROLLER;
            } else if (btnAction.equals("ListAllCategories")) {
                url = LIST_ALL_CATEGORIES;
            } else if (btnAction.equals("DeleteCategory")) {
                url = DELETE_CATEGORY_CONTROLLER;
            } else if (btnAction.equals("SearchCategory")) {
                url = SEARCH_CATEGORY_CONTROLLER;
            } else if (btnAction.equals("AddNewCategory")) {
                url = ADD_NEW_CATEGORY_CONTROLLER;
            } else if (btnAction.equals("FilterProductByCategory")) {
                url = FILTER_PRODUCT_BY_CATEGORY_CONTROLLER;
            } else if (btnAction.equals("SearchProductUsingName")) {
                url = SEARCH_PRODUCT_USING_NAME;
            } else if (btnAction.equals("LoadUpdateCategory")) {
                url = LOAD_UPDATE_CATEGORY_CONTROLLER;
            } else if (btnAction.equals("UpdateCategory")) {
                url = UPDATE_CATEGORY_CONTROLLER;
            }
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
