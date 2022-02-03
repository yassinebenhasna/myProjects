/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WEB;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MODEL.RechercheModel;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import service.Normalisation;

/**
 *
 * @author yassine
 */
public class Controleur extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /*request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");*/
        
        
        
        String page = (String) request.getParameter("page");
        String requete = (String) request.getParameter("requete");

        
        System.out.println("haaaaaaaaaaaa requete = " + requete);
        System.out.println("haaaaaaaaaaaa page = " + page);
        
         RechercheModel rechercherModel = new RechercheModel(); 
                           
            
              
           

        if( page != null ){
        
        
                if( page.equals("")  || page.equals("index")  ){

                    getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);

                }else if(page.equals("1")){
                    rechercherModel.setRequete(requete);
                    Map<Integer , Map< String , String > > resultat = rechercherModel.chercher(0);
                    String totalResult = rechercherModel.getTotalResult();
                    getServletContext().getRequestDispatcher("/WEB-INF/Affichage.jsp").forward(request,response);

                }else if(page.equals("2")){
                    rechercherModel.setRequete(requete);
                    Map<Integer , Map< String , String > > resultat = rechercherModel.chercher(10);
                    String totalResult = rechercherModel.getTotalResult();
                    getServletContext().getRequestDispatcher("/WEB-INF/Affichage.jsp").forward(request,response);

                }else if(page.equals("3")){
                    rechercherModel.setRequete(requete);
                    Map<Integer , Map< String , String > > resultat = rechercherModel.chercher(20);
                    String totalResult = rechercherModel.getTotalResult();
                    getServletContext().getRequestDispatcher("/WEB-INF/Affichage.jsp").forward(request,response);

                }else if(page.equals("4")){
                    rechercherModel.setRequete(requete);
                    Map<Integer , Map< String , String > > resultat = rechercherModel.chercher(30);
                    String totalResult = rechercherModel.getTotalResult();
                    getServletContext().getRequestDispatcher("/WEB-INF/Affichage.jsp").forward(request,response);

                }else if(page.equals("5")){
                    rechercherModel.setRequete(requete);
                    Map<Integer , Map< String , String > > resultat = rechercherModel.chercher(40);
                    String totalResult = rechercherModel.getTotalResult();
                    getServletContext().getRequestDispatcher("/WEB-INF/Affichage.jsp").forward(request,response);

                }
                
        }else{
            getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
        }
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /*
        String webPath = getServletContext().getRealPath("/");
        String javaPath = webPath + "..\\..\\src\\test.txt\\";
        */
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        RechercheModel rechercherModel = new RechercheModel();
               
        //get la requete du client
        
            String requete = (String) request.getParameter("champ_search");  
            
        //normaliser cette requete
        
            Normalisation normalisation = new Normalisation();
                
                String requeteNormalise = normalisation.normaliserRequete(requete);
                
        //chercher dans l'index
           
            rechercherModel.setRequete(requeteNormalise);
              
            Map<Integer , Map< String , String > > resultat = rechercherModel.chercher(0);
            String totalResult = rechercherModel.getTotalResult();
            
        //envoyer les resultats vers les pages JSP 
        
            request.setAttribute("result", resultat);
            
            request.setAttribute("requete", requete);
            
            request.setAttribute("totalResult", totalResult);
            
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/Affichage.jsp").forward(request,response);
        
    }

}
