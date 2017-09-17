package by.kroos.gifsearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anton on 17.09.2017.
 */

public class Original {

    @SerializedName("url")
    private String url;

    @SerializedName("width")
    private String width;

    @SerializedName("height")
    private String height;

    public String getUrl() {
        return url;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }
}
