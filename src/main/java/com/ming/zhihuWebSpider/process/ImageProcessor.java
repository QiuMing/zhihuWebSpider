package com.ming.zhihuWebSpider.process;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 下载图片的Processor demo
 * @Description TODO
 * @author Ming
 * @date: 2016年1月27日--下午12:10:41
 */
public class ImageProcessor {
	public static void main(String[] args) throws IOException {
		String url = "http://pic.meizitu.com/wp-content/uploads/2015a/10/35/01.jpg";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		InputStream in = entity.getContent();

		try {
			FileOutputStream fout = new FileOutputStream(new File("test.png"));
			int l = -1;
			byte[] tmp = new byte[1024];
			while ((l = in.read(tmp)) != -1) {
				fout.write(tmp, 0, l);
			}
			fout.flush();
			fout.close();

			System.err.println("close");
		} finally {
			in.close();
		}
	}
}
