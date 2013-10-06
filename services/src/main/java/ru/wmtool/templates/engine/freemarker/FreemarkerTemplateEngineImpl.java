package ru.wmtool.templates.engine.freemarker;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.templates.engine.TemplateEngine;
import ru.wmtool.templates.engine.exception.TemplateEngineException;
import freemarker.template.Configuration;

/**
 * Сооставление сообщений с помощью шаблонов Freemarker.
 * 
 * @author Evgin
 * 
 */
public class FreemarkerTemplateEngineImpl implements TemplateEngine {
	@SuppressWarnings("unused")
	@LoggerIn
	private Logger log;

	@Autowired
	private Configuration freemarkerConfig;

	@Override
	public String render(String templateName, Map<String, Object> modelData)
			throws TemplateEngineException {
		if (templateName == null || templateName.isEmpty()) {
			throw new IllegalArgumentException("templateName not defined");
		}

		try {
			String text = FreeMarkerTemplateUtils.processTemplateIntoString(
					freemarkerConfig.getTemplate(templateName), modelData);
			return text;
		} catch (Exception e) {
			throw new TemplateEngineException("Unnable to render template.", e);
		}
	}

}
