package by.kroos.gifsearch;

import com.google.gson.annotations.SerializedName;

class Data {

    @SerializedName("id")
    private String id;

    @SerializedName("images")
    private Images images;

    public String getId() {
        return id + "";
    }

    Images getImages() {
        return new Images(images);
    }
}
