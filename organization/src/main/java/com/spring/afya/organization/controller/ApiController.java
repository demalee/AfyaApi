package com.spring.afya.organization.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.spring.afya.organization.model.CategoryByName;
import com.spring.afya.organization.model.OrganizationById;
import com.spring.afya.organization.model.OrganizationKeyId;
import com.spring.afya.organization.model.Response;
import com.spring.afya.organization.service.OrganizationService;

@RestController
@RequestMapping(value = { "/" })
public class ApiController {

	@Autowired
	OrganizationService organizationService;

	RestTemplate restTemplate = new RestTemplate();
	private static final Logger LOGGER = Logger.getLogger(ApiController.class.getName());

	@GetMapping(value = "/")
	public ResponseEntity<Response> index() {
		String message = "Organization API Service";
		Response response = new Response();
		response.setCode(200);
		response.setMessage(message);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	// Add Category to the Database. Each category has a unique set of attributes.
	@PostMapping(value = "/addcategory", headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> addCategory(@RequestBody String json) {
		String result;
		String message = "";
		int code = -1;
		try {
			result = java.net.URLDecoder.decode(json, "UTF-8");
			JSONObject jSONObject = new JSONObject(result);
			String name = jSONObject.getString("name");
			String id = jSONObject.getString("id");
			String description = jSONObject.getString("description");

			CategoryByName categoryByName = new CategoryByName();
			categoryByName.setId(id);
			categoryByName.setName(name);
			categoryByName.setDescription(description);
			organizationService.addCategory(categoryByName);
			message = "Category: " + name + " added to database";
			code = 200;

		} catch (UnsupportedEncodingException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			code = 205;
		} catch (JSONException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			code = 205;
		}

		Response response = new Response();
		response.setCode(code);
		response.setMessage(message);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	// Fetch and Paginate Categories. Optional page No. as parameter
	@GetMapping(value = { "/category", "/category/page/{page}" })
	public ResponseEntity<Page<HashMap<String, Object>>> getCategoryAll(
			@PathVariable(value = "page") Optional<Integer> page) {

		int itsPage = page.orElse(1);
		int itsSize = 10;

		List<HashMap<String, Object>> categoryitemlist = new ArrayList<HashMap<String, Object>>();

		// Fetch All categories
		List<CategoryByName> catlist = organizationService.findCategoryAll();

		// Put fetched items in a HashMap List
		for (CategoryByName category : catlist) {

			HashMap<String, Object> catitem = new HashMap<String, Object>();
			catitem.put("id", category.getId());
			catitem.put("name", category.getName());
			categoryitemlist.add(catitem);
		}

		// Paginate the HashMap List
		Pageable pageable = PageRequest.of(itsPage - 1, itsSize);
		List<HashMap<String, Object>> list;
		int pageSize = itsSize;
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		if (categoryitemlist.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, categoryitemlist.size());
			list = categoryitemlist.subList(startItem, toIndex);
		}
		Page<HashMap<String, Object>> itemPage = new PageImpl<HashMap<String, Object>>(list,
				PageRequest.of(currentPage, pageSize), categoryitemlist.size());

		return new ResponseEntity<Page<HashMap<String, Object>>>(itemPage, HttpStatus.OK);
	}

	@PostMapping(value = "/addorganization", headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> addOrganization(@RequestBody String json) {
		String result;
		String message = "";
		try {
			result = java.net.URLDecoder.decode(json, "UTF-8");
			JSONObject jSONObject = new JSONObject(result);
			UUID uuid = UUID.randomUUID();
			String id = uuid.toString();
			String name = jSONObject.getString("name");
			String address = jSONObject.getString("address");
			String phone = jSONObject.getString("phone");
			String email = jSONObject.getString("email");
			String category = jSONObject.getString("category");
			String country = jSONObject.getJSONObject("location").getString("country");
			String region = jSONObject.getJSONObject("location").getString("region");
			String territory = jSONObject.getJSONObject("location").getString("territory");
			String zone = jSONObject.getJSONObject("location").getString("zone");
			JSONObject locObject = jSONObject.getJSONObject("gps");
			String lat = locObject.getString("lat");
			String lng = locObject.getString("lng");
			
			HashMap<String,String> gpsmap = new HashMap<String,String>();
			gpsmap.put("lat", lat);
			gpsmap.put("lng", lng);
			
			
			Map<String, String> locmap = new HashMap<String, String>();
			locmap.put("country", country);
			locmap.put("region", region);
			locmap.put("territory", territory);
			locmap.put("zone", zone);
			List<String> attributelist = new ArrayList<String>();
			if (jSONObject.has("attributes")) {
				JSONArray attributesarray = jSONObject.getJSONArray("attributes");
				for (int i = 0; i < attributesarray.length(); i++) {
					String attribute = attributesarray.getString(i);
					attributelist.add(attribute);
				}
			}

			JSONArray servicearray = jSONObject.getJSONArray("services");
			List<String> servicelist = new ArrayList<String>();
			for (int i = 0; i < servicearray.length(); i++) {
				String service = servicearray.getString(i);
				servicelist.add(service);
			}
			OrganizationKeyId organizationKeyId = new OrganizationKeyId();
			organizationKeyId.setId(id);
			OrganizationById organizationById = new OrganizationById();
			organizationById.setKey(organizationKeyId);
			organizationById.setName(name);
			organizationById.setCategory(category);
			organizationById.setPhone(phone);
			organizationById.setEmail(email);
			organizationById.setAddress(address);
			organizationById.setGps(gpsmap);
			organizationById.setLocation(locmap);
			organizationById.setAttributes(attributelist);
			organizationById.setServices(servicelist);

			organizationService.addOrganization(organizationById);

			message = "Organization: " + name + " added to database";

		} catch (UnsupportedEncodingException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			message = e.getMessage();
		} catch (JSONException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			message = e.getMessage();
		}

		Response response = new Response();
		response.setCode(200);
		response.setMessage(message);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	// Fetch and Paginate Organizations. Optional page No. as parameter
	@GetMapping(value = { "/all", "/all/page/{page}" })
	public ResponseEntity<Page<HashMap<String, Object>>> getOrganizaionAll(
			@PathVariable(value = "page") Optional<Integer> page) {

		int itsPage = page.orElse(1);
		int itsSize = 10;

		List<HashMap<String, Object>> orgitemlist = new ArrayList<HashMap<String, Object>>();
		List<OrganizationById> orglist = organizationService.findAll();
		for (OrganizationById organizationById : orglist) {
			HashMap<String, Object> orgitem = new HashMap<String, Object>();
			orgitem.put("id", organizationById.getKey().getId());
			orgitem.put("name", organizationById.getName());
			orgitem.put("phone", organizationById.getPhone());
			orgitem.put("email", organizationById.getEmail());
			orgitem.put("address", organizationById.getAddress());
			orgitem.put("location", organizationById.getLocation());
			orgitem.put("gps", organizationById.getGps());
			orgitemlist.add(orgitem);
		}

		// Paginate the HashMap List
		Pageable pageable = PageRequest.of(itsPage - 1, itsSize);
		List<HashMap<String, Object>> list;
		int pageSize = itsSize;
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		if (orgitemlist.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, orgitemlist.size());
			list = orgitemlist.subList(startItem, toIndex);
		}
		Page<HashMap<String, Object>> itemPage = new PageImpl<HashMap<String, Object>>(list,
				PageRequest.of(currentPage, pageSize), orgitemlist.size());

		return new ResponseEntity<Page<HashMap<String, Object>>>(itemPage, HttpStatus.OK);
	}

}
