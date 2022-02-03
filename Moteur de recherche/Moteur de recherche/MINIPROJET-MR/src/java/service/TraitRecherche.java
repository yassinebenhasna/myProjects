/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yassine
 */
public class TraitRecherche {
    
    public Map<Integer , Map< String , String > > traiterRequete( String premierResultat ){
        
        Map<Integer , Map< String , String > > resultas = new HashMap<>();
                
        //String result0 = premierResultat.replaceAll(".*\"hits\":\\[", "").replaceAll("\"contenu\":.*\\}," ,"").replaceAll("\"contenu\":.*" ,"");
        String result0 = premierResultat.replaceAll(".*\"hits\":\\[", "");
        String res[] = result0.split("\\{");
        
        int nbrResultast = 0;
        for( int i=1; i<res.length; i++ ){
            
            if( ! res[i].startsWith("\"_index\":") ){
                Map< String , String >  infosChaqueResult = new HashMap<>();
                String infos[] = res[i].split(",");
                
                String niveau = infos[1];
                String dossier = infos[2];
                String titre = infos[3];
                
                /*ArabeToASCII ar = new ArabeToASCII();
                        
                            niveau = ar.covertAscii(niveau);
                            dossier = ar.covertAscii(dossier);
                            titre = ar.covertAscii(titre);*/
                
                /*System.out.println("niveu : " + niveau);
                System.out.println("dossier : " + dossier);
                System.out.println("titre : " + titre);*/
                
               
                        
                                
                
                
                infosChaqueResult.put("niveau", niveau.replaceAll("\"niveau\":", "").replaceAll("\"", "").trim());
                infosChaqueResult.put("dossier", dossier.replaceAll("\"dossier\":", "").replaceAll("\"", "").trim());
                infosChaqueResult.put("titre", titre.replaceAll("\"titre\":", "").replaceAll("\"", "").trim());
                
                resultas.put( nbrResultast++ , infosChaqueResult);
            }
        }
        return resultas;
    }
    
}
