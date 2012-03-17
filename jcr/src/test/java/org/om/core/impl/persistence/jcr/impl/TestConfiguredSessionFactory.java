package org.om.core.impl.persistence.jcr.impl;

import org.om.core.impl.persistence.jcr.api.sessionfactory.SessionFactory;
import org.om.core.impl.persistence.jcr.impl.sessionfactory.ConfiguredSessionFactory;

/**
 * 
 * @author tome
 * 
 */
public class TestConfiguredSessionFactory extends BaseTestSessionFactory {

	@Override
	protected SessionFactory getSessionFactory() {
		return new ConfiguredSessionFactory("/configuredsessionfactory.properties");
	}
}