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
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        try{
        	Settings settings = Settings.builder()
        	        .put("client.transport.sniff", true).build();
        	TransportClient client = new PreBuiltTransportClient(settings)
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        	
        	
        	client.admin().indices().create(new CreateIndexRequest("shakespeare5")).actionGet();
        	
        	XContentBuilder mapping = jsonBuilder().prettyPrint()
	                .startObject()
                    .startObject("_default_")
                        .startObject("properties")
                           // .startObject("speaker").field("type", "keyword").field("index", "not_analyzed").endObject()
                        	.startObject("speaker").field("type", "keyword").endObject()
                        	.startObject("play_name").field("type", "keyword").endObject()
                        	.startObject("line_id").field("type", "integer").endObject()
                        	.startObject("speech_number").field("type", "integer").endObject()
                        .endObject()
                    .endObject()
                .endObject();
        	
        	System.out.println("Pretty Print ::: "+mapping.string());
        	
        	PutMappingResponse putMappingResponse = client.admin().indices()
        		    .preparePutMapping("shakespeare5")
        		    .setType("_default_")
        		    .setSource(mapping.prettyPrint())
        		    .execute().actionGet();
        	
        	System.out.println("putMappingResponse::: "+putMappingResponse.isAcknowledged());
        	
        	
        	
        	
        	
        	
        	/*IndexResponse response1 = client.prepareIndex("shakespeare1", "play1")
        		    .setSource(jsonBuilder().prettyPrint())
        		    .execute()
        		    .actionGet();*/
        	
        	
        	/*String json = "{" +
        	        "\"user\":\"kimchy\"," +
        	        "\"postDate\":\"2013-01-30\"," +
        	        "\"message\":\"trying out Elasticsearch\"" +
        	    "}";
*/        	
        	/*IndexResponse response =client.prepareIndex("twitter", "tweet")
        	.setSource(json)
            .get();*/
        	
        	/*IndexResponse response = client.prepareIndex("twitter", "tweet", "2")
        	        .setSource(jsonBuilder()
        	                    .startObject()
        	                        .field("user", "GVB")
        	                        .field("postDate", new Date())
        	                        .field("message", "trying out Elasticsearch")
        	                    .endObject()
        	                  )
        	        .get();*/
        	
        	//client.admin().indices().create(new CreateIndexRequest("shakespeare4")).actionGet();
        	//client.admin().indices().prepareDelete("shakespeare5").execute().actionGet();

        	/*XContentBuilder mapping = jsonBuilder()
        			.startObject()
        							  	.startObject("_default_")
        							  		.startObject("properties")
        							  			.field("speaker").startObject().field("type", "keyword")
        							  			.endObject()
        							  		.endObject()
        							  	.endObject()
        	      .endObject();
        	
        	System.out.println("=== "+mapping.string());
        	PutMappingResponse putMappingResponse = client.admin().indices()
        											.preparePutMapping("shakespeare4")
        											.setType("play")
        											.setSource(mapping)
        											.execute().actionGet();
        	*/
       
        							 
        	
        	
        /*	IndexResponse response = client.prepareIndex("shakespeare", "tweet", "2")
        	        .setSource(mapping
        	                    .startObject()
        	                        .field("user", "GVB")
        	                        .field("postDate", new Date())
        	                        .field("message", "trying out Elasticsearch")
        	                    .endObject()
        	                  )
        	        .get();*/
        	
        //	String id= putMappingResponse.toString();
        //	System.out.println("ID ::"+id);
        /*	GetResponse response1 = client.prepareGet("twitter", "tweet",id).get();
        	System.out.println(response1);*/
        	

        	client.close();
        }catch (Exception e) {
			e.printStackTrace();
		}
    }
}
