package LevelDataWrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fileID"
})
@Generated("jsonschema2pojo")
public class MCorrespondingSourceObject {

    @JsonProperty("fileID")
    public long fileID;

}
