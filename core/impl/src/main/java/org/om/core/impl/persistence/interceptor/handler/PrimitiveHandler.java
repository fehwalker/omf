package org.om.core.impl.persistence.interceptor.handler;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.interceptor.handler.ItemHandler;
import org.om.core.api.persistence.result.PersistenceResult;

/**
 * Property handler for primitive or autoboxed types. Will automatically perform
 * type conversion if necessary.
 * 
 * TODO: This doesn't have support for parsing dates.
 * 
 * @author Jakob Külzer
 * 
 */
public class PrimitiveHandler implements ItemHandler {

	public Object retrieve(MappedField mappedField, PersistenceAdapter adapter) {
		final PropertyMapping mapping = (PropertyMapping) mappedField.getMapping();
		final PersistenceResult result = adapter.getProperty(mapping);

		final Object input = result.getResult();

		// If we get a null, we can just return null, no type checking
		// necessary.
		if (input == null)
			return null;

		// If the returned object already has the correct type, just return it.
		if (mappedField.getType() == input.getClass()) {
			return input;
		}

		// If the returned object is a String, we'll have to parse it.
		if (String.class.equals(input.getClass())) {

			// This is real ugly: PropertyEditors return boxed primitive
			// types instead of exactly what you ask for.
			final PropertyEditor editor = PropertyEditorManager.findEditor(mappedField.getType());
			editor.setAsText((String) input);
			return editor.getValue();
		}

		throw new IllegalStateException("We have an unhandled type " + mappedField.getType() + ".");
	}

}