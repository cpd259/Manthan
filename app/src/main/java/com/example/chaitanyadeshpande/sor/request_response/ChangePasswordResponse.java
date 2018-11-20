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
"data",
"msg"
})
public class ChangePasswordResponse {

@JsonProperty("status")
private Boolean status;
@JsonProperty("data")
private UserDetails data = null;
@JsonProperty("msg")
private String msg;
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
public UserDetails getData() {
return data;
}

@JsonProperty("data")
public void setData(UserDetails data) {
this.data = data;
}

@JsonProperty("msg")
public String getMsg() {
return msg;
}

@JsonProperty("msg")
public void setMsg(String msg) {
this.msg = msg;
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