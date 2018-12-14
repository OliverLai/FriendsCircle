package test3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public class Register extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		String str = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
		Users user = JSONObject.parseObject(str, Users.class);
		String img = user.getIconUrl();
		if(!UsersDao.findUserByName(user.getUser())) {
			user.setIconUrl(TextUtils.imgSave(user.getIcon(), img.substring(img.lastIndexOf("."))));
			if(UsersDao.reg(user)) {
				pw.write("1");
			}else {
				pw.write("0");
			}
		}else {
			pw.write("0");
		}
		pw.close();
		return;
	}
}
