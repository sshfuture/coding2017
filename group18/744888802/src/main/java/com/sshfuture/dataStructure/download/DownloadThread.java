package com.sshfuture.dataStructure.download;

import com.sshfuture.dataStructure.download.api.Connection;

import java.io.IOException;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){

		try {
			this.conn.read(startPos,endPos);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
