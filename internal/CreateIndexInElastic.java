package com.internal;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.net.InetAddress;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class CreateIndexInElastic {

	public static void main(String[] args) {
		try{
			
			Settings settings = Settings.builder()
        	        .put("client.transport.sniff", true).build();
        		TransportClient client = new PreBuiltTransportClient(settings)
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        	
        	
        	client.admin().indices().create(new CreateIndexRequest("gpppayrollinstance")).actionGet();
        	
        	XContentBuilder mapping = jsonBuilder().prettyPrint()
	                .startObject()
                    .startObject("_default_")
                        .startObject("properties")
                           // .startObject("speaker").field("type", "keyword").field("index", "not_analyzed").endObject()
                        	.startObject("instanceid").field("type", "integer").endObject()
                        	.startObject("businessUnit").field("type", "keyword").endObject()
                        	.startObject("clientName").field("type", "keyword").endObject()
                        	.startObject("countryName").field("type", "keyword").endObject()
                        	.startObject("instanceCreationDate").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS").endObject()
                        	.startObject("departmentName").field("type", "keyword").endObject()
                        	.startObject("instanceDueDate").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS").endObject()
                        	.startObject("instanceStatus").field("type", "keyword").endObject()
                        	.startObject("payrollYear").field("type", "integer").endObject()
                        	.startObject("payAreaCode").field("type", "keyword").endObject()
                        .endObject()
                    .endObject()
                .endObject();
        	
        	System.out.println("Pretty Print ::: "+mapping.string());
        	
        	PutMappingResponse putMappingResponse = client.admin().indices()
        		    .preparePutMapping("gpppayrollinstance")
        		    .setType("_default_")
        		    .setSource(mapping.prettyPrint())
        		    .execute().actionGet();
        	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
