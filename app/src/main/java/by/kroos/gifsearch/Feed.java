package by.kroos.gifsearch;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class Feed {

    @SerializedName("data")
    private final List<Data> data;

    public Feed(final List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(data);
    }
}
