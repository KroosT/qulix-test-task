package by.kroos.gifsearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anton on 17.09.2017.
 */

class Data {

    @SerializedName("username")
    private String author;

    @SerializedName("embed_url")
    private String embed_url;

    @SerializedName("id")
    private String id;

    @SerializedName("images")
    private Images images;

    public String getAuthor() {
        return author;
    }

    public String getEmbed_url() {
        return embed_url;
    }

    public String getId() {
        return id;
    }

    public Images getImages() {
        return images;
    }
}
