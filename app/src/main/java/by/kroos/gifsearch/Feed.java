package by.kroos.gifsearch;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by anton on 17.09.2017.
 */

class Feed {

    @SerializedName("data")
    private ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }
}
