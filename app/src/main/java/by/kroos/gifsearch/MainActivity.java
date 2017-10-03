package by.kroos.gifsearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.jellytoolbar.listener.JellyListener;
import com.yalantis.jellytoolbar.widget.JellyToolbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static by.kroos.gifsearch.RequestType.*;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String API_KEY = "fWieUtS84ZkjIWupFAQvqpUapoYj1k29";
    private ApiInterface apiInterface;

    JellyToolbar jellyToolbar;
    AppCompatEditText editText;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ImageView imageView;
    TextView titleTextView;
    TextView toolbarTextView;
    String request;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        imageView = (ImageView) findViewById(R.id.imageView);
        titleTextView = (TextView) findViewById(R.id.title);
        toolbarTextView = (TextView) findViewById(R.id.toolbar_title);

        toolbarTextView.setOnClickListener(onToolbarTitleClick);

        jellyToolbar = (JellyToolbar) findViewById(R.id.toolbar);
        jellyToolbar.setJellyListener(jellyListener);

        editText = (AppCompatEditText) LayoutInflater.from(this).inflate(R.layout.edit_text, null);
        editText.setBackgroundResource(R.color.colorTransparent);
        editText.setTextColor(ContextCompat.getColor(this, R.color.white));
        jellyToolbar.setContentView(editText);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiInterface = ApiService.getClient().create(ApiInterface.class);

        getGIFs(TYPE_TRENDING);

    }

    View.OnClickListener onToolbarTitleClick = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            getGIFs(TYPE_TRENDING);
        }
    };

    private final JellyListener jellyListener = new JellyListener() {
        @Override
        public void onCancelIconClicked() {
            hideSoftKeyboard(editText);
            if (TextUtils.isEmpty(editText.getText())) {
                jellyToolbar.collapse();
            } else {
                request = editText.getText().toString();
                editText.getText().clear();
                jellyToolbar.collapse();
                getGIFs(TYPE_SEARCH);
            }
        }

        @Override
        public void onToolbarExpandingStarted() {
            super.onToolbarExpandingStarted();
            showSoftKeyboard(editText);
            toolbarTextView.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onToolbarCollapsingStarted() {
            super.onToolbarCollapsingStarted();
            toolbarTextView.setVisibility(View.VISIBLE);
        }
    };

    private void getGIFs(final RequestType request_type) {

        final Call<Feed> call;
        switch (request_type) {
            case TYPE_SEARCH:
                titleTextView.setText(getString(R.string.gifs_for).concat(" ").concat(request));
                call = apiInterface.getSearch(request, API_KEY);
                getFeed(call);
                break;
            case TYPE_TRENDING:
                titleTextView.setText(R.string.trending_now);
                call = apiInterface.getTrendingNow(API_KEY);
                getFeed(call);
                break;
            default:
                titleTextView.setText(R.string.trending_now);
                call = apiInterface.getTrendingNow(API_KEY);
                getFeed(call);
                break;
        }

    }

    private void getFeed(final Call<Feed> call) {
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(@NonNull final Call<Feed> call, @NonNull final Response<Feed> response) {
                Log.d(TAG, "onResponse: Server Response: " + response.toString());
                final List<Data> data = response.body().getData();
                if (data.size() != 0) {
                    recyclerAdapter = new RecyclerAdapter(data, getApplication());
                    recyclerView.setBackgroundColor(
                            ContextCompat.getColor(getApplicationContext(), R.color.black));
                    recyclerView.setAdapter(recyclerAdapter);
                } else {
                    recyclerAdapter = new RecyclerAdapter(data, getApplication());
                    recyclerView.setBackgroundColor(
                            ContextCompat.getColor(getApplicationContext(), R.color.white));
                    titleTextView.setText(getString(R.string.no_gifs).concat(" ").concat(request));
                    recyclerView.setAdapter(recyclerAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull final Call<Feed> call, @NonNull final Throwable t) {
                Log.e(TAG, "Something went wrong");
                Toast.makeText(getApplicationContext(),
                        "Something went wrong. Check your connection to Internet"
                        , Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void showSoftKeyboard(final View view){
        if(view.requestFocus()){
            final InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void hideSoftKeyboard(final View view){
        final InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
