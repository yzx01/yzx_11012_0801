package com.hand.bank;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.google.gson.JsonObject;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	HttpClient client = HttpClients.createDefault();
    	HttpGet get = new HttpGet("http://hq.sinajs.cn/list=sh601006");
        try {
			
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity,"UTF-8");
			String sub = result.substring(21, 55);
			ArrayList<String> list = new ArrayList<String>();
			String []str = sub.split(",");
			for(String s:str){
				System.out.println(s);
				list.add(s);
			}
			
			//创建xml文件
			Document doc = DocumentHelper.createDocument();
			Element xml = doc.addElement("xml");
			Element scoket = xml.addElement("scoket");
			Element name = scoket.addElement("name");
			Element open = scoket.addElement("open");
			Element close = scoket.addElement("close");
			Element current = scoket.addElement("current");
			Element high = scoket.addElement("high");
			Element low = scoket.addElement("low");
			
			name.addText(list.get(0));
			open.addText(list.get(1));
			close.addText(list.get(2));
			current.addText(list.get(3));
			high.addText(list.get(4));
			low.addText(list.get(5));
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			File file = new File("document/sina.xml");
			if(!file.exists()){
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file);
			
			XMLWriter writer = new XMLWriter(fw,format);
			
			writer.write(doc);
			
			writer.close();
			fw.close();
			//创建json文件
			 JsonObject object = new JsonObject();
			 object.addProperty("name", "大秦铁路");
			 object.addProperty("open", 6.170);
			 object.addProperty("close", 6.150);
			 object.addProperty("current", 6.150);
			 object.addProperty("high", 6.210);
			 object.addProperty("low", 6.110);
			String s = object.toString();
			 File f = new File("document/sina.json");
			 InputStream is = new ByteArrayInputStream(s.getBytes());
			 FileOutputStream fos = new FileOutputStream(f);
			 byte []b = new byte[10];
			 int len = -1;
			 while((len=is.read(b))!=-1){
				 fos.write(b, 0, len);
			 }
			 fos.close();
			 is.close();

			
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

