/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WEB;

import MODEL.AffineModel;
import MODEL.MultipliModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class Affine_cntrl extends HttpServlet {


  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
            getServletContext().getRequestDispatcher("/WEB-INF/affine.jsp").forward(request, response);
                
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String text = (String) request.getParameter("text");
        String a = (String) request.getParameter("a");
        String cle = (String) request.getParameter("cle");
        
        String mthd = (String) request.getParameter("mthd");
        
        if(mthd.equals("crypter")){
            
            AffineModel affinmodel = new AffineModel();
                
                affinmodel.setText(text);
                affinmodel.setA(Integer.parseInt(a));
                affinmodel.setCle(Integer.parseInt(cle));
                
                String text_sortie = affinmodel.CryptT(affinmodel);
            
            request.setAttribute("text", text);
            request.setAttribute("text_crypte", text_sortie);
            getServletContext().getRequestDispatcher("/WEB-INF/affine.jsp").forward(request, response);
            
        }else{
            
            AffineModel affinmodel = new AffineModel();
                
                affinmodel.setText(text);
                affinmodel.setA(Integer.parseInt(a));
                affinmodel.setCle(Integer.parseInt(cle));
                
                String text_sortie = affinmodel.DecryptT(affinmodel);

                request.setAttribute("text", text);
                request.setAttribute("text_decrypte", text_sortie);
                getServletContext().getRequestDispatcher("/WEB-INF/affine.jsp").forward(request, response);
        }
        
        
    }


}
