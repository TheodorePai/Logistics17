package cn.theo.logistics.servlet;

import cn.theo.logistics.dao.SignRecordDao;
import cn.theo.logistics.domain.Sign;
import cn.theo.logistics.domain.Signrecord;
import cn.theo.logistics.service.SignRecordService;
import cn.theo.logistics.service.SignService;
import cn.theo.logistics.service.impl.SignRecordServiceImpl;
import cn.theo.logistics.service.impl.SignServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@WebServlet("/signincheck")
public class SigninCheck extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.判断当前是否有签到
        SignService service = new SignServiceImpl();
        List<Sign> signs = service.querySigns(System.currentTimeMillis()/1000);
        List<Sign> overdueSigns = service.queryOverdueSign();
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis()/1000);
        SignRecordService recordService = new SignRecordServiceImpl();
        JSONObject jsonObject = JSONObject.fromObject("{\"check\":\"\",\"size\":\"\"}");
        jsonObject.element("check",false);
        jsonObject.element("size",signs.size());
        jsonObject.element("overduesize",overdueSigns.size());
        for (int i = 0; i < signs.size(); i++) {
            JSONObject temp = JSONObject.fromObject("{\"sign\":\"\",signinfo:\"\"}");
            Sign sign = signs.get(i);
            Signrecord signInfo = recordService.findSignInfo(sign.getSignid());
            temp.element("sign",JSONObject.fromObject(sign));
            temp.element("signinfo",JSONObject.fromObject(signInfo));
            jsonObject.accumulate("signlist",temp);
        }
        for (int i = 0; i < overdueSigns.size(); i++) {
            JSONObject temp = JSONObject.fromObject("{\"sign\":\"\",signinfo:\"\"}");
            Sign sign = overdueSigns.get(i);
            Signrecord signInfo = recordService.findSignInfo(sign.getSignid());
            temp.element("sign",JSONObject.fromObject(sign));
            temp.element("signinfo",JSONObject.fromObject(signInfo));
            jsonObject.accumulate("overduelist",temp);
        }
        if (signs.size()>0){
            jsonObject.element("check",true);
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonObject.toString());
        //2.若有签到，当前签到状态
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
