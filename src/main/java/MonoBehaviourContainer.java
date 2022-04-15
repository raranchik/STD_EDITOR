import com.fasterxml.jackson.annotation.JsonProperty;

class MonoBehaviourContainer {
    @JsonProperty
    private MonoBehaviour monoBehaviour;

    public MonoBehaviour getMonoBehaviour() { return monoBehaviour; }
    public void setMonoBehaviour(MonoBehaviour value) { this.monoBehaviour = value; }
}

class MonoBehaviour {
    private long m_ObjectHideFlags;
    private MCorrespondingSourceObject m_CorrespondingSourceObject;
    private MCorrespondingSourceObject m_PrefabInstance;
    private MCorrespondingSourceObject m_PrefabAsset;
    private MCorrespondingSourceObject m_GameObject;
    private long m_Enabled;
    private long m_EditorHideFlags;
    private MScript mScript;
    private String m_Name;
    private Object m_EditorClassIdentifier;
    private MScript pictureFirst;
    private MScript pictureSecond;
    private long timerTotalTime;
    private long timerMiddleBound;
    private long timerTopBound;
    private MScript[] differences;
}

class MScript {
    private long fileID;
    private String guid;
    private long type;

    public long getFileID() { return fileID; }
    public void setFileID(long value) { this.fileID = value; }

    public String getGUID() { return guid; }
    public void setGUID(String value) { this.guid = value; }

    public long getType() { return type; }
    public void setType(long value) { this.type = value; }
}

class MCorrespondingSourceObject {
    private long fileID;
}
