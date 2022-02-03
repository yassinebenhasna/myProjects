/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WEB;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yassine
 */
public class AffichageCntrl extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //rediriger le client vers les pages des fichiers
        
            String page = (String) request.getParameter("page");
            
            /*BufferedReader page1 = new BufferedReader(new InputStreamReader( new ByteArrayInputStream(  page.getBytes() ) , "UTF8" ) );
            
            page = page1.readLine();*/
            
            if( page.equals("") || page == null ){
                
                getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
            
            }else{

                request.setAttribute("nomFichier", page);System.out.println("******************************************************* " + page);
                
                getServletContext().getRequestDispatcher( "/WEB-INF/fichier.jsp" ).forward(request,response);
                
            }


    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

   
}
