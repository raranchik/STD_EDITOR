package SpriteWrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "mipMapMode",
        "enableMipMap",
        "sRGBTexture",
        "linearTexture",
        "fadeOut",
        "borderMipMap",
        "mipMapsPreserveCoverage",
        "alphaTestReferenceValue",
        "mipMapFadeDistanceStart",
        "mipMapFadeDistanceEnd"
})
@Generated("jsonschema2pojo")
public class Mipmaps {

    @JsonProperty("mipMapMode")
    public long mipMapMode;
    @JsonProperty("enableMipMap")
    public long enableMipMap;
    @JsonProperty("sRGBTexture")
    public long sRGBTexture;
    @JsonProperty("linearTexture")
    public long linearTexture;
    @JsonProperty("fadeOut")
    public long fadeOut;
    @JsonProperty("borderMipMap")
    public long borderMipMap;
    @JsonProperty("mipMapsPreserveCoverage")
    public long mipMapsPreserveCoverage;
    @JsonProperty("alphaTestReferenceValue")
    public double alphaTestReferenceValue;
    @JsonProperty("mipMapFadeDistanceStart")
    public long mipMapFadeDistanceStart;
    @JsonProperty("mipMapFadeDistanceEnd")
    public long mipMapFadeDistanceEnd;

}
