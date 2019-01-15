package com.itsol.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsol.dao.SliderDAO;
import com.itsol.model.Slider;

@Service
public class SliderService {

	@Autowired
	private SliderDAO sliderDAO;
	
	public List<Slider> getListSlider() {
		return sliderDAO.getListSlider();
	}
	
	public boolean updateSlider(int slideId, Slider slider) {
		return sliderDAO.updateSlider(slideId, slider);
	}
	
}
