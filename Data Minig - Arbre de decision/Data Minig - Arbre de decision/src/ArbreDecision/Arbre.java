package ArbreDecision;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Arbre {  
    
    LireFichier info = new LireFichier();
    Gain gain = new Gain();
    ArbreChemins arbreChemins = new ArbreChemins();

    List<String> arbre = new ArrayList<>();
    
    List<String> AffichageArbre = new ArrayList<>();
    List<String> AffichageArbreGrafic = new ArrayList<>();
    
    List<String> chemins = new ArrayList<>();
    
    List<String> tousCheminsDarbre = new ArrayList<>();
    
    List<String> list_classes = new ArrayList<>();
    
    List<String> CheminsBienFormate = new ArrayList<>();
    
    //------ infos extraits de la classe fichier -------------------------------------
        Map<String , List<String> > attributs_AND_LeurInstances = new HashMap<>();
        List<String> list_TsFichier = new ArrayList<>();
        List<String> list_instances = new ArrayList<>();
        List<String> list_modalites = new ArrayList<>();
        List<String> list_attributs = new ArrayList<>();        
    //-------------------------------------------------------------------------------
     

    StringBuffer textAfficheDansGrafic = new StringBuffer();


//****************************** affichage de l'arbre ***************************************************
        
        public void afficherArbreEnDetail(){
        
            for(String info : this.AffichageArbre)  
                System.out.println(info);
        
        }
        
//****************************** affichage de l'arbre Grafic ***************************************************
        public void afficherArbreGrafic(){
        
            for(String info : this.AffichageArbreGrafic)  
                this.textAfficheDansGrafic.append(info);
        
        }
        
//****************************** construire la racine de l'arbre ***************************************************    
    
    public void construireRacine( ){
        
        System.out.println("        *)on calcule les gain de tous les attributs et on choist le meilleur attribut comme racine :\n");
        this.textAfficheDansGrafic.append("1)------------ extraire la racine :-------------\n\n");
        this.textAfficheDansGrafic.append("        *)on calcule les gain de tous les attributs et on choist le meilleur attribut comme racine :\n\n");
        
        Map<String , Double > attributs_And_leurGain = new HashMap<>();            
        
    //calculer le gain de chaque attribut
        
        for( String attribut : attributs_AND_LeurInstances.keySet() ){
        
            // get la liste des instance de cet attribut
            List<String> listDoneesDeCetAttribut = attributs_AND_LeurInstances.get( attribut ) ;
            
            // appeler la mthd calculer le gain pour calculer le gain de cet attribut
            double gainDeCetAttribut = gain.calcuerGain( listDoneesDeCetAttribut ); 
            
            System.out.println("                gain("+attribut+") = " + gainDeCetAttribut);
            this.textAfficheDansGrafic.append("                gain("+attribut+") = " + gainDeCetAttribut+"\n\n");
            
            //charger cet attribut er son gain dans cerre liste
            attributs_And_leurGain.put(attribut, gainDeCetAttribut) ;
        
        }
        
    //appeler la mthd meiilleur attribut pour nous retourne le maeilleur attribut de ces attributs
    
        String racine = MeilleurAttribute(attributs_And_leurGain) ;
        
        System.out.println("\n        *)Alors le meilleur attribut qui a le gain max est : "+racine+" et on le choist comme racine. \n");
        this.textAfficheDansGrafic.append("        *)Alors le meilleur attribut qui a le gain max est : "+racine+" et on le choist comme racine. \n\n");
                
        List<String> listModalite = info.get_list_Modalite_pour_attribut( list_TsFichier , racine);
        
    //chager la racine et ses modalites dans l'arbre et construire le debut d'une liste des chemins
    
        this.arbre.add(racine);
        
        String ligneAjoute = "";    String ligneAjouteDansArbreAffichage = "";  String ligneAjouteDansArbreAffichageGrafic = "";
        
        for( String modalite : listModalite ){
            ligneAjoute += modalite + "||||";   ligneAjouteDansArbreAffichage += "{"+modalite + "}                ";ligneAjouteDansArbreAffichageGrafic += "{"+modalite + "}\t\t";
            this.chemins.add( modalite + "-" );this.tousCheminsDarbre.add(racine + "---");
        }

        this.arbre.add(ligneAjoute);
        
        this.AffichageArbre.add("                                     =========================== l'arbre pour le moment =========================== \n\n");
        this.AffichageArbreGrafic.add("\n\t\t\t=========================== l'arbre pour le moment =========================== \n");
        this.AffichageArbre.add("                                                                  <<" + racine +">>\n\n");
        this.AffichageArbreGrafic.add("\n\t\t\t\t\t<<" + racine +">>\n");
        this.AffichageArbre.add("                                                       " + ligneAjouteDansArbreAffichage+"\n\n");
        this.AffichageArbreGrafic.add("\n\t\t\t\t   " + ligneAjouteDansArbreAffichageGrafic+"\n");
        
        afficherArbreGrafic();
        this.textAfficheDansGrafic.append("\n\t\t\t===========================================================================\n");
        
        afficherArbreEnDetail();
                
    }
 
//****************************** get le meilleur attribut ***************************************************    

    public String MeilleurAttribute(Map<String, Double> gainMap) {
        
        String meilleur_Attribut = null;                
                // attribute avec gain maximum 
                
                double maxValue = -1;
                for (String i : gainMap.keySet()) {
                    if (gainMap.get(i) != -1) {
                        if (gainMap.get(i) > maxValue) {
                            maxValue = gainMap.get(i);
                            meilleur_Attribut = i;
                        }
                    } else {
                        maxValue = -1;
                        meilleur_Attribut = i;
                    }
                }
        

        return meilleur_Attribut;
    }
    
//****************************** charger la liste des classes ***************************************************    

    public void chargerClasses() {

        list_classes = info.get_list_Modalite_pour_attribut( list_TsFichier, list_attributs.get( list_attributs.size()-1 ));
        
    }

//****************************** mthd qui retourne soit le noeud plus descriminant ou la classe directement ***************************************************   
    
    public String getNoeudOUclass( List<String> listContientInstances ){
        
        String noeudOUclass = "";
        
        Map<String , List<String>> newListAtrributAndInstancePourCaluluerGain = new HashMap<>();

        String premierLigneDeCesInstance = listContientInstances.get(0);

        String parts[] = premierLigneDeCesInstance.split(",");
       
        
        for(int i=0; i<parts.length-1; i++){//list contient les modalites de new instances 

            String iemeModaliteDansPremierLigne = parts[i].trim();

            for( String attribut : this.attributs_AND_LeurInstances.keySet() ){

                List<String> listDonnesDeCetteAttribut = this.attributs_AND_LeurInstances.get(attribut); //les listes de chaque attribut               
                
                for(String listDonnesAttribut : listDonnesDeCetteAttribut){
                    if( listDonnesAttribut.matches(".*"+iemeModaliteDansPremierLigne+".*") ){//si la liste de cet attribut contient cette modalite

                        String attributDeCetteList = attribut;
                        List<String> newInstancePourcetAttribut = new ArrayList<>();

                        for( String ligneInstance : listContientInstances ){

                            String modalite[] = ligneInstance.split(",");

                            newInstancePourcetAttribut.add( modalite[i].trim() + "," + modalite[modalite.length-1] );

                        }

                        newListAtrributAndInstancePourCaluluerGain.put(attribut, newInstancePourcetAttribut);
                        break;
                    } 
                }
            }
        }
        
        
        
        
        //maintenant on reconstruit la liste des instances donné en une liste avec l'attribut de chaque colone et ses valeur
        
        Map<String , Double > attributs_And_leurGain = new HashMap<>();            
        
        //calculer le gain de chaque attribut
        
        for( String attribut : newListAtrributAndInstancePourCaluluerGain.keySet() ){
        
            // get la liste des instance de cet attribut
            List<String> listDoneesDeCetAttribut = newListAtrributAndInstancePourCaluluerGain.get( attribut ) ;
            
            // appeler la mthd calculer le gain pour calculer le gain de cet attribut
            double gainDeCetAttribut = gain.calcuerGain( listDoneesDeCetAttribut ); //System.out.println("attribut : " + attribut + "---- gain = " + gainDeCetAttribut);
            
            //charger cet attribut er son gain dans cerre liste
            attributs_And_leurGain.put(attribut, gainDeCetAttribut) ;
        
        }
        
        //verifier si tous les gain=0 pour retourner la clasee
        
        boolean tousLesGainsEgausZERO = true;
        
        for (String attribut : attributs_And_leurGain.keySet()) {
            if (attributs_And_leurGain.get(attribut) != 0) {
                tousLesGainsEgausZERO = false;
            }
        }
        //si tous le gains = 0  alors on prend directement la classe
        if( tousLesGainsEgausZERO == true ){
            
            noeudOUclass = parts[parts.length-1];
        
        //sinon donc on appel la mthd pour qu'il retourne le meilleur attribut    
        }else{
        
            //appeler la mthd meiilleur attribut pour nous retourne le maeilleur attribut de ces attributs 

                noeudOUclass = MeilleurAttribute(attributs_And_leurGain) ;
                
        }
        
        
        
        return noeudOUclass;
    
    }
    
    
//****************************** construire l'arbre ***************************************************    
 
    public void construireArbre(){
        
        this.textAfficheDansGrafic.append("\n2)------------ remplir maintenant l'arbre :-------------\n");    
        for( int i = 1; i<this.arbre.size(); i = i+2 ){
        
            if( this.arbre.get(i) . matches( ".*[a-zA-Z].*" ) ){
            
                String ligneAjoute = "" ;   String nextLigneAjoute = "" ;   
                String nextLigneAjouteForAffichage = "\n                                                    ";String nextLigneAjouteForAffichageGrafic = "\n\t\t\t             ";
                String ligneAjouteForAffichage = "\n                                                       ";String LigneAjouteForAffichageGrafic = "\n\t\t\t\t   ";
                
                String modalitesDansCetteLigne[] = this.arbre.get(i).split("\\|\\|\\|\\|");

                for( int j = 0; j<modalitesDansCetteLigne.length; j++ ){//entrer dans le chemin de chaque modalite de la racine
                
                    if( modalitesDansCetteLigne[j].matches(".*[a-zA-Z].*") ){
                    
                        String newChemin = ""; 
                        
                        //get le chemin qui commence par chaque modalite de la racine
                        String cheminVoulons = this.chemins.get(j);
                        
                        //extraire d'apres ce chemin les modalites qu'on veut supprimer
                        String cheminSupprime[] = cheminVoulons.split("\\|");
                        
                        //commencer le traitement de la suppression pour avoir le resultat de chaque modalite si on la supprime
                        for(int k = 0; k<cheminSupprime.length; k++){
                            
                            System.out.println("\n        *)On supprime maintenant ce chemin : { " + cheminSupprime[k] + " ] pour avoir soit un attribut ou la classe finale :\n");
                            this.textAfficheDansGrafic.append("\n        *)On supprime maintenant ce chemin : { " + cheminSupprime[k] + " ] pour avoir soit un attribut ou la classe finale :\n");
                        
                            if( ! cheminSupprime[k].equals("Fin") ){
                            
                                List<String> temp = this.list_instances;
                                
                                String modalitesSupprimes[] = cheminSupprime[k].split("-");
                                
                                //supprimer le chemin a partir de la premiere modalite de la racine
                                for( int m=0; m<modalitesSupprimes.length; m++ ){
                                
                                    List<String> temp2 = new ArrayList<>();
                                    
                                    //verifions est ce que tous d'abors la modalite voulons la supprimé est une vraiment une modalite ou une attribut ?
                                    if( this.list_modalites.contains( modalitesSupprimes[m] ) ){
                                    
                                        for( String instance : temp ){
                                        
                                            if( instance.contains( modalitesSupprimes[m] ) ){
                                            
                                                temp2.add( instance.replaceAll( modalitesSupprimes[m] + "," , "").trim() );

                                            }
                                            
                                        }
                                    
                                        temp = temp2;
                                    }
                                
                                }
                                
                                String noeud_OU_class = getNoeudOUclass(temp);    String nOUc = noeud_OU_class;                                                            
                                
                                if( this.list_attributs.contains(noeud_OU_class) ){
                                    
                                    nOUc = "<<"+nOUc+">>";
                                    
                                    System.out.println("        >>>>>>>>>>>>>>>>>>>> Alors on a obtient : " + nOUc +"\n");
                                    this.textAfficheDansGrafic.append("\n        >>>>>>>>>>>>>>>>>>>> Alors on a obtient : " + nOUc +"\n");
                                
                                    List<String> modalitesDeCetAtrribut = info.get_list_Modalite_pour_attribut(this.list_TsFichier, noeud_OU_class);
                                    
                                    nextLigneAjouteForAffichage += noeud_OU_class+"{ ";
                                    nextLigneAjouteForAffichageGrafic += noeud_OU_class+"{";
                                    for( String modalite : modalitesDeCetAtrribut ){
                                    
                                        newChemin += cheminSupprime[k] + noeud_OU_class + "-" + modalite + "-|";    
                                        this.tousCheminsDarbre.add( cheminSupprime[k] + noeud_OU_class + "-" + modalite + "-" );//utilise pour testet instance
                                        
                                        nextLigneAjoute += modalite + ",";
                                        
                                        nextLigneAjouteForAffichage += modalite + " "; nextLigneAjouteForAffichageGrafic += modalite + " ";
                                    
                                    }   nextLigneAjouteForAffichage += "} ";   nextLigneAjouteForAffichageGrafic += "}  ";                                                                  
                                    
                                }else{

                                    System.out.println("        >>>>>>>>>>>>>>>>>>>> Alors on a obtient : " + nOUc +"\n");
                                    this.textAfficheDansGrafic.append("        >>>>>>>>>>>>>>>>>>>> Alors on a obtient : " + nOUc +"\n");
                                
                                    newChemin += "Fin|";    nextLigneAjoute += "    ,"; nextLigneAjouteForAffichage += "  FIN  "; 
                                                                                        nextLigneAjouteForAffichageGrafic += "    FIN    ";
                                
                                    this.tousCheminsDarbre.add( cheminSupprime[k] + noeud_OU_class + "-" + nOUc + "-" );
                                }
                                
                                ligneAjoute += noeud_OU_class + ",";   ligneAjouteForAffichage +=  nOUc+"      ";
                                                                       LigneAjouteForAffichageGrafic += nOUc+"     ";
                            
                            }else{
                            
                                newChemin += "Fin|";    nextLigneAjoute += "    ,"; ligneAjoute += "    ,";
                                                        nextLigneAjouteForAffichage += "  FIN  ";      ligneAjouteForAffichage += "  FIN  ";
                                                        nextLigneAjouteForAffichageGrafic += "     FIN     ";  LigneAjouteForAffichageGrafic += "     FIN     ";
                            }
                            
                        }
                        
                        chemins.add(j , newChemin);chemins.remove(j+1);
                        ligneAjoute += "||||"; nextLigneAjoute += "||||";   
                        ligneAjouteForAffichage += "            ";  nextLigneAjouteForAffichage += "            ";
                        LigneAjouteForAffichageGrafic += "\t\t"; nextLigneAjouteForAffichageGrafic += "\t\t";
                    }else{
                    
                        chemins.add(j , "Fin|");chemins.remove(j+1);
                        ligneAjoute += "    ||||"; nextLigneAjoute += "    ||||";
                        ligneAjouteForAffichage += "            ";  nextLigneAjouteForAffichage += "            ";
                        LigneAjouteForAffichageGrafic += "\t\t"; nextLigneAjouteForAffichageGrafic += "\t\t";
                    }
                
                }
                
                arbre.add(ligneAjoute); this.AffichageArbre.add(ligneAjouteForAffichage);this.AffichageArbreGrafic.add(LigneAjouteForAffichageGrafic + "\n");
                                        
                arbre.add(nextLigneAjoute); this.AffichageArbre.add(nextLigneAjouteForAffichage);this.AffichageArbreGrafic.add(nextLigneAjouteForAffichageGrafic + "\n");
                
                
                afficherArbreGrafic();
                afficherArbreEnDetail();
                
                              
                
                
                System.out.println("\n                                     ============================================================================== \n");
                this.textAfficheDansGrafic.append("\n\t\t\t===========================================================================\n");
                                            
            }else{
                
                System.out.println("\n\n\n                                                               CONCLUSION :");
                this.textAfficheDansGrafic.append("\n\n\n \t\t\t                                                              CONCLUSION :\n");
                
                System.out.println("                                                               -----------\n");
                this.textAfficheDansGrafic.append("  \t\t\t                                                             --------------------\n\n");
                
                System.out.println("                                            ->) Alors voila les chemins obtenus de notre arbre :\n");
                this.textAfficheDansGrafic.append("     \t\t\t                                       ->) Alors voila les chemins obtenus de notre arbre :\n");
                
                chargerClasses();
                this.CheminsBienFormate = arbreChemins.construireChemins(tousCheminsDarbre, list_classes);
                for( String chemin : arbreChemins.getCheminsArbre() ){
                        System.out.println("\n                                          " + chemin);
                        this.textAfficheDansGrafic.append("\n  \t\t                                        " + chemin + "\n");
                }
            
                break;
                
            }
        
        }
    
    }
    
    
//************************************* main ************************************** 
//********************************************************************************* 

    public static void main(String[] args) throws IOException {
        
        Scanner clavier = new Scanner(System.in);
        
        System.out.println("    ***************************************************************************************************************************************************\n");
        System.out.println("                                                        PROJET DATA MINING                                                                            \n");
        System.out.println("                                                      ARBRE   DE   DECISION                                                                            \n");
        System.out.println("                                        realise par : BENHASNA YASSINE && KACHOUTI MOHAMED                                                              \n");
        System.out.println("    ***************************************************************************************************************************************************");
        
        System.out.println("\n\nIl existe 3 fichiers pour le test : 1:contact.arff      2:meteorologique.arff      3:maladies.arff\n\n");
        
        System.out.print("choisissez un fichier (1,2,3) SVP : ");        
        
        int choix = clavier.nextInt();
        
        System.out.println("\n");
        
        Arbre arbre = new Arbre();
        LireFichier lireFichier = new LireFichier();
        
        if( choix == 1 ){
                    
                //charger le fichier et obtenir ts infos concernant
                    System.out.print("*********************** on commence la construction de l'arbre de decision pour contact.arff ***********************\n\n ");
                    lireFichier.lire_fichier("src/contact.arff");   
                    
        }else if( choix == 2 ){
        
                //charger le fichier et obtenir ts infos concernant
                    System.out.print("*********************** on commence la construction de l'arbre de decision pour meteorologique.arff ***********************\n\n ");
                    lireFichier.lire_fichier("src/meteorologique.arff");   
        
        }else{
        
                //charger le fichier et obtenir ts infos concernant
                    System.out.print("*********************** on commence la construction de l'arbre de decision pour maladies.arff ***********************\n\n ");
                    lireFichier.lire_fichier("src/maladies.arff");   
        
        }
            
        //construire la racine et charger ses modalites
            arbre.list_attributs = lireFichier.getList_attributs();
            arbre.list_modalites = lireFichier.getList_modalites();
            arbre.list_instances = lireFichier.getList_instances();
            arbre.attributs_AND_LeurInstances = lireFichier.getAttributs_AND_LeurInstances();
            arbre.list_TsFichier = lireFichier.getList_TsFichier();
            
            System.out.println("1)------------ extraire la racine :-------------");
            
            arbre.construireRacine(  );
            
            System.out.println("2)------------ remplir maintenant l'arbre :-------------");
            
        //construire toute l'arbre
            arbre.construireArbre();
            
        //entrer une instance
            clavier.nextLine();
            Map<String , String> donnesEntreClient = new HashMap<>();
            
            System.out.println("\n\n3)------------ entrer une instance pour le test :------------\n\n");
        
            for( int i=0; i<arbre.list_attributs.size() - 1 ; i++ ){
                
                String attribut = arbre.list_attributs.get(i);
                
                String msgAffiché = "{ ";
                for(String modalite : arbre.info.get_list_Modalite_pour_attribut(arbre.list_TsFichier, attribut)){
                    
                    msgAffiché += modalite + " ";
                            
                }
                
                msgAffiché += " }";
                
                System.out.print("        choisir une modalitée " + msgAffiché + " pour l'attribut : \"" + attribut + "\" >> ");
                
                String modaliteChoisiParClient = clavier.nextLine();
                
                donnesEntreClient.put(modaliteChoisiParClient, attribut);
                
                System.out.println();
            }
            
        //appeler la classe qui traite les données d'un client
        
            TraiterInstance traiterInstance = new TraiterInstance();
                //on donne comme parametres les donnees saisient par client et la liste des chemins de l'arbre 
                traiterInstance.traitInstance(donnesEntreClient, arbre.CheminsBienFormate);

                
    }

}