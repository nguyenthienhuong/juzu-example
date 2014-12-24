package org.example.weather3;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import juzu.Path;
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
	public void index() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("location", "marseille");
	    parameters.put("temperature", weatherService.getTemperature("marseille"));
	    template.render(parameters);
	}
}
