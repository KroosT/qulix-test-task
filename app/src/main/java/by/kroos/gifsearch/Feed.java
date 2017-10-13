package by.kroos.gifsearch;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anton on 17.09.2017.
 */

class Feed {

    @SerializedName("data")
    private List<Data> mData;

    public List<Data> getData() {
        return mData;
    }
}
