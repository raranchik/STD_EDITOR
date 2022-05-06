package LevelDataWrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fileID",
        "guid",
        "type"
})
@Generated("jsonschema2pojo")
public class PictureFirst {

    @JsonProperty("fileID")
    public long fileID;
    @JsonProperty("guid")
    public String guid;
    @JsonProperty("type")
    public long type;

}
