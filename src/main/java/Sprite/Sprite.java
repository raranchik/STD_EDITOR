package Sprite;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "convertToNormalMap",
        "externalNormalMap",
        "heightScale",
        "normalMapFilter"
})
class Bumpmap {

    @JsonProperty("convertToNormalMap")
    public Integer convertToNormalMap;
    @JsonProperty("externalNormalMap")
    public Integer externalNormalMap;
    @JsonProperty("heightScale")
    public Float heightScale;
    @JsonProperty("normalMapFilter")
    public Integer normalMapFilter;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({

})
class ExternalObjects {


}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "mipMapMode",
        "enableMipMap",
        "sRGBTexture",
        "linearTexture",
        "fadeOut",
        "borderMipMap",
        "mipMapsPreserveCoverage",
        "alphaTestReferenceValue",
        "mipMapFadeDistanceStart",
        "mipMapFadeDistanceEnd"
})
class Mipmaps {

    @JsonProperty("mipMapMode")
    public Integer mipMapMode;
    @JsonProperty("enableMipMap")
    public Integer enableMipMap;
    @JsonProperty("sRGBTexture")
    public Integer sRGBTexture;
    @JsonProperty("linearTexture")
    public Integer linearTexture;
    @JsonProperty("fadeOut")
    public Integer fadeOut;
    @JsonProperty("borderMipMap")
    public Integer borderMipMap;
    @JsonProperty("mipMapsPreserveCoverage")
    public Integer mipMapsPreserveCoverage;
    @JsonProperty("alphaTestReferenceValue")
    public Float alphaTestReferenceValue;
    @JsonProperty("mipMapFadeDistanceStart")
    public Integer mipMapFadeDistanceStart;
    @JsonProperty("mipMapFadeDistanceEnd")
    public Integer mipMapFadeDistanceEnd;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "serializedVersion",
        "buildTarget",
        "maxTextureSize",
        "resizeAlgorithm",
        "textureFormat",
        "textureCompression",
        "compressionQuality",
        "crunchedCompression",
        "allowsAlphaSplitting",
        "overridden",
        "androidETC2FallbackOverride",
        "forceMaximumCompressionQuality_BC6H_BC7"
})
class PlatformSetting {

    @JsonProperty("serializedVersion")
    public Integer serializedVersion;
    @JsonProperty("buildTarget")
    public String buildTarget;
    @JsonProperty("maxTextureSize")
    public Integer maxTextureSize;
    @JsonProperty("resizeAlgorithm")
    public Integer resizeAlgorithm;
    @JsonProperty("textureFormat")
    public Integer textureFormat;
    @JsonProperty("textureCompression")
    public Integer textureCompression;
    @JsonProperty("compressionQuality")
    public Integer compressionQuality;
    @JsonProperty("crunchedCompression")
    public Integer crunchedCompression;
    @JsonProperty("allowsAlphaSplitting")
    public Integer allowsAlphaSplitting;
    @JsonProperty("overridden")
    public Integer overridden;
    @JsonProperty("androidETC2FallbackOverride")
    public Integer androidETC2FallbackOverride;
    @JsonProperty("forceMaximumCompressionQuality_BC6H_BC7")
    public Integer forceMaximumCompressionQualityBC6HBC7;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fileFormatVersion",
        "guid",
        "TextureImporter"
})
public class Sprite {

    @JsonProperty("fileFormatVersion")
    public Integer fileFormatVersion;
    @JsonProperty("guid")
    public String guid;
    @JsonProperty("TextureImporter")
    public TextureImporter textureImporter;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "x",
        "y",
        "z",
        "w"
})
class SpriteBorder {

    @JsonProperty("x")
    public Integer x;
    @JsonProperty("y")
    public Integer y;
    @JsonProperty("z")
    public Integer z;
    @JsonProperty("w")
    public Integer w;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "x",
        "y"
})
class SpritePivot {

    @JsonProperty("x")
    public Float x;
    @JsonProperty("y")
    public Float y;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "serializedVersion",
        "sprites",
        "outline",
        "physicsShape",
        "bones",
        "spriteID",
        "internalID",
        "vertices",
        "indices",
        "edges",
        "weights",
        "secondaryTextures"
})
class SpriteSheet {

    @JsonProperty("serializedVersion")
    public Integer serializedVersion;
    @JsonProperty("sprites")
    public List<Object> sprites = null;
    @JsonProperty("outline")
    public List<Object> outline = null;
    @JsonProperty("physicsShape")
    public List<Object> physicsShape = null;
    @JsonProperty("bones")
    public List<Object> bones = null;
    @JsonProperty("spriteID")
    public String spriteID;
    @JsonProperty("internalID")
    public Integer internalID;
    @JsonProperty("vertices")
    public List<Object> vertices = null;
    @JsonProperty("indices")
    public Object indices;
    @JsonProperty("edges")
    public List<Object> edges = null;
    @JsonProperty("weights")
    public List<Object> weights = null;
    @JsonProperty("secondaryTextures")
    public List<Object> secondaryTextures = null;

}

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
class TextureImporter {

