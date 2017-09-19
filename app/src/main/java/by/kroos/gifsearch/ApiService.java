package by.kroos.gifsearch;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anton on 17.09.2017.
 */

class ApiService {

    private static final String BASE_URL = "http://api.giphy.com/v1/gifs/";
    private static Retrofit retrofit = null;

    static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;

    }
}
