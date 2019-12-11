package com.spring.afya.location.service;

import java.util.List;

import com.spring.afya.location.model.LocationByName;
import com.spring.afya.location.model.LocationType;

public interface LocationService {

	public void createLocation(LocationByName locationByName);
	public void createLocation(LocationType locationType);
	
	public List<LocationType> findAll();
	public List<LocationType> findyByType(String type);
	
	public LocationByName findByName(String name);
	public List<LocationByName> findByParent(String parent);
 
}
