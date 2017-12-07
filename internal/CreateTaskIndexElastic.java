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

public class CreateTaskIndexElastic {

	public static void main(String[] args) {
		try{
			
			Settings settings = Settings.builder()
        	        .put("client.transport.sniff", true).build();
        		TransportClient client = new PreBuiltTransportClient(settings)
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        	
        	
        	client.admin().indices().create(new CreateIndexRequest("gpppayrolltasks")).actionGet();
        	
        	XContentBuilder mapping = jsonBuilder().prettyPrint()
	                .startObject()
                    .startObject("_default_")
                        .startObject("properties")
                           // .startObject("speaker").field("type", "keyword").field("index", "not_analyzed").endObject()
                        	.startObject("instanceid").field("type", "integer").endObject()
                        	.startObject("taskid").field("type", "integer").endObject()
                        	.startObject("actualStartDate").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS").endObject()
                        	.startObject("actualEndDate").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS").endObject()
                        	.startObject("taskCreatedDate").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS").endObject()
                        	.startObject("departmentName").field("type", "keyword").endObject()
                        	.startObject("performerName").field("type", "keyword").endObject()
                        	.startObject("plannedStartDate").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS").endObject()
                        	.startObject("plannedEndDate").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS").endObject()
                        	.startObject("taskName").field("type", "keyword").endObject()
                        	.startObject("taskStatus").field("type", "keyword").endObject()
                        .endObject()
                    .endObject()
                .endObject();
        	
        	System.out.println("Pretty Print ::: "+mapping.string());
        	
        	PutMappingResponse putMappingResponse = client.admin().indices()
        		    .preparePutMapping("gpppayrolltasks")
        		    .setType("_default_")
        		    .setSource(mapping.prettyPrint())
        		    .execute().actionGet();
        	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
