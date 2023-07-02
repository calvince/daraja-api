package dev.vexla.mpesaDaraja.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReferenceData {

    @JsonProperty("ReferenceItem")
    private ReferenceItem referenceItem;
}
