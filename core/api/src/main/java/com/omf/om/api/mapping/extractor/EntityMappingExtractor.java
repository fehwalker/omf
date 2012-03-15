package com.omf.om.api.mapping.extractor;

import com.omf.om.api.exception.MappingException;
import com.omf.om.api.mapping.EntityMapping;

/**
 * Extracts {@link EntityMapping}s from a given class.
 * 
 * @author Jakob Külzer
 * 
 */
public interface EntityMappingExtractor {

	/**
	 * Extracts an {@link EntityMapping} from the given class object.
	 * 
	 * @param type
	 * @return
	 * @throws MappingException
	 *             if the given class object is not an entity or for some other
	 *             reason the mapping could not be extracted.
	 */
	EntityMapping extract(Class<?> type) throws MappingException;

}
