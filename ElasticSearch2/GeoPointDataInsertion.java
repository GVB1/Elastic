package com.ElasticSearch2;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.FileReader;
import java.net.InetAddress;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

public class GeoPointDataInsertion {

	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
		try{
			Settings settings = Settings.builder()
        	        .put("client.transport.sniff", true).build();
        	TransportClient client = new PreBuiltTransportClient(settings)
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        	BulkRequestBuilder bulkRequest = client.prepareBulk();
        	Object obj = parser.parse(new FileReader("C:\\Users\\vijbhar\\Desktop\\Country.json"));

            JSONArray jsonarray = (JSONArray) obj;
            System.out.println(jsonarray.size());
            
            Gson gson= new Gson();
            for (int i = 0; i < jsonarray.size(); i++) {
            	JSONObject jsonobject = (JSONObject)jsonarray.get(i);
            	CountryInfo cobj= gson.fromJson(jsonobject.toJSONString(), CountryInfo.class);
        	bulkRequest.add(client.prepareIndex("gppgeolocation", "gppgeotype")
        	        .setSource(jsonBuilder()
        	                    .startObject()
        	                        .field("location").startObject().field("lat", cobj.getLatitude()).field("lon", cobj.getLongitude()).endObject()
        	                        .field("name", cobj.getName())
        	                        .field("businessUnit", cobj.getBu())
        	                        .endObject()
        	                  )
        	        );
        	
            }		   
        	BulkResponse bulkResponse = bulkRequest.get();
        	System.out.println("bulkResponse :::: "+bulkResponse.hasFailures());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
