package WEB;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(); 

        if (session != null){
            
            session.invalidate(); 
            
            request.setAttribute("errMessage", "Déconnexion avec succes !");
            
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);

        }
    }
}
