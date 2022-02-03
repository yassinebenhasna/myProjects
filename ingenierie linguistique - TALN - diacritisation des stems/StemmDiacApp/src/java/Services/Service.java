/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author yassine
 */
public class Service {
    
    int nbrLines;
    int nbrVraiLine = 0;
    List<String> suiteFileContent = new ArrayList<>();
    
    String srcPath;

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }
    
    
    
//----------------------------------------------------------get clitics(soit proc ou enc) with unvulowed form-------------------------------------------------------------------------------------    
    //traitement des fichiers XML
    public List<String> getUnvClitics(String fileName){
        
        List<String> clitics = new ArrayList<>();
        
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(getSrcPath() + "files/" + fileName);

        try {

        Document document = (Document) builder.build(xmlFile);
        Element rootNode = document.getRootElement(); //<prefixes>
        List list = rootNode.getChildren(); //liste des balises <prefixe>

        //System.out.println("rootNode = " + rootNode);
        
        for (int i = 0; i < list.size(); i++) {

           Element node = (Element) list.get(i); //chaque balise <prefixe>
           
           String attUnvoweledForm = node.getAttribute("unvoweledform").getValue();//get attribut unvoweledform de chaque node <prefixe>
           
           //String classe = node.getAttribute("classe").getValue();//get attribut classe de chaque node <prefixe>
           
            clitics.add(attUnvoweledForm);

           //System.out.println("unvoweledform : " + attUnvoweledForm + "\tclasse :" + classe + "\n");


        }

      } catch (Exception ex) {
        System.out.println("Error dans la generations des fichiers XML avec JDOM : " + ex);
      } 
        return clitics;
        
    }    
    
//-----------------------------------------------------------pour lire un fichier -------------------------------------------------------------------------------------------    
    //charger un fichier
    public List<String> chargerFichier(String fileName, String dossier){
        
        List<String> fileContent = new ArrayList<>();
        
        try {
            //BufferedReader bfr = new BufferedReader( new FileReader( getSrcPath() + "files\\" + fileName ) );
            //BufferedReader bfr = new BufferedReader( new InputStreamReader( new DataInputStream( new FileInputStream( getSrcPath() + "files/" + fileName )) ) );
            //BufferedReader nomFichier1 = new BufferedReader(new InputStreamReader( new ByteArrayInputStream( nomFichier.getBytes() ) , "UTF8" ) );
            BufferedReader bfr = new BufferedReader( new InputStreamReader( new FileInputStream( getSrcPath() + dossier + "/" + fileName ),"UTF8" ) );
            
            String line = "";
            while( ( line = bfr.readLine() ) != null ){
                fileContent.add(line);
            }
            bfr.close();
        } catch (Exception ex) {
            System.out.println("---->Error: on peut pa lire le fichier: " + fileName +" ----> " + ex);
        }
        suiteFileContent = fileContent;
        return fileContent;
    }

//----------------------------------------------------------------- get result from api -------------------------------------------------------------------------------------    
public List<String> getFarasaAPIResponse(List<String> fileContent){
    
    nbrLines = 0;
    
    List<String> result = new ArrayList<>();

    try { 
        
        //BufferedWriter bfw = new BufferedWriter( new FileWriter( getSrcPath() + "files/" + "resultTestTxt.txt" ) );
        
        for(String line : fileContent){//System.out.println("TEST.Test.getResponseSegmenter(): " + line);
            nbrLines++;nbrVraiLine++;
            //line = "لعب الولد في الفرشاة";
            //line = covertAscii(line);
            //String s = new String("\u0644\u0639\u0628".getBytes(), "UTF-8");
            //System.out.println("line avant if: " + line );
            line = line.replaceAll("\\s+", " ");
            
            //si la ligne contient les lettres arabes            
            line = line.replaceAll("[^أ-ي ءًٌٍَُِّْٓ0-9a-zA-Z]+", "");
            if( line.matches( "[أ-ي ءًٌٍَُِّْٓ0-9a-zA-Z]+" )  ){
                    System.out.println("traitement de ligne : " + nbrVraiLine);
                HttpResponse responseSegm = Unirest.post("https://farasa-api.qcri.org/msa/webapi/segmenter")
                    .header("content-type", "application/json")
                    .header("cache-control", "no-cache")
                    .body("{\"text\": \""+ line +"\"}")
                    .asString();
                
                HttpResponse responseDiac = Unirest.post("https://farasa-api.qcri.org/msa/webapi/diacritize")
                    .header("content-type", "application/json")
                    .header("cache-control", "no-cache")
                    .body("{\"text\": \""+ line +"\"}")
                    .asString();
                
                //String result1 = responseSegm.getBody().toString();
                //result1 = new String(result1.getBytes(StandardCharsets.ISO_8859_1));
                result.add(responseSegm.getBody().toString() + "&&" + responseDiac.getBody().toString() );
                //result.add(result1);
            }
        }
    } catch (Exception ex) {

        System.out.println("webApp -> Service -> getFarsaAPIResponse() -> Error: " + ex);
        
        if( nbrLines == 1 ){
            getFarasaAPIResponse(suiteFileContent);
        }else{

            for(int i=0; i<nbrLines; i++){
                suiteFileContent.remove(i);
            }
            
            getFarasaAPIResponse(suiteFileContent);
        }
        
        
    }
    
    return result;
}



