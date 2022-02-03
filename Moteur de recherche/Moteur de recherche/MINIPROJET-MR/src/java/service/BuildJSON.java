/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import service.Normalisation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yassine
 */
public class BuildJSON{
    
    private List<String> listFileNamesDeCHaqueNIVEAU = new ArrayList<>();
    
    private String CORPUS = "CorpusMR/";
    
    private int idFichier = 0;
    
    
    
    
//************************** extraire les noms des fichiers d'un dossier ****************************************************

    public void convertFilesToJSON(String directoryPath) {
                
        File directory = new File(directoryPath);
        
        if(!directory.exists()){
            
            System.out.println("Le fichier/répertoire '"+directoryPath+"' n'existe pas"); 
        
        }else if(!directory.isDirectory()){ 
            
            System.out.println("Le chemin '"+directoryPath+"' correspond à un fichier et non à un répertoire"); 
        
        }else{ 
            
            File[] subfiles = directory.listFiles(); 
        
            String message = "Le répertoire '"+directoryPath+"' contient "+ subfiles.length+" fichier"; 
            
            //System.out.println(message); 
            
            for(int i=0 ; i<subfiles.length; i++){                                
                
                if(subfiles[i].isDirectory()){

                    CORPUS += subfiles[i].getName() + "/" ;
                    convertFilesToJSON(CORPUS);
                    //System.out.println(subfiles[i].getName() + "est un dossier aussi");

                    if( subfiles.length == 1 ){
                        CORPUS = "CorpusMR/";                                            
                    }else{
                        CORPUS = CORPUS.replaceAll( subfiles[i].getName() + "/" , "");
                    }
                    
                }else{
                
                    this.listFileNamesDeCHaqueNIVEAU.add( CORPUS + subfiles[i].getName() );
                    //System.out.println("****************************************  "+CORPUS+subfiles[i].getName()); 
                    
                    //System.out.println("haaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa : " + CORPUS);
                    
                    
                    
                }
            
            } 
            
            //System.out.println("\n-------- commencer a extraire les infos  ... ------------\n");   
            if(CORPUS.matches(".*[0-9]/.+"))
                convert( CORPUS );
            this.listFileNamesDeCHaqueNIVEAU.clear();
            
        }
        
    } 
    

//**************************Lire un fichier ****************************************************
    public void convert(String level){
        
        System.out.println("******* extraire les informations de : " + level + "*********");
        
        String infos[] = level.split("/");
        
        String index = infos[0];
        String niveau = infos[1];
        String dossier = infos[2];
        
        
                            
        Normalisation normalisation = new Normalisation();
        
        int nbrfich = 1;
        for( String fileName : listFileNamesDeCHaqueNIVEAU ){
            
            String titre = fileName.replaceAll("(CorpusMR/[0-9]+/.*/)|.txt", "");
            
            String nomFichier = level.replaceAll("CorpusMR/.*", "id"+String.valueOf( idFichier ) + "-niv" + niveau+"-fich" +nbrfich );
                    
            //List<String> listMotsOfchaqueNiveau = new ArrayList<>();
            List<String> listLemmesOfchaqueFichier = new ArrayList<>();

            Map<String , List<String>> info = normalisation.normaliser(fileName);
            
            //listMotsOfchaqueNiveau.addAll( info.get("Mots") );
            listLemmesOfchaqueFichier.addAll( info.get("Lemmes") );
            
            
        
            /*Set suppRep = new HashSet();
                suppRep.addAll(listMotsOfchaqueNiveau);
                listMotsOfchaqueNiveau = new ArrayList(suppRep);
            */
            
            //supprimer reperitions
            Set suppRep1 = new HashSet();
                suppRep1.addAll(listLemmesOfchaqueFichier);
            listLemmesOfchaqueFichier = new ArrayList(suppRep1);
                        
            try {
                // creer les fichier en format JSON

                BufferedWriter bfw = new BufferedWriter( new FileWriter( "src/java/data_JSON/" + nomFichier + ".json" ) );
                                
                        bfw.write("{\n");
                        
                            bfw.write("\"index\": \""+ index +"\",\n");
                            bfw.write("\"niveau\": \""+ niveau +"\",\n");
                            bfw.write("\"dossier\": \""+ dossier +"\",\n");
                            bfw.write("\"titre\": \""+ titre +"\",\n");
                            
                            String lemmes = "";
                            for( String lemme : listLemmesOfchaqueFichier )
                                lemmes += lemme + " ";
                            
                            bfw.write("\"contenu\": \""+ lemmes +"\"\n");
                
                        bfw.write("}");
                
                bfw.close();
                
                
            } catch (IOException ex) {
                System.out.println("--------------- erreur lors d'ecrirute des fichiers JSON : " + ex + "------------------------------");
            }
            
            this.idFichier++; nbrfich++;
            
        }                                        
                      
    }
    
    
    
    
    
//************************** main ****************************************************
//******************************************************************************

    
    
    public static void main(String[] args) {
        
        BuildJSON construire = new BuildJSON();
        
        //lire tous les fichiers et chargrer les listes concernés
            construire.convertFilesToJSON("CorpusMR");


    }
    
}
