package dev.vexla.mpesaDaraja.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReferenceItem{

	@JsonProperty("Value")
	private String value;

	@JsonProperty("Key")
	private String key;
}