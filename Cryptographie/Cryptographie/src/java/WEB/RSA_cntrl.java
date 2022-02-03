/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WEB;

import MODEL.AffineModel;
import MODEL.MultipliModel;
import MODEL.RSAModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class RSA_cntrl extends HttpServlet {


  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
            getServletContext().getRequestDispatcher("/WEB-INF/RSA.jsp").forward(request, response);
                
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String text = (String) request.getParameter("text");
        String p = (String) request.getParameter("p");
        String q = (String) request.getParameter("q");
        String s = (String) request.getParameter("s");
        
        String mthd = (String) request.getParameter("mthd");
        
        if(mthd.equals("crypter")){
            
            RSAModel rsamodel = new RSAModel();
                
                rsamodel.setText(text);
                rsamodel.setP(Long.parseLong(p));
                rsamodel.setQ(Long.parseLong(q));
                rsamodel.setS(Long.parseLong(s));
                
                String text_sortie = rsamodel.Encryption(rsamodel);
            
            request.setAttribute("text", text);
            request.setAttribute("text_crypte", text_sortie);
            getServletContext().getRequestDispatcher("/WEB-INF/RSA.jsp").forward(request, response);
            
        }/*else{
            
            AffineModel affinmodel = new AffineModel();
                
                affinmodel.setText(text);
                affinmodel.setA(Integer.parseInt(a));
                affinmodel.setCle(Integer.parseInt(cle));
                
                String text_sortie = affinmodel.DecryptT(affinmodel);

                request.setAttribute("text", text);
                request.setAttribute("text_decrypte", text_sortie);
                getServletContext().getRequestDispatcher("/WEB-INF/RSA.jsp").forward(request, response);
        }*/
        
        
    }


}
