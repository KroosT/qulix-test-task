package by.kroos.gifsearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anton on 17.09.2017.
 */

class Images {

    @SerializedName("original")
    private Original original;

    Original getOriginal() {
        return original;
    }
}
