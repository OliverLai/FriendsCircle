package test1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ServerThread {
	private Socket socket;
	boolean flag = false;
	
	public ServerThread(Socket c) {
		this.socket = c;
	}
	
	public void run() {
		try {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			String line = dis.readUTF();
			String[] st = line.split("-");
			flag = CheckLogin.checklogin(st[0], st[1]);
			if(flag) {
				dos.writeUTF("true");
			}else {
				dos.writeUTF("false");
			}
			dis.close();
			dos.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
