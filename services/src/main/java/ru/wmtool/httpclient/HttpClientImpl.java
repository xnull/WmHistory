package ru.wmtool.httpclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;

/**
 * Класс для работы c https протоколом. Полный мануал по java security
 * http://download.oracle.com/javase/6/docs/technotes/guides/security/
 * 
 * @author nazgul
 */
public class HttpClientImpl implements HttpClient {
	private static final Logger log = Logger.getLogger(HttpClientImpl.class);

	private SSLContext sslContext;
	private HttpsURLConnection connection;

	static {
		// System.setProperty("java.protocol.handler.pkgs",
		// "com.sun.net.ssl.internal.www.protocol");
		System.setProperty("java.protocol.handler.pkgs", "javax.net.ssl");
	}

	public HttpClientImpl() throws SSLException {
		TrustManager[] trustAllCerts = initTrustManagers();
		sslContext = createSSLContext(trustAllCerts);
		HttpsURLConnection.setDefaultSSLSocketFactory(sslContext
				.getSocketFactory());
	}

	@Override
	public String getHttps(String url) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public HttpResponse postHttps(String urlAddr, String query) {
		HttpResponse response = new HttpResponse();
		URL url;
		try {
			url = createUrl(urlAddr);
		} catch (MalformedURLException e) {
			log.error("Некорректный URL: " + urlAddr);
			return response;
		}

		try {
			connection = (HttpsURLConnection) url.openConnection();
		} catch (IOException e) {
			log.error("Не удалось открыть соединение с сервером", e);
			return response;
		} catch (ClassCastException e) {
			log.error("Соединение не установлено. Ошибка приведения типов", e);
			return response;
		}
		try {
			configureConnection(connection, query.length());
		} catch (ProtocolException e) {
			log.error("Неподдерживаемый протоколом метод");
			return response;
		}

		try {
			sendData(query, connection);
		} catch (IOException e) {
			log.error("Не удалось послать данные");
			return response;
		}

		response = fillHttpResponse(connection);
		return response;
	}

	private HttpResponse fillHttpResponse(HttpsURLConnection connection) {
		HttpResponse response = new HttpResponse();
		int respCode;
		try {
			respCode = connection.getResponseCode();
		} catch (IOException e) {
			log.error("Невозможно получить код ответа http сервера");
			return response;
		}
		String respMessage;
		try {
			respMessage = connection.getResponseMessage();
		} catch (IOException e) {
			log.error("Невозможно получить статус ответа http сервера");
			return response;
		}

		String answer = null;
		try {
			answer = inputStreamToString(connection.getInputStream());
		} catch (IOException e) {
			log.error("Не удалось получить данные с сайта");
			return response;
		}

		response.setResponseCode(respCode);
		response.setResponseMessage(respMessage);
		response.setContent(answer);

		return response;
	}

	private void sendData(String query, HttpsURLConnection connection)
			throws IOException {
		DataOutputStream output = new DataOutputStream(
				connection.getOutputStream());
		output.writeBytes(query);
		// output.close();
	}

	private void configureConnection(HttpsURLConnection connection,
			int queryLenght) throws ProtocolException {
		connection.setDoInput(true);
		connection.setDoOutput(true);

		connection.setRequestMethod("POST");

		// connection.setFollowRedirects(true);

		connection.setRequestProperty("Content-length",
				String.valueOf(queryLenght));
		connection.setRequestProperty("Content-Type",
				"application/x-www- form-urlencoded");
		connection.setRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 5.0; Linux; DigExt)");
	}

	private URL createUrl(String urlAddr) throws MalformedURLException {
		java.security.Security
				.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		URL url = new URL(urlAddr);
		return url;
	}

	/**
	 * Преобразовать inputStream в String
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	private String inputStreamToString(InputStream inputStream)
			throws IOException {
		if (inputStream != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(
						inputStream, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				inputStream.close();
			}
			return writer.toString();
		} else {
			return "";
		}
	}

	/**
	 * Задать настройки для HttpsUrlConnection, чтобы он при посылке данных не
	 * проверял наличие сертификата, для сервера на который посылаются данные
	 * 
	 * @param trustAllCerts
	 * @throws SSLException
	 */
	private SSLContext createSSLContext(TrustManager[] trustAllCerts)
			throws SSLException {
		try {
			sslContext = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e) {
			String err = "Не удалось создать SSL контекст";
			log.error(err);
			throw new SSLException(err, e);
		}
		try {
			sslContext.init(null, trustAllCerts,
					new java.security.SecureRandom());
		} catch (KeyManagementException e) {
			String err = "Не удалось инициализировать SSL контекст";
			log.error(err);
			throw new SSLException(err, e);
		}
		return sslContext;
	}

	/**
	 * Настроить траст менеджер, чтобы он возращал пустое значение. Это
	 * необходимо чтобы отключить на клиенте поиск корневого сертификата для
	 * сервера, на который посылаются данные.
	 * 
	 * @return
	 */
	private TrustManager[] initTrustManagers() {
		TrustManager[] trustAllCerts = new TrustManager[] { getEmptyTrustManager() };
		return trustAllCerts;
	}

	/**
	 * Получить пустой траст менеджер
	 * 
	 * @return
	 */
	private X509TrustManager getEmptyTrustManager() {
		X509TrustManager emptyX509TrustManager = new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}
		};
		return emptyX509TrustManager;
	}
}
