package ru.wmtool.test.templates.engine;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.wmtool.templates.engine.TemplateEngine;
import ru.wmtool.templates.engine.exception.TemplateEngineException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class FreemarkerTemplateEngineTests {

	@Resource
	ApplicationContext appCtx;

	@Autowired
	TemplateEngine templateEngine;


	@Test(expected = IllegalArgumentException.class)
	public void NullTemplateName() throws Exception {
		templateEngine.render(null, new HashMap<String, Object>());
	}

	@Test(expected = TemplateEngineException.class)
	public void NotExistsTemplate() throws Exception {
		String notExistsTemplateName = "DarkSide.ftl";
		templateEngine.render(notExistsTemplateName, new HashMap<String, Object>());
	}

	@Test
	public void ExistsTemplate() throws Exception {
		String existsTemplateName = "test.ftl";
		final String expRes = "It's Work!";

		Map<String, Object> modelData = new HashMap<String, Object>();
		modelData.put("object", expRes);

		String actResult = templateEngine.render(existsTemplateName, modelData);

		Assert.assertEquals(expRes, actResult);
	}

}
