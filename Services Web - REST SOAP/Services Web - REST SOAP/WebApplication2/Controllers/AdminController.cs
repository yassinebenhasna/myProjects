using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.Mvc;
using System.Web.UI.WebControls;
using WebApplication2.Models;

namespace WebApplication2.Controllers
{
    public class AdminController : Controller
    {

        CnxRequest req = new CnxRequest();

        public ActionResult Home()
        {

            //Session["role"] = "admin";

            //HttpCookie data = TempData["myCookie"] as HttpCookie;


            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString())
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

        //1. -------------------------------------//-------------------------------------//-------------------------------------//-------------------------------------

        public ActionResult AllMasters()
        {
            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString())
                {
                    string xmlData = req.sendRequest("58584", "Service1.svc", "allMasters/"+Session["apoge"]+"/" + Session["pass"], "GET");

                    return Content(xmlData, "text/xml", System.Text.Encoding.UTF8);
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


        //2. -------------------------------------//-------------------------------------//-------------------------------------//-------------------------------------
        public ActionResult EtudSem()
        {
            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString())
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
        public ActionResult EtudSemAffich(string master, string sem, string formEtudSem)
        {

            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString())
                {

                    if (formEtudSem.Equals("Afficher XML"))
                    {
                        string xmlData = req.sendRequest("58584", "Service1.svc", "infosSemestre/" + master + "/" + sem + "/" + Session["apoge"] + "/" + Session["pass"], "GET");
                        return Content(xmlData, "text/xml", System.Text.Encoding.UTF8);
                    }
                    else
                    {
                        Stream pdfData = req.sendRequest2("58584", "Service1.svc", "infosSemestrePDF/" + master + "/" + sem + "/" + Session["apoge"] + "/" + Session["pass"], "GET");
                        //byte[] byteArray = Encoding.ASCII.GetBytes(pdfData);
                        
                        //MemoryStream stream = new MemoryStream(byteArray);
                        //Stream stream2 = stream;

                        //StreamReader reader = new StreamReader(new MemoryStream(byt));
                        //string text = reader.ReadToEnd();

                        return File(pdfData, "application/pdf", "etudSemestre.pdf");
                    }
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


        //3. -------------------------------------//-------------------------------------//-------------------------------------//-------------------------------------
        public ActionResult EtudMod()
        {
            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString())
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
        public ActionResult EtudModAffich(string master, string sem)
        {

            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString())
                {

                   
                        string xmlData = req.sendRequest("58584", "Service1.svc", "infosModule/" + master + "/" + sem + "/" + Session["apoge"] + "/" + Session["pass"], "GET");
                        return Content(xmlData, "text/xml", System.Text.Encoding.UTF8);
                    
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


        //4. -------------------------------------//-------------------------------------//-------------------------------------//-------------------------------------
        public ActionResult Rlv()
        {
            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString())
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
        public ActionResult RlvAffich(string sem,string apoge, string formEtudSem)
        {

            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString())
                {

                    if (formEtudSem.Equals("Afficher XML"))
                    {
                        string xmlData = req.sendRequest("58584", "Service1.svc", "rlv/" + apoge + "/" + sem , "GET");
                        return Content(xmlData, "text/xml", System.Text.Encoding.UTF8);
                    }
                    else
                    {
                        Stream pdfData = req.sendRequest2("58584", "Service1.svc", "rlvPDF/" + apoge + "/" + sem, "GET");
                        //byte[] byteArray = Encoding.ASCII.GetBytes(pdfData);

                        //MemoryStream stream = new MemoryStream(byteArray);
                        //Stream stream2 = stream;

                        //StreamReader reader = new StreamReader(new MemoryStream(byt));
                        //string text = reader.ReadToEnd();

                        return File(pdfData, "application/pdf", "rlv.pdf");
                    }
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


        //5. -------------------------------------//-------------------------------------//-------------------------------------//-------------------------------------
        public ActionResult Att()
        {
            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString())
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
        public ActionResult AttAffich(string apoge)
        {

            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString())
                {

                    
                        Stream pdfData = req.sendRequest2("58584", "Service1.svc", "att/" + apoge , "GET");

                        Stream pdfData2 = pdfData;
                        //byte[] byteArray = Encoding.ASCII.GetBytes(pdfData);

                        //MemoryStream stream = new MemoryStream(byteArray);
                        //Stream stream2 = stream;

                        StreamReader reader = new StreamReader(pdfData2);
                        string text = reader.ReadToEnd();

                        if (text == "pas encore")
                        {
                            TempData["msg"] = "Ooops! Vous pouvez pas actuellement récupérer cette Attestation (un ou plusieurs semestre(s) ne sont pas validés)";
                            return RedirectToAction("Att", "Admin");
                        }


                    Stream pdfData3 = req.sendRequest2("58584", "Service1.svc", "att/" + apoge, "GET");

                    return File(pdfData3, "application/pdf", "attestation.pdf");
                        
                    
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



        public ActionResult LogOut()
        {
            Session.Clear();
            Response.Cookies.Clear();
            return RedirectToAction("Login", "Login");
        }

    }
}