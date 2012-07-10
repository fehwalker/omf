package org.om.core.impl.session.factory;

import org.om.core.api.persistence.PersistenceAdapterFactory;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.mapping.registry.OnDemandMappingRegistry;
import org.om.core.impl.persistence.cglib.CglibProxyFactory;
import org.om.core.impl.persistence.interceptor.factory.PersistenceInterceptorFactoryImpl;

/**
 * 
 * @author tome
 * 
 * 
 * Not quite sure what this class does, so I'm deprecating it. 
 * 
 * @author Jakob Külzer
 * 
 */
@Deprecated
public class DefaultSessionFactory extends ImmutableSessionFactory {

	public DefaultSessionFactory(PersistenceAdapterFactory persistenceDelegateFactory) {
		super(persistenceDelegateFactory, new OnDemandMappingRegistry(new EntityMappingExtractorImpl()), new CglibProxyFactory(
				new PersistenceInterceptorFactoryImpl()));
	}
}
