package hello.servlet.web.servlet.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//컨트롤러 단지 form을 띄우는 역할을 함 그냥 jsp만 불러오면 됨
@WebServlet(name = "mvcMemberFormServlet",urlPatterns = "/servlet-mvc/members/new-form")
public class MVCMemberFormServlet extends HttpServlet {

    //mvc패턴은 항상 컨트롤러를 거쳐서 뷰로 들어가야함 고객의 요청이 오면 이게 실행되느 것임
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //이 serviec가 이 아래의 jsp 경로를 호출
        String viewPath = "/WEB-INF/views/new-form.jsp";
        //이건 컨트롤러에서 뷰로 이동할 때 사용하는 것임
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);//이 경로로 이동
        //이걸 해주면 서블릿에서 jsp를 호춯할  수 있음 jsp를 찾아서 호출함 서버에서 내부끼리 호출 그러니까 제어권을 넘겨준다는 것이 이것임
        //이제 jsp를 만들자
        dispatcher.forward(request,response);
    }
}
