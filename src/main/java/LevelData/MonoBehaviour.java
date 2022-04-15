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
class Difference {

    @JsonProperty("fileID")
    public Long fileID;
    @JsonProperty("guid")
    public String guid;
    @JsonProperty("type")
    public Integer type;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fileID"
})
class MCorrespondingSourceObject {

    @JsonProperty("fileID")
    public Long fileID;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fileID"
})
class MGameObject {

    @JsonProperty("fileID")
    public Long fileID;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fileID"
})
class MPrefabAsset {

    @JsonProperty("fileID")
    public Long fileID;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fileID"
})
class MPrefabInstance {

    @JsonProperty("fileID")
    public Long fileID;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fileID",
        "guid",
        "type"
})
class MScript {

    @JsonProperty("fileID")
    public Long fileID;
    @JsonProperty("guid")
    public String guid;
    @JsonProperty("type")
    public Integer type;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "MonoBehaviour"
})
public class MonoBehaviour {

    @JsonProperty("MonoBehaviour")
    public MonoBehaviour__1 monoBehaviour;

}

