package com.sachinhandiekar.oracle.aq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import oracle.jms.AQjmsSession;
import oracle.xdb.XMLType;

import org.springframework.jms.listener.DefaultMessageListenerContainer;

public class OracleXMLMessageListenerContainer extends DefaultMessageListenerContainer {

	protected MessageConsumer createConsumer(Session session, Destination destination) throws JMSException {
		return ((AQjmsSession) session).createConsumer(destination, null, XMLType.getORADataFactory(), null, false);
	}

}
