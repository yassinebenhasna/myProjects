using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.ServiceModel.Channels;
using System.ServiceModel.Web;
using System.Text;
using iTextSharp.text;
using Sw1Rest.affichageEntities;
using Sw1Rest.Services;

namespace Sw1Rest
{
    // REMARQUE : vous pouvez utiliser la commande Renommer du menu Refactoriser pour changer le nom de classe "Service1" à la fois dans le code, le fichier svc et le fichier de configuration.
    // REMARQUE : pour lancer le client test WCF afin de tester ce service, sélectionnez Service1.svc ou Service1.svc.cs dans l'Explorateur de solutions et démarrez le débogage.
    public class Service1 : IService1
    {

        //------------------------------------------------//------------------------------------------------//------------------------------------------------
        public string verify(string apogeParam, string passParam)
        {
            
            string x = "";

            //verifier tout d'abord l'authentification
            Authentification auth = new Authentification();

            x = auth.verifierAuth(apogeParam, passParam);
            /*
            List<Master> allMastersenv = new List<Master>();

            using (SWDBEntities1 dbCnx = new SWDBEntities1())
            {

                 x = dbCnx.Masters.FirstOrDefault(c => c.MasterName == "M2I").MasterName;

                 var test1 = (from master in dbCnx.Masters
                         where master.MasterName == "M2I" && master.MasterID == 1

                         select new { id = master.MasterID, name = master.MasterName }
                         //select  master

                     ).ToList();

                 foreach (var m in test1)
                 {
                     Master master = new Master();

                     master.MasterName = m.name;
                     master.MasterID = m.id;

                     allMastersenv.Add(master);
                 }
            }
            /*
            List<string> testAnnee = new List<string>();
            testAnnee.Add("2019/2020"); testAnnee.Add("2020/2021"); testAnnee.Add("2021/2022");
            string x = testAnnee.Max(s => Int32.Parse( s.Split('/')[0] ) ).ToString();
            */


                return x;
        }


        //------------------------------------------------//------------------------------------------------//------------------------------------------------


        //2)listeMasters : fourni la liste des masters (sous format XML) ;
        public List<Master> getAllMasters(string apogeParam, string passParam)
        {

            List<Master> allMastersenv = new List<Master>();

            //verifier tout d'abord l'authentification
            Authentification auth = new Authentification();

            if (auth.verifierAuth(apogeParam, passParam) == "admin")
            {

                List<Master> allMasters = new List<Master>();

                using (SWDBEntities1 dbCnx = new SWDBEntities1())
                {

                    allMasters = dbCnx.Masters.ToList();


                }

                foreach (var mast in allMasters)
                {
                    Master master = new Master();

                    master.MasterName = mast.MasterName;
                    master.MasterID = mast.MasterID;

                    allMastersenv.Add(master);
                }

            }
            return allMastersenv;
        }


        //------------------------------------------------//------------------------------------------------//------------------------------------------------


