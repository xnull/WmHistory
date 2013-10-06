package ru.wmtool.metadata;

import java.util.ArrayList;
import java.util.List;

public class EntityView {
	private String name;
	private List<Attribute> attributes = new ArrayList<Attribute>();

	public EntityView() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return "EntityView [name=" + name + ", attributes=" + attributes + "]";
	}

}
