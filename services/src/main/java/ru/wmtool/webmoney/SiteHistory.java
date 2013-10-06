package ru.wmtool.webmoney;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import ru.wmtool.commons.xml.JAXBUtils;
import ru.wmtool.commons.xml.XmlUtils;
import ru.wmtool.httpclient.HttpClient;
import ru.wmtool.httpclient.HttpResponse;
import ru.wmtool.webmoney.xmlinterfaces.history.History;
import ru.wmtool.webmoney.xmlinterfaces.history.HistoryRequest;

/**
 * Получение истории операции с сайта вебмани.
 */
public class SiteHistory {
	private static final Logger log = Logger.getLogger(SiteHistory.class);

	private HttpClient myHttpClient;

	public SiteHistory(HttpClient httpClient) {
		this.myHttpClient = httpClient;
	}

	/**
	 * Получаем xml документ с сайта вебмани, и преобразовываем его в класс
	 * истории операций
	 * 
	 * @param historyRequest
	 *            запрос пользователя к сайту вебмани.
	 * @return класс истории операций.
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws MalformedURLException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public History getHistoryFromSite(HistoryRequest historyRequest)
			throws MalformedURLException, UnsupportedEncodingException,
			IOException, ParserConfigurationException, SAXException {
		/*
		 * TODO 1. Урлы разные, где прописать какой урл надо использовать?
		 * classic - https://w3s.webmoney.ru/asp/XMLOperations.asp light -
		 * https://w3s.wmtransfer.com/asp/XMLOperationsCert.asp
		 */
		log.info(String.format(
				"Отправляем запрос на получение истории операций: %s",
				historyRequest));

		HttpResponse result = myHttpClient.postHttps(
				"https://w3s.webmoney.ru/asp/XMLOperations.asp",
				historyRequest.toString());
		XmlUtils xmlUtils = new XmlUtils();
		Document doc = xmlUtils.stringToDocument(result.getContent());
		return mapping(doc);
	}

	/**
	 * Отображаем xml документ, на History класс.
	 * 
	 * @param doc
	 *            документ.
	 * @return возращаем историю операций
	 */
	private History mapping(Document doc) {
		JAXBUtils jaxbUtils = new JAXBUtils();
		History history = null;
		try {
			history = jaxbUtils.xmlDocumentToClass(doc, History.class);
		} catch (JAXBException e) {
			log.debug(
					"Не удалось отобразить xml документ с историей операций на History класс",
					e);
		}
		return history;
	}
}
