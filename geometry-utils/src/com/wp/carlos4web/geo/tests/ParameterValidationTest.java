package com.wp.carlos4web.geo.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.vividsolutions.jts.geom.Point;
import com.wp.carlos4web.geo.parser.GeometryUtils;

public class ParameterValidationTest {

	@Test
	public void throwsExceptionForNotConfiguratedGeometries()
	{
		GeometryUtils geo = new GeometryUtils();
		
		assertNotNull(geo);
		
		Point p = geo.to(Point.class);
		
		assertNull(p);
	}

}
