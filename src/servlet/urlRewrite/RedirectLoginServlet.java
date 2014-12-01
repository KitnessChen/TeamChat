package servlet.urlRewrite;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by whd on 2014/11/30.
 */
public class RedirectLoginServlet extends RedirectServlet {
    public RedirectLoginServlet() {
        super("/pages/front_end/login.jsp");
    }
}
