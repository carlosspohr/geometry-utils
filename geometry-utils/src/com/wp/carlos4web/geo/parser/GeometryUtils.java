package com.wp.carlos4web.geo.parser;

import org.geotools.geometry.jts.WKTReader2;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;

/**
 * This class converts Geometry objects based in WKTs and
 * XY coordinates.
 * 
 * @author Carlos Alberto Junior Spohr Poletto (carlosjrcabello@gmail.com)
 */
public class GeometryUtils 
{
	private String wkt 	= null;
	
	private Double x 	= null;
	
	private Double y 	= null;
	
	private int projection = 4326;
	
	public GeometryUtils()
	{
		
	}
	
	/**
	 * Sets the XY coordinates to geometry target.
	 * 
	 * @param x
	 * 
	 * @param y
	 * 
	 * @return
	 */
	public GeometryUtils from (Double x, Double y)
	{
		if(x == null || y == null)
		{
			throw new IllegalArgumentException("You must specify the XY coordinates.");
		}
		
		this.x = x;
		this.y = y;
		
		return this;
	}
	
	/**
	 * Sets the WKT string for geometry target.
	 * 
	 * @param wkt
	 * 
	 * @return
	 */
	public GeometryUtils from (String wkt)
	{
		if(wkt == null || wkt.isEmpty())
		{
			throw new IllegalArgumentException("You must specify the WKT string.");
		}
		
		this.wkt = wkt;
		
		return this;
	}
	
	/**
	 * Sets the projection code for geometry target.
	 * 
	 * @param projection
	 * 
	 * @return
	 */
	public GeometryUtils withProjection (int projection)
	{
		if(projection > 0)
		{
			this.projection = projection;
		}
		
		return this;
	}
	
	/**
	 * Shortcut for to method.
	 * 
	 * @param target
	 * 
	 * @return
	 */
	public <T extends Geometry> T convertTo (Class<? extends Geometry> target)
	{
		return this.to(target);
	}
	
	/**
	 * Returns an instantied Geometry object.
	 * 
	 * @param target
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends Geometry> T to (Class<? extends Geometry> target)
	{
		if(target == null)
		{
			throw new IllegalArgumentException("You must specify the target geometry class.");
		}
		
		if( (this.wkt == null || this.wkt.isEmpty()) && (this.x == null || this.y == null))
		{
			throw new IllegalArgumentException("You must specify the configuration parameters before make an geometry instance.");
		}
		
		try
		{
			return (T) this.getInstance(target);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * This method returns an instance of Geometry class based on configured parameters of
	 * this class.
	 * 
	 * @param target
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 */
	private Geometry getInstance (Class<? extends Geometry> target) throws ParseException
	{
		Geometry instance = null;
		
		if(this.wkt != null && !this.wkt.isEmpty())
		{
			instance = new WKTReader2().read(wkt);
		}
		else
		{
			GeometryFactory factory 	= new GeometryFactory(new PrecisionModel(),	this.projection);
			
			instance = factory.createPoint(new Coordinate(this.x, this.y, this.projection));
		}
		
		if(this.projection > 0)
		{
			instance.setSRID(this.projection);
		}
		
		return instance;
	}
}