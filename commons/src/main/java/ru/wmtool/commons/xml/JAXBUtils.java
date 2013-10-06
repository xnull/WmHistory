package ru.wmtool.commons.xml;

import java.io.StringWriter;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Document;

/**
 * Класс для JAXB преобразований.
 * @param <T>
 */
public class JAXBUtils {

	/**
	 * Отобразить xml документ на класс
	 * 
	 * @param <T>
	 *            класс на который будет отображен xml документ
	 * @param xmlDoc
	 *            документ источник, который будет отображен на класс
	 * @param unmarshallClass
	 *            класс на который будет отображен xml документ
	 * @return <T> новый класс на который отобразился xml документ
	 * @throws JAXBException
	 */
	public <T> T xmlDocumentToClass(Document xmlDoc, Class<T> unmarshallClass)
			throws JAXBException {
		if (xmlDoc == null) {
			return null;
		}

		JAXBContext context = JAXBContext.newInstance(unmarshallClass);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		JAXBElement<T> result = unmarshaller.unmarshal(xmlDoc, unmarshallClass);

		return result.getValue();
	}
	/**
         * Отображает класс на xml документ.
         * @param jaxbClass объект.
         * @return документ.
         * @throws Throwable
         */
	public Document classToXmlDocument(Object jaxbClass) throws Throwable{
		XmlUtils xmlUtils = new XmlUtils();
		Document resultXmlDoc =  xmlUtils.createDocument();
		JAXBContext context = JAXBContext.newInstance(jaxbClass.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.marshal(jaxbClass, resultXmlDoc);
		return resultXmlDoc;
	}

	/**
	 * Получение из JAXB класса, строкового представления в виде xml строки
	 * @param jaxbClass
	 * @return xml-строка.
	 */
	public String classToXmlString(Object jaxbClass) {
		StringWriter xmlString = new StringWriter();
		JAXB.marshal(jaxbClass, xmlString);
		return xmlString.toString();
	}
}