        //3. listeEtudiantsSemestre : pour un Master et un semestre donné, elle fournit la liste des étudiants inscrits durant le semestre donné (sous formats XML et PDF) ;
        public List<EtudSemestre> getListeEtudiantsSemestre(string masterParam, string semestreParam, string apogeParam, string passParam)
         {

             List<EtudSemestre> listPresoEnv = new List<EtudSemestre>();

            //verifier tout d'abord l'authentification
            Authentification auth = new Authentification();

            if (auth.verifierAuth(apogeParam, passParam) == "admin")
            {

                using (SWDBEntities1 dbCnx = new SWDBEntities1())
                {
                    //1. get id master 
                    int idMaster = 0;
                    Master mas = dbCnx.Masters.FirstOrDefault(masterClass => masterClass.MasterName == masterParam);

                    if (mas != null)
                        idMaster = mas.MasterID;

                    //2. get all etuds in sem and master donné 
                    var personneInSemMaster = (

                        from info in dbCnx.Infos
                        // join master in dbCnx.Masters
                        //   on info.masterID equals master.MasterID 
                        where info.masterID == idMaster
                              && info.Semestre == semestreParam
                        //&& info.Annee == "2019/2020"

                        select new
                        {
                            personne = info.Personne,
                            annee = info.Annee

                        }

                    ).ToList();


                    //charger list pour env
                    foreach (var variable in personneInSemMaster)
                    {

                        EtudSemestre persoAffich = new EtudSemestre();

                        persoAffich.Nom = variable.personne.Nom;
                        persoAffich.Prenom = variable.personne.Prenom;
                        persoAffich.Annee = variable.annee;

                        if (listPresoEnv.Exists(afich => afich.Annee == persoAffich.Annee)
                            &&
                            listPresoEnv.Exists(afich => afich.Nom == persoAffich.Nom))
                        {

                        }
                        else
                        {
                            listPresoEnv.Add(persoAffich);
                        }

                    }

                }
            }

            return listPresoEnv;

        }
        //--------------------------- PDF -----------------------------------------------------
        public Stream getListeEtudiantsSemestrePDF(string masterParam, string semestreParam, string apogeParam, string passParam)
        {

            //verifier tout d'abord l'authentification
            Authentification auth = new Authentification();

            if (auth.verifierAuth(apogeParam, passParam) == "admin")
            {
                List<EtudSemestre> listPresoEnv = getListeEtudiantsSemestre(masterParam, semestreParam, apogeParam, passParam);
                //generer le PDF
                if (listPresoEnv.Count > 0)
                {
                    new Sw1Rest.exportPDF.EtudSemestrePDF().generatePDF(listPresoEnv, masterParam, semestreParam);

                }
            }

            WebOperationContext.Current.OutgoingResponse.ContentType = "application/pdf";
            var byt = File.ReadAllBytes(AppDomain.CurrentDomain.BaseDirectory + @"\filesExported\" + "EtudSemestre.pdf");
            WebOperationContext.Current.OutgoingResponse.ContentLength = byt.Length;

            //StreamReader reader = new StreamReader(new MemoryStream(byt));
            //string text = reader.ReadToEnd();

            return new MemoryStream(byt);
        }


        //------------------------------------------------//------------------------------------------------//------------------------------------------------
        //4. listeEtudiantsModule : pour un master et un semestre donné, pour chaque module, elle fournit la liste des étudiants avec leurs notes(sous formats XML et CSV);

        public List<EtudModule> getListeEtudiantsModule(string masterParam, string semestreParam, string apogeParam, string passParam)
        {
            List<EtudModule> listModuleInfos = new List<EtudModule>();

            //verifier tout d'abord l'authentification
            Authentification auth = new Authentification();

            if (auth.verifierAuth(apogeParam, passParam) == "admin")
            {

                using (SWDBEntities1 dbCnx = new SWDBEntities1())
                {
                    //1. get id master 
                    int idMaster = 0;
                    Master mas = dbCnx.Masters.FirstOrDefault(masterClass => masterClass.MasterName == masterParam);

                    if (mas != null)
                        idMaster = mas.MasterID;

                    //2. get all etudiants ids
                    var listpersonneAffich = (from perso in dbCnx.Personnes select perso).ToList();


                    //3. get all Modules infos for an semestre and master to each etudiant
                    foreach (var personne in listpersonneAffich)
                    {

                        EtudModule etudModule = new EtudModule();

                        int idPerso = personne.PersonneID;

                        List<Info> infosModules = (

                            from info in dbCnx.Infos

                            where info.personneID == idPerso && info.masterID == idMaster &&
                                  info.Semestre == semestreParam

                            select info

                        ).ToList();

                        if (infosModules.Exists(info => info.personneID == personne.PersonneID))
                        {
                            //4. stocker ces data dans moduleAffich

                            //charger la listes des modules de chaque etuds
                            List<ModuleAffich> ModulesForEtud = new List<ModuleAffich>();

                            foreach (var infos in infosModules)
                            {


                                ModuleAffich moduleAff = new ModuleAffich();
                                moduleAff.Module = infos.Module.ModuleName;
                                moduleAff.Etat = infos.Etat;
                                moduleAff.Note = infos.Note;
                                moduleAff.Session = infos.Session;
                                moduleAff.Annee = infos.Annee;

                                ModulesForEtud.Add(moduleAff);

                            }

                            //charger les infos de l'etud avec ses donnés pour ses modules
                            etudModule.Nom = personne.Nom;
                            etudModule.Prenom = personne.Prenom;
                            etudModule.Master = personne.Master.MasterName;
                            etudModule.Module = ModulesForEtud;

                            //charger la liste globale d'infos pour la retournés
                            listModuleInfos.Add(etudModule);

                        }
                    }
                }
            }

            return listModuleInfos;

        }



        //------------------------------------------------//------------------------------------------------//------------------------------------------------
        //5. releveNotes : pour un étudiant donné, elle fournit un relevé des notes pour un semestre donnée(sous formats XML et PDF).

        public EtudModule getRlv(string apogeParam, string semestreParam )
        {

            EtudModule etudModule = new EtudModule();


            using (SWDBEntities1 dbCnx = new SWDBEntities1())
            {

                //get infos for an etud par son apoge
                List<Info> infosModules = (

                    from info in dbCnx.Infos

                    where info.Personne.NApoge == apogeParam && info.Semestre == semestreParam

                    select info

                ).ToList();



                //stocker ces data dans moduleAffich

                //charger la listes des modules de chaque etuds
                List<ModuleAffich> ModulesForEtud = new List<ModuleAffich>();

                foreach (var infos in infosModules)
                {


                    ModuleAffich moduleAff = new ModuleAffich();
                    moduleAff.Module = infos.Module.ModuleName;
                    moduleAff.Etat = infos.Etat;
                    moduleAff.Note = infos.Note;
                    moduleAff.Session = infos.Session;
                    moduleAff.Annee = infos.Annee;

                    ModulesForEtud.Add(moduleAff);

                }

               


                //charger les infos de l'etud avec ses donnés pour ses modules
                if (infosModules.Count != 0)
                {
                    etudModule.Nom = infosModules.Find(info => info.Personne.NApoge.Equals(apogeParam)).Personne.Nom;
                    etudModule.Prenom = infosModules.Find(info => info.Personne.NApoge.Equals(apogeParam)).Personne
                        .Prenom;
                    etudModule.Master = infosModules.Find(info => info.Personne.NApoge.Equals(apogeParam)).Personne.Master.MasterName;
                    etudModule.Module = ModulesForEtud;


                    //traiter les notes de cet etudiant pour avoir seulement les notes final de ses 6 modules
                    Dictionary<string, string> notesFinalModules = new Sw1Rest.Services.RlvSrv().getNotesModulesValides(etudModule);


                    List<ModuleAffich> top6Modules = new List<ModuleAffich>();

                    foreach (ModuleAffich module in ModulesForEtud)
                    {
                        foreach (var VARIABLE in notesFinalModules)
                        {

                            if (module.Module.Equals( VARIABLE.Key ) && module.Note .Equals( VARIABLE.Value.Split(';')[0])
                                                              && module.Annee .Equals( VARIABLE.Value.Split(';')[1]) )
                            {
                                top6Modules.Add(module);
                                break;
                            }


                        }

                    }


                    etudModule.Module = top6Modules;
                    etudModule.Moyenne = new Sw1Rest.Services.RlvSrv().getMoyenne(notesFinalModules);


                    string mention = "";

                    if (Convert.ToDouble(etudModule.Moyenne) >= 10 && Convert.ToDouble(etudModule.Moyenne) < 12)
                        mention = "Passable";
                    if (Convert.ToDouble(etudModule.Moyenne) >= 12 && Convert.ToDouble(etudModule.Moyenne) < 14)
                        mention = "Assez Bien";
                    if (Convert.ToDouble(etudModule.Moyenne) >= 14 && Convert.ToDouble(etudModule.Moyenne) < 16)
                        mention = "Bien";
                    if (Convert.ToDouble(etudModule.Moyenne) >= 16)
                        mention = "Trés Bien";


                    etudModule.Mention = mention;

                }

                

            }

            return etudModule;
        }
        //----------------------- PDF ------------------------------------
        public Stream getRlvPDF(string apogeParam, string semestreParam)
        {

            EtudModule etudModule = getRlv(apogeParam, semestreParam );
            //generer le PDF
            if (etudModule.Nom != null)
            {
                new Sw1Rest.exportPDF.RlvPDF().generatePDF(etudModule, apogeParam, semestreParam);

            }

            WebOperationContext.Current.OutgoingResponse.ContentType = "application/pdf";
            var byt = File.ReadAllBytes(AppDomain.CurrentDomain.BaseDirectory + @"\filesExported\" + "rlvEtud.pdf");
            WebOperationContext.Current.OutgoingResponse.ContentLength = byt.Length;
            return new MemoryStream(byt);

        }




        //------------------------------------------------//------------------------------------------------//------------------------------------------------
        //6. attestationReussite : pour un étudiant donné, elle fournit une attestation de réussite sous format PDF.
        public Stream getAttestation(string apogeParam)
        {

            Dictionary<string, string> notesFinal = new Dictionary<string, string>();
            AttestationService srvc = new AttestationService();

            EtudModule rlvS1 = getRlv(apogeParam, "s1");
            EtudModule rlvS2 = getRlv(apogeParam, "s2");
            EtudModule rlvS3 = getRlv(apogeParam, "s3");
            EtudModule rlvS4 = getRlv(apogeParam, "s4");

            byte[] byt = null;

            //verifier tout d'abord est ce que l'etudiant termine tous ses semestre
            if (rlvS1.Nom != null && rlvS4.Nom != null && rlvS3.Nom != null && rlvS4.Nom != null)
            {
                bool isYear1Valide = srvc.isYearValid(srvc.getNotesModulesValides(rlvS1), srvc.getNotesModulesValides(rlvS2), "sem12" );

                bool isYear2Valide = srvc.isYearValid(srvc.getNotesModulesValides(rlvS3), srvc.getNotesModulesValides(rlvS4), "sem34");


                //si les annees sont validés alors creer PDF Attestation
                if (isYear1Valide == true && isYear2Valide == true)
                {
                    double sommeAnnee = 0;

                    foreach (double VARIABLE in srvc.getMoySemestres())
                    {
                        sommeAnnee += VARIABLE;
                    }

                    double moyAnnee = sommeAnnee / 4;

                    string mention = "";

                    if (moyAnnee >= 10 && moyAnnee < 12)
                        mention = "Passable";
                    if (moyAnnee >= 12 && moyAnnee < 14)
                        mention = "Assez Bien";
                    if (moyAnnee >= 14 && moyAnnee < 16)
                        mention = "Bien";
                    if (moyAnnee >= 16)
                        mention = "Trés Bien";

                    new Sw1Rest.exportPDF.AttPDF().genererAttPDF(apogeParam, srvc.Nom, srvc.Prenom, srvc.Master,
                        mention);

                    WebOperationContext.Current.OutgoingResponse.ContentType = "application/pdf";
                    var byt1 = File.ReadAllBytes(AppDomain.CurrentDomain.BaseDirectory + @"\filesExported\" + "attestation.pdf");
                    WebOperationContext.Current.OutgoingResponse.ContentLength = byt1.Length;
                    return new MemoryStream(byt1);
                }
                else
                {
                    byt = Encoding.ASCII.GetBytes("pas encore");
                }


            }
            else
            {
                byt = Encoding.ASCII.GetBytes("pas encore");
            }

            
            return new MemoryStream(byt);
        }

        
        
    }

}
