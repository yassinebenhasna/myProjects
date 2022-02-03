<!--================Header Menu Area =================-->
<header class="header_area">
    <div class="main_menu">
        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <a class="navbar-brand logo_h" href="<%=request.getContextPath()%>/Cntrl_user?page=user_home"><img src="img/logo-FSO.png" alt="" style="width: 150px;"></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                    <ul class="nav navbar-nav menu_nav ml-auto">
                        
                        <li class="nav-item active"><a class="nav-link" href="<%=request.getContextPath()%>/Cntrl_user?page=user_home"><i class="fa fa-home">  Home</i></a></li> 
                        
                        <li class="nav-item submenu dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Enseignant</a>
                            <ul class="dropdown-menu">
                                <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/Cntrl_user?page=afficherEns">Afficher</a>
                            </ul>
                        </li> 
                        
                        <li class="nav-item submenu dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Module</a>
                            <ul class="dropdown-menu">
                                <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/Cntrl_user?page=afficherModule">Afficher</a>
                                <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/Cntrl_user?page=choisirModule">Choisir</a>
                            </ul>
                        </li> 

                        
                        <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/logout"><i class="fa fa-sign-out">  Déconnexion</i></a></li>
                        <li ></li>
                        <li class="nav-item submenu dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-cog">  Paramètres</i></a>
                            <ul class="dropdown-menu">
                                <li class="nav-item"><a class="nav-link"href="<%=request.getContextPath()%>/Cntrl_user?page=modif_compte"><i class="fa fa-pencil fa-fw"></i> Compte</a></li>
                            </ul>
                        </li>
                    </ul>
                </div> 
            </div>
                        <p class="nav-item"style="color: #10ddd6;    padding-top: 15px; padding-right: 35px;"><i class="fa fa-user"> Bienvenue, <%=session.getAttribute("user")%></i></p>
        </nav>
    </div>
</header>
<!--================Header Menu Area =================-->