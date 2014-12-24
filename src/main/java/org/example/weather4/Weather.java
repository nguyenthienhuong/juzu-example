package org.example.weather4;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import juzu.Path;
import juzu.Route;
import juzu.View;
import juzu.template.Template;

import org.example.WeatherService;


public class Weather {
	@Inject
	WeatherService weatherService;

	@Inject
	@Path("index.gtmpl")
	Template template;

	@View
	@Route("/show/{location}")
	public void index(String location) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("location", location);
		parameters.put("temperature", weatherService.getTemperature(location));
		template.render(parameters);
	}
}
