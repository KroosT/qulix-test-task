package by.kroos.gifsearch;

import com.google.gson.annotations.SerializedName;

class Original {

    @SerializedName("url")
    private final String url;

    Original() {
        url = "";
    }

    Original(final Original original) {
        if (original == null) {
            url = "";
        } else {
            url = original.getUrl();
        }
    }

    String getUrl() {
        return url + "";
    }
}
