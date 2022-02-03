/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WEB;

import MODEL.CesarModel;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class Attaque_cesar_cntrl extends HttpServlet {


  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        
            getServletContext().getRequestDispatcher("/WEB-INF/attaque_cesar.jsp").forward(request, response);
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String text = (String) request.getParameter("text");
        
        String mthd = (String) request.getParameter("mthd");
        

            
            CesarModel cesarmodel = new CesarModel();

                    cesarmodel.setText(text);

                    List<String> text_sortie = cesarmodel.attaque(cesarmodel);

                request.setAttribute("text", text);
                request.setAttribute("list", text_sortie);
                getServletContext().getRequestDispatcher("/WEB-INF/attaque_cesar.jsp").forward(request, response);
        
        
        
    }


}
