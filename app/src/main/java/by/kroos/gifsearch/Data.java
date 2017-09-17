package by.kroos.gifsearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anton on 17.09.2017.
 */

class Data {

    @SerializedName("username")
    private String author;

    @SerializedName("url")
    private String url;

    @SerializedName("id")
    private String id;

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

}
