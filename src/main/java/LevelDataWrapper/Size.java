package LevelDataWrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "x",
        "y"
})
@Generated("jsonschema2pojo")
public class Size {

    @JsonProperty("x")
    public long x;
    @JsonProperty("y")
    public long y;

}
