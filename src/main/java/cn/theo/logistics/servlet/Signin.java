package cn.theo.logistics.servlet;

import cn.theo.logistics.domain.Sign;
import cn.theo.logistics.service.SignRecordService;
import cn.theo.logistics.service.SignService;
import cn.theo.logistics.service.impl.SignRecordServiceImpl;
import cn.theo.logistics.service.impl.SignServiceImpl;
import cn.theo.logistics.utils.MapUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.List;

@WebServlet("/signin")
public class Signin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取签到id
        long signdate = (System.currentTimeMillis()/1000);
        long signid = Long.parseLong(request.getParameter("signid"));
        double  latitude = Double.parseDouble(request.getParameter("latitude"));
        double  longitude = Double.parseDouble(request.getParameter("longitude"));
        HttpSession session = request.getSession();
        String sno = (String)session.getAttribute("sno");
        String device = request.getParameter("device");
        SignService service = new SignServiceImpl();
        Sign sign = service.findSignById(signid);
        int duration = sign.getDuration();
        String json = null;
        double distance = MapUtil.getDistance(new Point2D.Double(longitude, latitude), new Point2D.Double(sign.getLongitude(), sign.getLatitude()));
        if(distance>(double)sign.getPrecise()){
            json ="{\"flag\":false,msg:\"你的位置超出签到允许范围\"}";
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSONObject.fromObject(json).toString());
            return;
        }
        if((signid + duration)>signdate){
            SignRecordService signRecordService = new SignRecordServiceImpl();
            int insert = signRecordService.insert(signid, sno, signdate, device,latitude,longitude);
            if(insert>=1){
                json = "{\"flag\":true,\"signdate\":" + signdate +",device:"+ "\"" + device +"\""+"}";
            }else{
                json = "{\"flag\":false,msg:\"该签到可能已经过期或被删除\"}";
            }
        }else{
            json = "{\"flag\":false,msg:\"该签到可能已经过期\"}";
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSONObject.fromObject(json).toString());


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
