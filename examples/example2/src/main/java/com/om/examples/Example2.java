package com.om.examples;

import org.om.core.api.path.Path;

import com.om.examples.example2.dao.MyPojoDAO;
import com.om.examples.example2.pojo.MyPojo;

/**
 * 
 * @author tome
 * 
 */
public class Example2 {
	/**
	 * void main
	 */
	public static void main(String args[]) throws java.io.IOException, java.io.FileNotFoundException {
		try {
			/*
			 * declare the DAO
			 */
			MyPojoDAO dao = new MyPojoDAO();
			/*
			 * fetch object
			 */
			MyPojo myPojo = dao.get(new Path("/jcr/mystuff/myPojo"));
		} catch (Exception e) {

		}
	}

}