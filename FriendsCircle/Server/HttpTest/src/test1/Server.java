package test1;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(5555);
			System.out.println("Server is launching");
			while(true) {
				Socket c = server.accept();
				ServerThread st = new ServerThread(c);
				st.run();
			}
		}catch(Exception e) {
				e.printStackTrace();
		}
	}
}
