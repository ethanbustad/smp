package com.liferay.smp.sql;

import com.liferay.smp.SQLException;

public interface ResultSet {

	public void close() throws SQLException;
	public void next() throws SQLException;
	public boolean getBoolean(int columnIndex) throws SQLException;
	public int getInt(int columnIndex) throws SQLException;
	public long getLong(int columnIndex) throws SQLException;
	public String getString(int columnIndex) throws SQLException;
}