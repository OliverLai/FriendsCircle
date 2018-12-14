package test3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;

public class Publish extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.getWriter().write("nihao");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String date = df.format(new Date());
		PrintWriter pw = response.getWriter();
		String str = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
		Users user = JSONObject.parseObject(str, Users.class);
		user.setDate(date);
		String img = user.getPicUrl();
		if(!img.equals("")) {
			user.setPicUrl(TextUtils.imgSave(user.getPic(), img.substring(img.lastIndexOf("."))));
		}else {
			user.setPicUrl("");
		}
		if(UsersDao.pub(user)) {
			pw.write("1");
		}else {
			pw.write("0");
		}
		pw.flush();
		pw.close();
		return;
	}

}
