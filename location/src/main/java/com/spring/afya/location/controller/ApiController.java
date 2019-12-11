package com.spring.afya.location.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.spring.afya.location.model.LocationByName;
import com.spring.afya.location.model.LocationType;
import com.spring.afya.location.model.Response;
import com.spring.afya.location.service.LocationService;

@RestController
@RequestMapping(value = { "/" })
public class ApiController {	

	RestTemplate restTemplate = new RestTemplate();
	private static final Logger LOGGER = Logger.getLogger(ApiController.class.getName());
	
	@Autowired
	LocationService locationService;
	
	@GetMapping(value = "/")
	public ResponseEntity<String> index() {
		String message = "Afya Location API Service";
		return new ResponseEntity<String>(message, HttpStatus.OK);
	} 
	
	@PostMapping(value = "/addtype", headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> addType(@RequestBody String json) {
		String result;
		String message = "";
		int code = -1;
		try {
			result = java.net.URLDecoder.decode(json, "UTF-8");
			JSONObject jSONObject = new JSONObject(result);
			String parent = jSONObject.getString("parent");
			String id = jSONObject.getString("id");
			String type = jSONObject.getString("type");
			
			LocationType locationType = new LocationType();
			locationType.setId(id);
			locationType.setName(type);
			locationType.setParent(parent);			
			
			locationService.createLocation(locationType);
			message = "Location: " + type + " added to database";
			code = 200;

		} catch (UnsupportedEncodingException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			code = 205;
			message = e.getMessage();
		} catch (JSONException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			code = 208;
			message = e.getMessage();
		}

		Response response = new Response();
		response.setCode(code);
		response.setMessage(message);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = "/addlocation", headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> addLocation(@RequestBody String json) {
		String result;
		String message = "";
		int code = -1;
		try {
			result = java.net.URLDecoder.decode(json, "UTF-8");
			JSONObject jSONObject = new JSONObject(result);
			String name = jSONObject.getString("name");
			String parent = jSONObject.getString("parent");
			String id = jSONObject.getString("id");
			String type = jSONObject.getString("type");

			LocationByName locationByName = new LocationByName();
			locationByName.setId(id);
			locationByName.setName(name);
			locationByName.setParent(parent);
			locationByName.setType(type);
						
			locationService.createLocation(locationByName);
			message = "Location: " + name + " added to database";
			code = 200;

		} catch (UnsupportedEncodingException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			message = e.getMessage();
			code = 205;
		} catch (JSONException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			message = e.getMessage();
			code = 208;
		}

		Response response = new Response();
		response.setCode(code);
		response.setMessage(message);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<HashMap<String, Object>>> getCategory() {
		List<LocationByName> list1 = locationService.findByParent("");
		List<HashMap<String, Object>> maplist = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < list1.size(); i++) {
			HashMap<String, Object> map1 = new HashMap<String, Object>();
			String id = list1.get(i).getId();
			String name = list1.get(i).getName();
			map1.put("id", id);
			map1.put("name", name);
			List<LocationByName> list2 = locationService.findByParent(name);
			List<HashMap<String, Object>> childlist = new ArrayList<HashMap<String, Object>>();
			for (int j = 0; j < list2.size(); j++) {
				HashMap<String, Object> map2 = new HashMap<String, Object>();
				String id2 = list2.get(j).getId();
				String name2 = list2.get(j).getName();
				map2.put("id", id2);
				map2.put("name", name2);
				List<LocationByName> list3 = locationService.findByParent(name2);
				List<HashMap<String, Object>> grandchildlist = new ArrayList<HashMap<String, Object>>();
				for (int k = 0; k < list3.size(); k++) {
					HashMap<String, Object> map3 = new HashMap<String, Object>();
					String id3 = list3.get(k).getId();
					String name3 = list3.get(k).getName();
					map3.put("id", id3);
					map3.put("name", name3);
					
					List<LocationByName> list4 = locationService.findByParent(name3);
					List<HashMap<String, Object>> greatgrandchildlist = new ArrayList<HashMap<String, Object>>();
					for(int l=0;l<list4.size();l++) {
						HashMap<String, Object> map4 = new HashMap<String, Object>();
						String id4 = list4.get(k).getId();
						String name4 = list4.get(k).getName();
						map4.put("id", id4);
						map4.put("name", name4);
						greatgrandchildlist.add(map4);						
					}
					map3.put("children", greatgrandchildlist);
					grandchildlist.add(map3);
				}
				map2.put("children", grandchildlist);
				childlist.add(map2);
			}
			map1.put("children", childlist);

			maplist.add(map1);

		}
		return new ResponseEntity<List<HashMap<String, Object>>>(maplist, HttpStatus.OK);
	}
	
	


}
