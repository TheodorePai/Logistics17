package cn.theo.logistics.servlet;

import cn.theo.logistics.domain.Sign;
import cn.theo.logistics.service.SignService;
import cn.theo.logistics.service.impl.SignServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Map;

@WebServlet("/startsignin")
public class Startsignin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        HttpSession session = request.getSession();
        int auth = (int) session.getAttribute("auth");
        Sign sign = new Sign();
        String json = "";
        response.setContentType("application/json;charset=utf-8");
        if(auth>2){
            json = "{\"flag\":\"0\",\"msg\":\"发起失败，权限不足\"}";
            response.getWriter().write(json);
        }else {
        String sno = (String)session.getAttribute("sno");
        long timestamp = (Calendar.getInstance().getTimeInMillis()/1000);
        try {
            BeanUtils.populate(sign,parameterMap);
            sign.setStarttime(timestamp);
            sign.setSno(sno);
            sign.setSignid(timestamp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        SignService service = new SignServiceImpl();
        boolean flag = service.startSign(sign);
        if(flag){
            json = "{\"flag\":\"1\",\"msg\":\"签到发起成功\"}";
        }else {
            json = "{\"flag\":\"0\",\"msg\":\"签到发起失败，未知原因\"}";
        }
        response.getWriter().write(json);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
