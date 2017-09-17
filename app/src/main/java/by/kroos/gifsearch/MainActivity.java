package by.kroos.gifsearch;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.yalantis.jellytoolbar.listener.JellyListener;
import com.yalantis.jellytoolbar.widget.JellyToolbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String API_KEY = "fWieUtS84ZkjIWupFAQvqpUapoYj1k29";
    private static final String BASE_URL = "http://api.giphy.com/v1/gifs/";
    private ApiInterface apiInterface;

    JellyToolbar jellyToolbar;
    AppCompatEditText editText;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        imageView = (ImageView) findViewById(R.id.imageView);

        jellyToolbar = (JellyToolbar) findViewById(R.id.toolbar);
        jellyToolbar.setJellyListener(jellyListener);

        editText = (AppCompatEditText) LayoutInflater.from(this).inflate(R.layout.edit_text, null);
        editText.setBackgroundResource(R.color.colorTransparent);
        jellyToolbar.setContentView(editText);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiInterface = ApiService.getClient().create(ApiInterface.class);

    }

    private JellyListener jellyListener = new JellyListener() {
        @Override
        public void onCancelIconClicked() {
            if (TextUtils.isEmpty(editText.getText())) {
                jellyToolbar.collapse();
            } else {
                String request = editText.getText().toString();
                editText.getText().clear();
                jellyToolbar.collapse();

                Call<Feed> call = apiInterface.getStuff(request, API_KEY);
                call.enqueue(new Callback<Feed>() {
                    @Override
                    public void onResponse(@NonNull Call<Feed> call, @NonNull Response<Feed> response) {
                        Log.d(TAG, "onResponse: Server Response: " + response.toString());
                        List<Data> data = response.body().getData();
                        recyclerAdapter = new RecyclerAdapter(data, getApplication());
                        recyclerView.setAdapter(recyclerAdapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Feed> call, @NonNull Throwable t) {
                        Log.e(TAG, "Something went wrong");
                    }
                });
            }
        }
    };
}
