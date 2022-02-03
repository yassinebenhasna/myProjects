<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="WEB.MODEL.PersonneModel"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <%@include file="/include/css.jsp"%>

    </head>

    <% //si pas de session cree pour admin , le rediriger vers page de login
        if (session.getAttribute("admin") == null) {
    %>

    <jsp:forward page="../login.jsp"></jsp:forward>

    <%  } %>


    <body>

        <%@include file="/include/header_admin.jsp"%>

        <!--================Home Banner Area =================-->
        <section class="home_banner_area">
            <div class="box_1620">

                <!--=================================================================================================================================================-->



                <%
                    if (request.getAttribute("msgModel") == null) {
                        out.print("");

                    } else {

                        PersonneModel msg = (PersonneModel) request.getAttribute("msgModel");

                        out.print(msg.getValidationAjout());
                    }
                %>

                <h3 style="text-align: center;color: red;background-color: beige">Ajouter des enseignats...</h3><br>



                </head>


                <div class="container">
                    <div class="row">
                        <div class="col-md-5 offset-3">
                            <div class="panel panel-success">

                                <div class="panel-body">

                                    <form action="Cntrl_adm?page=ajouterEns" method="post" >

                                        <div class="form-group">
                                            <label for="nom">Nom:(*)</label>
                                            <input type="text" name="nom" id="nom" class="form-control" required="required">
                                            <span id="nom_manq"></span>
                                        </div>

                                        <div class="form-group">
                                            <label for="prenom">Prenom:(*)</label>
                                            <input type="text" name="prenom" id="prenom" class="form-control"required >
                                            <span id="prenom_manq"></span>
                                        </div>

                                        <div class="form-group">
                                            <label for="CIN">CIN:(*)</label>
                                            <input type="text" name="CIN" id="CIN" class="form-control"required>
                                            <span id="CIN_manq"></span>
                                        </div>

                                        <div class="form-group">
                                            <label for="email">Email:(*)</label>
                                            <input type="email" name="email" id="email" class="form-control"required>
                                            <span id="email_manq"></span>
                                        </div>

                                        <div class="form-group">
                                            <label for="tel">Tel:(*)</label>
                                            <input type="text" name="tel" id="tel" class="form-control"required>
                                            <span id="tel_manq"></span>
                                        </div>

                                        <div class="form-group">
                                            <label for="pass">Password:(*)</label>
                                            <input type="password" name="pass" id="pass" class="form-control"required>
                                            <span id="pass_manq"></span>
                                        </div>

                                        <div class="form-group">
                                            <label for="fct">Fonction:</label><p/>
                                            <select name="fct" id="fct" class="form-control"required>
                                                <c:forEach items="${listFct.listFct}" var="listFct" >
                                                    <option value="${listFct.ID_fct}">${listFct.nom_fct}</option>
                                                </c:forEach>
                                            </select>
                                            <span id="fct_manq"></span>
                                        </div><br/><br/>
                                        <div class="form-group">
                                            <label for="grd">Grade:</label><p/>
                                            <select name="grd" id="grd" class="form-control"required>
                                                <c:forEach items="${listGrd.listGrd}" var="listGrd" >
                                                    <option value="${listGrd.ID_GRADE}">${listGrd.NOM_GRADE}</option>
                                                </c:forEach>
                                            </select>
                                            <span id="grd_manq"></span>
                                        </div><br/><br/>
                                        <!--
                                                <div class="form-group">
                                                    <label for="etat">Etat :</label>
                                                    <select name="etat" id="etat" class="form-control">
                                        <c:forEach items="${listEtat.listEtat}" var="listEtat" >
                                            <option value="${listEtat.id_etat}">${listEtat.etat}</option>
                                        </c:forEach>
                                    </select><span></span>
                                </div>
                                        -->
                                        <div class="form-group">
                                            <input type="submit" value="Ajouter" class=" btn btn-default" id="ajouter"/>
                                        </div>

                                    </form> 
                                    <script>
                                        
                                        var validation = document.getElementById("ajouter");
                                        
                                        var nom = document.getElementById("nom");
                                        var nom_m = document.getElementById("nom_manq");
                                        var nom_v = /^[a-zA-Zéàè]/;
                                        
                                        var prenom = document.getElementById("prenom");
                                        var prenom_m = document.getElementById("prenom_manq");
                                        var prenom_v = /^[a-zA-Zéàè]/;
                                        
                                        var CIN = document.getElementById("CIN");
                                        var CIN_m = document.getElementById("CIN_manq");
                                        
                                        var email = document.getElementById("email");
                                        var email_m = document.getElementById("email_manq");
                                        var email_v = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
                                        
                                        var tel = document.getElementById("tel");
                                        var tel_m = document.getElementById("tel_manq");
                                        var tel_v = /^(0[1-68])(?:[ _.-]?(\d{2})){4}$/;
                                        
                                        var pass = document.getElementById("pass");
                                        var pass_m = document.getElementById("pass_manq");
                                        
                                        validation.addEventListener("click",f_valid);

                                        function f_valid(e){
                                            if(nom.validity.valueMissing){
                                                e.preventDefault();
                                                nom_manq.textContent = "nom manquant";
                                                nom_manq.style.color = "red";
                                            }else if(nom_v.test(nom.value) == false){
                                                event.preventDefault();
                                                nom_m.textContent = "format incorrect: seul les alphabets requis";
                                                nom_m.style.color = "red";
                                            }else{
                                                nom_manq.textContent = "";
                                                nom_m.textContent = "";
                                            }
                                            if(prenom.validity.valueMissing){
                                                e.preventDefault();
                                                prenom_manq.textContent = "prenom manquant";
                                                prenom_manq.style.color = "red";
                                            }else if(prenom_v.test(prenom.value) == false){
                                                event.preventDefault();
                                                prenom_m.textContent = "format incorrect: seul les alphabets requis";
                                                prenom_m.style.color = "red";
                                            }else{
                                                prenom_manq.textContent = "";
                                                prenom_m.textContent = "";
                                            }
                                            if(CIN.validity.valueMissing){
                                                e.preventDefault();
                                                CIN_manq.textContent = "CIN manquant";
                                                CIN_manq.style.color = "red";
                                            }else{
                                                CIN_manq.textContent = "";
                                            }
                                            if(email.validity.valueMissing){
                                                e.preventDefault();
                                                email_manq.textContent = "email manquant";
                                                email_manq.style.color = "red";
                                            }else if(email_v.test(email.value) == false){
                                                event.preventDefault();
                                                email_m.textContent = "format incorrect!";
                                                email_m.style.color = "red";
                                            }else{
                                                email_manq.textContent = "";
                                                email_m.textContent = "";
                                            }
                                            if(tel.validity.valueMissing){
                                                e.preventDefault();
                                                tel_manq.textContent = "tel manquant";
                                                tel_manq.style.color = "red";
                                            }else if(tel_v.test(tel.value) == false){
                                                event.preventDefault();
                                                tel_m.textContent = "format incorrect!";
                                                tel_m.style.color = "red";
                                            }else{
                                                tel_manq.textContent = "";
                                                tel_m.textContent = "";
                                            }
                                            if(pass.validity.valueMissing){
                                                e.preventDefault();
                                                pass_manq.textContent = "password manquant";
                                                pass_manq.style.color = "red";
                                            }else{
                                                pass_manq.textContent = "";
                                            }
                                        }
                                        
                                        
                                    </script>

                                </div>
                            </div>

                        </div>
                    </div>                
                </div>
            </div>        
        </div>           







        <!--=================================================================================================================================================-->              

    </div>
</section>
<!--================End Home Banner Area =================-->


<%@include file="/include/footer.jsp"%>

</body>

<%@include file="/include/js.jsp"%>


</html>
