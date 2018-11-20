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
"new_password",
"current_password"
})
public class ChangePasswordRequest {

@JsonProperty("m")
private String m;
@JsonProperty("user_id")
private Integer userId;
@JsonProperty("new_password")
private String newPassword;
@JsonProperty("current_password")
private String currentPassword;
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

@JsonProperty("new_password")
public String getNewPassword() {
return newPassword;
}

@JsonProperty("new_password")
public void setNewPassword(String newPassword) {
this.newPassword = newPassword;
}

@JsonProperty("current_password")
public String getCurrentPassword() {
return currentPassword;
}

@JsonProperty("current_password")
public void setCurrentPassword(String currentPassword) {
this.currentPassword = currentPassword;
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