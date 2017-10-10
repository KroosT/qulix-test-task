package by.kroos.gifsearch;

import com.google.gson.annotations.SerializedName;

class Images {

    @SerializedName("original")
    private final Original mOriginal;

    Images(final Images images) {
        if (images == null) {
            mOriginal = new Original();
        } else {
            mOriginal = images.getOriginal();
        }
    }

    Original getOriginal() {
        return new Original(mOriginal);
    }
}
