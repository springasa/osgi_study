package freeingo.helloword;

import org.osgi.framework.BundleContext;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by xiaofg on 16-11-20.
 */
public class PrintNameServlet extends HttpServlet {
    private static final long serialVersionUID = -9080875068147052401L;
    private final BundleContext context;

    public PrintNameServlet(BundleContext context) {
        super();
        this.context = context;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        System.out.println("Receive name: " + name);

        String s = "";
        if (name == null || "".equals(name.trim())) {
            s = "你没有输入名字";
        } else {
            s = "你的名字是：" + name.trim();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<html><title>Response</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        sb.append("<body>");
        sb.append(s);
        sb.append("</body></html>");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream(),"UTF-8"));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
