package ru.wmtool.templates.engine.exception;

public class TemplateEngineException extends Exception {
	private static final long serialVersionUID = -1838659325681888888L;

	public TemplateEngineException() {
		super();
	}

	public TemplateEngineException(String message, Throwable cause) {
		super(message, cause);
	}

	public TemplateEngineException(String message) {
		super(message);
	}

	public TemplateEngineException(Throwable cause) {
		super(cause);
	}

}
