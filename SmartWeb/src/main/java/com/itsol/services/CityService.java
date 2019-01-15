package com.itsol.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsol.dao.CityDAO;
import com.itsol.model.City;

@Service
public class CityService {
	
	@Autowired
	private CityDAO cityDAO;
	
	public List<City> getListCity() {
		return cityDAO.getListCity();
	}

	public int insertCity(City city) {
		return cityDAO.insertCity(city);
	}
}
