package by.kroos.gifsearch;

import com.google.gson.annotations.SerializedName;

class Images {

    @SerializedName("original")
    private final Original original;

    Images(final Images images) {
        if (images == null) {
            original = new Original();
        } else {
            original = images.getOriginal();
        }
    }

    Original getOriginal() {
        return new Original(original);
    }
}
