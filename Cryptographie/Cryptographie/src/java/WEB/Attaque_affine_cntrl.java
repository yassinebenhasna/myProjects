/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WEB;

import MODEL.AffineModel;
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
public class Attaque_affine_cntrl extends HttpServlet {


  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        
            getServletContext().getRequestDispatcher("/WEB-INF/attaque_affine.jsp").forward(request, response);
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String text = (String) request.getParameter("text");
        String a = (String) request.getParameter("a");                

            
            AffineModel affinemodel = new AffineModel();

                    affinemodel.setText(text);
                    affinemodel.setA(Integer.parseInt(a));

                    List<String> text_sortie = affinemodel.attaquer(affinemodel);

                request.setAttribute("text", text);
                request.setAttribute("list", text_sortie);
                getServletContext().getRequestDispatcher("/WEB-INF/attaque_affine.jsp").forward(request, response);
        
        
        
    }


}
