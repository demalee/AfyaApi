package com.spring.afya.location.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.afya.location.model.LocationByName;
import com.spring.afya.location.model.LocationType;
import com.spring.afya.location.repository.LocationRepository;
import com.spring.afya.location.repository.LocationTypeRepository;

@Service
@Transactional
public class LocationServiceImpl implements LocationService{

	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	LocationTypeRepository locationTypeRepository;
	
	@Override
	public void createLocation(LocationByName locationByName) {
		locationRepository.save(locationByName);		
	}

	@Override
	public void createLocation(LocationType locationType) {
		locationTypeRepository.save(locationType);		
	}

	@Override
	public List<LocationType> findAll() {
		return locationTypeRepository.findAll();
	}

	@Override
	public LocationByName findByName(String name) {
		return locationRepository.findByName(name);
	}

	@Override
	public List<LocationByName> findByParent(String parent) {
		return locationRepository.findByParent(parent);
	}

	@Override
	public List<LocationType> findyByType(String type) {
		return locationTypeRepository.findByName(type);
	}

	
 
}
