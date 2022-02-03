using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using WebApplication2.Models;

namespace WebApplication2.Controllers
{
    public class Rest2Controller : Controller
    {
        CnxRequest req = new CnxRequest();


        // GET: Rest2
        public ActionResult UpdatePassPage()
        {
            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString() && Session["role"] == "admin")
                {
                    return View();
                }
                else
                {
                    return RedirectToAction("Login", "Login");
                }


            }
            catch (Exception ex)
            {
                return RedirectToAction("Login", "Login");
            }
        }
        [HttpPost]
        public ActionResult UpdatePass(string apoge, string newPass)
        {
            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString() && Session["role"] == "admin")
                {
                    string xmlData = req.sendRequest("58241", "Service1.svc", "updatePass/" + apoge + "/" + newPass, "GET");
                    TempData["Succes"] = "Le mot de passe a été bien modifié";
                    return RedirectToAction("Home", "Admin");
                }
                else
                {
                    return RedirectToAction("Login", "Login");
                }


            }
            catch (Exception ex)
            {
                return RedirectToAction("Login", "Login");
            }
        }









        /*updateNotes/{apogeAdminParam
        }/{passwordParam

        }/{apogeEtudParam}/
        
        {moduleParam}/{sessionParam}/{AnneeParam}/{newNote}*/

        public ActionResult UpdateNotePage()
        {
            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString() && Session["role"] == "admin")
                {
                    return View();
                }
                else
                {
                    return RedirectToAction("Login", "Login");
                }


            }
            catch (Exception ex)
            {
                return RedirectToAction("Login", "Login");
            }
        }
        [HttpPost]
        public ActionResult UpdateNote(string apogeEtud, string infos, string newNote)
        {
            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString() && Session["role"] == "admin")
                {
                    string module = infos.Split('/')[1]; 
                    string session = infos.Split('/')[2];
                    string annee = infos.Split('/')[3];

                    string xmlData = req.sendRequest("58241", "Service1.svc", "updateNotes/" + Session["apoge"] + "/" + Session["pass"]
                                                                              + "/" + apogeEtud + "/" + module + "/" + session + "/" + annee + "/" + newNote, "GET");

                    return RedirectToAction("Home", "Admin");
                }
                else
                {
                    return RedirectToAction("Login", "Login");
                }


            }
            catch (Exception ex)
            {
                return RedirectToAction("Login", "Login");
            }
        }











        public ActionResult AddEtudPage()
        {
            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString() && Session["role"] == "admin")
                {
                    return View();
                }
                else
                {
                    return RedirectToAction("Login", "Login");
                }


            }
            catch (Exception ex)
            {
                return RedirectToAction("Login", "Login");
            }
        }
        [HttpPost]
        public ActionResult AddEtud(string nom, string prenom, string email, string apoge, string cne, string master)
        {

            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString() && Session["role"] == "admin")
                {

                    string xmlData = req.sendRequest("58241", "Service1.svc", "insertEtud/" + Session["apoge"] + "/" + Session["pass"]
                                                                              + "/" + nom + "/" + prenom + "/" + email + "/" + apoge + "/" + cne + "/" + master, "GET");

                    TempData["Succes"] = "L'étudiant a été bien ajouté";
                    return RedirectToAction("Home", "Admin");
                }
                else
                {
                    return RedirectToAction("Login", "Login");
                }


            }
            catch (Exception ex)
            {
                return RedirectToAction("Login", "Login");
            }
        }



        public ActionResult DeleteEtudPage()
        {
            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString() && Session["role"] == "admin")
                {
                    return View();
                }
                else
                {
                    return RedirectToAction("Login", "Login");
                }


            }
            catch (Exception ex)
            {
                return RedirectToAction("Login", "Login");
            }
        }
        [HttpPost]
        public ActionResult DeleteEtud(string apogeEtud)
        {

            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString() && Session["role"] == "admin")
                {

                    string xmlData = req.sendRequest("58241", "Service1.svc", "deleteEtud/" + Session["apoge"] + "/" + Session["pass"]
                                                                              + "/" + apogeEtud , "GET");

                    TempData["Succes"] = "L'étudiant a été bien supprimé";
                    return RedirectToAction("Home", "Admin");
                }
                else
                {
                    return RedirectToAction("Login", "Login");
                }


            }
            catch (Exception ex)
            {
                return RedirectToAction("Login", "Login");
            }
        }




    }
    
}