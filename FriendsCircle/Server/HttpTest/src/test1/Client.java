package test1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		try{
			Socket sc=new Socket(InetAddress.getLocalHost(),5555);
			DataInputStream dis = new DataInputStream(sc.getInputStream());
			DataOutputStream dos = new DataOutputStream(sc.getOutputStream());
			System.out.println("please input your user:");
			String user = scan.next();
			System.out.println("please input your password:");
			String pwd = scan.next();
			String user_pwd = user + "-" + pwd;
			dos.writeUTF(user_pwd);
			String result = dis.readUTF();
			if(result.equals("true")) {
				System.out.println("you have already login");
			}else {
				System.out.println("user's name or password is wrong");
			}
			dis.close();
			dos.close();
			sc.close();
			scan.close();
		}catch(Exception e){
			e.printStackTrace();
			}
		}
}
