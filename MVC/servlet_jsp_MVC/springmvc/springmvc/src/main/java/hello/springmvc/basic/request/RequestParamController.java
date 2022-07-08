package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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
    @ResponseBody//이거 적어주면 ok라는 문자를 http응답 메시지 바디에 넣어줌
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

    @RequestMapping("/request-param-v4")
    @ResponseBody
    public String requestParamV4(String username, int age){

        log.info("username={},age={}",username,age);

        return "ok";
    }

    @RequestMapping("/request-param-required")
    @ResponseBody
    public String requestParamV5(
            //true면 username이 꼭 들어와야 함
            //false면 꼭 들어오지 않아도 됨
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age){

        log.info("username={},age={}",username,age);

        return "ok";
    }

    @RequestMapping("/request-param-default")
    @ResponseBody
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") int age){

        log.info("username={},age={}",username,age);

        return "ok";
    }

    //요청 파라미터를 아예 맵으로 받아서 조회 가능
    @RequestMapping("/request-param-map")
    @ResponseBody
    public String requestParamMap(@RequestParam Map<String,Object> paramMap){

        log.info("username={},age={}",paramMap.get("username"),paramMap.get("age"));

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username = {},age = {}",helloData.getUsername(),helloData.getAge());
        log.info("helloData = {}",helloData);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username = {},age = {}",helloData.getUsername(),helloData.getAge());
        log.info("helloData = {}",helloData);

        return "ok";
    }
}
