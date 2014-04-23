package com.liferay.smp.io;

import com.liferay.smp.IOException;

public interface PrintWriter {

	public void open(String file) throws IOException;
	public void println(String line) throws IOException;
	public void close() throws IOException;
}