package com.ElasticSearch2;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.net.InetAddress;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class BulkInsert {

	public static void main(String[] args) {
		try{
			Settings settings = Settings.builder()
        	        .put("client.transport.sniff", true).build();
        	TransportClient client = new PreBuiltTransportClient(settings)
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        	BulkRequestBuilder bulkRequest = client.prepareBulk();
        	
        	bulkRequest.add(client.prepareIndex("gppinstancecount", "gppinstance")
        	        .setSource(jsonBuilder()
        	                    .startObject()
        	                        .field("businessUnit", "UK")
        	                        .field("departmentName", "DEP2")
        	                        .field("instanceNumber", 11)
        	                        .field("instanceCreationDate", "2017-07-27")
        	                        .endObject()
        	                  )
        	        );
        	
        			   
        	BulkResponse bulkResponse = bulkRequest.get();
        	System.out.println("bulkResponse :::: "+bulkResponse.hasFailures());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
