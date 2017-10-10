package by.kroos.gifsearch;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class Feed {

    @SerializedName("data")
    private final List<Data> mData;

    public Feed(final List<Data> data) {
        this.mData = data;
    }

    public List<Data> getData() {
        if (mData == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(mData);
    }
}
