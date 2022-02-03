/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbreDecision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author yassine
 */
public class Gain {
    
    LireFichier infoFichier = new LireFichier();
    Entropie entropie = new Entropie();
    
//************************************* calculer le gain **************************************    

    public double calcuerGain(List<String> attribut){//il prend comme parametre les donnes d'une seule attribut avec ses classes
    
        List<String> modalites = new ArrayList<>();
        
        double ENTROPIE_de_cette_ATTRIBUT = 0;
        
        Map<String, Double> listContientSeulement_entrpoiesANDnbr_DeTsLesModaliteDeCette_ATTRIBUT = new HashMap<String, Double>();
    
        for (int i = 0; i < attribut.size(); i++) {

            String line = attribut.get(i);

            String[] colonne = line.split(",");
        
            modalites.add( colonne[0] );
        
        }
        
        Set x = new HashSet(modalites);

        modalites = new ArrayList<>(x);
        
        
        for (int i = 0; i < modalites.size(); i++) {
            
            double ENTROPIE_de_cette_MODALITE = 0;
            
            int nbrDeLa_ieme_Modalite = 0;            List<String> listDeLa_ieme_Modalite = new ArrayList<>();

            
            String modalite = "";
            for (int j = 0; j < attribut.size(); j++) {
                
                String line = attribut.get(j);

                String[] colonne = line.split(",");                                  
                
                if( modalites.get(i).trim() . equals( colonne[0].trim() ) ){
                
                    nbrDeLa_ieme_Modalite++;  
                    listDeLa_ieme_Modalite.add( attribut.get(j).trim() ) ;
                    
                    modalite = colonne[0];
                    
                }
                
            }
            
            ENTROPIE_de_cette_MODALITE = entropie.calculerEntropy(listDeLa_ieme_Modalite ) ;   // System.out.println("modalite : " + modalite + "-----entropie = " + ENTROPIE_de_cette_MODALITE);
            listContientSeulement_entrpoiesANDnbr_DeTsLesModaliteDeCette_ATTRIBUT.put( modalite + "," + nbrDeLa_ieme_Modalite  , ENTROPIE_de_cette_MODALITE);
        }
        
        
        for(String s : listContientSeulement_entrpoiesANDnbr_DeTsLesModaliteDeCette_ATTRIBUT.keySet()){
            
            String info[] = s.split(",");
            
            int nbrDeCetteMODALITEDansATTRIBUT = Integer.parseInt( info[1] );
            int tailleEnsAPP = attribut.size();
            
            double ProbaliteDeCetteMODALITE = (double)nbrDeCetteMODALITEDansATTRIBUT / (double) tailleEnsAPP;  
            double entropieDeCetteMODALITE = listContientSeulement_entrpoiesANDnbr_DeTsLesModaliteDeCette_ATTRIBUT.get(s);
            
            ENTROPIE_de_cette_ATTRIBUT +=  ( ProbaliteDeCetteMODALITE * entropieDeCetteMODALITE ) ; // l'entropie total de cette attribut par la somme des entropies de ses modalites

        }        
        
        //calculons le gain
        
        double entropieTOTAL = entropie.calculerEntropy( attribut );  // entrpoie total de la classe  infoFichier.getList_instances()
                
        double GAIN = entropieTOTAL - ENTROPIE_de_cette_ATTRIBUT ;  // le gain de cette attribut
                
        return GAIN;
    
    }
    
}
