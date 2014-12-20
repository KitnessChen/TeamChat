package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by whd on 2014/12/1.
 */
public class BaseServlet extends HttpServlet {
    private String jspLocation;

    public BaseServlet() {
        this.jspLocation = "/pages/front_end/404.jsp";
    }

    public BaseServlet(String jspLocation) {
        this.jspLocation = jspLocation;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = request.getParameter("action");
        response.setContentType("text/html;charset=utf-8");
        if (null == request.getParameter("action")) {
            request.getRequestDispatcher(jspLocation).forward(request, response);
        } else {
            try {
                Method method = this.getClass().getDeclaredMethod
                        (actionName + "Action", new Class[]{HttpServletRequest.class, HttpServletResponse.class});
                method.invoke(this, request, response);
                System.out.println(method.getName());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = request.getParameter("action");
        response.setContentType("text/html;charset=utf-8");
        try {
            Method method = this.getClass().getDeclaredMethod
                    (actionName + "Action", new Class[]{HttpServletRequest.class, HttpServletResponse.class});
            method.invoke(this, request, response);
            System.out.println(method.getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
