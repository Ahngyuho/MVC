package hello.servlet.web.servlet;


import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//회원 등록용 html폼을 볼 수 있게 서브릿을 통해서
@WebServlet(name = "memberFormServlet",urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //메시지 바디의 타입 정해줘야 함 html임
        response.setContentType("text/html");
        //인코딩 정보 꼭 넣어주자
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        //여기에 html코드를 넣어야함 이렇게 자바 코드로 직접 넣어주어야 함
        w.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                " <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/servlet/members/save\" method=\"post\">\n" +
                " username: <input type=\"text\" name=\"username\" />\n" +
                " age: <input type=\"text\" name=\"age\" />\n" +
                " <button type=\"submit\">전송</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>\n");
    }
}
