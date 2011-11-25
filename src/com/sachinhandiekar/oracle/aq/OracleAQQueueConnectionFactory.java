package com.sachinhandiekar.oracle.aq;

import javax.jms.ConnectionFactory;
import javax.sql.DataSource;

public class OracleAQQueueConnectionFactory {

	private DataSource dataSource;

	public ConnectionFactory createConnectionFactory() throws Exception {
		return oracle.jms.AQjmsFactory.getQueueConnectionFactory(dataSource);
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
