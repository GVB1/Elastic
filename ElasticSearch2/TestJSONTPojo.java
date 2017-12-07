package com.ElasticSearch2;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

public class TestJSONTPojo {

	public static void main(String[] args) {
		JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("Country.json"));

            JSONArray jsonarray = (JSONArray) obj;
            System.out.println(jsonarray.size());
            
            Gson gson= new Gson();
            
            for (int i = 0; i < jsonarray.size(); i++) {
            	JSONObject jsonobject = (JSONObject)jsonarray.get(i);
            	CountryInfo cobj= gson.fromJson(jsonobject.toJSONString(), CountryInfo.class);
            	System.out.println(cobj.getBu());
            }
            /*String name = (String) jsonObject.get("name");
            System.out.println(name);

            long age = (Long) jsonObject.get("age");
            System.out.println(age);

            // loop array
            JSONArray msg = (JSONArray) jsonObject.get("messages");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        } 
	}
}
