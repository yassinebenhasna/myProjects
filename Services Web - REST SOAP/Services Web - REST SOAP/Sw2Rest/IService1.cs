using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace Sw2Rest
{
    // REMARQUE : vous pouvez utiliser la commande Renommer du menu Refactoriser pour changer le nom d'interface "IService1" à la fois dans le code et le fichier de configuration.
    [ServiceContract]
    public interface IService1
    {

        //modifier Password-------------------------------------------------------------------------------------------------------------------------

        [OperationContract]
        [WebInvoke(Method = "GET",
            RequestFormat = WebMessageFormat.Xml,
            ResponseFormat = WebMessageFormat.Xml,
            BodyStyle = WebMessageBodyStyle.Wrapped,
            UriTemplate = "updatePass/{nApogeParam}/{newPassParam}")
        ]
        string updatePass(string nApogeParam, string newPassParam);


        //modifier les notes d'un etudiant-----------------------------------------------------------------------------------------------------------
        [OperationContract]
        [WebInvoke(Method = "GET",
            RequestFormat = WebMessageFormat.Xml,
            ResponseFormat = WebMessageFormat.Xml,
            BodyStyle = WebMessageBodyStyle.Wrapped,
            UriTemplate = "updateNotes/{apogeAdminParam}/{passwordParam}/{apogeEtudParam}/{moduleParam}/{sessionParam}/{AnneeParam}/{newNote}")
        ]
        string updateNotes(string apogeAdminParam, string passwordParam, string apogeEtudParam, 
            string moduleParam, string sessionParam, string AnneeParam, string newNote);


        //ajouter un étudiant -----------------------------------------------------------------------------------------------------------------------
        [OperationContract]
        [WebInvoke(Method = "GET",
            RequestFormat = WebMessageFormat.Xml,
            ResponseFormat = WebMessageFormat.Xml,
            BodyStyle = WebMessageBodyStyle.Wrapped,
            UriTemplate = "insertEtud/{apogeAdminParam}/{passwordParam}/{NomParam}/{PrenomdParam}/{EmailParam}/{NApogeParam}/{CneParam}/{masterParam}")
        ]
        string insertEtud(string apogeAdminParam, string passwordParam, string NomParam, string PrenomdParam, string EmailParam,
            string NApogeParam, string CneParam, string masterParam);


        //supprimer un étudiant-----------------------------------------------------------------------------------------------------------------------
        [OperationContract]
        [WebInvoke(Method = "GET",
            RequestFormat = WebMessageFormat.Xml,
            ResponseFormat = WebMessageFormat.Xml,
            BodyStyle = WebMessageBodyStyle.Wrapped,
            UriTemplate = "deleteEtud/{apogeAdminParam}/{passwordParam}/{NApogeParam}")
        ]
        string deleteEtud(string apogeAdminParam, string passwordParam, string NApogeParam);

    }
}