//------------------------------------------------------- traiter result -----------------------------------------------------------------------------------------------
public List< List<String> > traitResult(List<String> result){
    
    //charger les listes des clitics
    List<String> listProc = getUnvClitics("proclitics.xml");
    List<String> listEnc = getUnvClitics("enclitics.xml");
    
    //la liste qui va contenir tous les informations du corpus (donne une list pour chaque phrase)
    List< List<String> > infosCorpus = new ArrayList<>();
    
    //traitement sur chaque ligne du corpus
    for(String lineResult : result){
        
        //la liste qui contiendra les infos sur chaque ligne du corpus (chaque token de la phrase avec son stem enc proc stemdiacr)
        List<String> infosLine = new ArrayList<>();
        
        //lineResult = {"segtext":["هسبريس","من","ال+رباط"]} && ........................
        
        String resultSegm = lineResult.split("&&")[0].replaceAll("segtext|[\"\\[\\]{}:]+", "");// resultSegm = هسبريس,من,ال+رباط
        String resultDiac = lineResult.split("&&")[1].replaceAll("output|[\"\\[\\]{}:]+", "").replaceAll("\\s+", ",");// phrase mchkola mrigla 

        //ajouter la phrase tout d'abord au liste
        String phrase = resultSegm.replaceAll("\\+", "").replaceAll(",", " ");
        infosLine.add(phrase);
        
                
        String tokens[] = resultSegm.split(",");
        String tokensDiac[] = resultDiac.split(",");
        
        for(int i=0; i<tokens.length; i++){
            
            String tokenPhrase = tokens[i].replaceAll("\\+", "");
            //System.out.println("\t\t token = " + token.replaceAll("\\+", ""));
            
            String tokenDiac = tokensDiac[i];//الرباط  mchkola pr ex
            
            String stemm = "", enc = "", proc = "" ,stemmDiac = tokenDiac;
            
            if(tokens[i].contains("+")){
                //token = ال+رباط
                String tabMotsDivise[] = tokens[i].split("\\+");//ال   رباط
                
                //verifier l'existence de ces mots divisés est ce qu'ils sont des proclitics ou enclitics sinon rattachez le avec le reste du mot
                for(String motDivise : tabMotsDivise){
                    
                    if( listProc.contains(motDivise) ){
                        
                        proc += motDivise;                          
                        char characsProc[] = proc.toCharArray();                
                        for(char c : characsProc){
                            stemmDiac = stemmDiac.replaceAll("^"+String.valueOf(c)+"[ًٌٍَُِّْٓ]*", "");
                        }
                        
                    }else if(listEnc.contains(motDivise)){
                        
                        enc += motDivise;
                        char characsEnc[] = enc.toCharArray();                
                        for(int j=characsEnc.length-1; j>=0; j--){
                            stemmDiac = stemmDiac.replaceAll(String.valueOf(characsEnc[j])+"[ًٌٍَُِّْٓ]*$", "");
                        }
                        
                    }else{
                        stemm += motDivise;
                    }
                                           
                }
                
                if( stemm.length() == 1 || stemm.length() == 0 ){
                        proc = "#"; enc = "#"; stemm = tokenPhrase; stemmDiac = tokenDiac;
                }
                
                if(proc.equals(""))
                    proc = "#";
                if(enc.equals(""))
                    enc = "#";
                
            }else{
                proc = "#"; enc = "#"; stemm = tokenPhrase; stemmDiac = tokenDiac;
            }     
            
            //eliminer chedda
            if(stemmDiac.length() >= 2){
                if(stemmDiac.toCharArray()[1] == 'ّ')
                    stemmDiac = stemmDiac.substring(0, 1) + stemmDiac.substring(2, stemmDiac.length());
            }
            //ajouter les infos de chaque token de chaque phrase du corpus dans la liste
            infosLine.add(tokenPhrase + "->" + proc + "->" + stemm + "->" + enc + "->" + stemmDiac );
            //System.out.println("token=" + tokenPhrase + "->proc=" + proc + "->stemm=" + stemm + "->enc=" + enc+ "->stemmDiac=" + stemmDiac);
            
            
            
        }
        //ajouter la liste infos des phrase dans la liste globale
        infosCorpus.add(infosLine);
    }
    
    return infosCorpus;
}

//------------------------------------------------------- ecrire result dans xml -----------------------------------------------------------------------------------------------
public void generateXML( List< List<String> > result ){
    
    Document doc = new Document();
        doc.setRootElement(new Element("phrases"));
    
    for( List<String> infosLine : result ){
        
        String phrase = infosLine.get(0);
        
        Element phraseElem = new Element("phrase");
            phraseElem.setAttribute("value", phrase);
        
        for( int i=1; i<infosLine.size(); i++ ){                        
            
            String tokensInfos[] = infosLine.get(i).split("->");
            
            String token = tokensInfos[0];
            String proc = tokensInfos[1];
            String stemm = tokensInfos[2];
            String enc = tokensInfos[3];
            String stemmDiac = tokensInfos[4];
            
            phraseElem.addContent( 
                                new Element("info")
                                        .setAttribute("token", token)
                                        .setAttribute("proc", proc)
                                        .setAttribute("enc", enc)
                                        .setAttribute("stemm", stemm)
                                        .setAttribute("stemmDiac", stemmDiac)
            );
            
            
        }
        
        doc.getRootElement().addContent(phraseElem);
        
    }
    
    //JDOM document is ready now, lets write it to file now
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        try {
            //output xml to console for debugging
            //xmlOutputter.output(doc, System.out);
            xmlOutputter.output(doc, new FileOutputStream(getSrcPath() + "outputXmlFiles/results.xml"));
        } catch (Exception ex) {
            System.out.println("generate XML file ERROR = " + ex);
        }

}



}