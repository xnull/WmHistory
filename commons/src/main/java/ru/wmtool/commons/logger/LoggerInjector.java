package ru.wmtool.commons.logger;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

public class LoggerInjector implements BeanPostProcessor {

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
		ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				ReflectionUtils.makeAccessible(field);
				LoggerIn inject = field.getAnnotation(LoggerIn.class);
				if (inject != null) {
					Logger log = null;
					String loggerName = inject.loggerName();
					if (loggerName.isEmpty()) {
						log = Logger.getLogger(bean.getClass());
					} else {
						log = Logger.getLogger(inject.loggerName());
					}
					field.set(bean, log);
				}
			}
		});
		return bean;
	}
}
