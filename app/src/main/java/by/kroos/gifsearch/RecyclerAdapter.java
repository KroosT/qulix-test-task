package by.kroos.gifsearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    private final Context mContext;

    private List<Data> mData;

    RecyclerAdapter(final List<Data> data, final Context context) {
        this.mData = data;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Glide.with(mContext)
                .load(mData.get(position).getImages().getOriginal().getUrl())
                .apply(RequestOptions.placeholderOf(R.mipmap.ic_downloading))
                .into(holder.imageView);
        Log.d(TAG, "OnBind2");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;

        MyViewHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public void updateData(final List<Data> data) {
        mData = data;
    }
}
