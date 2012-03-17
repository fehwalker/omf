package com.omf.om.core.persistence.delegate.cglib;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.persistence.PersistenceContext;
import org.om.core.api.persistence.PersistenceDelegateFactory;

import com.omf.om.core.mapping.EntityWithPrimitiveProperties;
import com.omf.om.core.mapping.extractor.EntityMappingExtractorImpl;
import com.omf.om.core.persistence.cglib.CglibProxyFactory;
import com.omf.om.core.persistence.delegate.TestingDelegateFactory;
import com.omf.om.core.persistence.delegate.TestingPersistenceContext;
import com.omf.om.core.persistence.interceptor.factory.PersistenceInterceptorFactoryImpl;

public class CglibProxyFactoryTest {

	@Test
	public void testThatCallsToProxyAreDelegated() {
		final EntityMapping mapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		final PersistenceDelegateFactory delegateFactory = new TestingDelegateFactory();
		final PersistenceContext persistenceContext = new TestingPersistenceContext().addProperty("fieldWithDefaultSettings", "BOOYAH!");
		final CglibProxyFactory proxyFactory = new CglibProxyFactory(new PersistenceInterceptorFactoryImpl());

		EntityWithPrimitiveProperties proxy = (EntityWithPrimitiveProperties) proxyFactory.create(null, mapping,
				delegateFactory.create(null, null, mapping, persistenceContext));

		assertThat(proxy, notNullValue());
		String fieldWithDefaultSettings = proxy.getFieldWithDefaultSettings();

		assertThat(fieldWithDefaultSettings, notNullValue());
		assertThat(fieldWithDefaultSettings, is("BOOYAH!"));
		assertThat(proxy.getUnmappedField(), is("unmapped"));
	}

	@Test
	public void testThatCallsToNonMappedFields() {
		final EntityMapping mapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
		final PersistenceDelegateFactory delegateFactory = new TestingDelegateFactory();
		final PersistenceContext persistenceContext = new TestingPersistenceContext().addProperty("fieldWithDefaultSettings", "BOOYAH!");
		final CglibProxyFactory proxyFactory = new CglibProxyFactory(new PersistenceInterceptorFactoryImpl());

		EntityWithPrimitiveProperties proxy = (EntityWithPrimitiveProperties) proxyFactory.create(null, mapping,
				delegateFactory.create(null, null, mapping, persistenceContext));

		assertThat(proxy, notNullValue());
		assertThat(proxy.getUnmappedField(), is("unmapped"));
		assertThat(proxy.toString(), notNullValue());
	}
}
