package SpriteWrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fileFormatVersion",
        "guid",
        "TextureImporter"
})
@Generated("jsonschema2pojo")
public class Sprite {

    @JsonProperty("fileFormatVersion")
    public long fileFormatVersion;
    @JsonProperty("guid")
    public String guid;
    @JsonProperty("TextureImporter")
    public TextureImporter textureImporter;

}

