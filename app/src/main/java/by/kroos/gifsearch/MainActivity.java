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
    private ApiInterface mApiInterface;

    JellyToolbar mJellyToolbar;
    AppCompatEditText mEditText;
    RecyclerView mRecyclerView;
    RecyclerAdapter mRecyclerAdapter;
    ImageView mImageView;
    TextView mTitleTextView;
    TextView mToolbarTextView;
    String mRequest;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mImageView = (ImageView) findViewById(R.id.imageView);
        mTitleTextView = (TextView) findViewById(R.id.title);
        mToolbarTextView = (TextView) findViewById(R.id.toolbar_title);

        mToolbarTextView.setOnClickListener(onToolbarTitleClick);

        mJellyToolbar = (JellyToolbar) findViewById(R.id.toolbar);
        mJellyToolbar.setJellyListener(jellyListener);

        mEditText = (AppCompatEditText) LayoutInflater.from(this).inflate(R.layout.edit_text, null);
        mEditText.setBackgroundResource(R.color.colorTransparent);
        mEditText.setTextColor(ContextCompat.getColor(this, R.color.white));
        mJellyToolbar.setContentView(mEditText);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mApiInterface = ApiService.getClient().create(ApiInterface.class);

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
            hideSoftKeyboard(mEditText);
            if (TextUtils.isEmpty(mEditText.getText())) {
                mJellyToolbar.collapse();
            } else {
                mRequest = mEditText.getText().toString();
                mEditText.getText().clear();
                mJellyToolbar.collapse();
                getGIFs(TYPE_SEARCH);
            }
        }

        @Override
        public void onToolbarExpandingStarted() {
            super.onToolbarExpandingStarted();
            showSoftKeyboard(mEditText);
            mToolbarTextView.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onToolbarCollapsingStarted() {
            super.onToolbarCollapsingStarted();
            mToolbarTextView.setVisibility(View.VISIBLE);
        }
    };

    private void getGIFs(final RequestType request_type) {

        final Call<Feed> call;
        switch (request_type) {
            case TYPE_SEARCH:
                mTitleTextView.setText(getString(R.string.gifs_for).concat(" ").concat(mRequest));
                call = mApiInterface.getSearch(mRequest, API_KEY);
                getFeed(call);
                break;
            case TYPE_TRENDING:
                mTitleTextView.setText(R.string.trending_now);
                call = mApiInterface.getTrendingNow(API_KEY);
                getFeed(call);
                break;
            default:
                mTitleTextView.setText(R.string.trending_now);
                call = mApiInterface.getTrendingNow(API_KEY);
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
                    mRecyclerAdapter = new RecyclerAdapter(data, getApplication());
                    mRecyclerView.setBackgroundColor(
                            ContextCompat.getColor(getApplicationContext(), R.color.black));
                    mRecyclerView.setAdapter(mRecyclerAdapter);
                } else {
                    mRecyclerAdapter = new RecyclerAdapter(data, getApplication());
                    mRecyclerView.setBackgroundColor(
                            ContextCompat.getColor(getApplicationContext(), R.color.white));
                    mTitleTextView.setText(getString(R.string.no_gifs).concat(" ").concat(mRequest));
                    mRecyclerView.setAdapter(mRecyclerAdapter);
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
