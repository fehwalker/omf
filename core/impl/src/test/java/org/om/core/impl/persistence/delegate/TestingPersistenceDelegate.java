package org.om.core.impl.persistence.delegate;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.Mapping;
import org.om.core.api.persistence.PersistenceContext;
import org.om.core.api.persistence.PersistenceDelegate;

public class TestingPersistenceDelegate implements PersistenceDelegate {

	private final EntityMapping entityMapping;

	private final TestingPersistenceContext persistenceContext;

	public TestingPersistenceDelegate(EntityMapping entityMapping, PersistenceContext persistenceContext) {
		this.entityMapping = entityMapping;
		this.persistenceContext = (TestingPersistenceContext) (persistenceContext == null ? new TestingPersistenceContext() : persistenceContext);
	}

	public EntityMapping getEntityMapping() {
		return entityMapping;
	}

	public TestingPersistenceDelegate addProperty(String propertyName, Object value) {
		persistenceContext.addProperty(propertyName, value);
		return this;
	}

	public Object getProperty(Mapping propertyMapping) {
		return persistenceContext.getProperty(propertyMapping);
	}

	public boolean canProvide(Mapping mapping) {
		return persistenceContext.hasProperty(mapping.getFieldname());
	}

}
