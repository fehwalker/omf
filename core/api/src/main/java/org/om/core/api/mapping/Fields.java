package org.om.core.api.mapping;

import java.util.Collection;

/**
 * Contains all {@link MappedField}s for an {@link EntityMapping}.
 * 
 * @author Jakob Külzer
 * 
 */
public interface Fields extends Iterable<MappedField> {

	/**
	 * Returns all {@link Mapping}s.
	 * 
	 * @return
	 */
	Collection<MappedField> getAll();

	/**
	 * Returns true if this map contains a field with the given name.
	 * 
	 * @param name
	 * @return
	 */
	boolean hasField(String name);

	/**
	 * Returns the property that has been marked as the id property.
	 * 
	 * @return
	 */
	MappedField getIdProperty();

	/**
	 * Finds a field by name.
	 * 
	 * @param fieldname
	 * @return
	 */
	MappedField getField(String fieldname);

	int getSize();

	boolean isEmpty();

	MappedField getFieldByMapping(Mapping mapping);
}