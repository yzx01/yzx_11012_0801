package com.hand.bank;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client{

	
	
	public static void main(String[] args) {

		InputStream is = null;
		BufferedInputStream bis = null;

		FileOutputStream fos = null;
		BufferedOutputStream bos = null;

		Socket socket = null;

		try {
			socket = new Socket(InetAddress.getLocalHost(), 9999);

			is = socket.getInputStream();
			bis = new BufferedInputStream(is);

			byte[] b = new byte[1024];
			int len = -1;

			fos = new FileOutputStream(new File("document/new_target.pdf"));
			bos = new BufferedOutputStream(fos);

			while ((len = bis.read(b)) != -1) {
				bos.write(b, 0, len);
				bos.flush();
			}
			bos.close();
			fos.close();
			bis.close();
			is.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}

