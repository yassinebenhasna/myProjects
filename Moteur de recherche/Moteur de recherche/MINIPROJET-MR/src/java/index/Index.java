/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ArabeToASCII;

/**
 *
 * @author yassine
 */




public class Index {
    
    public void construireIndex() {
   
    //1)construire l'index
        try{
            System.out.println("construire l'index :\n");
            ProcessBuilder builder = new ProcessBuilder("curl" , "-X PUT" , "http://localhost:9200/corpusmr/");

            builder.redirectErrorStream(true);
            Process p = builder.start();
            
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            String line;
            while ( (line = r.readLine()) != null ) {
                System.out.println(line);
            }
    
    //2)inserer un document
            System.out.println("\nidexer les documents :\n");
            
            indexerDocs("src/java/data_JSON");
            
                    
            
    
            
            

        } catch (IOException ex) {
            System.out.println("!!!!!!!!!!!!!!!!!!! erreur : " + ex + " !!!!!!!!!!!!!!!!!!!!!!");
        }
     
        
        
    }
    
//************************** extraire les noms des fichiers d'un dossier ****************************************************

    public void indexerDocs(String directoryPath) {
                        
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
                
                String nomFichier = subfiles[i].getName() ;
                
                
                try{                    
                
                    ProcessBuilder builder2 = new ProcessBuilder("curl" , "-X PUT" , "-H","\"Content-Type: application/json;charset=UTF-8\"", "-d", "@src/java/data_JSON/"+nomFichier+"", "http://localhost:9200/corpusmr/index/"+i);

                    builder2.redirectErrorStream(true);
                    Process p2 = builder2.start();

                    BufferedReader r2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));

                    String line2;
                    while ( (line2 = r2.readLine()) != null ) {
                        System.out.println(line2);
                    }
                    
                } catch (IOException ex) {
                    System.out.println("-------------- erreeur lors indexation du fichier : " + nomFichier + " -----------------------  ");
                }

            } 
            
        }
        
    } 
    
    
    
    
    
    
    
    public static void main(String[] args){
        
        Index index = new Index();
        
            index.construireIndex();
        
    }

}
/*
        try {
                
            URL url = new URL("http://localhost:9200/");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
        // Enable output for the connection.
            conn.setDoOutput(true);
            //conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            //conn.setRequestProperty("Accept", "application/json");
            
        // Set HTTP request method.
            conn.setRequestMethod("PUT");

        // creer la requete
            //JSONObject jsonObj = new JSONObject().put("userId", 1).put("id", id).put("title", text).put("body", text);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            //writer.write(jsonObj.toString());
            writer.write("yassine_index2/");
            writer.close();

        } catch (Exception ex) {
            System.out.println("!!!!!!!!!!!!!!!!!!! erreur : " + ex + " !!!!!!!!!!!!!!!!!!!!!!");
        }
*/




//>curl -X GET http://localhost:9200/yassine_index/formation/_search? -H  "Content-Type: application/json" -d "{ \"query\": {\"match\":{\"sous-titre\": \"notre societe\"}}}"

//curl -X PUT -H "Content-Type: application/json" -d "{ \"titre\": \"sami\",\"sous-titre\": \"himar chaykhi fil3a9aba resposable de notre societe \"}" http://localhost:9200/yassine_index/formation/4