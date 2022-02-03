using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.Compilation;
using System.Web.Services;
using System.Web.UI.HtmlControls;

namespace ServiceSOAP
{
    /// <summary>
    /// Description résumée de WebService1
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // Pour autoriser l'appel de ce service Web depuis un script à l'aide d'ASP.NET AJAX, supprimez les marques de commentaire de la ligne suivante. 
    // [System.Web.Script.Services.ScriptService]
    public class WebService1 : System.Web.Services.WebService
    {

      //  [WebMethod]
        public string HelloWorld()
        {
            return "Hello World";
        }




      //  [WebMethod]
        public string getModuleInsemestre(int id)
        {


            String name = "";
            String prenom = "";

            SqlConnection connexion = new SqlConnection();
            connexion.ConnectionString = ConfigurationManager.ConnectionStrings["DBconnexion"].ToString();
            connexion.Open();
            SqlCommand cmd = new SqlCommand("select Nom,Prenom from Personne where PersonneID=" + id + "", connexion);
           
            SqlDataReader dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                name = dr[0].ToString();
                prenom = dr[1].ToString();
            }
            connexion.Close();
            return name +""+prenom;
            



        }
      //  [WebMethod]
        public List<string> getNoteModulesSemestre(string id_personne,string semestre)


        {

            List<string> etudsANDNotesForModule = new List<string>();
            string apogePer = "";

           
            string idperso = "";
            string sem = "";
            string idMod = "";
            string note = "";
           

            SqlConnection connexion = new SqlConnection();
            connexion.ConnectionString = ConfigurationManager.ConnectionStrings["DBconnexion"].ToString();
            connexion.Open();
            SqlCommand cmd = new SqlCommand("select personneID,semestre,note from Info ", connexion);

            SqlDataReader dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                idperso = dr[0].ToString();
                sem = dr[1].ToString();
                //idMod = dr[2].ToString(); ;
                note= dr[2].ToString();
                apogePer = getPersonne(idperso); 
                if (idperso.Equals(id_personne) && semestre.Equals(sem))
                   
                {

                    etudsANDNotesForModule.Add(apogePer + "/"+ note);

                }
            }

