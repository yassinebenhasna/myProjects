/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alg3PC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author yassine
 */
public class ServeurPrincipal extends Thread{
    
    private double montantActuel;
    
    //******************************************* demarrer le thread qui se charge pour faire la cnx ***************************************
    @Override
    public void run(){
        
        try {
            
            //1) créer serveur principal
                ServerSocket serveurPrincipal = new ServerSocket(1234);
                    System.out.println("******** la creation du serveur principale avec port 1234 est reussite ***********");
                    
                
                
            //2)gérer les cnx de ce serveur
                while(true){
                
                    //attendre qu'un client se connecte
                    Socket socket = serveurPrincipal.accept();  
                    
                    //charger le montant du client
                
                    BufferedReader bfr = new BufferedReader( new FileReader( "src/Alg3PC/MontantClient.txt" ) );
                        String montantClient = bfr.readLine();
                        this.montantActuel = Double.parseDouble(montantClient);
                    bfr.close();

                    System.out.println("\n\n====>le montant actuel du client : " + montantActuel + "(DH)");
                    
                    //envoyer cette socket du client au thread(traier) qui gere la communication avec ce client 
                    Traiter trait = new Traiter(socket , montantActuel);
                        trait.start();
                    
                }
            
            
            }catch(Exception ex){
                System.out.println("ereuuuuuuuuuuuuuuuuuur : " + ex);
            }
    }
    
    //******************************************* class pour le traimtement des cnx ***************************************
    class Traiter extends Thread{

        Socket socket;
        double montantActuel;
        
        public Traiter(Socket socket , double montantActuel) {
            this.socket = socket;
            this.montantActuel = montantActuel;
        }
                
        
        @Override
        public void run(){

            try{
                        
                //charger les entrees sorties
                    //entrees
                        InputStream is = socket.getInputStream();
                            InputStreamReader isr = new InputStreamReader(is);
                            BufferedReader bfr = new BufferedReader(isr);

                    //sorties
                        OutputStream os = socket.getOutputStream();
                            PrintWriter pwr = new PrintWriter(os , true);
                       
                //envoyer un msg au client pour l'informer de son montant actuel
                pwr.println(String.valueOf( montantActuel ) );
                
                //attendre les infos du client sur l'operation qu'il veut faire
                String infos = bfr.readLine();
                
                    String parts[] = infos.split(",");
                    
                    //le type d'operation que le cilent veut faire : ajouter ou retirer
                    String typeOperation = parts[0];
                    
                    //le montant qu'il a saisit
                    String montantSaisit = parts[1];
                    
                    //le scenario choisit
                    String choixScenario = parts[2];
                
                //si le client veut ajouter un montant
                if(typeOperation.equals("1")){    
                    System.out.println("\n==>le client veut ajouter un montant de " + montantSaisit + "(DH)");
                }
                //s'il veut retirer un montant
                else{
                    System.out.println("\n==>le client veut retirer un montant de " + montantSaisit + "(DH)");
                }
                  
                System.out.println("\n\n\t*)verification du serveurs de backup en cours ...");
                    
        // maintenant en envoie ces infos au coordinateur pour verifier est ce que les serveurs du backup sont capables pour faire cette modification ou non
                
                Coordinateur coordinateur = new Coordinateur( typeOperation , montantSaisit , choixScenario );
                
                    coordinateur.phase1();
                    
                    String ValiderOUAnnulerOUPlanter = coordinateur.phase2();

                    //si la decision globae est valider donc on passe a la phase 3 
                        if(ValiderOUAnnulerOUPlanter.equals("valider")){
                            coordinateur.phase3();
                            System.out.println("\n======> Traitemant de la demande du client en cours....");
                            
                                if(typeOperation.equals("1")){    
                                    montantActuel = montantActuel + Double.parseDouble( montantSaisit );                     
                                }
                                //s'il veut retirer un montant
                                else{
                                    montantActuel = montantActuel - Double.parseDouble( montantSaisit );                    
                                }
                            
                            System.out.println("\n\t\t\t Traitemant validé .");
                            System.out.println("\n====> le montant actuel du client = " + montantActuel + "(DH)\n\n");
                            
                            //envoyer la reponse au client
                                pwr.println("Traitemant validé : votre montant actuel = " + String.valueOf( montantActuel ) + "(DH)");
                            
                        }
                    //sinon si la decision globae est annuler donc on fait rien  
                        else if( ValiderOUAnnulerOUPlanter.equals("annuler") ){
                            System.out.println("\n\n\t*)traitemant de la demande du client en cours....");
                            System.out.println("\n\t\t\t Traitemant Annulé .");
                            System.out.println("\n====> le montant actuel du client = " + montantActuel + "(DH)\n\n");
                            
                            //envoyer la reponse au client
                                pwr.println("Traitemant Annulé : votre montant actuel = " + String.valueOf( montantActuel ) + "(DH)");
                            
                        }
                        
                    //sinon si le coordinateur est planté donc on fait rien     
                        else{
                            
                            System.out.println("\n\n\t*)traitemant de la demande du client en cours....");
                            System.out.println("\n\t\t\t le coordinateur est planté dpnc le Traitemant sera Annulé .");
                            System.out.println("\n====> le montant actuel du client = " + montantActuel + "(DH)\n\n");
                            
                            //envoyer la reponse au client
                                pwr.println("Traitemant Annulé : votre montant actuel = " + String.valueOf( montantActuel ) + "(DH)");
                            
                        }
                        
                //modifier le montant du client dans le fichier                
                    BufferedWriter bfw = new BufferedWriter( new FileWriter("src/Alg3PC/MontantClient.txt") );
                        bfw.write(String.valueOf(montantActuel));
                    bfw.close();
                        
                System.out.print("\n---------------------------------------------- TERMINE --------------------------------------------------------------");                                      
            }catch(Exception ex){
                System.out.println("--------------erreur : " + ex);
            }
            
        }   
        
    }
        
    
    
    
//******************************************* main mthd principal ***************************************
    
    public static void main(String[] args) {

        new ServeurPrincipal().start();

    }
    
        
}
