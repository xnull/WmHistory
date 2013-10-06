package ru.wmtool.metadata;

/**
 * Атрибут xml-файла.
 */
public class Attribute {
	private String name;
	private Class<?> clazz;
	private String caption;

	public Attribute() {
	}

	public Attribute(String name, Class<?> clazz, String caption) {
		super();
		this.name = name;
		this.clazz = clazz;
		this.caption = caption;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Override
	public String toString() {
		return "Attribute [name=" + name + ", clazz=" + clazz + ", caption="
				+ caption + "]";
	}

}
