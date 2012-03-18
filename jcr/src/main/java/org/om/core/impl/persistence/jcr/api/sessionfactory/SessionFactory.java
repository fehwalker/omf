package org.om.core.impl.persistence.jcr.api.sessionfactory;

import javax.jcr.Session;

/**
 * A factory for JCR sessions.
 * 
 * @author tome
 */
public interface SessionFactory {
	/**
	 * get a JCR Session
	 */
	Session getSession();
}
