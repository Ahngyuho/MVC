package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username = {},age = {}",username,age);

        response.getWriter().write("ok");
    }

    //위에 보다 더 개선된 것

    @RequestMapping("/request-param-v2")
    @ResponseBody
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge){

        log.info("username={},age={}",memberName,memberAge);
        //@Controller이면서 return이 String이면 ok라는 뷰를 찾게됨
        //@ResponseBody이걸 함수 단위에 붙여주면 응답메시지에 담아서 보내줄수있음
        return "ok";
    }

    @RequestMapping("/request-param-v3")
    @ResponseBody
    public String requestParamV3(
            @RequestParam("username") String username,
            @RequestParam("age") int age){

        log.info("username={},age={}",username,age);
        //@Controller이면서 return이 String이면 ok라는 뷰를 찾게됨
        //@ResponseBody이걸 함수 단위에 붙여주면 응답메시지에 담아서 보내줄수있음
        return "ok";
    }
}
