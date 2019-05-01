package cn.theo.logistics.servlet;

import cn.theo.logistics.domain.User;
import cn.theo.logistics.service.UserService;
import cn.theo.logistics.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/bind")
public class BindSno extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(user.getPid());
        UserService service = new UserServiceImpl();
        System.out.println(request.getSession().getId());
        System.out.println(request.getSession().getAttribute("openid"));
        boolean res = service.bindsno(user, (String) request.getSession().getAttribute("openid"));
        String json = "";
        if(res==true){
            json = "{\"flag\":\"1\",\"msg\":\"绑定成功\"}";
        }else {
            json = "{\"flag\":\"0\",\"msg\":\"绑定失败，请检查输入\"}";
        }

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
