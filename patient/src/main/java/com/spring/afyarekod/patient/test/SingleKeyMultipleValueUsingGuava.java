package com.spring.afyarekod.patient.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.json.JSONObject;

public class SingleKeyMultipleValueUsingGuava {

    public static void main(String[] args) {
        //First User
        JSONObject userDetails = new JSONObject();
        userDetails.put("id", 100);
        userDetails.put("firstName", "Ramesh");

        System.out.println(userDetails);
    }

}
