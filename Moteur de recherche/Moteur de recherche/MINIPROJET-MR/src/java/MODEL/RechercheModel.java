/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.ArabeToASCII;

import service.TraitRecherche;

/**
 *
 * @author yassine
 */
public class RechercheModel {
    
    String requete ;//requete de client
    String totalResult;
    Map<Integer , Map< String , String > > resultat = new HashMap<>();//resultat retourne 
    
//****************************************** mthd de la recherche ***************************************************************************
    
    public Map<Integer , Map< String , String > > chercher( int from ) throws UnsupportedEncodingException, IOException{                                
        
        ArabeToASCII ar = new ArabeToASCII();
        
        String premierResultat = "";
        
    //convetritr la requete en code ASCII pour que ELASTICSEARCH puisse la rechercher
        this.requete = ar.covertAscii(this.requete);

        try{
        
            ProcessBuilder builder3 = new ProcessBuilder("curl", "-X GET", "http://localhost:9200/corpusmr/index/_search?", "-H",  "\"Content-Type: application/json;charset=UTF-8\"", "-d", "{ \\\"query\\\": {\\\"match\\\":{\\\"contenu\\\": \\\""+ requete +"\"}},\\\"from\\\": "+from+",\\\"size\\\": 10}");
            
                builder3.redirectErrorStream(true);

                    Process p3 = builder3.start();
                

                    BufferedReader r3 = new BufferedReader(new InputStreamReader(p3.getInputStream()));

        
                    String line3 ;
                    while ( (line3 = r3.readLine()) != null ) {
                        /*BufferedReader tr = new BufferedReader(new InputStreamReader( new ByteArrayInputStream(  line3.getBytes() ) , "UTF8" ) );
                        line3 = tr.readLine();*/
                        //System.out.println("************************************    " + line3 + "\n");
                        premierResultat += line3.replaceAll("\"contenu\": \"[^\\}]*\"", "\\}").replaceAll("\"contenu\": \".*\"", "");
                        //.replaceAll("\"contenu\": \".*[0-9]*.*\"\\}", "hhhhhhhhhhhhhhhhhhhhhh")
                    }

                    
                    
        }catch (IOException ex) {
            System.out.println("!!!!!!!!!!!!!!!!!!! erreur : " + ex + " !!!!!!!!!!!!!!!!!!!!!!");
        } 
        
       //System.out.println("*****pemier = " + premierResultat.replaceAll("\"contenu\": \"[^\\}]*\\}", "\\}").replaceAll("\"contenu\": \".*\"", ""));
       
       int index = premierResultat.indexOf("\"value\":");
       
        totalResult = premierResultat.substring(index, index+14).replaceAll("(\"value\":|,.*)", "").trim();
       //System.out.println("ha lgabed " + totalResult);
        //traiter le premier resultat
        
        TraitRecherche traitement = new TraitRecherche();
        
            resultat = traitement.traiterRequete(premierResultat);
        
        return resultat;        
    }
    
//****************************************** GETTERS AND SETTERS ***************************************************************************

    public String getRequete() {
        return requete;
    }

    public void setRequete(String requete) {
        this.requete = requete;
    }

    public Map<Integer, Map<String, String>> getResultat() {
        return resultat;
    }

    public void setResultat(Map<Integer, Map<String, String>> resultat) {
        this.resultat = resultat;
    }
/*
    public static void main(String[] args) throws IOException {
        
        RechercheModel construire = new RechercheModel();
        
        //lire tous les fichiers et chargrer les listes concernés
            System.out.println(construire.chercher(10));

    }
*/
    public String getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }
   
    

    
}
