/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbreDecision;

import java.io.BufferedReader;
import java.io.FileReader;
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
public class LireFichier {
    
    private List<String> list_TsFichier = new ArrayList<>();
    
    private List<String> list_instances = new ArrayList<>();
    
    private List<String> list_attributs = new ArrayList<>();
    
    private List<String> list_modalites = new ArrayList<>();
    
    private Map<String , List<String> > attributs_AND_LeurInstances = new HashMap<>();

    
    
//************************************* lire le fichier et charger la liste des instances et attributs et modalites **************************************    
    public void lire_fichier( String fileName ) {       

        BufferedReader bufReader = null;
        FileReader fileReader = null;

        try {
            fileReader = new FileReader( fileName );
            bufReader = new BufferedReader(fileReader);

            String contenu_ligne;

            while ((contenu_ligne = bufReader.readLine()) != null) {
                
                //charger ts le fichier
                
                    this.list_TsFichier.add(contenu_ligne);
                
                //charger liste des attributs
                
                if (contenu_ligne.contains("@attribute")) {
                   
                    this.list_attributs.add( contenu_ligne.replaceAll("@attribute", "") . replaceAll("\\{.*", "") . trim() );

                }

                if (contenu_ligne.matches("^[a-zA-Z].*")) {
                    
                    //charger liste des instances
                        this.list_instances.add(contenu_ligne);
                    
                    //charger liste des modalites
                        String[] value = contenu_ligne.split(",");

                        int columns = value.length;
                        for (int i = 0; i < columns - 1; i++) {

                            this.list_modalites.add(value[i]);

                            Set x = new HashSet(this.list_modalites);

                            this.list_modalites = new ArrayList<>(x);

                        }
                    
                }
                                
            }                        
                
            bufReader.close();
        } catch (Exception e) {
            System.out.println("erreur lors lecture du fichier !!");
        }

        //charger la liste des attributs avec leur modalites
            AttributsAvecLeurDonnes();
        
    }    
    
//************************************* get chaque attribut avec ses donnes **************************************    

    public void AttributsAvecLeurDonnes(){
    
        for( int i = 0 ; i < this.list_attributs.size() - 1 ; i++ ){
                        
            List<String> donnes_de_cet_attribut = new ArrayList<>();
            
            for( String instances : this.list_instances ){
            
                String ligne[] = instances.split(",");
                String donne_de_cet_attribut = ligne[i].trim() ;
            
                donnes_de_cet_attribut.add(donne_de_cet_attribut + "," + ligne[ ligne.length-1 ]);
            }
            
            this.attributs_AND_LeurInstances.put( this.list_attributs.get(i) , donnes_de_cet_attribut );

        }
    /*    
        for(String attribut : attributs_AND_LeurInstances.keySet()){
            
            System.out.println("-- attribut : " + attribut);
        
            for(String s : attributs_AND_LeurInstances.get(attribut)){
            
                System.out.println("                instance : " + s);
                
            }
            
        }
    */    
    }
    
//************************************* get les modalites d'un attribut donne **************************************    

    public List<String> get_list_Modalite_pour_attribut(List<String> list_TsFichier, String nomAttribut) {
        
        List<String> list_ModaliteDeCetAttribut = new ArrayList<String>();

        for (int i = 0; i < list_TsFichier.size(); i++) {

            if (list_TsFichier.get(i).contains("@attribute") && list_TsFichier.get(i).contains(nomAttribut)) {
/*
                String[] value = list_TsFichier.get(i).split("( )+");

                int columns = value.length;

                for (int j = 2; j < columns; j++) {

                    String m = value[j];
                    String t = m.replaceAll("[^\\d\\p{L} ]", " ");
                 String r = t.replaceAll(",", "");
                    list_ModaliteDeCetAttribut.add(r.trim());//System.out.println("ha li rah yyzid : " +r);
                }
*/
                
                String ligne = list_TsFichier.get(i).replaceAll(".*\\{", "").replaceAll("\\}", "").trim();
                String modalite[] = ligne.split(",");
                
                for(String s : modalite){
                
                    list_ModaliteDeCetAttribut.add(s.trim());
                    
                }
                
            }

        }

        return list_ModaliteDeCetAttribut;
    }
    
//************************************* GETTERS AND SETTERS **************************************    

    public List<String> getList_instances() {
        return list_instances;
    }

    public void setList_instances(List<String> list_instances) {
        this.list_instances = list_instances;
    }

    public List<String> getList_attributs() {
        return list_attributs;
    }

    public void setList_attributs(List<String> list_attributs) {
        this.list_attributs = list_attributs;
    }

    public List<String> getList_modalites() {
        return list_modalites;
    }

    public void setList_modalites(List<String> list_modalites) {
        this.list_modalites = list_modalites;
    }

    public List<String> getList_TsFichier() {
        return this.list_TsFichier;
    }

    public void setList_TsFichier(List<String> list_TsFichier) {
        this.list_TsFichier = list_TsFichier;
    }

    public Map<String, List<String>> getAttributs_AND_LeurInstances() {
        return attributs_AND_LeurInstances;
    }

    public void setAttributs_AND_LeurInstances(Map<String, List<String>> attributs_AND_LeurInstances) {
        this.attributs_AND_LeurInstances = attributs_AND_LeurInstances;
    }

    
    
}
