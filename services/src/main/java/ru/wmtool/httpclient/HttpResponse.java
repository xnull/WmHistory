package ru.wmtool.httpclient;

/**
 * Класс http ответа от сервера
 * 
 * @author null
 * 
 */
public class HttpResponse {

	private int responseCode;
	private String responseMessage;

	private String content;

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public HttpResponse() {
	}

	public HttpResponse(int responseCode, String responseMessage, String content) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.content = content;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public String getContent() {
		return content;
	}
}
