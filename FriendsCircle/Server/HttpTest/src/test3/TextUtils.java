package test3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class TextUtils {
	static String path = "D:/File/Javawork/workplace/HttpTest/WebContent/pic/";
	
	public static String imgSave(byte[] bytes, String format) throws IOException {
		String filename = UUID.randomUUID().toString() + format;
		OutputStream os = new FileOutputStream(path + filename);
		os.write(bytes);
		os.flush();
		os.close();
		return filename;
	}
}