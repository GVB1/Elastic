package com.ElasticSearch2;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.net.InetAddress;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class TaskDemo {

	public static void main(String[] args) {
		try{
			
			Settings settings = Settings.builder()
        	        .put("client.transport.sniff", true).build();
        		TransportClient client = new PreBuiltTransportClient(settings)
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        	
        	
        	client.admin().indices().create(new CreateIndexRequest("gpptaskidx")).actionGet();
        	
        	XContentBuilder mapping = jsonBuilder().prettyPrint()
	                .startObject()
                    .startObject("_default_")
                        .startObject("properties")
                           // .startObject("speaker").field("type", "keyword").field("index", "not_analyzed").endObject()
                        	.startObject("instanceNumber").field("type", "integer").endObject()
                        	.startObject("taskNumber").field("type", "integer").endObject()
                        	.startObject("taskName").field("type", "keyword").endObject()
                        	.startObject("localtaskName").field("type", "keyword").endObject()
                        	.startObject("actualStartDate").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").endObject()
                        	.startObject("actualEndDate").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").endObject()
                        	.startObject("plannedStartDate").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").endObject()
                        	.startObject("plannedEndDate").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").endObject()
                        	.startObject("assignedTo").field("type", "keyword").endObject()
                        	.startObject("departmentName").field("type", "keyword").endObject()
                        	.startObject("taskCreationDate").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").endObject()
                        .endObject()
                    .endObject()
                .endObject();
        	
        	System.out.println("Pretty Print ::: "+mapping.string());
        	
        	PutMappingResponse putMappingResponse = client.admin().indices()
        		    .preparePutMapping("gpptaskidx")
        		    .setType("_default_")
        		    .setSource(mapping.prettyPrint())
        		    .execute().actionGet();
        	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
