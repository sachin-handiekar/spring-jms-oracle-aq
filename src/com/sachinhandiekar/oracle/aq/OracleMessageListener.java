package com.sachinhandiekar.oracle.aq;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.jdom.Document;
import org.jdom.input.DOMBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.transaction.annotation.Transactional;

import oracle.jms.AQjmsAdtMessage;
import oracle.xdb.XMLType;

public class OracleMessageListener implements MessageListener {

	@Transactional
	public void onMessage(Message message) {

		try {

			// Converting message into XmlType payload
			XMLType xmlMsg = (XMLType) ((AQjmsAdtMessage) message).getAdtPayload();

			Document document = convertDOMtoJDOM(xmlMsg.getDOM());

			System.out.println("JDOM ==> " + document);

			Format format = Format.getPrettyFormat();
			format.setOmitDeclaration(true);

			XMLOutputter outputter = new XMLOutputter(format);

			String xmlString = outputter.outputString(document);
			System.out.println("XML Payload ==> " + xmlString);

		}
		catch (Exception e) {
			// Catch any exception here
			e.printStackTrace();
		}

	}

	public Document convertDOMtoJDOM(org.w3c.dom.Document input) {
		DOMBuilder builder = new DOMBuilder();
		Document output = builder.build(input);
		return output;
	}

}