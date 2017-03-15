package com.coderising.download;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread {
	// private Lock lock = new ReentrantLock();// Ëø¶ÔÏó

	Connection conn;
	int startPos;
	int endPos;
	int length;

	public DownloadThread(Connection conn, int startPos, int endPos) {

		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
	}

	public synchronized void run() {
		System.out.println("1 run method go");
		try {
			System.out.println("2 run method go");

			RandomAccessFile raf = new RandomAccessFile("e://kk2.png", "rw");
			byte[] bys = null;
			raf.seek(startPos);
			bys = conn.read(startPos, endPos);
			int len = bys.length;
			System.out.println("3 run  " + len);
				raf.write(bys, 0, len);
				raf.close();
			// conn.read(startPos, endPos);
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}