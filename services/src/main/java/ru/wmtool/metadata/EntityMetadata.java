package ru.wmtool.metadata;

import java.util.ArrayList;
import java.util.List;

/**
 * Xml-тег, описывающий класс сущности.
 */
public class EntityMetadata {
	private String caption;
	private String name;
	private Class<?> clazz;
	private List<Attribute> attributes = new ArrayList<Attribute>();
	private List<EntityView> views = new ArrayList<EntityView>();

	public EntityMetadata() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaption() {
		return caption;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public List<EntityView> getViews() {
		return views;
	}

	public void setViews(List<EntityView> views) {
		this.views = views;
	}

	@Override
	public String toString() {
		return "EntityMetadata [caption=" + caption + ", name=" + name
				+ ", clazz=" + clazz + ", attributes=" + attributes
				+ ", views=" + views + "]";
	}
}
