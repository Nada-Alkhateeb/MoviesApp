package com.example.popularmovies.Data;

import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("id")
    private String Id;
    @SerializedName("iso_639_1")
    private String Iso6391;
    @SerializedName("iso_3166_1")
    private String Iso31661;
    @SerializedName("key")
    private String Key;
    @SerializedName("name")
    private String Name;
    @SerializedName("site")
    private String Site;
    @SerializedName("size")
    private int Size;
    @SerializedName("type")
    private String Type;

    public String getKey() {
        return Key;
    }

    public String getName() {
        return Name;
    }

    public String getSite() {
        return Site;
    }

    public int getSize() {
        return Size;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setSize(int size) {
        Size = size;
    }

    public void setSite(String site) {
        Site = site;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIso6391() {
        return Iso6391;
    }

    public void setIso6391(String iso6391) {
        Iso6391 = iso6391;
    }

    public String getIso31661() {
        return Iso31661;
    }

    public void setIso31661(String iso31661) {
        Iso31661 = iso31661;
    }
}
