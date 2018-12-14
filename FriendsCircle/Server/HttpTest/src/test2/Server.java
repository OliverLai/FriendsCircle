package test2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		Server server = new Server();
		server.init();
		System.out.println("Server is launching");
	}
	
	public void init() {
		try {
			ServerSocket serversocket = new ServerSocket(8888);
			while(true) {
				Socket sc = serversocket.accept();
				new HandlerThread(sc);
			}
		}catch(Exception e) {
			System.out.println("Server error: " + e.getMessage());
		}
	}
	
	private class HandlerThread implements Runnable{
		private Socket socket;
		public HandlerThread(Socket client) {
			this.socket = client;
			new Thread(this).start();
		}
		
		public void run() {
			try {
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				String[] str = dis.readUTF().split("-");
				if("admin".equals(str[0]) && "123456".equals(str[1])) {
					dos.writeUTF("true");
				}else {
					dos.writeUTF("false");
				}
				dis.close();
				dos.close();
			}catch(Exception e) {
				System.out.println("Server error: " + e.getMessage());
			}finally {
				if(socket != null) {
					try {
						socket.close();
					}catch(Exception e) {
						socket = null;
						System.out.println("Server error: " + e.getMessage());
					}
				}
			}
		}
	}
}
