/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WEB;

import MODEL.CesarModel;
import MODEL.MultipliModel;
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
public class Attaque_mul_cntrl extends HttpServlet {


  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        
            getServletContext().getRequestDispatcher("/WEB-INF/attaque_multiplication.jsp").forward(request, response);
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String text = (String) request.getParameter("text");
        
        String mthd = (String) request.getParameter("mthd");
        

            
            MultipliModel mulmodel = new MultipliModel();

                    mulmodel.setText(text);

                    List<String> text_sortie = mulmodel.attaquer(mulmodel);

                request.setAttribute("text", text);
                request.setAttribute("list", text_sortie);
                getServletContext().getRequestDispatcher("/WEB-INF/attaque_multiplication.jsp").forward(request, response);
        
        
        
    }


}
