package ru.wmtool.templates.engine;

/***********************************************************************
 * Module:  IWmTemplateRenderer.java
 * Author:  Maxim
 * Purpose: Defines the Interface IWmTemplateRenderer
 ***********************************************************************/

import java.util.Map;

import ru.wmtool.templates.engine.exception.TemplateEngineException;

/**
 * Составление сообщения используя движок-шаблонизатор.
 * 
 * @pdOid 96972caf-a1a9-4f05-bf7b-48b2041dfaf4
 */
public interface TemplateEngine {
	/**
	 * Рендер сообщения.
	 * 
	 * @param templateName
	 *            имя шаблона
	 * @param modelData
	 *            исходная подель для построения сообщения
	 * @throws Exception
	 * @pdOid 4b1a235a-db9f-4bdf-b9f9-7f097e55ab9b
	 */
	String render(String templateName, Map<String, Object> modelData)
			throws TemplateEngineException;

}