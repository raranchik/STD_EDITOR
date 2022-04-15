package LevelData;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "m_ObjectHideFlags",
        "m_CorrespondingSourceObject",
        "m_PrefabInstance",
        "m_PrefabAsset",
        "m_GameObject",
        "m_Enabled",
        "m_EditorHideFlags",
        "m_Script",
        "m_Name",
        "m_EditorClassIdentifier",
        "pictureFirst",
        "pictureSecond",
        "timerTotalTime",
        "timerMiddleBound",
        "timerTopBound",
        "differences"
})
public class MonoBehaviour__1 {

    @JsonProperty("m_ObjectHideFlags")
    public Integer mObjectHideFlags;
    @JsonProperty("m_CorrespondingSourceObject")
    public MCorrespondingSourceObject mCorrespondingSourceObject;
    @JsonProperty("m_PrefabInstance")
    public MPrefabInstance mPrefabInstance;
    @JsonProperty("m_PrefabAsset")
    public MPrefabAsset mPrefabAsset;
    @JsonProperty("m_GameObject")
    public MGameObject mGameObject;
    @JsonProperty("m_Enabled")
    public Integer mEnabled;
    @JsonProperty("m_EditorHideFlags")
    public Integer mEditorHideFlags;
    @JsonProperty("m_Script")
    public MScript mScript;
    @JsonProperty("m_Name")
    public String mName;
    @JsonProperty("m_EditorClassIdentifier")
    public Object mEditorClassIdentifier;
    @JsonProperty("pictureFirst")
    public PictureFirst pictureFirst;
    @JsonProperty("pictureSecond")
    public PictureSecond pictureSecond;
    @JsonProperty("timerTotalTime")
    public Integer timerTotalTime;
    @JsonProperty("timerMiddleBound")
    public Integer timerMiddleBound;
    @JsonProperty("timerTopBound")
    public Integer timerTopBound;
    @JsonProperty("differences")
    public List<Difference> differences = null;

}
