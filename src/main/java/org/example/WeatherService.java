package org.example;

import java.net.URLEncoder;
import java.util.HashMap;

import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

public class WeatherService {
	public final HashMap<String, String> cache = new HashMap<String, String>();

	public WeatherService() {
		System.out.println("WeatherService is runnung ....");
	}

	public String getTemperature(String location) {
		String temperature = cache.get(location);
		if (temperature == null) {
			cache.put(location, temperature = retrieveTemperature(location));
		}
		return temperature;
	}

	private String getValue(String url, String xpath) throws Exception {
		XPathExpression expr = XPathFactory.newInstance().newXPath()
				.compile(xpath);
		InputSource src = new InputSource(url);
		return expr.evaluate(src);
	}

	protected String retrieveTemperature(String location) {
		try {
			// First we get the location WOEID
			String woeidURL = "http://query.yahooapis.com/v1/public/yql"
					+ "?q=select%20*%20from%20geo.places%20where%20text%3D%22"
					+ URLEncoder.encode(location, "UTF-8") + "%22&format=xml";
			String woeid = getValue(woeidURL,
					"//*[local-name()='woeid']/text()");

			// Now get weather temperature
			String weatherURL = "http://weather.yahooapis.com/forecastrss?w="
					+ URLEncoder.encode(woeid, "UTF-8") + "&u=c";
			return getValue(weatherURL, "//*[local-name()='condition']/@temp");
		} catch (Exception e) {
			// Unavailable
			return "?";
		}
	}
}
