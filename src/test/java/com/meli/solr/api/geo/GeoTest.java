package com.meli.solr.api.geo;

import org.junit.jupiter.api.Test;
import org.springframework.data.geo.Point;

import com.meli.solr.api.domain.Planet;

public class GeoTest {


	@Test
	public void testGetCurrentUserLogin() {

		Planet betasoide = new Planet(com.meli.solr.api.domain.enumeration.PlanetType.Betasoide);
		Planet vulcano = new Planet(com.meli.solr.api.domain.enumeration.PlanetType.Vulcano);
		Planet ferengi = new Planet(com.meli.solr.api.domain.enumeration.PlanetType.Ferengi);
	
//
		int days = 360;
		int years = 10;
//
//		for (int i = 1; i < years*days; i++) {
//
				Point angleBetasoide = betasoide.getPoint(3);
				Point angleVulcano = vulcano.getPoint(3);
				Point angleFerengi = ferengi.getPoint(3);
				
				System.out.println("b "+angleBetasoide + " " + betasoide.getPlanet().getOrbitDirection());
				System.out.println("v "+angleVulcano  + " " + vulcano.getPlanet().getOrbitDirection());
				System.out.println("f "+angleFerengi + " " + ferengi.getPlanet().getOrbitDirection());
				
//				Point point = betasoide.getPointInSpace(5);
//				double anlgeVulcano = betasoide.getAngularPosition(3);
//				double angleFerengi = betasoide.getAngularPosition(3);
				
				
//			}
//		}

	}

	@Test
	public void testDistanceTwoPints() {

//		double actual = Distanse.between(new Point(3, 2), new Point(5, 6));



		

	}
	
	@Test
	public void testAlign3Points() {

//		Geo l = new GeoService().new Line(new Point(1,1), new Point(2,2), new Point(3,3));
		
//		Assert.assertEquals(true, ((Line)l).isAligned());
		

	}
	
	@Test
	public void testAlign3PointsWithSun() {

//		Geo l = new GeoService().new Line(new Point(1,1), new Point(2,2), new Point(3,3));
//		Assert.assertEquals(true, ((Line)l).isAlignedWith(new Point(0,0)));

	}


}
