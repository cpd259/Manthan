package com.example.chaitanyadeshpande.sor.request_response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"status",
"data"
})
public class SectionListResponse {

@JsonProperty("status")
private Boolean status;
@JsonProperty("data")
private List<Section> data = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("status")
public Boolean getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(Boolean status) {
this.status = status;
}

@JsonProperty("data")
public List<Section> getData() {
return data;
}

@JsonProperty("data")
public void setData(List<Section> data) {
this.data = data;
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