package com.liferay.smp.io;

import com.liferay.smp.IOException;

public interface BufferedReader {

	public void open(String file) throws IOException;
	public String readLine() throws IOException;
	public void close() throws IOException;
}