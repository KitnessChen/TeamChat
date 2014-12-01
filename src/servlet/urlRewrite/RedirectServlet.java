package servlet.urlRewrite;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by whd on 2014/12/1.
 */
public class RedirectServlet extends HttpServlet {
    private String jspLocation;

    public RedirectServlet(String jspLocation) {
        this.jspLocation = jspLocation;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(jspLocation).forward(request, response);
    }
}
