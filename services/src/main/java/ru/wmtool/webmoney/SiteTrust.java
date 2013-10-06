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
import ru.wmtool.webmoney.xmlinterfaces.trust.TrustList;
import ru.wmtool.webmoney.xmlinterfaces.trust.TrustListRequest;

/**
 * Получение информации о доверии с сайта вебмани.
 */
public class SiteTrust {
	private static final Logger log = Logger.getLogger(SiteTrust.class);

	private HttpClient myHttpClient;

	public SiteTrust(HttpClient httpClient) {
		this.myHttpClient = httpClient;
	}

	/**
	 * Получаем xml документ со списком доверия с сайта вебмани, и
	 * преобразовываем его в класс истории операций
	 */
	public TrustList getTrustsFromSite(TrustListRequest trustRequest)
			throws MalformedURLException, UnsupportedEncodingException,
			IOException, ParserConfigurationException, SAXException {
		log.info(String.format(
				"Отправляем запрос на получение списков доверия: %s",
				trustRequest));

		HttpResponse result = myHttpClient.postHttps(
				"https://w3s.webmoney.ru/asp/XMLTrustList.asp",
				trustRequest.toString());
		XmlUtils xmlUtils = new XmlUtils();
		Document doc = xmlUtils.stringToDocument(result.getContent());
		return mapping(doc);
	}

	/**
	 * Отображаем xml документ, на TrustList класс
	 */
	private TrustList mapping(Document doc) {
		JAXBUtils jaxbUtils = new JAXBUtils();
		TrustList trustList = null;
		try {
			trustList = jaxbUtils.xmlDocumentToClass(doc, TrustList.class);
		} catch (JAXBException e) {
			log.debug(
					"Не удалось отобразить xml документ со списком доверия на TrustList класс",
					e);
		}
		return trustList;
	}
}
