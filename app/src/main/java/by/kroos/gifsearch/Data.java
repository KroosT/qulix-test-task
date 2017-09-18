package by.kroos.gifsearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anton on 17.09.2017.
 */

class Data {

    @SerializedName("id")
    private String id;

    @SerializedName("images")
    private Images images;

    public String getId() {
        return id;
    }

    public Images getImages() {
        return images;
    }
}
