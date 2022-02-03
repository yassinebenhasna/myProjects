/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Minfo
 */
public class Outils {
    
    
//-----------------------------------Partie1:Traitement de corpus-----------------------
    // Question1:fonction pour charger un text_List

    public List<String> chargerFichier(String nomFichier) {

        List<String> text_List = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/java/Crawler/" + nomFichier));

            String line = "";
            while ((line = br.readLine()) != null) {
                text_List.add(line);
            }
            br.close();
        } catch (Exception ex) {
            System.out.println("---->erreur detecte! impossible de  lire le fichier: " + nomFichier + " ----> " + ex);
        }
        return text_List;
    }

    //fonction facultatif pour tester l'affichage
    public void afficher(List list) {

        for (int i = 0; i < list.size(); i++) {
            System.out.println("    " + list.get(i));
        }

    }

    //--------------------------------------------------------------------- les fonctions pour manipuler la partie_1 ---------------------------------------------------------------------------------
//Question2: fonction pour segmenter le corpus avec la liste des markeurs ,afin d'eviter les longues phrases
    
    public static List<String> Transformation(List<String> listCorpus, List<String> marks) {
    List<String> sentencesMarks = new ArrayList<>();
    

        for (String line : listCorpus) {
            String sentencesArray =line;
for (String line1 : marks) {
               
             sentencesArray = sentencesArray.replaceAll(" "+line1+" ",".");

                                       

                    }
                        sentencesMarks.add(sentencesArray);

            
            //line = line.replaceAll("( ){2,}", " ");//Réduire le nombre d’espaces entre les mots en un seul.
           
            //String sentencesArray[] = line.replaceAll("( )+", " ").split("\\.|،");
        }
        return sentencesMarks;
    }
    
    //fonction pour normaliser
    public List<String> normalize(List<String> listCorpus, List<String> marks) {
        List<String> sentences = new ArrayList<>();
        List<String> sentencesMarks = new ArrayList<>();
        List<String> text_List2 = new ArrayList<>();

        List<String> text_List_Normalize = new ArrayList<>();
text_List2=Transformation(listCorpus,marks);
        for (String line : text_List2) {

            //  line = line.replaceAll("\\p{Punct}+", " ");
            // line = line.replaceAll("[^ (أ-ي)ء]", "");
            line = line.replaceAll("( ){2,}", " ");//Réduire le nombre d’espaces entre les mots en un seul.
            //line = line.replaceAll("(.)\\1{1,}", "$1");//Eliminer les lettres qui se répètent plus que deux fois successives..
            line = line.replaceAll("ـ+", "");
            //sentences extraction (Arabic corpus extraction)
            
            String sentencesArray[] = line.replaceAll("( )+", " ").split("\\.|،");

            for (String sentence : sentencesArray) {

                sentences.add(sentence);

            }

            
        }

        for (String line : sentences) {
           
            line = line.replaceAll("\\p{Punct}+", " ");
            line = line.replaceAll("[^ (أ-ي)ء]", "");

            line = line.replaceAll("( ){2,}", " ");//Réduire le nombre d’espaces entre les mots en un seul.
            //line = line.replaceAll("(.)\\1{1,}", "$1");//Eliminer les lettres qui se répètent plus que deux fois successives..

            //sentences extraction (Arabic corpus extraction)
            text_List_Normalize.add(line);

            //
        }

        for (String line : text_List_Normalize) {
            int count=0;
            for (String line1 : marks) {
               if (line.contains(" "+line1+" ")) {

                    String sentencesArray1[] = line.split(line1);

                    //System.out.print(marks.get(0));
                    for (String sentence1 : sentencesArray1) {

                        sentencesMarks.add(sentence1);

                    }
                    count=1;

                    }
              
            }
            
            if(count==0)
                sentencesMarks.add(line);
           

        }

        return sentencesMarks;
    }

    
    //fonction pour compter les phrases de corpus 
    public int compteurPhrases(List<String> listeCorpus) {

        List<String> CorpusPreTrait = new ArrayList<>();

    
      // Initialiser le compteur à zéro
      int nbrLine = 0;            
       
      String str;
      // Lire le contenu du fichier
     for (String line : listeCorpus) {
         //Pour chaque ligne, incrémentez le nombre de lignes
         nbrLine++;               
            
      }
     
        return nbrLine;
   }
          
        
   
    //la fonction pour compter le nombre de mots de corpus

    public int getTermsCount(List<String> CorpusPreTrait) {
int countTerms = 0;
        List<String> listOfTokens = new ArrayList<>();

        for (String line : CorpusPreTrait) {

            String[] words = line.split(" ");

            for (String tk : words) {

                listOfTokens.add(tk);
            }
 countTerms = listOfTokens.size();
        }

        return countTerms;
    }




    
    //Question 4: la fonction writeInFile   
    public void writeInFile(List<String> CorpusPreTrait, String nomFichier) {
        String chemin = "src/java/Crawler/" + nomFichier;

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(chemin));

            for (String line : CorpusPreTrait) {
                bw.write(line + "\n");
            }

            bw.close();

        } catch (Exception ex) {
            System.out.println("---->Erreur detecete! impossible d'ecrire dans  le : " + chemin + " ----> " + ex);
        }

    }
//fonction qui supprmier les lignes vides
  public  void removEmptyLines(String nomFichierEntrant, String nomFichierSortant) {

 List<String> text_List = new ArrayList<>();

     
   Scanner file;
        PrintWriter writer;

        try {

            file = new Scanner(new File("src/java/Crawler/"+nomFichierEntrant));
            writer = new PrintWriter("src/java/Crawler/"+nomFichierSortant);

            while (file.hasNext()) {
                String line = file.nextLine();
                if (!line.isEmpty()) {
                    writer.write(line);
                    writer.write("\n");
                }
            }

            file.close();
            writer.close();

        } catch (FileNotFoundException ex) {
          System.out.print(ex);
        }
  	
    
  }
/*  //test de diactirisation 
   public static String removeDiacritics(String s) {
        //s = pAllDiacritics.matcher(s).replaceAll("");
         s = s.replaceAll("[\u0640\u064b\u064c\u064d\u064e\u064f\u0650\u0651\u0652\u0670]", "");
        return s;
    }

    public static String getDiacritizedStem(String stemmed, String diacritized) {
        
        String output = "";
        String[] parts = (" " + stemmed + " ").split(";");

        if (parts.length != 3) {
            if (parts.length == 2) {
                String prefixe1 = parts[0].trim();
                
        String stem1 = parts[1].trim();
int i = diacritized.length();
while (i > 0 && !removeDiacritics(diacritized.substring(i, diacritized.length())).equals(stem1)) {

            i--;

        }
 // head would be the word without the suffix
 //if(removeDiacritics((diacritized.startsWith("ال")))){
     
        String head = diacritized.substring(0, i);
        // System.out.print(head);
        String tail = diacritized.substring(i);

        // next get the stem
       if (tail.length()> head.length()) {

        return tail;
       }
        else {
                return head;
            }
       

            } else {
                return diacritized;
            }
        }

        String suffixes = parts[2].trim();

        String stem = parts[1].trim();

        int i = diacritized.length();

        while (i > 0 && !diacritized.substring(i, diacritized.length()).equals(suffixes)) {

            i--;

        }
        // head would be the word without the suffix
        String head = diacritized.substring(0, i);
        // System.out.print(head);
        String tail = diacritized.substring(i);

        // next get the stem
        i = head.length();

        while (i > 0 && !head.substring(i, head.length()).equals(stem)) {
            i--;
        }
        String diacritizedStem = head.substring(i);

        return diacritizedStem;
    }
    
*/
}
