/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alg3PC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author yassine
 */
public class ServeurBackup2 extends Thread{
    
    double montantActuel;
    int delaiDeGarde = 15000;// 15 sec
    
    String typeOperation ;
    String montantSaisit;
    
    
    @Override
    public void run(){
        try {
            
            // créer serveur serveurBackup2
                ServerSocket serveurBackup2 = new ServerSocket(1236);
                    System.out.println("******** la creation du serveurBackup2 avec port 1236 est reussite ***********");                                
            
            //attendre un msg de coordinateur  
                while(true){
                
                    Socket socketCoord = serveurBackup2.accept();     
                    
                    //charger le montant du client
                
                    BufferedReader bfr = new BufferedReader( new FileReader( "src/Alg3PC/MontantClient.txt" ) );
                        String montantClient = bfr.readLine();
                        this.montantActuel = Double.parseDouble(montantClient);
                    bfr.close();

                    System.out.println("\n\n====>le montant actuel du client : " + montantActuel + "(DH)");

                        new Communiquer(socketCoord).start();
                }
                    
        }catch(Exception ex){
            System.out.println("erreeeeeeeeeeeur serv backup 2 = " + ex);
        }
    }
    
//******************************************* calss du communiquation avec coordinateur ***************************************
    
    class Communiquer extends Thread{
        
        Socket socketCoord;
        
        public Communiquer( Socket socketCoord ){
            this.socketCoord = socketCoord;
        }
    
        @Override
        public void run(){
            
            try{
            //charger les entrees sorties pour communiquer avec le coordinateur
                    //entrees
                        InputStream is = socketCoord.getInputStream();
                            InputStreamReader isr = new InputStreamReader(is);
                            BufferedReader bfr = new BufferedReader(isr);

                    //sorties
                        OutputStream os = socketCoord.getOutputStream();
                            PrintWriter pwr = new PrintWriter(os , true);
                            
            //attendre la demande du vote de coordinateur avec le choix du client
                String demandeVoteANDClientInfos = bfr.readLine();
                                
                        String parts[] = demandeVoteANDClientInfos.split(",");

                        //la demande de vote
                        String demandeVote = parts[0];
                        
                        //le type d'operation que le cilent veut faire : ajouter ou retirer
                        typeOperation = parts[1];

                        //le montant qu'il a saisit
                        montantSaisit = parts[2];
                        
                System.out.println("\n\n------------>msg du coordinateur : " + demandeVote);
                
            //envoyer la reponse du vote
                System.out.println("\n\t\tmon vote est : oui");
                
                String reponse = "oui";
                
                pwr.println(reponse);
                System.out.println("\n\n\t>>>>>>>> la reponse est envoyé.");
                
            //si la reponse qu'on a choisit est "oui" alors on arme un delai de garde pour att la decision globale 
                if( reponse.equals("oui") ){
                    
                    //1)armer un delai de garde attendant le decison globale
                        sleep(3000);
                        socketCoord.setSoTimeout(delaiDeGarde);
                        System.out.print("\n\t\t-------ATT la decision globale de la part du coordinateur(delai de garde 15 sec)... ");
                        //2)si le coordinateur envoie la decision globale
                            try{
                               
                                String decisonGlobale = bfr.readLine(); //cette reponse peut etre "preparer" ou "annuler"                                                                
                                
                            //si la decision globale est "preparer"
                                if(decisonGlobale.equals("preparer")){
                                    
                                    //3)envoyer ACK au coordinateur
                                        System.out.println("\n\n------------>msg du coordinateur : la decision globale est > \""+ decisonGlobale +"\"" );
                                        System.out.println("\n\n\t\t\tj'envoie mon ACK au coordinateur ");
                                        pwr.println("j'ai reçu la décision globale.");
                                        
                                    //armer un delai de garde pour attendre le reçoit de la validation du coordinateur
                                        sleep(3000);
                                        socketCoord.setSoTimeout(delaiDeGarde);
                                        System.out.print("\n\t\t-------ATT la validation de la part du coordinateur(delai de garde 15 sec)... ");
                                        
                                        //si le coordinateur envoie la validation
                                            try{
                                                String recoitValidation = bfr.readLine();  //recoit "valider"
                                                
                                                System.out.println("\n\n------------>msg du coordinateur : valider l'operation" );
                                                System.out.println("\n======> Traitemant de la demande du client en cours....");
                                                
                                                        //si le client veut ajouter un montant
                                                        if(typeOperation.equals("1")){    
                                                            montantActuel = montantActuel + Double.parseDouble( montantSaisit );                     
                                                        }
                                                        //s'il veut retirer un montant
                                                        else{
                                                            montantActuel = montantActuel - Double.parseDouble( montantSaisit );                    
                                                        }

                                                System.out.println("\n\t\t\t Traitemant Validé .");
                                                                                                    
                                        //si le coordinateur n'envoie pas la validation globale et le delai est expiré alors on valide l'operation
                                            }catch(Exception ex){                       
                                                System.out.println("\n\n------------>le coordinateur n'envoie pas la validation(delai expiré) pourtant il faut valider l'operation" );
                                                System.out.println("\n======> Traitemant de la demande du client en cours....");
                                                
                                                        //si le client veut ajouter un montant
                                                        if(typeOperation.equals("1")){    
                                                            montantActuel = montantActuel + Double.parseDouble( montantSaisit );                     
                                                        }
                                                        //s'il veut retirer un montant
                                                        else{
                                                            montantActuel = montantActuel - Double.parseDouble( montantSaisit );                    
                                                        }

                                                System.out.println("\n\t\t\t Traitemant Validé .");
                                            }       
                                }
                            //si la decision globale est "annuler" 
                                else{
                                    System.out.println("\n\n------------>msg du coordinateur : la decision globale est > \""+ decisonGlobale +"\"" );
                                    System.out.println("\n======> Traitemant de la demande du client en cours....");
                                    System.out.println("\n\t\t\t Traitemant Annulé .");
                                }

                        //4)si le coordinateur n'envoie pas la decision globale et le delai est expiré alors on fait rien
                            }catch(Exception ex){                       
                                System.out.println("\n\n------------>le coordinateur n'envoie pas la decision globale donc il sera \"annuler\"" );
                                System.out.println("\n======> Traitemant de la demande du client en cours....");
                                System.out.println("\n\t\t\t Traitemant Annulé .");
                            }            
                    
                }
            //si la reponse du vote est "non" alors on fait rien
                else{
                    System.out.println("\n======> Traitemant de la demande du client en cours....");
                    System.out.println("\n\t\t\t Traitemant Annulé .");
                }
                
                System.out.println("\n\n====> le montant actuel du client = " + montantActuel + "(DH)\n\n");
                System.out.print("\n---------------------------------------------- TERMINE --------------------------------------------------------------");         
            }catch(Exception ex){
                System.out.println("erreeeur serveurBackup2 class(communiquer) = " + ex);
            }
        }
        
    }
    
    
//******************************************* main mthd principal ***************************************
    public static void main(String[] args){
     
        new ServeurBackup2().start();
        
    }
    
}
