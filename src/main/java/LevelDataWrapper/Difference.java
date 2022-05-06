package LevelDataWrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "position",
        "size"
})
@Generated("jsonschema2pojo")
public class Difference {

    @JsonProperty("position")
    public Position position;
    @JsonProperty("size")
    public Size size;

}
