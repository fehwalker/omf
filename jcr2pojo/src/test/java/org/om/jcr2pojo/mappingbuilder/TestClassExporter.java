package org.om.jcr2pojo.mappingbuilder;

import java.io.ByteArrayOutputStream;

import junit.framework.Assert;

import org.junit.Test;
import org.om.core.impl.mapping.EntityMappingImpl;
import org.om.jcr2pojo.classexporter.ClassExporter;
import org.om.jcr2pojo.mapping.BasicPropertyMap;
import org.om.jcr2pojo.mapping.BasicPropertyMapping;

/**
 * 
 * @author tome
 * 
 */
public class TestClassExporter {

	@Test
	public void test1() {
		try {

			final EntityMappingImpl entityMappingImpl = new EntityMappingImpl();
			final BasicPropertyMap propertyMap = new BasicPropertyMap();
			entityMappingImpl.setPropertyMap(propertyMap);

			propertyMap.add("a", new BasicPropertyMapping("a"));
			propertyMap.add("b", new BasicPropertyMapping("b"));
			propertyMap.add("c", new BasicPropertyMapping("c"));
			propertyMap.add("d", new BasicPropertyMapping("d"));

			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final ClassExporter classExporter = new ClassExporter();
			classExporter.exportClass("testname", "com.khubla", entityMappingImpl, baos);
			System.out.println(baos.toString());

		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
