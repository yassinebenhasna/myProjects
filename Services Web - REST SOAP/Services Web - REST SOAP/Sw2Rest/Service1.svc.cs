using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using Sw2Rest.Services;

namespace Sw2Rest
{
    // REMARQUE : vous pouvez utiliser la commande Renommer du menu Refactoriser pour changer le nom de classe "Service1" dans le code, le fichier svc et le fichier de configuration.
    // REMARQUE : pour lancer le client test WCF afin de tester ce service, sélectionnez Service1.svc ou Service1.svc.cs dans l'Explorateur de solutions et démarrez le débogage.
    public class Service1 : IService1
    {


        //modifier Password -----------------------------------------------------------------------------------------------------------
        public string updatePass(string nApogeParam, string newPassParam)
        {

            using (SWDBEntities2 dbCnx = new SWDBEntities2())
            {
                Personne perso = dbCnx.Personnes.FirstOrDefault(personne => personne.NApoge == nApogeParam );

                perso.Password = newPassParam;

                dbCnx.SaveChanges();
            }

            return "goood";
        }



        //modifier les notes d'un etudiant -------------------------------------------------------------------------------------------
        public string updateNotes(string apogeAdminParam, string passwordParam, string apogeEtudParam,
            string moduleParam, string sessionParam, string AnneeParam, string newNote)
        {
            //verifier l'auth est ce qu'un addmin
            Auth auth = new Auth();

            if (auth.verifierAuth(apogeAdminParam, passwordParam) == "admin")
            {
                using (SWDBEntities2 dbCnx = new SWDBEntities2())
                {
                    //get module concerne
                    Info info = dbCnx.Infos.FirstOrDefault(info1 => info1.Personne.NApoge == apogeEtudParam
                                                                 && info1.Module.ModuleName == moduleParam
                                                                 && info1.Session == sessionParam
                                                                 && info1.Annee == AnneeParam
                    );

                    info.Note = newNote;

                    dbCnx.SaveChanges();
                }
            }

            return "goooood";
        }




        //ajouter un étudiant -----------------------------------------------------------------------------------------------------------
        public string insertEtud(string apogeAdminParam, string passwordParam, string NomParam, string PrenomdParam, 
            string EmailParam, string NApogeParam, string CneParam, string masterParam)
        {

            //verifier l'auth est ce qu'un addmin
            Auth auth = new Auth();

            if (auth.verifierAuth(apogeAdminParam, passwordParam) == "admin")
            {
                using (SWDBEntities2 dbCnx = new SWDBEntities2())
                {
                    Personne newPerso = new Personne();
                    newPerso.Nom = NomParam;
                    newPerso.Prenom = PrenomdParam;
                    newPerso.Email = EmailParam;
                    newPerso.NApoge = NApogeParam;
                    newPerso.Cne = CneParam;
                    newPerso.Role = "etud";
                    newPerso.Password = CneParam;
                    newPerso.masterID = dbCnx.Masters.FirstOrDefault(master => master.MasterName == masterParam).MasterID;

                    dbCnx.Personnes.Add(newPerso);
                    dbCnx.SaveChanges();
                }
            }

            return "gooooood";
        }



        //supprimer un étudiant-----------------------------------------------------------------------------------------------------------------------
        public string deleteEtud(string apogeAdminParam, string passwordParam, string NApogeParam)
        {

            //verifier l'auth est ce qu'un addmin
            Auth auth = new Auth();

            if (auth.verifierAuth(apogeAdminParam, passwordParam) == "admin")
            {
                using (SWDBEntities2 dbCnx = new SWDBEntities2())
                {
                    Personne persoDelete = dbCnx.Personnes.FirstOrDefault(personne => personne.NApoge == NApogeParam);

                    dbCnx.Personnes.Remove(persoDelete);
                    dbCnx.SaveChanges();
                }
            }

            return "gooooood";
        }


    }
}
