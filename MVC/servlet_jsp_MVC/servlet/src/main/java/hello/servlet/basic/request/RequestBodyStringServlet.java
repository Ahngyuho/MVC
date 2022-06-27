package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet" ,urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //이렇게 메시지 바디의 내용을 바이트 코드 형태로 바로 얻을 수 있음
        ServletInputStream inputStream = request.getInputStream();
        //바이트 코드를 String형태로 바꿔주는 spring에서 제공하는 utility class 사용
        //그리고 바이트를 문자로 변활할 때는 인코딩 정보를 알려주어야 함 그 역도 마찬가지
        String MessageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("MessageBody = " + MessageBody);
        response.getWriter().write("ok");
    }
}
