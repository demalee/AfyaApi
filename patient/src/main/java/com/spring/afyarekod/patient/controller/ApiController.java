package com.spring.afyarekod.patient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.spring.afyarekod.patient.model.*;
import com.spring.afyarekod.patient.repository.PatientIdRepository;
import com.spring.afyarekod.patient.service.PatientService;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


@RestController
@RequestMapping(value = { "/" })
public class ApiController
{
    private static final Logger LOGGER = Logger.getLogger(ApiController.class.getName());

    @Autowired
    PatientIdRepository patientIdRepository;
    private Object PatientByGender;
    private PatientService patientService;

    @GetMapping(value = "/")
    public ResponseEntity<String> index() {
        String message = "Patient API Service";
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @GetMapping({"/patients", "/patients/{page}"})
    public ResponseEntity<Page<HashMap<String, Object>>> getAllPatients(@PathVariable(value = "page") Optional<Integer> page) {

        int itsPage = page.orElse(1);
        int itsSize = 10;

        List<HashMap<String, Object>> patientlist = new ArrayList<HashMap<String, Object>>();

        //fetch patient Iterable from repository
        List<PatientById> plist = patientIdRepository.findAll();

        System.out.println("patients " + plist.size());
        //List<Person> persons = repository.findAll();


        // Put fetched items in a HashMap List
        plist.forEach(n -> {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("patientid", n.getPatientid());
            item.put("firstname", n.getFirstname());
            item.put("lastname", n.getLastname());
            item.put("email", n.getEmail());
            item.put("gender", n.getGender());
            item.put("id_number", n.getIdentification_number());
            item.put("insurance", n.getInsurance());
            item.put("next_of_kin", n.getNext_of_kin());
            patientlist.add(item);
        });

        //Paginate the HashMap List
        Pageable pageable = PageRequest.of(itsPage - 1, itsSize);
        List<HashMap<String, Object>> list;
        int pageSize = itsSize;
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        //get the size of the patient list and compare with starting point int
        if (patientlist.size() < startItem) {
            list = Collections.emptyList();
        }else {
            int toIndex = Math.min(startItem + pageSize, patientlist.size());
            list = patientlist.subList(startItem, toIndex);
        }
        Page<HashMap<String, Object>> itemPage = new PageImpl<>(list,
                PageRequest.of(currentPage, pageSize), patientlist.size());
        return new ResponseEntity<Page<HashMap<String, Object>>>(itemPage, HttpStatus.OK);
    }

    @PostMapping(value = "/patients/create", headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> createPatient(@RequestBody String json) {

        String result;
        String message = "";
        String uname = "";
        String log = "";
        int code = -1;
        try {
            result = java.net.URLDecoder.decode(json, "UTF-8");
            uname = saveUser(result);
            message = "Patient successfully created.";
            log = uname;
            code = 200;
            
        } catch (UnsupportedEncodingException e) {
            message = e.getMessage();
            LOGGER.severe("Error: " + e.getMessage());
            code = 301;
        } catch (JSONException e) {
            message = e.getMessage();
            LOGGER.severe("Error: " + e.getMessage());
            code = 302;
        }

        HashMap<String, Object> usermap = new HashMap<String, Object>();
        usermap.put("code", code);
        usermap.put("log", log);
        usermap.put("message", message);

        return new ResponseEntity<HashMap<String, Object>>(usermap, HttpStatus.OK);
    }

    private String saveUser(String json) {

        LOGGER.info("Patient Create Request");
        LOGGER.info("");
        LOGGER.info("");
        LOGGER.info(json);
        LOGGER.info("");
        LOGGER.info("");

        String message = "";
        try {
            JSONObject jSONObject = new JSONObject(json);
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString();

            String fname = jSONObject.getString("firstname");
            String lname = jSONObject.getString("lastname");
//            String phone = jSONObject.getString("phone");;
            String email = jSONObject.getString("email");
            String gender = jSONObject.getString("gender");
            String address = jSONObject.getString("address");
            String identification = jSONObject.getString("identification");
            int score = 0;
            String status = "active";
            String dob = jSONObject.getString("dob");
            String occupation = jSONObject.getString("occupation");
            String residence = jSONObject.getString("residence");

//            String country = jSONObject.getJSONObject("location").getString("country");
//            String region = jSONObject.getJSONObject("location").getString("region");
//            String territory = jSONObject.getJSONObject("location").getString("territory");
//            String zone = jSONObject.getJSONObject("location").getString("zone");
            JSONArray phone = jSONObject.getJSONArray("phone");
            JSONArray ins = jSONObject.getJSONArray("insurance");
            JSONArray next_of_kin = jSONObject.getJSONArray("next_of_kin");

//            String ins_title = jSONObject.getJSONObject("insurance").getString("title");

            Map<String, String> insu = new HashMap<>();
            for (int i = 0; i < ins.length(); i++) {
                String name = ins.getJSONObject(i).getString("name");
                Integer limits = ins.getJSONObject(i).getInt("limits");
                insu.put(name, limits.toString());
            }

//            JSONObject locObject = jSONObject.getJSONObject("gps");
//            String lat = locObject.getString("lat");
//            String lng = locObject.getString("lng");

            Map<String, String> noks = new HashMap<>();
            JSONObject new_kin = new JSONObject();
            for (int j = 0; j < next_of_kin.length(); j++) {
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                LOGGER.info(next_of_kin.getJSONObject(j).toString());
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                String key = next_of_kin.getJSONObject(j).getString("fullname");
                String value = next_of_kin.getJSONObject(j).getString("contact");

                new_kin.put("fullname", next_of_kin.getJSONObject(j).getString("fullname"));
                new_kin.put("contact", next_of_kin.getJSONObject(j).getString("contact"));
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                LOGGER.info(new_kin.toString());
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
            }

//            Map<String, String> insux = new HashMap<>();
//            for (int i = 0; i < ins.length(); i++) {
//                String name = ins.getJSONObject(i).getString("name");
//                Integer limits = ins.getJSONObject(i).getInt("limits");
//                insu.put(name, limits.toString());
//            }

            //Add employees to list
            JSONArray pList = new JSONArray();
            pList.put(new_kin);

            Map<String, String> insux = new HashMap<>();
            for (int i = 0; i < pList.length(); i++) {
                String name = pList.getJSONObject(i).getString("fullname");
                String limits = pList.getJSONObject(i).getString("contact");
                insux.put(name, limits);
            }

//            Map<String, String> locmap = new HashMap<String,String>();
//            locmap.put("country", country);
//            locmap.put("region", region);
//            locmap.put("territory", territory);
//            locmap.put("zone", zone);
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            Date date = new Date();
            String curdate = dateFormat.format(date);



            List<String> phonelist = new ArrayList<String>();
            for (int i = 0; i < phone.length(); i++) {
                phonelist.add(phone.getString(i));
            }

            PatientById patientById = new PatientById();
            patientById.setPatientid(id);
            patientById.setFirstname(fname);
            patientById.setLastname(lname);
            patientById.setEmail(email);
            patientById.setGender(gender);
            patientById.setAddress(address);
            patientById.setIdentification_number(identification);
            patientById.setDate_of_birth(date);
            patientById.setOccupation(occupation);
            patientById.setPlace_of_residence(residence);
            patientById.setScore(score);
            patientById.setStatus(status);
            patientById.setPhone_number(phonelist);
            patientById.setNext_of_kin(insux);
            patientById.setInsurance(insu);
            patientById.setCreatedDate(date);
            patientById.setUpdatedDate(date);





            PatientByLocation patientByLocation = new PatientByLocation();
            patientByLocation.setPatientid(id);
            patientByLocation.setFirstname(fname);
            patientByLocation.setLastname(lname);
            patientById.setEmail(email);
            patientById.setGender(gender);
            patientById.setAddress(address);
            patientById.setIdentification_number(identification);
            patientById.setDate_of_birth(date);
            patientById.setOccupation(occupation);
            patientById.setPlace_of_residence(residence);
            patientById.setScore(score);
            patientById.setStatus(status);
            patientById.setPhone_number(phonelist);
            patientById.setNext_of_kin(insux);
            patientById.setInsurance(insu);
            patientById.setCreatedDate(date);
            patientById.setUpdatedDate(date);


            PatientByGender patientByGender = new PatientByGender();
            patientByGender.setPatientid(id);
            patientByGender.setFirstname(fname);
            patientByGender.setLastname(lname);
            patientByGender.setEmail(email);

            patientByGender.setAddress(address);
            patientByGender.setIdentification_number(identification);
            patientByGender.setDate_of_birth(date);
            patientByGender.setOccupation(occupation);
            patientByGender.setPlace_of_residence(residence);
            patientByGender.setScore(score);
            patientByGender.setStatus(status );
            
            
            PatientByDob patientByDob = new PatientByDob();
            patientByDob.setPatientid(id);
            patientByDob.setFirstname(fname);
            patientByDob.setLastname(lname);
            patientByDob.setEmail(email);

            patientByDob.setAddress(address);
            patientByDob.setIdentification_number(identification);
            patientByDob.setDate_of_birth(date);
            patientByDob.setOccupation(occupation);
            patientByDob.setPlace_of_residence(residence);
            patientByDob.setScore(score);
            patientByDob.setStatus(status );


                    PatientByPhone patientByPhone = new PatientByPhone();
            patientByPhone.setPatientid(id);
            patientByPhone.setFirstname(fname);
            patientByPhone.setLastname(lname);
            patientByPhone.setEmail(email);

            patientByPhone.setAddress(address);
            patientByPhone.setIdentification_number(identification);
            patientByPhone.setDate_of_birth(date);
            patientByPhone.setOccupation(occupation);
            patientByPhone.setPlace_of_residence(residence);
            patientByPhone.setScore(score);
//            PatientByEmail patientByEmail;
            patientByPhone.setStatus(status);


                    PatientByEmail patientByEmail = new PatientByEmail();
            patientByEmail.setPatientid(id);
            patientByEmail.setFirstname(fname);
            patientByEmail.setLastname(lname);
            patientByEmail.setEmail(email);

            patientByEmail.setAddress(address);
            patientByEmail.setIdentification_number(identification);
            patientByEmail.setDate_of_birth(date);
            patientByEmail.setOccupation(occupation);
            patientByEmail.setPlace_of_residence(residence);
            patientByEmail.setScore(score);
            patientByEmail.setStatus(status);




                    PatientByName patientByName = new PatientByName();
            patientByName.setPatientid(id);
            patientByName.setFirstname(fname);
            patientByName.setLastname(lname);
            patientByName.setEmail(email);

            patientByName.setAddress(address);
            patientByName.setIdentification_number(identification);
            patientByName.setDate_of_birth(date);
            patientByName.setOccupation(occupation);
            patientByName.setPlace_of_residence(residence);
            patientByName.setScore(score);
            patientByName.setStatus(status);

            

            



//            PatientService patientService = patientService;
            patientService.createPatient(patientById);
            patientService.createPatient( patientByGender);
            patientService.createPatient(patientByDob);
            patientService.createPatient(patientByName);
            patientService.createPatient(patientByLocation);
            patientService.createPatient(patientByEmail);
            patientService.createPatient(patientByPhone);


            message = id;
            LOGGER.info(message);

        } catch (JSONException e) {
            LOGGER.severe("AGENT_CREATE_REQUEST_ERROR " + e.getMessage());
            message = e.getMessage();
        } catch (NullPointerException e) {
            message = e.getMessage();
        } catch (Exception e) {
            message = e.getMessage();
        }

        return message;
    }


}
