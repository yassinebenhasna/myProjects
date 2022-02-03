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
public class TraiterInstance {

//******************************************** mthd pour traiter les donnes du test **********************************************    
    public String traitInstance( Map<String,String> donnesEntreClient , List<String> CheminsBienFormate ){
                                            //Map<modaliteChoisiParClient , attribut>
                                            
        String resultas = "";

        String cheminResultat = "";
        String classeResultat = "";
        
                for( int i=0; i<CheminsBienFormate.size(); i++ ){//obtient chaque chemin
                    
                    String elementOfChemins[] = CheminsBienFormate.get(i).split("----");                                        
                    
                    for( int j=0; j<elementOfChemins.length-1; j = j + 2 ){//obtient les elements de ce chemin
                        
                        int verif = 0;
                        
                        String attributChemin = elementOfChemins[j];   //contient les attributs du chemin
                        String modaliteOUclasseChemin = elementOfChemins[j+1];//contient soit une modalite ou une classe du chemin
                        
                        for( String modaliteChoisiParClient : donnesEntreClient.keySet() ){//obtient chaque donne par client
                            
                            String modaliteChoisit = modaliteChoisiParClient;   //modalite choisit par client
                            String attributDECETTEModaliteChoisit = donnesEntreClient.get(modaliteChoisit);//attribut de cette modalite choisit par client
                            
                            if( attributChemin.equals( attributDECETTEModaliteChoisit ) && modaliteOUclasseChemin.equals( modaliteChoisit ) ){                                                                
                                
                                if( ! cheminResultat.contains( attributChemin ) ){
                                    cheminResultat += attributChemin + "----" + modaliteOUclasseChemin + "----";
                                    classeResultat = elementOfChemins[elementOfChemins.length-1];
                                }
                                                                
                                verif = 1;
                                
                            }
                            
                        }
                        
                        if( verif == 0 ){
                            break;
                        }
                                                
                    }
                    
                }
                cheminResultat += classeResultat;
                System.out.println("    Alors voila le chemin suivit : " + cheminResultat );
                System.out.println("\n    Donc la classe de cette instance = " + classeResultat );
                
                resultas += "    " + cheminResultat +"\n\n";
                resultas += "    Donc la classe de cette instance = " + classeResultat ;
        
        return resultas;//resultats retournes c'est le chemin avec la classe trouvée
    } 
    
}
