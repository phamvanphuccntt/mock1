package com.itsol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsol.model.City;
import com.itsol.services.CityService;

@RestController
public class CityController {

	@Autowired
	private CityService cityService;
	
	@RequestMapping(value = "city", method = RequestMethod.GET)
	public List<City> getListCity() {
		return cityService.getListCity();
	}
	
	@RequestMapping(value = "city", method = RequestMethod.POST)
	public int insertCity(@RequestBody City city) {
		return cityService.insertCity(city);
	}
	
}
