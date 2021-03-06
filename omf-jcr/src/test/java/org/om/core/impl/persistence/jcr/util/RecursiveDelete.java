package org.om.core.impl.persistence.jcr.util;

import javax.jcr.Node;
import javax.jcr.NodeIterator;

import org.om.core.impl.persistence.jcr.exception.JcrException;

/**
 * 
 * @author tome
 * 
 */
public class RecursiveDelete {
	public static void recursiveDelete(Node node) throws JcrException {
		try {
			final NodeIterator iter = node.getNodes();
			while (iter.hasNext()) {
				final Node n = iter.nextNode();
				if (-1 == n.getName().indexOf(":")) {
					if (n.hasNodes()) {
						recursiveDelete(n);
					}
					try {
						n.remove();
					} catch (final Exception e) {

					}
				}
			}
		} catch (final Exception e) {
			throw new JcrException("Exception in recursiveDelete", e);
		}
	}
}
