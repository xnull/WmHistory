package ru.wmtool.httpclient;

import javax.net.ssl.SSLException;

/**
 * Интерфейс https-клиента.
 * 
 * @author nazgul
 */
public interface HttpClient {
	/**
	 * Отправляет данные на сервер по методу GET
	 * 
	 * @param url
	 *            адрес.
	 * @return ответ
	 */
	public String getHttps(String url);

	/**
	 * Отправляет данные по адресу методом post.
	 * 
	 * @param url
	 *            адрес.
	 * @param postData
	 *            данные.
	 * @return ответ пользователю.
	 * @throws SSLException
	 */
	public HttpResponse postHttps(String url, String postData)
			throws SSLException;
}
