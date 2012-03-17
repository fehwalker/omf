package org.om.core.impl.persistence.jcr.impl.sessionfactory;

import java.io.InputStream;
import java.util.Properties;

import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.commons.JcrUtils;
import org.om.core.impl.persistence.jcr.api.sessionfactory.SessionFactory;
import org.om.core.impl.persistence.jcr.exception.JCRException;

/**
 * 
 * @author tome
 * 
 */
public class ConfiguredSessionFactory implements SessionFactory {
	/**
	 * propertiesFile
	 */
	private final String propertiesFile;

	/**
	 * ctor
	 */
	public ConfiguredSessionFactory(String propertiesFile) {
		this.propertiesFile = propertiesFile;
	}

	public Session getSession() throws JCRException {
		try {
			/*
			 * get the props file
			 */
			final InputStream inputStream = ConfiguredSessionFactory.class.getResourceAsStream(propertiesFile);
			if (null != inputStream) {
				/*
				 * load props
				 */
				final Properties properties = new Properties();
				properties.load(inputStream);
				/*
				 * go for it
				 */
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
			} else {
				throw new Exception("Unable to find '" + propertiesFile + "'");
			}
		} catch (final Exception e) {
			throw new JCRException("Exception in getSession", e);
		}
	}
}