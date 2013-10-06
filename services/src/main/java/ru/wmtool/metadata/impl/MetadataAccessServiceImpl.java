package ru.wmtool.metadata.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ru.wmtool.commons.xml.XmlUtils;
import ru.wmtool.metadata.Attribute;
import ru.wmtool.metadata.EntityMetadata;
import ru.wmtool.metadata.EntityView;
import ru.wmtool.metadata.MetadataAccessService;
import ru.wmtool.metadata.MetadataException;
import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

/**
 * 
 * @author Evgin
 */
public class MetadataAccessServiceImpl implements MetadataAccessService {
	private static final Logger log = Logger
			.getLogger(MetadataAccessServiceImpl.class);

	private static final Class<?>[] entities = new Class<?>[] { WmOperation.class };

	private static final Map<Class<?>, EntityMetadata> metadata = loadMetadata();

	private class Constants {
		private static final String NAME = "name";
		private static final String CLASS = "class";
		private static final String CAPTION = "caption";
		private static final String ATTRIBUTE = "attribute";
		private static final String VIEW = "view";
		private static final String REF = "ref";
	}

	@Override
	public EntityMetadata getMetadata(Class<?> clazz) throws MetadataException {
		if (!metadata.containsKey(clazz)) {
			throw new MetadataException("Метаданные не найдены.");
		}
		return metadata.get(clazz);
	}

	private static Map<Class<?>, EntityMetadata> loadMetadata() {
		Map<Class<?>, EntityMetadata> metadata = new HashMap<Class<?>, EntityMetadata>();
		try {
			XmlUtils xmlUtils = new XmlUtils();
			for (Class<?> entity : entities) {
				log.debug("загружаем метаданные: " + entity.getCanonicalName());
				String filePath = defineMetadataUrl(entity);

				InputStream in = ClassLoader
						.getSystemResourceAsStream(filePath);

				Document doc = xmlUtils.load(in);

				Node entityNode = doc.getChildNodes().item(0);

				EntityMetadata entityMetadata = new EntityMetadata();
				entityMetadata
						.setName(readNodeAttr(entityNode, Constants.NAME));
				entityMetadata.setCaption(readNodeAttr(entityNode,
						Constants.CAPTION));
				entityMetadata.setClazz(Class.forName(readNodeAttr(entityNode,
						Constants.CLASS)));

				NodeList attributesNodeList = entityNode.getChildNodes();

				Map<String, Attribute> attributes = new HashMap<String, Attribute>();
				List<EntityView> entityViews = new ArrayList<EntityView>();

				for (int i = 0; i < attributesNodeList.getLength(); i++) {
					Node attrNode = attributesNodeList.item(i);
					if (attrNode.getNodeName().equals(Constants.ATTRIBUTE)) {
						Attribute attribute = new Attribute();
						attribute
								.setName(readNodeAttr(attrNode, Constants.NAME));
						attribute.setCaption(readNodeAttr(attrNode,
								Constants.CAPTION));
						attribute.setClazz(Class.forName(readNodeAttr(attrNode,
								Constants.CLASS)));
						attributes.put(attribute.getName(), attribute);
					}
					if (attrNode.getNodeName().equals(Constants.VIEW)) {
						EntityView entityView = new EntityView();
						entityView.setName(readNodeAttr(entityNode,
								Constants.NAME));
						List<Attribute> refs = new ArrayList<Attribute>();
						NodeList refNodes = attrNode.getChildNodes();
						for (int k = 0; k < refNodes.getLength(); k++) {
							Node refNode = refNodes.item(k);
							if (refNode.getNodeName().equals(
									Constants.ATTRIBUTE)) {
								String refName = readNodeAttr(refNode,
										Constants.REF);
								Attribute ref = attributes.get(refName);
								refs.add(ref);
							}
						}
						entityView.setAttributes(refs);
						entityViews.add(entityView);
					}
				}
				entityMetadata.setAttributes(new ArrayList<Attribute>(
						attributes.values()));
				entityMetadata.setViews(entityViews);
				log.debug("загрузка окончена: " + entity.getCanonicalName());
			}
		} catch (Exception e) {
			throw new RuntimeException("Метаданные не удалось загрузить", e);
		}

		return metadata;
	}

	private static String readNodeAttr(Node node, String attrName) {
		String attrVal = node.getAttributes().getNamedItem(attrName)
				.getTextContent();
		return attrVal;
	}

	/**
	 * Получить путь к классу в файловой системе
	 * 
	 * @param clazz
	 * @return
	 */
	public static String defineMetadataUrl(Class<?> clazz) {
		String separator = System.getProperty("file.separator");
		String url = separator
				+ clazz.getCanonicalName().replace(".", separator) + ".xml";
		return url;
	}
}
