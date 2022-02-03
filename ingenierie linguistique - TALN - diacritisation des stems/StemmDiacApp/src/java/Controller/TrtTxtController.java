/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Crawler.Outils;
import Services.Service;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yassine
 */
@WebServlet(name = "TrtTxtController", urlPatterns = {"/TrtTxtController"})
public class TrtTxtController extends HttpServlet {
      
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml; charset=UTF-8");
        
        PrintWriter prw = response.getWriter();
        
        String webPath = getServletContext().getRealPath("/");
        String srcPath = webPath + "..\\..\\src\\java\\";
        
        Service srv = new Service();
            srv.setSrcPath(srcPath);
            
        Outils Traitement = new Outils();
                

        //get la requete du client
        String req = request.getParameter("text");

        //get quel button a selectionner
        String typeButton = request.getParameter("sub");

       
        if( typeButton.equals("Infos") ){
            
            String tab[] = req.split("\\.|،|,");
            
            List<String> listTxtUser = new ArrayList<>();
            for(String line : tab){
                line = line.trim().replaceAll("\\s+", " ");
                System.out.println("Controller.TrtTxtController.doPost()============" + line);
                listTxtUser.add(line);
            }
                
            int countMot = Traitement.getTermsCount(listTxtUser);
            int countPhrase = Traitement.compteurPhrases(listTxtUser);
            int moyenne = countMot / countPhrase ;
            
            request.setAttribute("mots", String.valueOf( countMot ) );
            request.setAttribute("phrases", String.valueOf(countPhrase));
            request.setAttribute("moy", String.valueOf(moyenne));
            request.setAttribute("req", req);
            
            request.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
            
        }else{
                     
            List<String> listTxtUser = new ArrayList<>();//transformer la req du client vers une list
                listTxtUser.add(req);

            List<String> result = srv.getFarasaAPIResponse(listTxtUser);// envoyer la liste au API FARASA pour get result

            List< List<String> > resultatTraite = srv.traitResult(result); // traiter resultat de FARASA
            
            srv.generateXML(resultatTraite);
            
            List<String> resultXML = srv.chargerFichier("results.xml", "outputXmlFiles");            
            
            if( typeButton.equals("Analyser") ){
                for(String line : resultXML)
                    prw.println(line);
            
            }else{
                request.setAttribute("res", resultXML);
                getServletContext().getRequestDispatcher("/DownloadXML").forward(request, response);
            }

        }

    }
   
}
