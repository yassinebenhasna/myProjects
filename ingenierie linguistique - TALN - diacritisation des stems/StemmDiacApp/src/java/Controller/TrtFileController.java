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
@WebServlet(name = "TrtFileController", urlPatterns = {"/TrtFileController"})
public class TrtFileController extends HttpServlet {


   
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

        //get quel button a selectionner
        String typeButton = request.getParameter("sub");
        
        String filename = request.getParameter("fileName");

        
        if( typeButton.equals("Infos") ){
            
            List<String> fileContent = srv.chargerFichier("clientFile.txt", "filesUploaded");
            
            int countMot = Traitement.getTermsCount(fileContent);
            int countPhrase = Traitement.compteurPhrases(fileContent);
            int moyenne = countMot / countPhrase ;
            
            request.setAttribute("mots1", String.valueOf( countMot ) );
            request.setAttribute("phrases1", String.valueOf(countPhrase));
            request.setAttribute("moy1", String.valueOf(moyenne));
            request.setAttribute("msgSucc", filename);
            
            request.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
            
        }else {
            
            List<String> fileContent = srv.chargerFichier("clientFile.txt", "filesUploaded");

            List<String> result = srv.getFarasaAPIResponse(fileContent);// envoyer la liste au API FARASA pour get result

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
