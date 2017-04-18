package com.sshfuture.dataStructure.download.impl;

import com.sshfuture.dataStructure.download.api.Connection;
import com.sshfuture.dataStructure.download.api.ConnectionException;
import com.sshfuture.dataStructure.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		Connection con = new ConnectionImpl(url);
		return con;
	}

}
