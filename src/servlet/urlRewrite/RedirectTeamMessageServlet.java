package servlet.urlRewrite;

import servlet.RegisterServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by whd on 2014/12/1.
 */
public class RedirectTeamMessageServlet extends RedirectServlet {
    public RedirectTeamMessageServlet() {
        super("/pages/front_end/messages.jsp");
    }
}
