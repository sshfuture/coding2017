package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {
	private Lock lock = new ReentrantLock();// Ëø¶ÔÏó
	private String url;
	InputStream inStream = null;
	HttpURLConnection conn;

	public ConnectionImpl(String url) {
		this.url = url;
	}

	@Override
	public /* synchronized */ byte[] read(int startPos, int endPos) throws IOException {
		URL urlObj = new URL(url);
		conn = (HttpURLConnection) urlObj.openConnection();
		conn.setRequestMethod("GET");
//		conn.setReadTimeout(1000 * 1000);
		synchronized (this) {
			conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
			// String s = con.getRequestProperty("Range");
			inStream = conn.getInputStream();}
		
		// inStream.skip(startPos);
		int arrSize = 1024;
		int len = 0;
		byte[] midBuffer = new byte[arrSize];
		int start = 0;
		byte[] buffer = new byte[0];
		System.out.println("startPos " + startPos);
		System.out.println("endPos " + endPos);
		System.out.println("1  " + buffer.length);

		while ((len = inStream.read(midBuffer)) != -1) {
			// int arrLength = buffer.length;
			System.out.println("len  " + len);
			buffer = Arrays.copyOf(midBuffer, buffer.length + len);
			System.out.println("2 " + buffer.length);
			System.arraycopy(midBuffer, 0, buffer, start, len);
			System.out.println("3 " + buffer.length);
			start += len;
		}

		// RandomAccessFile raf = new RandomAccessFile("e://kk.gif", "rw");
		// raf.seek(startPos);
		// while ((len = inStream.read(midBuffer)) != -1) {
		// raf.write(midBuffer, 0, len);
		// }
		// inStream.close();
		// raf.close();
		return buffer;
	}

	@Override
	public int getContentLength() {
		int length = 0;
		try {
			length = new URL(url).openConnection().getContentLength();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return length;
	}

	@Override
	public void close() {
		// try {
		// if (inStream != null) {
		// inStream.close();
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}
}