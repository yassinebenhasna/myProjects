/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbreDecision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yassine
 */
public class Entropie {
    
    
    
    public double calculerEntropy(List<String> donneAttribut) {//il prend comme parametre les donnes d'une seule modalites avec ses classes 
        
        double entropy = 0.0;
        
        Map<String, Integer> frequence_Classe = new HashMap<String, Integer>();

        for (int i = 0; i < donneAttribut.size(); i++) {

            String line = donneAttribut.get(i);

            String[] colonne = line.split(",");
            
            

            // colonne de la classe
            int col_classe = colonne.length - 1;
            //  Mettre la valeur de classe et sa fréquence dans l’ensemble de données  dans une Map
            if (frequence_Classe.containsKey(colonne[col_classe])) {
                frequence_Classe.put(colonne[col_classe], frequence_Classe.get(colonne[col_classe]) + 1);
            } else {
                frequence_Classe.put(colonne[col_classe], 1);
            }

        }
        
        for (String nom_classe : frequence_Classe.keySet()) {

            double prob = (double) frequence_Classe.get(nom_classe) / donneAttribut.size();

        // Probabilité de la valeur de la classe dans l’ensemble de données 
            entropy -= prob * Math.log(prob) / Math.log(2);
        }

        return entropy;// retourne l'entropie de l'attribut par rapport ses modalites
    }
    
}
