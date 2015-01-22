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
 * <p>对于这个项目里面所有的Servlet都使用这个类作为基类</p>
 * <p>实现了对于jsp文件名的封装，地址栏呈现的不是带有jsp后缀的地址而是带有功能性描述的域名，比如/team</p>
 * <p>通过java的反射机制实现了通过request数据中的action域来调用对应处理方法的功能，这样比起if等方式更加优美</p>
 */
public class BaseServlet extends HttpServlet {
    private String jspLocation;

    /**
     * 如果构造函数中没有指定jsp文件路径的话默认跳转到404页面
     */
    public BaseServlet() {
        this.jspLocation = "/pages/front_end/404.jsp";
    }

    /**
     *
     * @param jspLocation 与该域名对应的jsp文件路径
     */
    public BaseServlet(String jspLocation) {
        this.jspLocation = jspLocation;
    }

    /**
     * 把get请求根据参数里面的action域提交给相应的以Action为后缀的函数处理
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
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

    /**
     * 把post请求根据参数里面的action域提交给相应的以Action为后缀的函数处理
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
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
