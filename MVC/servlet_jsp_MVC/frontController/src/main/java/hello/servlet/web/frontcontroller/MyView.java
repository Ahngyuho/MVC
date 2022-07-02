package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //이렇게 model에 있는 값을 request에 다 담아놓는다.
        //render하기 위해서는 jsp에서는 setAttribute에 값들을 담아놔야함
        modelToRequestAttribute(model, request);
        //위 코드 복붙 후 수정
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);
    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        //jsp는 여기서 값을 꺼내오기 때문에 setAttribute 필요함 다른 view template들은 다르다고 함
        model.forEach((key, value)-> request.setAttribute(key,value));
    }
}
