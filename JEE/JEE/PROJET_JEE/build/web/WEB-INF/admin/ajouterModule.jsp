<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="WEB.MODEL.ModuleModel"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <%@include file="/include/css.jsp"%>
    </head>

    <% //si pas de session cree pour admin , le rediriger vers page de login
        if(session.getAttribute("admin")== null) {
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
    if(request.getAttribute("moduleModel") == null){
        out.print("");
        
    }else{
        
        ModuleModel msg = (ModuleModel) request.getAttribute("moduleModel");
        
        out.print(msg.getValidationModule());
    }
%>

<h3 style="text-align: center;color: red;background-color: beige">Ajouter des Modules...</h3><br>

        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-3">
                    <div class="panel panel-success">
                        <div class="panel-body">

                            <form action="Cntrl_adm?page=ajouterModule" method="post" >

                                        <div class="form-group">
                                            <label for="Module">Module :(*)</label>
                                            <input type="text" name="Module" id="Module" class="form-control"required="required">
                                            <span id="Module_manq"></span>
                                        </div>

                                        <div class="form-group">
                                            <label for="Responsable">Responsable :</label><p/>
                                            <select name="Responsable" id="Responsable" class="form-control">
                                                <c:forEach items="${listNom.list}" var="listperso" >
                                                    <option value="${listperso.id_pers}">${listperso.nom}</option>
                                                </c:forEach>
                                            </select>
                                        </div><br/><br/>

                                        <div class="form-group">
                                            <label for="Semetre">Semetre :</label><p/>
                                            <select name="Semetre" id="Semetre" class="form-control">
                                                <c:forEach items="${listSem.listSemestre}" var="listSem" >
                                                    <option value="${listSem.ID_SEM}">${listSem.nom_sem}</option>
                                                </c:forEach>
                                            </select>
                                        </div><br/><br/>

                                        <div class="form-group">
                                            <label for="Type">Type :</label><p/>
                                            <select name="Type" id="Type" class="form-control">
                                                <c:forEach items="${listType.listType}" var="listType" >
                                                    <option value="${listType.ID_TYPE}">${listType.type_module}</option>
                                                </c:forEach>
                                            </select>
                                        </div><br/><br/>
                                                                                
                                        <div class="form-group">
                                            <label for="Filiere">Filiere :</label><p></p>
                                            <select name="Filiere" id="Filiere" class="form-control" >
                                                <c:forEach items="${listFil.listFiliere}" var="listFiliere" >
                                                    <option value="${listFiliere.ID_FIL}">${listFiliere.fil}</option>
                                                </c:forEach>
                                            </select>
                                        </div><br/><br/>

                                        <div class="form-group">
                                            <input type="submit" value="Ajouter" class=" btn btn-default" id="ajouter">
                                        </div>

                                    </form> 
                                    <script>
                                        
                                        var validation = document.getElementById("ajouter");
                                        
                                        var Module = document.getElementById("Module");
                                        var Module_m = document.getElementById("Module_manq");                                        

                                        
                                        validation.addEventListener("click",f_valid);

                                        function f_valid(e){
                                            if(Module.validity.valueMissing){
                                                e.preventDefault();
                                                Module_manq.textContent = "Module manquant";
                                                Module_manq.style.color = "red";
                                            }else{
                                                Module_manq.textContent = "";
                                            }

                                        }
                                        
                                    </script>

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
