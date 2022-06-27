package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestParamServlet",urlPatterns = "/request-param")
public class requestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //전체 쿼리 파라미터 조회
        request.getParameterNames().asIterator().
                forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));

        String username = request.getParameter("username");
        System.out.println("username = " + username);
        String age = request.getParameter("age");
        System.out.println("age = " + age);

        //이름이 같은 복수 파라미터 조회
        String[] usernames = request.getParameterValues("username");
        for(String user : usernames){
            System.out.println("user = " + user);
        }
    }
}
