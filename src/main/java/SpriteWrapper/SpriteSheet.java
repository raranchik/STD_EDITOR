package SpriteWrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "serializedVersion",
        "sprites",
        "outline",
        "physicsShape",
        "bones",
        "spriteID",
        "internalID",
        "vertices",
        "indices",
        "edges",
        "weights",
        "secondaryTextures"
})
@Generated("jsonschema2pojo")
public class SpriteSheet {

    @JsonProperty("serializedVersion")
    public long serializedVersion;
    @JsonProperty("sprites")
    public List<Object> sprites = null;
    @JsonProperty("outline")
    public List<Object> outline = null;
    @JsonProperty("physicsShape")
    public List<Object> physicsShape = null;
    @JsonProperty("bones")
    public List<Object> bones = null;
    @JsonProperty("spriteID")
    public String spriteID;
    @JsonProperty("internalID")
    public long internalID;
    @JsonProperty("vertices")
    public List<Object> vertices = null;
    @JsonProperty("indices")
    public Object indices;
    @JsonProperty("edges")
    public List<Object> edges = null;
    @JsonProperty("weights")
    public List<Object> weights = null;
    @JsonProperty("secondaryTextures")
    public List<Object> secondaryTextures = null;

}
