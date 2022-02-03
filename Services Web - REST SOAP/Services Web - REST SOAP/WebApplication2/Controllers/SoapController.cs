using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.Mvc;
using WebApplication2.SWSoap;

namespace WebApplication2.Controllers
{
    public class SoapController : Controller
    {
        
        SWSoap.WebService1 ser = new SWSoap.WebService1();

        [HttpPost]
        public ActionResult SemMoy(string apoge, string sem)
        {
            double res = ser.getMoyenneSemestre(apoge, sem);

            string res2 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<string>" + res.ToString()+"</string>";

            return Content(res2,"text/xml", System.Text.Encoding.UTF8);
        }




        [HttpPost]
        public ActionResult AnnMoy(string apoge, string annee, string sem1, string sem2)
        {
            double res = ser.GetMoyenneAnnee(apoge, annee, sem1, sem2);

            string res2 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<string>" + res.ToString() + "</string>";

            return Content(res2, "text/xml", System.Text.Encoding.UTF8);
        }




        [HttpPost]
        public ActionResult MasterMoy(string apoge)
        {
            string res = ser.GetMoyenneMasterMention(apoge);

            string res2 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<string>" + res + "</string>";

            return Content(res2, "text/xml", System.Text.Encoding.UTF8);
        }



        [HttpPost]
        public ActionResult VerfValidSem(string apoge, string sem)
        {
            bool res = ser.validationSemestre(apoge, sem);

            string res2 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<string>" + res.ToString() + "</string>";

            return Content(res2, "text/xml", System.Text.Encoding.UTF8);
        }
    }
}
