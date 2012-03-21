package org.om.core.impl.persistence.jcr;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

import org.om.core.api.annotation.PropertyMissingStrategy;
import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.api.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Jakob Külzer
 * @author tom
 * 
 */
public class JcrPersistenceDelegate implements PersistenceDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(JcrPersistenceDelegate.class);

	private final Node node;

	public JcrPersistenceDelegate(Session session, EntityMapping entityMapping, Node node) {
		this.node = node;
	}

	public Object getProperty(PropertyMapping propertyMapping) {
		// TODO: should check if the given mapping actually exists in
		// entityMapping.

		// TODO: The missing handling should be moved into the parent:
		final String propertyName = propertyMapping.getPropertyName();
		try {
			if (!node.hasProperty(propertyName)) {
				final PropertyMissingStrategy strategy = propertyMapping.getMissingStrategy();
				switch (strategy) {
				case DefaultValue:
					return propertyMapping.getDefaultValue();

				case ReturnNull:
					return null;

				case ThrowException:
					try {
						throw propertyMapping.getMissingException().newInstance();
					} catch (final InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (final IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (final Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			final Property property = node.getProperty(propertyName);
			if (property.isMultiple()) {
				LOGGER.warn("Cannot handle multi-value properties yet.");
				return null;
			}

			return property.getValue().getString();

		} catch (final RepositoryException e) {
			throw new ObjectMapperException("Could not retrieve property " + propertyName, e);
		}

	}

	public boolean hasProperty(PropertyMapping mapping) {
		try {
			return node.hasProperty(mapping.getPropertyName());
		} catch (final RepositoryException e) {
			throw new ObjectMapperException("Could not retrieve property.", e);
		}
	}

	public void setProperty(PropertyMapping propertyMapping, Object object) {
		throw new ObjectMapperException("not implemented");
	}
}
