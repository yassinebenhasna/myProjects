/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import AlKhalil2.text.tokenization.Tokenization;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *
 * @author yassine
 */
public class Normalisation {
    
    List<String> Mots = new ArrayList<>();
    List<String> Lemmes = new ArrayList<>();
    
    
    //************************** afficher une list ************************************************ 
    
    public void afficher_list( List<String> list ){
    
        for( String s : list )
            System.out.println( s );
    
    }
    
    
    //************************** get le lemme d'un mot *********************************************                                           

    private String lemme(String mot , int enter) {                                                
    
    // 1) retirer les voyelles du mot entré
    
        String textEnter = mot;
        Tokenization obj = new Tokenization();
        obj.setTokenizationString(textEnter);
        List results = obj.getAllTokensUnvoweled();
        
        String motSansVoy = "";
        if(!results.isEmpty())
            motSansVoy = results.get(0).toString();
  
    if( enter == 1 ){
            // 2) afficher les lemmes de ce mot             

                    // sans voyelles

                    List resultLemma = new AlKhalil2.morphology.analyzer.AnalyzerAllTokens().getResultLemma(motSansVoy);

                        if(resultLemma.isEmpty()){

                        }else{
                            // liste de lemmes avec répétitions
                            List ListLemmeAvecRep = new ArrayList();

                            for(int i=0; i<resultLemma.size(); i++){
                                obj.setTokenizationString(resultLemma.get(i).toString());
                                results = obj.getAllTokensUnvoweled();
                                motSansVoy = results.get(0).toString();
                                ListLemmeAvecRep.add(motSansVoy);
                            }

                            // supprimer les répétitions

                            Set suppRep = new HashSet();
                            suppRep.addAll(ListLemmeAvecRep);
                            List ListLemmeSansRep = new ArrayList(suppRep);

                            this.Lemmes.addAll(ListLemmeSansRep);
                        }      
                        
    }
        return motSansVoy;
        
    }
    
    
    //************************** normalisation ************************************************
                

                //------------- commencer la normalisation ---------------------

    public Map<String , List<String>> normaliser(String fileName){
        
        this.Mots.clear();this.Lemmes.clear();

        Map<String , List<String>> infoFichier = new HashMap<>();
                        
        try {
                BufferedReader bf = new BufferedReader( new FileReader( fileName ) );
                
                String contenu; 
                while( ( contenu = bf.readLine() ) != null ){    

                    //--------------- 1) Analyse lexicale ---------------------------------------------------------------------------------------    
                
                    contenu = contenu.replaceAll("[0-9]|[\\+\\(\\)\\:\\[\\]|\\!\\?\\؟\\,\\،\\-\\–\\꞉\\,\\’]*", "") ; //eleminer les ponctuations et les numeros
                    contenu = contenu.replaceAll("\\.+", "");//eliminer le point de ponctuation qui n'a pas de sens à un mot et le point de fin de ligne
                    //contenu = contenu.replaceAll("(     )+", "") ;
                    
                    contenu = contenu.replaceAll("[\\-\\+\\.\\^:,\\|\\]\\[\\»\\«\\؟\\–\\.\\،\\:]","");
                    contenu = contenu.replaceAll("[\\ǃ\\.\\،\"\\:\\»\\«\\.\\ǃ\\ǃ\\؛\\:\\:\\/\\)\\(\\*\\!\\…\\—]+","");
                    contenu = contenu.replaceAll("[\\٠\\١\\٢\\٣\\٤\\٥\\٦\\٧\\٨\\٩]+", "");//supprimer les numero arabe
                    contenu = contenu.toLowerCase().trim() ; //convertir tout le text en miniscule
                    
                    contenu = contenu.replaceAll("(\t)+", " ").replaceAll("( )+", " ") ; //les espaces multiples sont réduits à un seul espace
                    contenu = contenu.trim();
                    
                    //System.out.println(contenu);                        
                    
                
                    String parts0[] = contenu.split(" ");

                    for( String mot_of_contenu : parts0 ){

                        // eliminer les voyelles
                        if( ! mot_of_contenu.isEmpty() ){

                            mot_of_contenu = lemme(mot_of_contenu , 0);                                
                                                                
                                    this.Mots.add( mot_of_contenu );
                                    lemme(mot_of_contenu , 1);

                        }
                    
                    }
                    
                    
                }
             
            bf.close();
                        
            } catch (Exception ex) {
                System.out.println("----------------Erreur lors de lecture du ficheir : "+ex+"---------------fhad fichier : " + fileName);
            }   
        
        Set suppRep1 = new HashSet();
            suppRep1.addAll(this.Lemmes);
            this.Lemmes = new ArrayList(suppRep1);
        
            
        infoFichier.put("Mots", this.Mots);
        infoFichier.put("Lemmes", this.Lemmes);
        
        
        
        return infoFichier;                
    
    }
    
    
    
    //************************** normalisation de la requete ************************************************
    
     public String normaliserRequete(String requete){
                        
            this.Lemmes.clear();
         
            String contenu = requete; 
            
            String requeteNormalise = "";


                    //--------------- 1) Analyse lexicale ---------------------------------------------------------------------------------------    
                
                    contenu = contenu.replaceAll("[0-9]|[\\+\\(\\)\\:\\[\\]|\\!\\?\\؟\\,\\،\\-\\–\\꞉\\,\\’]*", "") ; //eleminer les ponctuations et les numeros
                    contenu = contenu.replaceAll("\\.+", "");//eliminer le point de ponctuation qui n'a pas de sens à un mot et le point de fin de ligne
                    //contenu = contenu.replaceAll("(     )+", "") ;
                    
                    contenu = contenu.replaceAll("[\\-\\+\\.\\^:,\\|\\]\\[\\»\\«\\؟\\–\\.\\،\\:]","");
                    contenu = contenu.replaceAll("[\\ǃ\\.\\،\"\\:\\»\\«\\.\\ǃ\\ǃ\\؛\\:\\:\\/\\)\\(\\*\\!\\…\\—]+","");
                    contenu = contenu.replaceAll("[\\٠\\١\\٢\\٣\\٤\\٥\\٦\\٧\\٨\\٩]+", "");//supprimer les numero arabe
                    contenu = contenu.toLowerCase().trim() ; //convertir tout le text en miniscule
                    
                    contenu = contenu.replaceAll("(\t)+", " ").replaceAll("( )+", " ") ; //les espaces multiples sont réduits à un seul espace
                    contenu = contenu.trim();
                    
                    //System.out.println(contenu);                        
                    
                
                    String parts0[] = contenu.split(" ");

                    for( String mot_of_contenu : parts0 ){

                        // eliminer les voyelles
                        if( ! mot_of_contenu.isEmpty() ){

                            mot_of_contenu = lemme(mot_of_contenu , 0);                                
                                                                
                                    lemme(mot_of_contenu , 1);
                                    
                                    if( !this.Lemmes.isEmpty() )
                                    
                                        requeteNormalise += this.Lemmes.get(0) + " ";
                                    
                                    this.Lemmes.clear();

                        }
                    
                    }
        
        requeteNormalise = requeteNormalise.trim();
        
        return requeteNormalise;                
    
    }
    
/*    
     public static void main(String[] args) {
        
        Normalisation construire = new Normalisation();
        
        //lire tous les fichiers et chargrer les listes concernés
            System.out.println(construire.normaliserRequete("ذهب الحمار الى المخبزة"));


    }
*/
}
