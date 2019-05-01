package cn.theo.logistics.servlet;



import cn.theo.logistics.domain.User;
import cn.theo.logistics.service.UserService;
import cn.theo.logistics.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("js_code");
        System.out.println(code);
        String appId = "wx28d518e710bdeadb";
        String appSecret = "b301ed903459f1f2fb5f4e4a4865d3f9";
        String json = "";
        String temp = "";
        try {
            URL url = new URL("https://api.weixin.qq.com/sns/jscode2session?appid="+appId+"&secret="+appSecret
                    +"&js_code="+code+"&grant_type=authorization_code");
            InputStream in =url.openStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader bufr = new BufferedReader(isr);

            while ((temp = bufr.readLine()) != null) {
                json += temp;
            }
            bufr.close();
            isr.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.fromObject(json);
        UserService service = new UserServiceImpl();
        String sessionid = request.getSession().getId();
        HttpSession session = request.getSession();
        String openid = (String) jsonObject.get("openid");
        boolean checkBind = service.checkBind(openid);
        session.setAttribute("openid",openid);
        if (checkBind){
            User user = service.findUserInfo(openid);
            session.setAttribute("sno",user.getSno());
            session.setAttribute("sname",user.getSname());
            session.setAttribute("auth",user.getAuth());
            jsonObject.element("sno",user.getSno());
            jsonObject.element("sname",user.getSname());
            jsonObject.element("auth",user.getAuth());
        }else{
            jsonObject.element("sno","");
            jsonObject.element("sname","");
            jsonObject.element("auth","");}
        jsonObject.element("session",sessionid);
        session.setAttribute("openid",jsonObject.get("openid"));
        session.setAttribute("checkbind",checkBind);
        jsonObject.element("checkbind",checkBind);
        jsonObject.remove("openid");
        System.out.println(jsonObject);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonObject.toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

}
