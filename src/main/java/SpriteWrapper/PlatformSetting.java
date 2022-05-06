package SpriteWrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "serializedVersion",
        "buildTarget",
        "maxTextureSize",
        "resizeAlgorithm",
        "textureFormat",
        "textureCompression",
        "compressionQuality",
        "crunchedCompression",
        "allowsAlphaSplitting",
        "overridden",
        "androidETC2FallbackOverride",
        "forceMaximumCompressionQuality_BC6H_BC7"
})
@Generated("jsonschema2pojo")
public class PlatformSetting {

    @JsonProperty("serializedVersion")
    public long serializedVersion;
    @JsonProperty("buildTarget")
    public String buildTarget;
    @JsonProperty("maxTextureSize")
    public long maxTextureSize;
    @JsonProperty("resizeAlgorithm")
    public long resizeAlgorithm;
    @JsonProperty("textureFormat")
    public long textureFormat;
    @JsonProperty("textureCompression")
    public long textureCompression;
    @JsonProperty("compressionQuality")
    public long compressionQuality;
    @JsonProperty("crunchedCompression")
    public long crunchedCompression;
    @JsonProperty("allowsAlphaSplitting")
    public long allowsAlphaSplitting;
    @JsonProperty("overridden")
    public long overridden;
    @JsonProperty("androidETC2FallbackOverride")
    public long androidETC2FallbackOverride;
    @JsonProperty("forceMaximumCompressionQuality_BC6H_BC7")
    public long forceMaximumCompressionQualityBC6HBC7;

}
