package by.kroos.gifsearch;

import com.google.gson.annotations.SerializedName;

class Original {

    @SerializedName("url")
    private final String mUrl;

    Original() {
        mUrl = "";
    }

    Original(final Original original) {
        if (original == null) {
            mUrl = "";
        } else {
            mUrl = original.getUrl();
        }
    }

    String getUrl() {
        return mUrl + "";
    }
}
