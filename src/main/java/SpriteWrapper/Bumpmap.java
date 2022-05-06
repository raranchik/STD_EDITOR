package SpriteWrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "convertToNormalMap",
        "externalNormalMap",
        "heightScale",
        "normalMapFilter"
})
@Generated("jsonschema2pojo")
public class Bumpmap {

    @JsonProperty("convertToNormalMap")
    public long convertToNormalMap;
    @JsonProperty("externalNormalMap")
    public long externalNormalMap;
    @JsonProperty("heightScale")
    public double heightScale;
    @JsonProperty("normalMapFilter")
    public long normalMapFilter;

}
