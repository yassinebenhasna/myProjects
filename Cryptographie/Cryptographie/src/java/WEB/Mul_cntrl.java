/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WEB;

import MODEL.CesarModel;
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
public class Mul_cntrl extends HttpServlet {


  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
            getServletContext().getRequestDispatcher("/WEB-INF/multiplication.jsp").forward(request, response);
                
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String text = (String) request.getParameter("text");
        String cle = (String) request.getParameter("cle");
        
        String mthd = (String) request.getParameter("mthd");
        
        if(mthd.equals("crypter")){
            
            MultipliModel mulmodel = new MultipliModel();
                
                mulmodel.setText(text);
                mulmodel.setCle(Integer.parseInt(cle));
                
                String text_sortie = mulmodel.CryptT(mulmodel);
            
            request.setAttribute("text", text);
            request.setAttribute("text_crypte", text_sortie);
            getServletContext().getRequestDispatcher("/WEB-INF/multiplication.jsp").forward(request, response);
            
        }else{
            
            MultipliModel mulmodel = new MultipliModel();
                
                mulmodel.setText(text);
                mulmodel.setCle(Integer.parseInt(cle));
                
                String text_sortie = mulmodel.DecryptT(mulmodel);

                request.setAttribute("text", text);
                request.setAttribute("text_decrypte", text_sortie);
                getServletContext().getRequestDispatcher("/WEB-INF/multiplication.jsp").forward(request, response);
        }
        
        
    }


}
