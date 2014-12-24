package org.example.weather5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import juzu.Action;
import juzu.Path;
import juzu.Response;
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

	static Set<String> locations = new HashSet<String>();

	static {
		locations.add("marseille");
		locations.add("paris");
	}

	@View
	@Route("/show/{location}")
	public void index(String location) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("location", location);
		parameters.put("temperature", weatherService.getTemperature(location));
		parameters.put("locations", locations);
		template.render(parameters);
	}

	@Action
	@Route("/add")
	public Response.View add(String location) {
		locations.add(location);
		return Weather_.index(location);
	}

}
