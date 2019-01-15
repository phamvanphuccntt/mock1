package com.itsol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.itsol.dao.SettingDAO;
import com.itsol.model.Setting;

@Repository
public class SettingService {
	@Autowired
	SettingDAO settingDao;
	
	public Setting getListSetting(int id){
		return settingDao.getListSetting(id);
	}

	public boolean updateSetting(Setting setting, int settingId) {
		return settingDao.updateSetting(settingId, setting);
	}
	
}
