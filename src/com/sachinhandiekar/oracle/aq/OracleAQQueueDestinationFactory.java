package com.sachinhandiekar.oracle.aq;

import org.springframework.beans.factory.FactoryBean;
import oracle.jms.AQjmsSession;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;

public class OracleAQQueueDestinationFactory implements FactoryBean<Queue> {

	private QueueConnectionFactory connectionFactory;

	private String queueUser;

	private String queueName;

	public Queue getObject() throws Exception {
		QueueConnection queueConnection = connectionFactory.createQueueConnection();
		AQjmsSession session = (AQjmsSession) queueConnection.createQueueSession(true, Session.SESSION_TRANSACTED);

		return session.getQueue(queueUser, queueName);
	}

 	public Class<Queue> getObjectType() {
		return javax.jms.Queue.class;
	}

	public boolean isSingleton() {
		return false;
	}

	public void setConnectionFactory(QueueConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public void setQueueUser(String queueUser) {
		this.queueUser = queueUser;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
}