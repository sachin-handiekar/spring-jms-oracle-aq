package com.sachinhandiekar.oracle.aq;

import java.sql.Connection;
import java.sql.SQLException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.sql.DataSource;

import oracle.jms.AQjmsSession;
import oracle.xdb.XMLType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

		JmsTemplate jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");

		final DataSource dataSource = (DataSource) ctx.getBean("dataSource");

		for (int i = 0; i <= 10; i++) {
			final String xmlMessage = "<sample>Message " + i + "</sample>";

			jmsTemplate.send("q_history", new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {

					Connection conn = null;
					XMLType payload = null;
					try {
						conn = dataSource.getConnection();
						payload = XMLType.createXML(conn, xmlMessage);
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
					finally {
						try {
							conn.close();
						}
						catch (SQLException e) {
							// ignore it
						}
					}

					Message msg = ((AQjmsSession) session).createORAMessage(payload);

					return msg;
				}
			});
			System.out.println("Message Sent!!!");

		}

	}
}
