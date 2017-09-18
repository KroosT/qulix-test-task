package by.kroos.gifsearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by anton on 17.09.2017.
 */

public interface ApiInterface {

    @GET("search")
    Call<Feed> getSearch(@Query("q") String request, @Query("api_key") String api_key);

    @GET("trending")
    Call<Feed> getTrendingNow(@Query("api_key") String api_key);

}
