package com.itsol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsol.model.Slider;
import com.itsol.services.SliderService;

@RestController
public class SliderController {

	@Autowired
	private SliderService sliderService;

	@RequestMapping(value = "slider", method = RequestMethod.GET)
	public List<Slider> getListSlider() {
		return sliderService.getListSlider();
	}

	@RequestMapping(value = "slider/{slideId}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_MARKETING') or hasRole('ROLE_MANAGER') or hasRole('ROLE_HR')")
	public boolean updateSlider(@PathVariable("slideId") int slideId, @RequestBody Slider slider) {
		return sliderService.updateSlider(slideId, slider);
	}

}
