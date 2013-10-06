package ru.wmtool.commons.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Класс для работы с xml.
 */
public class XmlUtils {

	/**
	 * Преобразование строки в xml документ.
	 * 
	 * @param xmlDoc
	 *            строковое представление xml документа.
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public Document stringToDocument(String xmlDoc)
			throws ParserConfigurationException, SAXException, IOException {
		if (xmlDoc == null) {
			return null;
		}
		return createDocumentBuilder().parse(
				new InputSource(new StringReader(xmlDoc)));
	}

	/**
	 * Преобразование xml документа в строковое представление
	 * 
	 * @param xmlDoc
	 *            xml документ, который необходимо преобразовать в строку.
	 * @return
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	public String documentToString(Document xmlDoc)
			throws TransformerFactoryConfigurationError, TransformerException {
		if (xmlDoc == null) {
			return null;
		}

		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		StreamResult result = new StreamResult(new StringWriter());
		transformer.transform(new DOMSource(xmlDoc), result);
		return ((StringWriter) result.getWriter()).toString();
	}

	/**
	 * Создание пустого xml документа.
	 * 
	 * @return пустой документ.
	 * @throws ParserConfigurationException
	 */
	public Document createDocument() throws ParserConfigurationException {
		return createDocumentBuilder().newDocument();
	}

	/**
	 * Создание билдера документа.
	 * 
	 * @return DocumentBuilder.
	 * @throws ParserConfigurationException
	 */
	private DocumentBuilder createDocumentBuilder()
			throws ParserConfigurationException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = docFactory.newDocumentBuilder();
		return builder;
	}

	/**
	 * Загрузка DOM-документа из потока.
	 * 
	 * @param in поток ввода.
	 * @return DOM-документ.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public Document load(InputStream in)
			throws ParserConfigurationException, SAXException, IOException {
		return createDocumentBuilder().parse(new InputSource(in));
	}

	public Document load(String url) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilder xmldoc = createDocumentBuilder();
		return xmldoc.parse(url);
	}
}
