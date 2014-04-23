package com.liferay.smp.sql;

import java.util.Date;

import com.liferay.smp.SQLException;

public interface PreparedStatement {

	public void close() throws SQLException;
	public ResultSet executeQuery() throws SQLException;
	public int executeUpdate() throws SQLException;
	public void setInt(int parameterIndex, int x) throws SQLException;
	public void setLong(int parameterIndex, long x) throws SQLException;
	public void setString(int parameterIndex, String x) throws SQLException;
	public void setDate(int parameterIndex, Date x) throws SQLException;
}