            connexion.Close();
            return etudsANDNotesForModule;
         


        }
       // [WebMethod]
        public string getPersonneId(string apogee)

        {
            string resultat = "";

            string idperso = "";
            string apog = "";


            SqlConnection connexion = new SqlConnection();
            connexion.ConnectionString = ConfigurationManager.ConnectionStrings["DBconnexion"].ToString();
            connexion.Open();
            SqlCommand cmd = new SqlCommand("select personneID,NApoge from Personne ", connexion);

            SqlDataReader dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                idperso = dr[0].ToString();
                apog = dr[1].ToString();
                if (apogee.Equals(apog))
                {

                    resultat = idperso;

                }
            }

            connexion.Close();

            return resultat;


        }

        //[WebMethod]
        public string getPersonne(string id_personne)

        {
            string resultat = "";

            string idperso = "";
            string apog = "";


            SqlConnection connexion = new SqlConnection();
            connexion.ConnectionString = ConfigurationManager.ConnectionStrings["DBconnexion"].ToString();
            connexion.Open();
            SqlCommand cmd = new SqlCommand("select personneID,NApoge from Personne ", connexion);

            SqlDataReader dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                idperso = dr[0].ToString();
                apog = dr[1].ToString();
                if (id_personne.Equals(idperso))
                {

                    resultat = apog;

                }
            }

            connexion.Close();
            return resultat;
           


        }
        [WebMethod]
        public Double getMoyenneSemestre(string apogee,String semestre)

        {
            String idPers = "";
            List<string> EtudiantAvecNoteModule = new List<string>();
            idPers = getPersonneId(apogee);

            //EtudiantAvecNoteModule = getNoteModulesSemestre(idPers, semestre);
            EtudiantAvecNoteModule = getNoteModulesSemestreF(idPers, semestre);

            Double moyenne =0.0 ;
            Double moyenneSemestre = 0.0;
            for (int i = 0; i < EtudiantAvecNoteModule.Count; i++)
            {

                string[] infos = EtudiantAvecNoteModule[i].Split('/');
                
                string note = infos[2];
                 moyenne =moyenne+ Double.Parse(note);
            }
            moyenneSemestre = moyenne / EtudiantAvecNoteModule.Count;
            return moyenneSemestre;
        }
        //2. moyenneAnnée : fournit la moyenne d'une année ;
       // [WebMethod]
        
        public List<string> GetMoyenneAnneeS(string apogee, string annee)

        {

            string semes = "";
            string anne = "";
            List<string> EtudiantAvecNoteModule = new List<string>();
            List<string> semestres = new List<string>();

            string idPerso = "";
            idPerso = getPersonneId(apogee);
            string idP = "";

            SqlConnection connexion = new SqlConnection();
            connexion.ConnectionString = ConfigurationManager.ConnectionStrings["DBconnexion"].ToString();
            connexion.Open();
            SqlCommand cmd = new SqlCommand("select distinct Semestre,Annee,personneID from Info " , connexion);
            //SqlCommand cmd = new SqlCommand("select distinct Semestre from Info where Annee=" + annee, connexion);
            SqlDataReader dr = cmd.ExecuteReader();
            while (dr.Read())
            {

                semes = dr[0].ToString();
                anne = dr[1].ToString();
                idP= dr[2].ToString();
                if (anne.Equals(annee) && idP.Equals(idPerso))

                {

                    semestres.Add(semes);
                }

            }



            connexion.Close();


            return semestres;
        }
        [WebMethod]
        public Double GetMoyenneAnnee(string apogee, string annee, string semestre1, string semestre2)

        {
            String semes = "";
            List<string> EtudiantAvecNoteModule = new List<string>();
            List<string> listsemestreVoulu = new List<string>();

            Double moyenne1;
            Double moyenne2;
            Double moyenneAnne;

            EtudiantAvecNoteModule = GetMoyenneAnneeS(apogee, annee);
            for (int i = 0; i < EtudiantAvecNoteModule.Count; i++)
            {
                if (EtudiantAvecNoteModule[i].Equals(semestre1) || EtudiantAvecNoteModule[i].Equals(semestre2))
                {
                    listsemestreVoulu.Add(EtudiantAvecNoteModule[i]);

                }
            }

             moyenne1 = getMoyenneSemestre(apogee, listsemestreVoulu[0]);
              moyenne2 = getMoyenneSemestre(apogee, listsemestreVoulu[1]);
              moyenneAnne = (moyenne1 + moyenne2)/2;


            return moyenneAnne;
        }


        //moyenneMasterMention : fournit la moyenne du Master ainsi que la mention ;
       // [WebMethod]
        public List<string> GetMoyenneMasterMentions(string apogee)

        {
            String semes = "";
            List<string> EtudiantAvecNoteModule = new List<string>();
            List<string> semestres = new List<string>();

        
            String id = getPersonneId(apogee);
            SqlConnection connexion = new SqlConnection();
            connexion.ConnectionString = ConfigurationManager.ConnectionStrings["DBconnexion"].ToString();
            connexion.Open();
            SqlCommand cmd = new SqlCommand("select  distinct Semestre from Info where PersonneID= " + id + "", connexion);

            SqlDataReader dr = cmd.ExecuteReader();
            while (dr.Read())
            {

                semes = dr[0].ToString();
                semestres.Add(semes);
             
                }
            if (semestres.Count == 4)
            {
                for (int i = 0; i < semestres.Count; i++)
                {
                    EtudiantAvecNoteModule.Add(semestres[i]);
                }
            }
            else
                {

                    EtudiantAvecNoteModule.Add("information incomplet pour avoir la moyenne du master");
                }



                connexion.Close();
            
                return EtudiantAvecNoteModule;
        }
        [WebMethod]
        public string GetMoyenneMasterMention(string apogee)

        {
            String semes = "";
            List<string> EtudiantAvecNoteModule = new List<string>();
            List<string> semestres = new List<string>();

            Double moyenne1;
            Double moyenne2;
            Double moyenneAnne=0.0;
            double moyenne3;
            Double moyenne4;
            String Mention = "";
            EtudiantAvecNoteModule = GetMoyenneMasterMentions(apogee);
            if (EtudiantAvecNoteModule.Count < 4) {
                return moyenneAnne + "/" + Mention;

            } else { 
            string smstr1 = EtudiantAvecNoteModule[0];
            string smstr2 = EtudiantAvecNoteModule[1];
            string smstr3 = EtudiantAvecNoteModule[2];
            string smstr4 = EtudiantAvecNoteModule[3];
            moyenne1 = getMoyenneSemestre(apogee, smstr1);
            moyenne2 = getMoyenneSemestre(apogee, smstr2);
            moyenne3 = getMoyenneSemestre(apogee, smstr3);
            moyenne4 = getMoyenneSemestre(apogee, smstr4);
            moyenneAnne = (moyenne1 + moyenne2 + moyenne3 + moyenne4) / 4;
            if (moyenneAnne < 10)
            {
                Mention = "noValide";
            }
            if ((moyenneAnne > 10) && (moyenneAnne < 12))
            {
                Mention = "passable";
            }
            if ((moyenneAnne > 12) && (moyenneAnne < 14))
            {
                Mention = "assez bien";
            }
            if ((moyenneAnne > 14) && (moyenneAnne < 16))
            {
                Mention = " bien";
            }
            }

            return moyenneAnne +"/"+ Mention;
          
        }
        [WebMethod]
        public Boolean validationSemestre(String apogee,String semestre)
        {
            Boolean valideSemestre = false;
            String idPers = "";
            List<string> EtudiantAvecNoteModule = new List<string>();
            List<Double> NoteModule = new List<Double>();
            List<Double> NoteModuleValide = new List<Double>();


            idPers = getPersonneId(apogee);
             List<Double> NoteModuleNoValide = new List<Double>();

            EtudiantAvecNoteModule = getNoteModulesSemestre(idPers, semestre);
            Double moyenneSemestre = 0.0;
            int count = 0;
            int count1 = 0;

            for (int i = 0; i < EtudiantAvecNoteModule.Count; i++)
            {

                string[] infos = EtudiantAvecNoteModule[i].Split('/');

                Double note =Double.Parse( infos[1]);
                NoteModule.Add(note);

            }
           
            
            for (int j = 0; j < NoteModule.Count; j++)
            {
                if (NoteModule[j] < 7)
                {

                    NoteModuleNoValide.Add(NoteModule[j]);
                }


                if (NoteModule[j] >= 7)
                {
                    NoteModuleValide.Add(NoteModule[j]);

                }
            }

            for (int k = 0; k < NoteModuleNoValide.Count; k++)
            {

                if (NoteModuleNoValide[k] < 7)
                {
                    count1++;
                }
            }
                for (int k = 0; k < NoteModuleValide.Count; k++)
            {
                
                if( NoteModuleValide[k] < 10)
                {
                    count++;
                }

            }
           if ( NoteModule.Count==0)
            {
                valideSemestre = false;


            }
             else if (count > 1 || count1 > 0)
            {
                valideSemestre = false;
               
             
            }


            
            else
            {
                moyenneSemestre = getMoyenneSemestre(apogee, semestre);
                if (moyenneSemestre < 10)
                {
                    valideSemestre = false;


                }else
                {

                    valideSemestre = true;


                }

            }


            return valideSemestre;
           
        }

        public Boolean validationAnnee(String apogee, String semestre)
        {
            Boolean valideAnne = false;
            //pour que l anne soit valide ,il faut avoir 11/12 de notes supereir de 10 et uune seule note 7>note<10
            //get note semestre1
            //getnote semestre2
            //if semestre1 ===true and semestre2=true
            //if list.count2Semetre <12 do false
            //else true
            //
            


            return valideAnne;


        }


       // [WebMethod]
        public List<string> getNoteModulesSemestreF(string id_personne, string semestre)


        {

            List<string> notesFinalModules = new List<string>();
            List<string> top7Modules = new List<string>();

            Double moyenne = 0.0;
            Double moyenneSemestre = 0.0;

            List<string> etudsANDNotesForModule = new List<string>();
            //List<Double> top6Modules = new List<Double>();

            string apogePer = "";
            string nameModule = "";


            string idperso = "";
            string sem = "";
            string idMod = "";
            string note = "";
            string session = "";
            string etat = "";
            string annee = "";



            SqlConnection connexion = new SqlConnection();
            connexion.ConnectionString = ConfigurationManager.ConnectionStrings["DBconnexion"].ToString();
            connexion.Open();
            SqlCommand cmd = new SqlCommand("select personneID,semestre,note,moduleID,Etat,Session,Annee from Info ", connexion);

            SqlDataReader dr = cmd.ExecuteReader();

            while (dr.Read())
            {
                idperso = dr[0].ToString();
                sem = dr[1].ToString();
                
                note = dr[2].ToString();
                idMod= dr[3].ToString();

                etat = dr[4].ToString();
                session = dr[5].ToString();
                annee = dr[6].ToString();

                nameModule = getModule(idMod);
                apogePer = getPersonne(idperso);
                if (idperso.Equals(id_personne) && semestre.Equals(sem))

                {

                    etudsANDNotesForModule.Add(idperso + "/" + idMod  +"/"+sem +"/" + note + "/" + etat + "/" + session + "/"+ annee);
                    //traiter les notes de cet etudiant pour avoir seulement les notes final de ses 6 modules
                    
                }


               
                notesFinalModules = getNotesModulesValides(etudsANDNotesForModule);


            }

            connexion.Close();

            return notesFinalModules;
            



        }




       // [WebMethod]
        public string getModule(string id_module)

        {
            string resultat = "";

            string idmodule = "";
            string module = "";


            SqlConnection connexion = new SqlConnection();
            connexion.ConnectionString = ConfigurationManager.ConnectionStrings["DBconnexion"].ToString();
           connexion.Open();
            SqlCommand cmd = new SqlCommand("select moduleID,ModuleName from Module ", connexion);

            SqlDataReader dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                idmodule = dr[0].ToString();
                module = dr[1].ToString();
                if (id_module.Equals(idmodule))
                {

                    resultat = module;

                }
            }

            connexion.Close();

            return resultat;

        }


        public List<string> getNotesModulesValides(List<String> infoModules)
        {
            int annee = 0;
            List<Double> notepure = new List<Double>();

            List<string> notesFinal = new List<string>();//la liste return qui contient les notes final de chaque modules
            List<string> notesFinal1 = new List<string>();//la liste return qui contient les notes final de chaque modules

            //get chaque module name
            string idModule = "";
            string idPersonne = "";
            Double noteMax = 0.0;

            for (int i = 0; i < infoModules.Count; i++)
            {
                string[] infos = infoModules[i].Split('/');

                //Double note = Double.Parse(infos[1]);
                idModule = infos[1];
                idPersonne = infos[0];

                if (!notesFinal.Contains(idModule))
                {
                    //get all infos for this module

                    // List<String> infosNameModule = new List<String>();

                    List<String> infosNameModule = getInfoModulePersonne(idModule, idPersonne);



                    //traiter ces data
                    if (infosNameModule.Count == 1) //donc ce module validé au session normal
                    {

                        for (int j = 0; j < infosNameModule.Count; j++)
                        {
                            string[] infos1 = infosNameModule[j].Split('/');

                            //Double note = Double.Parse(infos[1]);


                            notesFinal.Add(idPersonne + "/" + idModule + "/" + infos1[3] + "/" + infos1[5]);

                        }
                    }

                    else
                    {

                        List<int> listAnne = new List<int>();

                        for (int j = 0; j < infosNameModule.Count; j++)
                        {
                            string[] infos2 = infosNameModule[j].Split('/');
                            listAnne.Add(int.Parse(infos2[5].Split('-')[0]));

                        }


                        //find l'année la plus recente

                        annee = listAnne.Max();

                        List<String> noteanne = getInfoModulePersonneAnnee(idModule, idPersonne, annee.ToString());
                        for (int j = 0; j < noteanne.Count; j++)
                        {

                            string[] infos1 = noteanne[j].Split('/');
                            if (infos1[3] == String.Empty)
                                infos1[3] = "0";
                            notepure.Add(Double.Parse(infos1[3]));

                        }
                        noteMax = notepure.Max();

                        notesFinal.Add(idPersonne + "/" + idModule + "/" + noteMax + "/" + annee);

                    }


                }

            }
            HashSet<string> seulNote = new HashSet<string>(notesFinal);
            notesFinal1 = new List<string>(seulNote);



            return notesFinal1;

        }
        //[WebMethod]
        public List<String> getInfoModulePersonne(string idModule,string idPersonne)
        {
            List<String> listeModules = new List<String>();
            


            string idperso = "";
            string sem = "";
            string idMod = "";
            string note = "";
            String annee = "";
            String etat = "";
            String session = "";
            SqlConnection connexion = new SqlConnection();
            connexion.ConnectionString = ConfigurationManager.ConnectionStrings["DBconnexion"].ToString();
            connexion.Open();
            SqlCommand cmd = new SqlCommand("select personneID, moduleID,semestre,Note,Etat,Annee,Session from Info ", connexion);

            SqlDataReader dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                idperso = dr[0].ToString();
                idMod = dr[1].ToString();
               
                sem = dr[2].ToString();
                note = dr[3].ToString();
                etat = dr[4].ToString();
                annee = dr[5].ToString();
                session = dr[6].ToString();
                
                if (idperso.Equals(idPersonne) && idMod.Equals(idModule))

                {

                    listeModules.Add(idperso + "/" + idMod + "/" + sem + "/" + note + "/" + etat + "/" + annee + "/" + session );

                }
            }

            connexion.Close();

            return listeModules;


        }

      //  [WebMethod]
        public List<String> getInfoModulePersonneAnnee(string idModule, string idPersonne, string ann)
        {
            List<String> listeModules = new List<String>();



            string idperso = "";
            string sem = "";
            string idMod = "";
            string note = "";
            String annee = "";
            String etat = "";
            String session = "";
            SqlConnection connexion = new SqlConnection();
            connexion.ConnectionString = ConfigurationManager.ConnectionStrings["DBconnexion"].ToString();
            connexion.Open();
            SqlCommand cmd = new SqlCommand("select personneID, moduleID,semestre,Note,Etat,Annee,Session from Info ", connexion);

            SqlDataReader dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                idperso = dr[0].ToString();
                idMod = dr[1].ToString();

                sem = dr[2].ToString();
                note = dr[3].ToString();
                etat = dr[4].ToString();
                annee = dr[5].ToString();
                session = dr[6].ToString();

                if (idperso.Equals(idPersonne) && idMod.Equals(idModule) && annee.Split('-')[0].Equals(ann))

                {

                    listeModules.Add(idperso + "/" + idMod + "/" + sem + "/" + note + "/" + etat + "/" + annee + "/" + session);

                }
            }

            connexion.Close();

            return listeModules;

        }

    }
}
