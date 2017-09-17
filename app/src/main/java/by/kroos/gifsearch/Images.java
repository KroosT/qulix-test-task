package by.kroos.gifsearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anton on 17.09.2017.
 */

public class Images {

    @SerializedName("original")
    private Original original;

    public Original getOriginal() {
        return original;
    }
}
