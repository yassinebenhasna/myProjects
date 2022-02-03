using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using iTextSharp.text;
using Sw1Rest.affichageEntities;

namespace Sw1Rest
{
    // REMARQUE : vous pouvez utiliser la commande Renommer du menu Refactoriser pour changer le nom d'interface "IService1" à la fois dans le code et le fichier de configuration.
    [ServiceContract]
    public interface IService1
    {
        [OperationContract]
        [WebInvoke(Method = "GET",
            //ResponseFormat = WebMessageFormat.Xml,
            BodyStyle = WebMessageBodyStyle.Bare,
            UriTemplate = "verify/{apogeParam}/{passParam}")
        ]
        string verify(string apogeParam, string passParam);


        //2. listeMasters : fourni la liste des masters (sous format XML) ;
        [OperationContract]
        [WebInvoke(Method = "GET",
            ResponseFormat = WebMessageFormat.Xml,
            BodyStyle = WebMessageBodyStyle.Wrapped,
            UriTemplate = "allMasters/{apogeParam}/{passParam}")
        ]
        List<Master> getAllMasters(string apogeParam, string passParam);



        //3. listeEtudiantsSemestre : pour un Master et un semestre donné, elle fournit la liste des étudiants inscrits durant le semestre donné (sous formats XML et PDF) ;
        [OperationContract]
        [WebInvoke(Method = "GET",
            ResponseFormat = WebMessageFormat.Xml,
            BodyStyle = WebMessageBodyStyle.Wrapped,
            UriTemplate = "infosSemestre/{masterParam}/{semestreParam}/{apogeParam}/{passParam}")
        ]
        List<EtudSemestre> getListeEtudiantsSemestre( string masterParam, string semestreParam, string apogeParam, string passParam);
        //----------------- PDF -------------------------
        [OperationContract]
        [WebInvoke(Method = "GET",
            ResponseFormat = WebMessageFormat.Xml,
            BodyStyle = WebMessageBodyStyle.Bare,
            UriTemplate = "infosSemestrePDF/{masterParam}/{semestreParam}/{apogeParam}/{passParam}")
        ]
        Stream getListeEtudiantsSemestrePDF(string masterParam, string semestreParam, string apogeParam, string passParam);



        //4. listeEtudiantsModule : pour un master et un semestre donné, pour chaque module, elle fournit la liste des étudiants avec leurs notes(sous formats XML et CSV);
        [OperationContract]
        [WebInvoke(Method = "GET",
            ResponseFormat = WebMessageFormat.Xml,
            BodyStyle = WebMessageBodyStyle.Wrapped,
            UriTemplate = "infosModule/{masterParam}/{semestreParam}/{apogeParam}/{passParam}")
        ]
        List<EtudModule> getListeEtudiantsModule(string masterParam, string semestreParam, string apogeParam, string passParam);


        //5. releveNotes : pour un étudiant donné, elle fournit un relevé des notes pour un semestre donnée(sous formats XML et PDF).
        [OperationContract]
        [WebInvoke(Method = "GET",
            ResponseFormat = WebMessageFormat.Xml,
            BodyStyle = WebMessageBodyStyle.Wrapped,
            UriTemplate = "rlv/{apogeParam}/{semestreParam}")
        ]
        EtudModule getRlv(string apogeParam, string semestreParam); 
        //----------------- PDF -------------------------
        [OperationContract]
        [WebInvoke(Method = "GET",
            ResponseFormat = WebMessageFormat.Xml,
            BodyStyle = WebMessageBodyStyle.Bare,
            UriTemplate = "rlvPDF/{apogeParam}/{semestreParam}")
        ]
        Stream getRlvPDF(string apogeParam, string semestreParam);


        //6. attestationReussite : pour un étudiant donné, elle fournit une attestation de réussite sous format PDF.
        [OperationContract]
        [WebInvoke(Method = "GET",
            ResponseFormat = WebMessageFormat.Xml,
            BodyStyle = WebMessageBodyStyle.Bare,
            UriTemplate = "att/{apogeParam}")
        ]
        Stream getAttestation(string apogeParam);

    }


}
