package com.itsol.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.itsol.model.Setting;
import com.itsol.services.SettingService;


@RestController
public class SettingController {
	@Autowired
	SettingService settingService;

	@RequestMapping(value = "/setting/{id}", method = RequestMethod.GET)
	public Setting getListSetting(@PathVariable("id") int id) {
		return settingService.getListSetting(id);
	}

	@RequestMapping(value = "/setting/{settingId}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public boolean updateSetting(@PathVariable("settingId") int settingId,
			@RequestBody Setting setting) {
		return settingService.updateSetting(setting, settingId);
	}
}
