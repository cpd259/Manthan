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
"id",
"section_title",
"description",
"reading_level_id",
"is_active"
})
public class Section {

@JsonProperty("id")
private Integer id;
@JsonProperty("section_title")
private String sectionTitle;
@JsonProperty("description")
private String description;
@JsonProperty("reading_level_id")
private Integer readingLevelId;
@JsonProperty("is_active")
private String isActive;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

@JsonProperty("section_title")
public String getSectionTitle() {
return sectionTitle;
}

@JsonProperty("section_title")
public void setSectionTitle(String sectionTitle) {
this.sectionTitle = sectionTitle;
}

@JsonProperty("description")
public String getDescription() {
return description;
}

@JsonProperty("description")
public void setDescription(String description) {
this.description = description;
}

@JsonProperty("reading_level_id")
public Integer getReadingLevelId() {
return readingLevelId;
}

@JsonProperty("reading_level_id")
public void setReadingLevelId(Integer readingLevelId) {
this.readingLevelId = readingLevelId;
}

@JsonProperty("is_active")
public String getIsActive() {
return isActive;
}

@JsonProperty("is_active")
public void setIsActive(String isActive) {
this.isActive = isActive;
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