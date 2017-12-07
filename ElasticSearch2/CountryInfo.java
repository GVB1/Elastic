package com.ElasticSearch2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"bu",
"name",
"latitude",
"longitude"
})
public class CountryInfo {

@JsonProperty("bu")
private String bu;
@JsonProperty("name")
private String name;
@JsonProperty("latitude")
private Double latitude;
@JsonProperty("longitude")
private Double longitude;

@JsonProperty("bu")
public String getBu() {
return bu;
}

@JsonProperty("bu")
public void setBu(String bu) {
this.bu = bu;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("latitude")
public Double getLatitude() {
return latitude;
}

@JsonProperty("latitude")
public void setLatitude(Double latitude) {
this.latitude = latitude;
}

@JsonProperty("longitude")
public Double getLongitude() {
return longitude;
}

@JsonProperty("longitude")
public void setLongitude(Double longitude) {
this.longitude = longitude;
}

}