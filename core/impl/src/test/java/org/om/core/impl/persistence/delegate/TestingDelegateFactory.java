package org.om.core.impl.persistence.delegate;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.persistence.PersistenceContext;
import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.api.persistence.PersistenceDelegateFactory;
import org.om.core.api.session.Session;

/**
 * @author Jakob Külzer
 */
public class TestingDelegateFactory implements PersistenceDelegateFactory {

	public PersistenceDelegate create(Session session, Object id, EntityMapping mapping, PersistenceContext persistenceContext, boolean createNode) {
		return new TestingPersistenceDelegate(mapping, persistenceContext);
	}

}
