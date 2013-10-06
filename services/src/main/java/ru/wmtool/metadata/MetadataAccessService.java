package ru.wmtool.metadata;

public interface MetadataAccessService {
	EntityMetadata getMetadata(Class<?> clazz) throws MetadataException;
}
