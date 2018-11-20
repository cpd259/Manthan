package com.example.chaitanyadeshpande.sor.request_response;


import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"m",
"user_id",
"section_id"
})
public class AttachmentListRequest {

@JsonProperty("m")
private String m;
@JsonProperty("user_id")
private Integer userId;
@JsonProperty("section_id")
private Integer sectionId;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("m")
public String getM() {
return m;
}

@JsonProperty("m")
public void setM(String m) {
this.m = m;
}

@JsonProperty("user_id")
public Integer getUserId() {
return userId;
}

@JsonProperty("user_id")
public void setUserId(Integer userId) {
this.userId = userId;
}

@JsonProperty("section_id")
public Integer getSectionId() {
return sectionId;
}

@JsonProperty("section_id")
public void setSectionId(Integer sectionId) {
this.sectionId = sectionId;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}