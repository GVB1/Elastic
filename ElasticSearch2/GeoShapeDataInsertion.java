package com.ElasticSearch2;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.net.InetAddress;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

//.field("lat", 17.28042971).field("lon", 80.16005774)

public class GeoShapeDataInsertion {

	public static void main(String[] args) {
		try{
			Settings settings = Settings.builder()
        	        .put("client.transport.sniff", true).build();
        	TransportClient client = new PreBuiltTransportClient(settings)
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        	BulkRequestBuilder bulkRequest = client.prepareBulk();
        	
        	bulkRequest.add(client.prepareIndex("gppgeoshapelocation", "gppgeoshapetype")
        	        .setSource(jsonBuilder()
        	                    .startObject()
        	                        .field("location").startObject().field("type", "polygon").field("coordinates", "[[[100.0, 0.0], [101.0, 0.0], [101.0, 1.0], [100.0, 1.0], [100.0, 0.0] ]]").endObject()
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
