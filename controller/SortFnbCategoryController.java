package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.*;

@WebServlet(name = "SortFnbCategoryController", urlPatterns = {"/SortFnbCategoryController"})
public class SortFnbCategoryController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            FnbCategoryDA fnbCategoryDA = new FnbCategoryDA();

            String columnName = request.getParameter("columnName");
            String order = request.getParameter("order");
            
            ArrayList<FnbCategory> sortFnbCategoryList = fnbCategoryDA.sortFnbCategory(columnName, order);
            
            request.setAttribute("sortFnbCategoryList", sortFnbCategoryList);
            request.getRequestDispatcher("fnbCategory.jsp").include(request, response);  

        } catch(Exception ex) {
            StackTraceElement[] element = ex.getStackTrace();

            out.println("ERROR: " + ex.toString() + "<br/><br/>");

            for(StackTraceElement e: element) {
                out.println("File Name: " + e.getFileName() + "<br/>");
                out.println("Class Name: " + e.getClassName() + "<br/>");
                out.println("Method Name: " + e.getMethodName() + "<br/>");
                out.println("Line Number: " + e.getLineNumber() + "<br/>");
            }
        }
        
    }
}
