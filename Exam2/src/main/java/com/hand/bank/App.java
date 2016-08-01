package com.hand.bank;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9999);
			System.out.println("·þÎñ¶Ë¿ªÆô");
			while (true) {
				Socket socket = serverSocket.accept();
				trans(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void trans(final Socket socket) {

		new Thread(new Runnable() {
			public void run() {
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				OutputStream os = null;
				BufferedOutputStream bos = null;

				try {
					fis = new FileInputStream(new File("document/target.pdf"));
					bis = new BufferedInputStream(fis);
					byte[] b = new byte[1024];
					os = socket.getOutputStream();
					bos = new BufferedOutputStream(os);
					int len = -1;
					while ((len = bis.read(b)) != -1) {
						bos.write(b, 0, len);
						bos.flush();
					}
					bos.close();
					os.close();
					bis.close();
					fis.close();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}).start();
	}
}