    @JsonProperty("internalIDToNameTable")
    public List<Object> internalIDToNameTable = null;
    @JsonProperty("externalObjects")
    public ExternalObjects externalObjects;
    @JsonProperty("serializedVersion")
    public Integer serializedVersion;
    @JsonProperty("mipmaps")
    public Mipmaps mipmaps;
    @JsonProperty("bumpmap")
    public Bumpmap bumpmap;
    @JsonProperty("isReadable")
    public Integer isReadable;
    @JsonProperty("streamingMipmaps")
    public Integer streamingMipmaps;
    @JsonProperty("streamingMipmapsPriority")
    public Integer streamingMipmapsPriority;
    @JsonProperty("vTOnly")
    public Integer vTOnly;
    @JsonProperty("grayScaleToAlpha")
    public Integer grayScaleToAlpha;
    @JsonProperty("generateCubemap")
    public Integer generateCubemap;
    @JsonProperty("cubemapConvolution")
    public Integer cubemapConvolution;
    @JsonProperty("seamlessCubemap")
    public Integer seamlessCubemap;
    @JsonProperty("textureFormat")
    public Integer textureFormat;
    @JsonProperty("maxTextureSize")
    public Integer maxTextureSize;
    @JsonProperty("textureSettings")
    public TextureSettings textureSettings;
    @JsonProperty("nPOTScale")
    public Integer nPOTScale;
    @JsonProperty("lightmap")
    public Integer lightmap;
    @JsonProperty("compressionQuality")
    public Integer compressionQuality;
    @JsonProperty("spriteMode")
    public Integer spriteMode;
    @JsonProperty("spriteExtrude")
    public Integer spriteExtrude;
    @JsonProperty("spriteMeshType")
    public Integer spriteMeshType;
    @JsonProperty("alignment")
    public Integer alignment;
    @JsonProperty("spritePivot")
    public SpritePivot spritePivot;
    @JsonProperty("spritePixelsToUnits")
    public Integer spritePixelsToUnits;
    @JsonProperty("spriteBorder")
    public SpriteBorder spriteBorder;
    @JsonProperty("spriteGenerateFallbackPhysicsShape")
    public Integer spriteGenerateFallbackPhysicsShape;
    @JsonProperty("alphaUsage")
    public Integer alphaUsage;
    @JsonProperty("alphaIsTransparency")
    public Integer alphaIsTransparency;
    @JsonProperty("spriteTessellationDetail")
    public Integer spriteTessellationDetail;
    @JsonProperty("textureType")
    public Integer textureType;
    @JsonProperty("textureShape")
    public Integer textureShape;
    @JsonProperty("singleChannelComponent")
    public Integer singleChannelComponent;
    @JsonProperty("flipbookRows")
    public Integer flipbookRows;
    @JsonProperty("flipbookColumns")
    public Integer flipbookColumns;
    @JsonProperty("maxTextureSizeSet")
    public Integer maxTextureSizeSet;
    @JsonProperty("compressionQualitySet")
    public Integer compressionQualitySet;
    @JsonProperty("textureFormatSet")
    public Integer textureFormatSet;
    @JsonProperty("ignorePngGamma")
    public Integer ignorePngGamma;
    @JsonProperty("applyGammaDecoding")
    public Integer applyGammaDecoding;
    @JsonProperty("platformSettings")
    public List<PlatformSetting> platformSettings = null;
    @JsonProperty("spriteSheet")
    public SpriteSheet spriteSheet;
    @JsonProperty("spritePackingTag")
    public Object spritePackingTag;
    @JsonProperty("pSDRemoveMatte")
    public Integer pSDRemoveMatte;
    @JsonProperty("pSDShowRemoveMatteOption")
    public Integer pSDShowRemoveMatteOption;
    @JsonProperty("userData")
    public Object userData;
    @JsonProperty("assetBundleName")
    public Object assetBundleName;
    @JsonProperty("assetBundleVariant")
    public Object assetBundleVariant;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "serializedVersion",
        "filterMode",
        "aniso",
        "mipBias",
        "wrapU",
        "wrapV",
        "wrapW"
})
class TextureSettings {

    @JsonProperty("serializedVersion")
    public Integer serializedVersion;
    @JsonProperty("filterMode")
    public Integer filterMode;
    @JsonProperty("aniso")
    public Integer aniso;
    @JsonProperty("mipBias")
    public Integer mipBias;
    @JsonProperty("wrapU")
    public Integer wrapU;
    @JsonProperty("wrapV")
    public Integer wrapV;
    @JsonProperty("wrapW")
    public Integer wrapW;

}