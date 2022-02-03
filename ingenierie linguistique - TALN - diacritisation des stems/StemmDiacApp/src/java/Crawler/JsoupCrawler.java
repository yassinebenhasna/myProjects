package Crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServlet;

public class JsoupCrawler {
//liste pour stocker les hyperliens ..
         

    private List<String> lien;
    String text = "";

    public JsoupCrawler() {
        lien = new ArrayList<String>();
    }

    public List<String> getUrl(String URL) throws IOException {
        //etape 1! Vérifiez si vous avez déjà parcouru les URL

        if (!lien.contains(URL)) {
            try {
                //Sinon, ajoutez-le à l’index
                if (lien.add(URL)) {
                   // System.out.println(URL);
                }
                if (lien.size() > 1) { //limité le telechargement 
                    return lien;
                }

                //Récupérer le code HTML
                Document doc = Jsoup.connect(URL).get();
                // Analysez le code HTML pour extraire des liens vers d’autres URL
                Elements lienPage = doc.select("a[href]");

                //Pour chaque URL extraiten revenir à l’étape 1 .
                for (Element page : lienPage) {
                    getUrl(page.attr("abs:href"));
                }
                //text = doc.body().text();
            } catch (IOException e) {

                //System.err.println("pour'" + URL + "': " + e.getMessage());

            }
        }
        return lien;
    }
public  void WrtiteFileCrawling() throws IOException{


    List<String> liens = new ArrayList<>();
List<String> liens2 = new ArrayList<>();

               //Choisir une URL  pour le tester

     
        JsoupCrawler site1 = new JsoupCrawler();
                JsoupCrawler site2 = new JsoupCrawler();

        liens = site1.getUrl("https://assabah.ma/");
                liens2 = site2.getUrl("https://www.hespress.com/");

        System.out.println( "Voilà le crawling web du site :"+" "+liens.get(0)+"\n \n");

        File file = new File( /*srcPath + */"src/java/Crawler/corpusFarasaCrawling.txt");
int count=0;
int count1=0;
        for (int i = 0; i < liens.size(); i++) {
count++;
            org.jsoup.nodes.Document doc = Jsoup.connect(liens.get(i)).get();

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(doc.body().text() + "\n");
            bw.close();
            System.out.println("Crawling de URL numéro:" +" "+count+"----> URL:"+liens.get(i)+" --->"+" Situation:  terminée!");
        }
        System.out.println( "le nombre de URL crawlés: "+" "+liens.size()+"\n \n");
         System.out.println( "Voilà le crawling web du site :"+" "+liens2.get(0)+"\n \n");
          for (int i = 0; i < liens2.size(); i++) {
count1++;
count++;
            org.jsoup.nodes.Document doc = Jsoup.connect(liens2.get(i)).get();

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(doc.body().text() + "\n");
            bw.close();
            System.out.println("Crawling de URL numéro:" +" "+count1+"----> URL:"+liens2.get(i)+" --->"+" Situation:  terminée!");
        }
       
        System.out.println( "le nombre de URL crawlés: "+" "+liens2.size()+"\n \n");
        System.out.println( "le nombre de URL crawlés: "+" "+count);

}
     public static void main(String[] args) throws IOException {
         
       new JsoupCrawler().WrtiteFileCrawling();
       int countMot;
        int countPhrase;

        Outils Traitement = new Outils();
            //Traitement.setSrcPath( new JsoupCrawler().getSrcPath() );
        List<String> CorpusNormalize = Traitement.chargerFichier("corpusFarasaCrawling.txt");
        List<String> Arabic_Markers = Traitement.chargerFichier("List-of-arabic-markers.txt");

        CorpusNormalize = Traitement.normalize(CorpusNormalize, Arabic_Markers);

        Traitement.writeInFile(CorpusNormalize, "corpusFarasaaNormalized.txt");
        Traitement.removEmptyLines("corpusFarasaaNormalized.txt", "corpusFarasaTraite.txt");
        List<String> CorpusNormalizeFinal = Traitement.chargerFichier("corpusFarasaTraite.txt");

        countMot = Traitement.getTermsCount(CorpusNormalizeFinal);
        countPhrase = Traitement.compteurPhrases(CorpusNormalizeFinal);
        System.out.println("nombre des mots :" + " " + countMot + " mots \n");
        System.out.println("nombre des phrases :" + " " + countPhrase + " phrases \n");
        System.out.println("moyenne mots par phrase :" + " " + countMot / countPhrase + " mots par phrases\n");

    }
}
