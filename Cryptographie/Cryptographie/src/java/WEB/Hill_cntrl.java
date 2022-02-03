/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WEB;

import MODEL.HillModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Minfo
 */
public class Hill_cntrl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/hill.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String text = (String) request.getParameter("text");
        String a = (String) request.getParameter("a");
        String b = (String) request.getParameter("b");
        String c = (String) request.getParameter("c");
        String d = (String) request.getParameter("d");

        String mthd = (String) request.getParameter("mthd");

        if (mthd.equals("crypter")) {

            HillModel hillmodel = new HillModel();

            hillmodel.setText(text);
            hillmodel.setA(Integer.parseInt(a));
            hillmodel.setB(Integer.parseInt(b));
            hillmodel.setC(Integer.parseInt(c));
            hillmodel.setD(Integer.parseInt(d));

            String text_sortie = hillmodel.CryptT(hillmodel);

            request.setAttribute("text", text);
            request.setAttribute("text_crypte", text_sortie);
            getServletContext().getRequestDispatcher("/WEB-INF/hill.jsp").forward(request, response);

        } else {

            HillModel hillmodel = new HillModel();

            hillmodel.setText(text);
            hillmodel.setA(Integer.parseInt(a));
            hillmodel.setB(Integer.parseInt(b));
            hillmodel.setC(Integer.parseInt(c));
            hillmodel.setD(Integer.parseInt(d));

            String text_sortie = hillmodel.DecryptT(hillmodel);

            request.setAttribute("text", text);
            request.setAttribute("text_decrypte", text_sortie);
            getServletContext().getRequestDispatcher("/WEB-INF/hill.jsp").forward(request, response);
        }

    }

}
