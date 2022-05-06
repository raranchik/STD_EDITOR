package SpriteWrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "serializedVersion",
        "filterMode",
        "aniso",
        "mipBias",
        "wrapU",
        "wrapV",
        "wrapW"
})
@Generated("jsonschema2pojo")
public class TextureSettings {

    @JsonProperty("serializedVersion")
    public long serializedVersion;
    @JsonProperty("filterMode")
    public long filterMode;
    @JsonProperty("aniso")
    public long aniso;
    @JsonProperty("mipBias")
    public long mipBias;
    @JsonProperty("wrapU")
    public long wrapU;
    @JsonProperty("wrapV")
    public long wrapV;
    @JsonProperty("wrapW")
    public long wrapW;

}
