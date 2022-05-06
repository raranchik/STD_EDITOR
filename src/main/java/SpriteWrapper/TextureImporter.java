package SpriteWrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "internalIDToNameTable",
        "externalObjects",
        "serializedVersion",
        "mipmaps",
        "bumpmap",
        "isReadable",
        "streamingMipmaps",
        "streamingMipmapsPriority",
        "vTOnly",
        "grayScaleToAlpha",
        "generateCubemap",
        "cubemapConvolution",
        "seamlessCubemap",
        "textureFormat",
        "maxTextureSize",
        "textureSettings",
        "nPOTScale",
        "lightmap",
        "compressionQuality",
        "spriteMode",
        "spriteExtrude",
        "spriteMeshType",
        "alignment",
        "spritePivot",
        "spritePixelsToUnits",
        "spriteBorder",
        "spriteGenerateFallbackPhysicsShape",
        "alphaUsage",
        "alphaIsTransparency",
        "spriteTessellationDetail",
        "textureType",
        "textureShape",
        "singleChannelComponent",
        "flipbookRows",
        "flipbookColumns",
        "maxTextureSizeSet",
        "compressionQualitySet",
        "textureFormatSet",
        "ignorePngGamma",
        "applyGammaDecoding",
        "platformSettings",
        "spriteSheet",
        "spritePackingTag",
        "pSDRemoveMatte",
        "pSDShowRemoveMatteOption",
        "userData",
        "assetBundleName",
        "assetBundleVariant"
})
@Generated("jsonschema2pojo")
public class TextureImporter {

    @JsonProperty("internalIDToNameTable")
    public List<Object> internalIDToNameTable = null;
    @JsonProperty("externalObjects")
    public ExternalObjects externalObjects;
    @JsonProperty("serializedVersion")
    public long serializedVersion;
    @JsonProperty("mipmaps")
    public Mipmaps mipmaps;
    @JsonProperty("bumpmap")
    public Bumpmap bumpmap;
    @JsonProperty("isReadable")
    public long isReadable;
    @JsonProperty("streamingMipmaps")
    public long streamingMipmaps;
    @JsonProperty("streamingMipmapsPriority")
    public long streamingMipmapsPriority;
    @JsonProperty("vTOnly")
    public long vTOnly;
    @JsonProperty("grayScaleToAlpha")
    public long grayScaleToAlpha;
    @JsonProperty("generateCubemap")
    public long generateCubemap;
    @JsonProperty("cubemapConvolution")
    public long cubemapConvolution;
    @JsonProperty("seamlessCubemap")
    public long seamlessCubemap;
    @JsonProperty("textureFormat")
    public long textureFormat;
    @JsonProperty("maxTextureSize")
    public long maxTextureSize;
    @JsonProperty("textureSettings")
    public TextureSettings textureSettings;
    @JsonProperty("nPOTScale")
    public long nPOTScale;
    @JsonProperty("lightmap")
    public long lightmap;
    @JsonProperty("compressionQuality")
    public long compressionQuality;
    @JsonProperty("spriteMode")
    public long spriteMode;
    @JsonProperty("spriteExtrude")
    public long spriteExtrude;
    @JsonProperty("spriteMeshType")
    public long spriteMeshType;
    @JsonProperty("alignment")
    public long alignment;
    @JsonProperty("spritePivot")
    public SpritePivot spritePivot;
    @JsonProperty("spritePixelsToUnits")
    public long spritePixelsToUnits;
    @JsonProperty("spriteBorder")
    public SpriteBorder spriteBorder;
    @JsonProperty("spriteGenerateFallbackPhysicsShape")
    public long spriteGenerateFallbackPhysicsShape;
    @JsonProperty("alphaUsage")
    public long alphaUsage;
    @JsonProperty("alphaIsTransparency")
    public long alphaIsTransparency;
    @JsonProperty("spriteTessellationDetail")
    public long spriteTessellationDetail;
    @JsonProperty("textureType")
    public long textureType;
    @JsonProperty("textureShape")
    public long textureShape;
    @JsonProperty("singleChannelComponent")
    public long singleChannelComponent;
    @JsonProperty("flipbookRows")
    public long flipbookRows;
    @JsonProperty("flipbookColumns")
    public long flipbookColumns;
    @JsonProperty("maxTextureSizeSet")
    public long maxTextureSizeSet;
    @JsonProperty("compressionQualitySet")
    public long compressionQualitySet;
    @JsonProperty("textureFormatSet")
    public long textureFormatSet;
    @JsonProperty("ignorePngGamma")
    public long ignorePngGamma;
    @JsonProperty("applyGammaDecoding")
    public long applyGammaDecoding;
    @JsonProperty("platformSettings")
    public List<PlatformSetting> platformSettings = null;
    @JsonProperty("spriteSheet")
    public SpriteSheet spriteSheet;
    @JsonProperty("spritePackingTag")
    public Object spritePackingTag;
    @JsonProperty("pSDRemoveMatte")
    public long pSDRemoveMatte;
    @JsonProperty("pSDShowRemoveMatteOption")
    public long pSDShowRemoveMatteOption;
    @JsonProperty("userData")
    public Object userData;
    @JsonProperty("assetBundleName")
    public Object assetBundleName;
    @JsonProperty("assetBundleVariant")
    public Object assetBundleVariant;

}
