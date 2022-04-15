package LevelData;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fileID",
        "guid",
        "type"
})
public class PictureFirst {

    @JsonProperty("fileID")
    public Long fileID;
    @JsonProperty("guid")
    public String guid;
    @JsonProperty("type")
    public Integer type;

}
