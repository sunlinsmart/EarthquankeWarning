package practice;
import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.mapdisplay.MapDisplayFactory;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.utils.MapUtils;
import module4.CityMarker;
import module4.LandQuakeMarker;
import module4.OceanQuakeMarker;
import parsing.ParseFeed;
import processing.core.PApplet;

public class EarthquakeCityMap extends PApplet {
	
	// You can ignore this.  It's to keep eclipse from generating a warning.
		private static final long serialVersionUID = 1L;

		// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
		private static final boolean offline = false;
		
		// Less than this threshold is a light earthquake
		public static final float THRESHOLD_MODERATE = 5;
		// Less than this threshold is a minor earthquake
		public static final float THRESHOLD_LIGHT = 4;
		
		//feed with magnitude 2.5+ Earthquakes
		private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	
	   private UnfoldingMap map;
	
	public void setup(){
		size(950,600,OPENGL);
		map = new UnfoldingMap(this,200,50,700,500, new OpenStreetMap.OpenStreetMapProvider());
		map.zoomToLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);
	    

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	   
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> quakeMarkers = new ArrayList<Marker>();
	    
	    // Add a loop here that calls createMarker (see below) 
	    // to create a new SimplePointMarker for each PointFeature in 
	    // earthquakes.  Then add each new SimplePointMarker to the 
	    // List markers (so that it will be added to the map in the line below)	    
	    for(PointFeature feature:earthquakes){
	    	quakeMarkers.add(new SimplePointMarker(feature.getLocation(),feature.getProperties()));
	    }
	    
	    // Add the markers to the map so that they are displayed
	    map.addMarkers(quakeMarkers);

		    
//		Location starCity = new Location(29.57f,106.55f);
//		Feature cityEq=new PointFeature(starCity);
//		cityEq.addProperty("标题", "汶川大地震");
//		cityEq.addProperty("级数", "8.5");
//		cityEq.addProperty("日期", "2008-05-12");
//		Marker starCityMarker = new SimplePointMarker(starCity,cityEq.getProperties());
//		map.addMarker(starCityMarker);

		
	}
	
	
	
	public void draw(){
		
		background(220,220,220);
		map.draw();
		
	}

}
