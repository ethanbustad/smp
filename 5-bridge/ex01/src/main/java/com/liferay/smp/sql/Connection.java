package com.liferay.smp.sql;

import com.liferay.smp.SQLException;

public interface Connection {

	public void close() throws SQLException;
	public void commit() throws SQLException;
	public PreparedStatement prepareStatement(String sql) throws SQLException;
}