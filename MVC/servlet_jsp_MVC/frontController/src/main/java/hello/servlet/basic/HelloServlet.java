package hello.servlet.basic;


import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet(name = "helloServlet",urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("response = " + response);
        System.out.println("request = " + request);

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        //content-type 헤더 정보에 들어가는것
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        //getWriter().write()해주면 http 메시지 바디에 데이터가 들어감
        response.getWriter().write("hello "+username);
    }
}
