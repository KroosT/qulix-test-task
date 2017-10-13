package by.kroos.gifsearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anton on 17.09.2017.
 */

class Data {

    @SerializedName("id")
    private String mId;

    @SerializedName("images")
    private Images mImages;

    public String getId() {
        return mId;
    }

    Images getImages() {
        return mImages;
    }
}
