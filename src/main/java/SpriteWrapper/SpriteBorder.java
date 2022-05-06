package SpriteWrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "x",
        "y",
        "z",
        "w"
})
@Generated("jsonschema2pojo")
public class SpriteBorder {

    @JsonProperty("x")
    public long x;
    @JsonProperty("y")
    public long y;
    @JsonProperty("z")
    public long z;
    @JsonProperty("w")
    public long w;

}
