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

public class Login extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		String str = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
		Users user = JSONObject.parseObject(str, Users.class);
//		String str = request.getParameter("user");
//		String[] tmp = str.split("-");
//		Users user = new Users();
//		user.setUser(tmp[0]);
//		user.setPassword(tmp[1]);
		System.out.println(user.getUser() + "---" + user.getPassword());
		if(UsersDao.findUser(user.getUser(), user.getPassword())){
			pw.write("1");
		}else {
			pw.write("0");
		}
		System.out.println("already");
		pw.flush();
		pw.close();
		return;
	}
}
