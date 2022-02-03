<%-- 
    Document   : home
    Created on : 16 janv. 2021, 22:51:56
    Author     : yassine
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stemm-Project</title>
        <!--Meta-->
        <!--<meta charset="UTF-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="description" content="A complete landing page solution for any business">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        -->
        <!--Favicon-->
        <link rel="icon" href="/dev/static/assets/img/favicon/144x144.png">

        <!--Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Dosis:400,500,600,700%7COpen+Sans:400,600,700" rel="stylesheet">

        <!--Icon fonts-->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/template/style.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/template/font-awesome.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/template/style_1.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/template/bundle.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/template/style_2.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/template/prism.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/template/farasa.css">

    </head>


    <body id="top">


        <header>
            <div >
                <nav>
                    <a style="margin-right: 220px" href="/">

                        <span >
                            <img src="img/logo.svg" alt="" style="width: 300px;height: 130px">
                        </span>
                    </a>

                </nav>

            </div> <!-- END container-->
        </header> <!-- END header -->

    <!-- ********************************************************************** sec 1: Acceuil ************************************************************************* -->

        <section class="u-py-100 u-h-100vh u-flex-center" style="
                 background: #0b4537;
                 background-image: -webkit-linear-gradient(left, #0b4537 0%, #068b4F 100%);
                 background-image: -o-linear-gradient(left, #0b4537 0%, #068b4F 100%);
                 background-image: -webkit-gradient(linear, left top, right top, from(#0b4537), to(#068b4F));
                 background-image: linear-gradient(to right, #0b4537 0%, #068b4F 100%);
                 background-repeat: repeat-x;
                 filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF8B3AC6', endColorstr='#FF4D3399', GradientType=1);
                 ">


            <h1><%
                //List<String> fileContent = (List<String>) request.getAttribute("info");

                //for(String x : fileContent)
                //  out.println( x ); 

                %></h1>
            <!-- Set image background -->
            <img src="img/Farasa.png" alt="" style="width: 550px;height: 330px">

            <div class="container">

                <div class="row">
                    <div class="col-12 u-mt-80 text-center">

                        <h1 class="display-4 u-fw-600 text-white u-mb-40">
                            Farasa est un <span class="text-yellow" data-type="' Word Segmentation ', ' Word Lemmatization ', ' Part-Of-Speech Tagging ',
                                                ' Diacritization ', ' Spellchecker ', ' Named-entity Recognition ', ' Dependency Parsing ', ' Constituency Parsing ', ' Dialect Processing '"></span>
                            <span class="typed-curcor text-yellow">|</span> module
                        </h1>
                        <p class="u-fs-22 text-white u-lh-1_8 u-mb-40">
                            Farasa est le package complet à la pointe de la technologie pour traiter le traitement de la langue arabe. 
                            Il a été développé par <a href="http://alt.qcri.org/" target="_blank" class="text-yellow">Arabic
                                Language Technologies Group</a> au <a href="https://qcri.org.qa/" target="_blank"
                                                                  class="text-yellow">Qatar Computing Research Institute
                                (QCRI)</a>
                            <!--an elegant, modern and fully customizable App Landing, SaaS Landing, WebApp, SEO Landing, Startup Landing & Product Landing Multi-Purpose HTML template-->
                            Il dispose d'une API Web RESTful que vous pouvez utiliser via votre langage de programmation favorable.
                        </p>



                    </div> <!-- END col-lg-6-->
                </div> <!-- END row-->

            </div> <!-- END container-->
        </section> <!-- END intro-hero-->


    <!-- ********************************************************************** sec 2: trt file ************************************************************************* -->


        <section id="demo">
            <div class="container">
                <div class="row">

                    <div class="col-lg-6 mx-auto mt-5 text-center">
                        <h2 class="h1">
                            Traiter un fichier (TXT)
                        </h2>
                        <div class="u-h-4 u-w-50 bg-primary rounded mt-4 u-mb-40 mx-auto"></div>
                       
                        <h2 style="color: red;" class="h2"><c:out default="" value="<%=(String)request.getAttribute("msgFailed")%>"/></h2>
                        <h2 style="color: #007bff" class="h2"><c:out default="" value="<%=(String)request.getAttribute("msgSucc")%>"/></h2>
                        
                        <form action = "Upload" method = "post" enctype = "multipart/form-data">
                            <input type = "file" name = "file" size = "50" required/>
                            <br />
                            <br/>
                            <input class="btn  btn-dark  float-right" type = "submit" value = "Upload File" />
                        </form>
                        
                        <br/><br/><br/>
                        
                        <%
                                String x =  (String)request.getAttribute("msgSucc") ;
                        %>

                        <form action = "TrtFileController" method = "post" >                                                        
                            
                            <div class="row">
                                <%
                                    if(x == null || x.equals("")){
                                %>
                                <div class="col-lg-4 text-center mt-5 mx-auto">
                                    <input type="submit" class="btn btn-rounded btn-primary u-w-170 float-right" name="sub" value="Infos" disabled>
                                </div>
                                <div class="col-lg-0  mt-5 mx-auto" >
                                    <input type="submit" class="btn btn-rounded btn-primary u-w-170 float-right" name="sub" value="Analyser" disabled>
                                </div>
                                <div class="col-lg-auto  mt-5 mx-auto" >
                                    <input type="submit" class="btn btn-rounded btn-primary u-w-170 float-right fa fa-download" name="sub" value="Télécharger XML" disabled>
                                </div>
                                
                                <%}else{%>
                                <div class="col-lg-4 text-center mt-5 mx-auto">
                                    <input type="submit" class="btn btn-rounded btn-primary u-w-170 float-right" name="sub" value="Infos" >
                                </div>
                                <div class="col-lg-0  mt-5 mx-auto" >
                                    <input type="submit" class="btn btn-rounded btn-primary u-w-170 float-right" name="sub" value="Analyser" >
                                </div>
                                <div class="col-lg-auto  mt-5 mx-auto" >
                                    <input type="submit" class="btn btn-rounded btn-primary u-w-170 float-right fa fa-download" name="sub" value="Télécharger XML" >
                                </div>
                                
                                <%}%>
                            </div> 
                                <input type="hidden" name="fileName" value="<c:out default="" value="<%=(String)request.getAttribute("msgSucc")%>"/>"/>
                        </form>

                    </div>
                </div> <!-- END row-->
                
                

                <br/><br/>

            </div> <!-- END row-->
            
    </section> <!-- END section-->

    <section id="webapi" style="padding-top: 50px;">
        <div class="container">
            <div class="row">
                <div class="col-12 text-center">
                    <!--<h2 class="text-success u-fs-60">6+</h2>-->
                    <h2 class="text-success u-fs-40 ">
                        Détails:
                    </h2>
                    <div class="u-h-4 u-w-50 bg-primary rounded mt-4 u-mb-40 mx-auto"></div>

                </div>
            </div> <!-- END row-->
        </div> <!-- END container-->
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <ul class="nav nav-tabs justify-content-center tabs-v3 text-center u-ff-dosis u-fw-600 u-fs-20" role="tablist">
                        <li class="nav-item mx-2">
                            <div class="nav-link  p-4" data-toggle="tab"  role="tab">
                                <span class="u-fs-30 d-block mb-1" style="color: orange">Mots:</span>
                                <c:out default="" value="<%=(String)request.getAttribute("mots1")%>"/>
                            </div>
                        </li>
                        <li class="nav-item mx-2">
                            <div class="nav-link p-4" data-toggle="tab"  role="tab">
                                <span class="u-fs-30 d-block mb-1" style="color: orange">Phrases:</span>
                                <c:out default="" value="<%=(String)request.getAttribute("phrases1")%>"/>
                            </div>
                        </li>
                        <li class="nav-item mx-2">
                            <div class="nav-link  p-7" data-toggle="tab"  role="tab">
                                <span class="u-fs-30 d-block mb-1" style="color: orange">Moyenne mots/phrase:</span>
                                <c:out default="" value="<%=(String)request.getAttribute("moy1")%>"/>
                            </div>
                        </li>
                    </ul>
                </div> <!-- END col-12 -->

            </div> <!--END row-->
        </div> <!-- END container-->
    </section> <!-- END section-->


    <br/><br/>

    <!-- ********************************************************************** sec 3: txt manusc ************************************************************************* -->


    <section id="demo">
        <div class="container">
            <div class="row">

                <div class="col-lg-6 mx-auto mt-5 text-center">
                    <h2 class="h1">
                        Traiter un text 
                    </h2>
                    <div class="u-h-4 u-w-50 bg-primary rounded mt-4 u-mb-40 mx-auto"></div>
                    
                </div>
            </div> <!-- END row-->
            <form class="row" action="TrtTxtController" method="POST" name="textTrt">
                                    
                    <div class="col-lg-9 text-center mt-5 mx-auto">
                    <!-- <form > -->

                    <div class="form-group">
                        <!--<label for="rawtext">Example textarea</label>-->
                        <textarea class="form-control u-rounded-15 p-3 u-mb-30" id="raw-text" name="text" rows="4" placeholder="أدخل النص هنا..." style = "direction : rtl;"  required><c:out default="" value="<%=(String)request.getAttribute("req")%>"/></textarea>
                    </div>


                <div class="row">
                    <div class="col-lg-2 text-center mt-5 mx-auto">
                        <input type="submit" class="btn btn-rounded btn-primary u-w-170 float-right" name="sub" value="Infos" />                        
                    </div>
                    <div class="col-lg-0  mt-5 mx-auto" >
                        <input type="submit" class="btn btn-rounded btn-primary u-w-170 float-right" name="sub" value="Analyser"/>
                    </div>
                    <div class="col-lg-0  mt-5 mx-auto" >
                        <input type="submit" class="btn btn-rounded btn-primary u-w-170 float-right" name="sub" value="Télécharger XML"/>
                    </div>
                    <!-- </form> -->
                </div> <!-- END col-lg-7 -->
                
                    <!-- </form> -->
                </div> <!-- END col-lg-7 -->       
                                   
                                       
            </form> <!-- END row-->
            
            <br/><br/><br/>

        </div> <!-- END container-->
    </section> <!-- END section-->

    <section id="webapi" style="padding-top: 50px;">
        <div class="container">
            <div class="row">
                <div class="col-12 text-center">
                    <!--<h2 class="text-success u-fs-60">6+</h2>-->
                    <h2 class="text-success u-fs-40 ">
                        Détails:
                    </h2>
                    <div class="u-h-4 u-w-50 bg-primary rounded mt-4 u-mb-40 mx-auto"></div>

                </div>
            </div> <!-- END row-->
        </div> <!-- END container-->
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <ul class="nav nav-tabs justify-content-center tabs-v3 text-center u-ff-dosis u-fw-600 u-fs-20" role="tablist">
                        <li class="nav-item mx-2">
                            <div class="nav-link  p-4" data-toggle="tab"  role="tab">
                                <span class="u-fs-30 d-block mb-1" style="color: orange">Mots:</span>
                                <c:out default="" value="<%=(String)request.getAttribute("mots")%>"/>
                            </div>
                        </li>
                        <li class="nav-item mx-2">
                            <div class="nav-link p-4" data-toggle="tab"  role="tab">
                                <span class="u-fs-30 d-block mb-1" style="color: orange">Phrases:</span>
                                <c:out default="" value="<%=(String)request.getAttribute("phrases")%>"/>
                            </div>
                        </li>
                        <li class="nav-item mx-2">
                            <div class="nav-link  p-7" data-toggle="tab"  role="tab">
                                <span class="u-fs-30 d-block mb-1" style="color: orange">Moyenne mots/phrase:</span>
                                <c:out default="" value="<%=(String)request.getAttribute("moy")%>"/>
                            </div>
                        </li>
                    </ul>
                </div> <!-- END col-12 -->

            </div> <!--END row-->
        </div> <!-- END container-->
    </section> <!-- END section-->
    <br/><br/>

    <!-- ********************************************************************** sec 4: team ************************************************************************* -->

    
    <section id="contact">
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h2 class="text-success u-fs-60">Equipe</h2>
                <div class="u-h-4 u-w-50 bg-primary rounded mt-4 u-mb-70 mx-auto"></div>

            </div>
        </div> <!-- END row-->
    </div> <!-- END container-->
    <div class="container">
        
        <div class="row">
            <h2 style="padding-left: 110px">Réalisé par :</h2>
            <div class="col-xl-5"></div>
            <h2 style="padding-left: 170px">Encadré par :</h2>
        </div>
        
        <br/><br/>
        
  <div class="row">
    <!-- Team Member 1 -->
    <div class="col-xl-4 col-md-6 mb-4">
      <div class="card border-0 shadow">
        <img src="img/yassine.jpg" class="card-img-top rounded-circle" alt="..." style="width: 250px;height: 250px;margin:auto;border:2px dashed green !important;">
        <div class="card-body text-center">
          <h5 class="card-title mb-0">Yassine BENHASNA</h5>
          <div class="card-text text-black-50">Master Etudiant</div>
        </div>
      </div>
    </div>
    
    
    <div class="col-xl-4"></div>
    
    <div class="col-xl-4 col-md-6 mb-4">
      <div class="card border-0 shadow">
        <img src="http://mazroui.oujda-nlp-team.net/wp-content/uploads/2016/02/cv-mazroui.png" class="card-img-top rounded-circle" alt="..." style="width: 250px;height: 250px;margin:auto;border:2px dashed green !important;">
        <div class="card-body text-center">
          <h5 class="card-title mb-0">Azzeddine MAZROUI</h5>
          <div class="card-text text-black-50">Professor</div>
        </div>
      </div>
    </div>
    <!-- Team Member 2 -->

  </div>
        
      <div class="row">
    <!-- Team Member 1 -->
    <div class="col-xl-4 col-md-6 mb-4">
      <div class="card border-0 shadow">
        <img src="img/med3.jpeg" class="card-img-top rounded-circle" alt="..." style="width: 250px;height: 250px;margin:auto;border:2px dashed green !important;">
        <div class="card-body text-center">
          <h5 class="card-title mb-0">Mohamed KACHOUTI</h5>
          <div class="card-text text-black-50">Master Etudiant</div>
        </div>
      </div>
    </div>
<div class="col-xl-4"></div>
    <div class="col-xl-4 col-md-6 mb-4">
      <div class="card border-0 shadow">
        <img src="img/no.jpg" class="card-img-top rounded-circle" alt="..." style="width: 250px;height: 250px;margin:auto;border:2px dashed green !important;">
        <div class="card-body text-center">
          <h5 class="card-title mb-0">Ibtissam TOUAHRI</h5>
          <div class="card-text text-black-50">Ph.D.candidate</div>
        </div>
      </div>
    </div>
    <!-- Team Member 2 -->

  </div>
  <!-- /.row -->

</div>
</section>

    <!-- ********************************************************************** Foooooooter ************************************************************************* -->

    <footer>
        <section class="" style="background-color: #065b48" id = "footer-area">
            <div class="container">
                <div class="row " >

                    <div class="col-lg-7 col-md-6 mb-5 mt-5">
                        <img src="/dev/static/assets/img/logo-yellow.png" alt="">
                        <p class="u-my-40 text-white">
                            Farasa is the state-of-the-art full-stack package to deal with Arabic Language Processing. It
                            has been
                            developed by <a href="http://alt.qcri.org/" target="_blank" class="text-yellow">Arabic
                                Language Technologies Group</a> at <a href="https://qcri.org.qa/" target="_blank"
                                                                  class="text-yellow">Qatar Computing Research Institute
                                (QCRI)</a>
                            <!--an elegant, modern and fully customizable App Landing, SaaS Landing, WebApp, SEO Landing, Startup Landing & Product Landing Multi-Purpose HTML template-->
                            It has a <a href="#">RESTful Web API</a> that you can use through your favorable programming
                            language.
                        </p>

                    </div>
                    <div class="col-lg-2 col-md-6 mb-5 mt-5"> </div>



                    <div class="col-lg-3 col-md-6 mb-5 mt-5 ml-auto">
                        <h4 class="text-white">Contact Info</h4>
                        <div class="u-h-4 u-w-50 bg-yellow rounded mt-3 u-mb-40"></div>
                        <ul class="list-unstyled text-white">
                            <li class="mb-3">
                                <span class="icon icon-Phone2 text-yellow mr-2"></span> Phone :+212 536500601/2
                            </li>
                            <li class="mb-3">
                                <span class="icon icon-Phone2 text-yellow mr-2"></span> Fax :+212 536500603
                            </li>
                            <li class="mb-3">
                                <span class="icon icon-Mail text-yellow mr-2"></span> BV Mohammed VI - BP 717 Oujda 60000
                            </li>
                            <li class="mb-3">
                                <span class="icon icon-Pointer text-yellow mr-2"></span> Maroc, Oujda
                            </li>
                        </ul>
                        <ul class="list-inline social social-rounded mt-4">
                            <li class="list-inline-item">
                                <a href=""><i class="fa fa-facebook"></i></a>
                            </li>
                            <li class="list-inline-item">
                                <a href=""><i class="fa fa-twitter"></i></a>
                            </li>
                            <li class="list-inline-item">
                                <a href=""><i class="fa fa-google-plus"></i></a>
                            </li>
                            <li class="list-inline-item">
                                <a href=""><i class="fa fa-linkedin"></i></a>
                            </li>
                        </ul>
                    </div>
                </div> <!-- END row-->
            </div> <!-- END container-->
        </section> <!-- END section-->

        <section class="u-py-40 " style="background-color: #0a4436;">
            <div class="container">
                <p class="mb-0 text-center text-white">
                   FSO-M2I &copy;<script>document.write(new Date().getFullYear());</script>, All rights reserved. </p>
            </div>
        </section>
    </footer> <!-- END footer-->


    <div class="scroll-top bg-white box-shadow-v1">
        <i class="fa fa-angle-up" aria-hidden="true"></i>
    </div>



    <script src="<%=request.getContextPath()%>/template/bundle.js"></script>
    <script src="<%=request.getContextPath()%>/template/bundle_1.js"></script>
    <script src="<%=request.getContextPath()%>/template/bundle_2.js"></script>
    <script src="<%=request.getContextPath()%>/template/bundle_3.js"></script>
    <script src="<%=request.getContextPath()%>/template/bundle_4.min.js"></script>




</body>
</html>