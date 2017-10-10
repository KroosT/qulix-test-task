package by.kroos.gifsearch;

import com.google.gson.annotations.SerializedName;

class Data {

    @SerializedName("id")
    private String mId;

    @SerializedName("images")
    private Images mImages;

    public String getId() {
        return mId + "";
    }

    Images getImages() {
        return new Images(mImages);
    }
}
