package me.chen_wei.zhihu.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.chen_wei.zhihu.R;
import me.chen_wei.zhihu.network.model.Latest;

/**
 * Created by Hander on 16/2/26.
 * <p/>
 * Email : hander_wei@163.com
 */
public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder> {

    private List<Latest.StoriesEntity> entities;
    private Context mContext;

    public StoriesAdapter(List<Latest.StoriesEntity> entities, Context context){
        this.entities = entities;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView img;

        public ViewHolder(View itemView){
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.story_title);
            img = (ImageView)itemView.findViewById(R.id.story_img);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View storyView = inflater.inflate(R.layout.item_story, parent, false);

        ViewHolder vh = new ViewHolder(storyView);
        return vh;
    }

    @Override
    public void onBindViewHolder(StoriesAdapter.ViewHolder holder, int position) {
        Latest.StoriesEntity entity = entities.get(position);

        TextView title = holder.title;
        title.setText(entity.getTitle());

        ImageView img = holder.img;
        Picasso.with(mContext).load(entity.getImages().get(0)).into(img);
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }
}
