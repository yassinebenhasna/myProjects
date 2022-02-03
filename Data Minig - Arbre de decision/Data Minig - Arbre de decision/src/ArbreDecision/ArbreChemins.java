/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbreDecision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yassine
 */
public class ArbreChemins {
    
    private List<String> cheminsArbre = new ArrayList<>();

//******************************************** mthd pour construire les chemins exatcs de l'arbre **********************************************    
    public List<String> construireChemins( List<String> tousCheminsDarbre , List<String> listClasses ){

                //get la racine
                    String racine = tousCheminsDarbre.get(0).trim() + "-";
        
                //formater cette arbre pour faciliter le traitement
                                        
                    for( String chemin : tousCheminsDarbre ){
                        
                        for( String classe : listClasses ){
                            
                            if( chemin.endsWith(classe + "-") ){
                                
                                cheminsArbre.add( racine + chemin.replaceAll(classe + "-", "").replaceAll("-", "----") + classe );
                                
                            }
                                
                        }
                    }                                   
        
        return cheminsArbre;//resultats retournes c'est le chemin avec la classe trouvée
    } 
    
//******************************************** GETTERS AND SETTERS **********************************************    

    public List<String> getCheminsArbre() {
        return cheminsArbre;
    }

    public void setCheminsArbre(List<String> cheminsArbre) {
        this.cheminsArbre = cheminsArbre;
    }

    
}
