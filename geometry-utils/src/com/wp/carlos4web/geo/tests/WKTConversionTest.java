package com.wp.carlos4web.geo.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.wp.carlos4web.geo.parser.GeometryUtils;

public class WKTConversionTest
{
	@Test
	public void wktEmptyPointConversion()
	{
		GeometryUtils geo = new GeometryUtils();
		
		Point p = geo.from("POINT EMPTY").convertTo(Point.class);
		
		assertNotNull(p);
		System.out.println(p);
	}
	
	@Test
	public void wktMultiPolygonConversion()
	{
		GeometryUtils geo = new GeometryUtils();
		
		MultiPolygon pol = geo
				.from("MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)),((20 35, 45 20, 30 5, 10 10, 10 30, 20 35),(30 20, 20 25, 20 15, 30 20)))")
				.withProjection(4326)
				.convertTo(MultiPolygon.class);
		
		assertNotNull(pol);
	}
	
	@Test
	public void wktMultiLineConversion()
	{
		GeometryUtils geo = new GeometryUtils();
		
		MultiLineString line = geo
				.from("MULTILINESTRING ((10 10, 20 20, 10 40),(40 40, 30 30, 40 20, 30 10))")
				.convertTo(MultiLineString.class);
		
		assertNotNull(line);
	}
	
	@Test
	public void wktLineConversion()
	{
		GeometryUtils geo = new GeometryUtils();
		
		LineString line = geo
				.from("LINESTRING (30 10, 10 30, 40 40)")
				.convertTo(LineString.class);
		
		assertNotNull(line);
	}
	
	@Test
	public void wktPointConversion()
	{
		GeometryUtils geo = new GeometryUtils();
		
		Point p = geo.from("POINT(-56.12345 -24.56743)").convertTo(Point.class);
		
		assertNotNull(p);
	}
	
	@Test
	public void wktPolygonConversion()
	{
		GeometryUtils geo = new GeometryUtils();
		
		Polygon pol = geo.from("POLYGON ((30 10, 10 20, 20 40, 40 40, 30 10))").convertTo(Polygon.class);
		
		assertNotNull(pol);
	}

}
