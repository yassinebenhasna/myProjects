
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="WEB.MODEL.Choix_moduleModel"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users Page</title>
        <%@include file="/include/css.jsp"%>
    </head>

    <% //si pas de session pour user , le rediriger vers page de login
        if ((session.getAttribute("user") == null)) {
    %>

    <jsp:forward page="login.jsp"></jsp:forward>

    <%  }%>

    <body>

        <%@include file="/include/header_user.jsp"%>

        <!--================Home Banner Area =================-->
        <section class="home_banner_area">
            <div class="box_1620">


<!--=================================================================================================================================================-->

    <%
        if(request.getAttribute("reponse_validation") == null){
            out.print("");

        }else{

            Choix_moduleModel ch_m = (Choix_moduleModel) request.getAttribute("reponse_validation");

%><h3 style="text-align: center;background-color: green;color: #000"><%out.print(ch_m.getValidationChoix());%></h3><%
        }
    
    %>

<h3 style="text-align: center;color: red;background-color: beige">Choisissez vos modules...</h3><br>


                <div class="container" >
                    <div class="row">
                        <div class="col-md-6 offset-3" >
                            <div class="panel panel-success">
                                <div class="panel-body">

                                    <form action="Cntrl_user?page=choisirModule" method="post" >
                                        
                                        <input type="hidden" name="user" value="<%=(String)session.getAttribute("user")%>"/>

                                        <div class="form-group">
                                            <label for="Module1">Module1 :</label><p/>
                                            <select name="Module1" id="Module1" class="form-control">
                                                <c:forEach items="${listeModules.listModules}" var="listeModules" >
                                                    <option value="${listeModules.ID_MODULE}">${listeModules.nom_mod}</option>
                                                </c:forEach>
                                            </select>
                                        </div><br/><br/>

                                        <div class="form-group">
                                            <label for="Module2">Module2 :</label><p/>
                                            <select name="Module2" id="Module2" class="form-control">
                                                <c:forEach items="${listeModules.listModules}" var="listeModules" >
                                                    <option value="${listeModules.ID_MODULE}">${listeModules.nom_mod}</option>
                                                </c:forEach>
                                            </select>
                                        </div><br/><br/>

                                        <div class="form-group">
                                            <label for="Module3">Module3 :</label><p/>
                                            <select name="Module3" id="Module3" class="form-control"/>
                                                <c:forEach items="${listeModules.listModules}" var="listeModules" >
                                                    <option value="${listeModules.ID_MODULE}">${listeModules.nom_mod}</option>
                                                </c:forEach>
                                            </select>
                                        </div><br/><br/>
                                        
                                        <div class="form-group">
                                            <input type="submit" value="Choisir" class=" btn btn-default">
                                        </div>
                                    </form> 


                                </div>
                            </div>                
                        </div>
                    </div>        
                </div>        




<!--=================================================================================================================================================-->






            </div>
        </section>
        <!--================End Home Banner Area =================-->

        <!--================Home Blog Area =================-->

        <!--================End Home Blog Area =================-->




        <%@include file="/include/footer.jsp"%>

    </body>

    <%@include file="/include/js.jsp"%>


</html>
