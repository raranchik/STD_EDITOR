package LevelDataWrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "MonoBehaviour"
})
@Generated("jsonschema2pojo")
public class MonoBehaviour {

    @JsonProperty("MonoBehaviour")
    public MonoBehaviour__1 monoBehaviour;

}

