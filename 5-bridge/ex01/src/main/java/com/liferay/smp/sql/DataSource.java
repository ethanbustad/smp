package com.liferay.smp.sql;

import com.liferay.smp.SQLException;

public interface DataSource {

	public Connection getConnection() throws SQLException;
}