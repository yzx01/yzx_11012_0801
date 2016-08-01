package com.hand.bank;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        File file = new File("document/target.pdf");
        File new_file = new File("document/2.pdf");
        
        try {
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			FileOutputStream fos = new FileOutputStream(new_file);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			byte []b = new byte[100];
			int len = 0;
			while((len=bis.read(b))!=-1){
				bos.write(b, 0, len);
			}
			
			bos.close();
			fos.close();
			
			bis.close();
			fis.close();
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
