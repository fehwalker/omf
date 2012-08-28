package org.om.core.api.mapping.registry;

import org.om.core.api.mapping.EntityMapping;

/**
 * Keeps track of {@link EntityMapping}s.
 * 
 * @author Jakob Külzer
 * 
 */
public interface MappingRegistry {

	EntityMapping getMapping(Class<?> clazz);

}
