package org.om.core.impl.persistence.jcr.sessionfactory.impl;

import java.io.InputStream;
import java.util.Properties;

import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.commons.JcrUtils;
import org.om.core.impl.persistence.jcr.exception.JcrException;
import org.om.core.impl.persistence.jcr.sessionfactory.JCRSessionFactory;

/**
 * 
 * A JCR Session factory which can be configured from a properties file. This is
 * very useful for the contribs, which aim to create a hibernate-like DAO
 * wrapper around OM, and which can benfit from configuration from a file.
 * 
 * @author tome.
 * 
 */
public class PropertiesConfiguredJCRSessionFactory implements JCRSessionFactory {

	/**
	 * default properties file (
	 */
	private static final String DEFAULT_PROPERTIES_FILE = "/objectmanagerjcr.properties";

	/**
	 * propertiesFile
	 */
	private final String propertiesFile;

	/**
	 * ctor
	 */
	public PropertiesConfiguredJCRSessionFactory() {
		propertiesFile = DEFAULT_PROPERTIES_FILE;
	}

	/**
	 * ctor
	 */
	public PropertiesConfiguredJCRSessionFactory(String propertiesFile) {
		this.propertiesFile = propertiesFile;
	}

	public Session getSession() throws JcrException {
		try {
			/*
			 * get the props file
			 */
			final InputStream inputStream = PropertiesConfiguredJCRSessionFactory.class.getResourceAsStream(propertiesFile);
			if (null == inputStream) {
				throw new Exception("Unable to find '" + propertiesFile + "'");
			}

			// load props
			final Properties properties = new Properties();
			properties.load(inputStream);

			// go for it
			final Repository repository = JcrUtils.getRepository(properties.getProperty("url"));
			final SimpleCredentials creds = new SimpleCredentials(properties.getProperty("username"), properties.getProperty("password").toCharArray());
			String workspace = null;
			if (properties.contains("workspace")) {
				final String ws = properties.getProperty("workspace").trim();
				if ((null != ws) && (ws.length() > 0)) {
					workspace = ws;
				}
			}
			return repository.login(creds, workspace);
		} catch (final Exception e) {
			throw new JcrException("Exception in doGetSession", e);
		}
	}
}
