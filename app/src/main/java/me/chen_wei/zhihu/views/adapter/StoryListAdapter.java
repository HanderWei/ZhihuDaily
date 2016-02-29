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

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import me.chen_wei.zhihu.R;
import me.chen_wei.zhihu.event.LoadContentEvent;
import me.chen_wei.zhihu.network.model.Contents;

/**
 * Created by Hander on 16/2/26.
 * <p/>
 * Email : hander_wei@163.com
 */
public class StoryListAdapter extends RecyclerView.Adapter<StoryListAdapter.ViewHolder>{

    private List<Contents.StoriesEntity> entities;
    private Context mContext;

    public StoryListAdapter(List<Contents.StoriesEntity> entities, Context context){
        this.entities = entities;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.story_title)
        TextView title;
        @Bind(R.id.story_img)
        ImageView img;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View storyView = inflater.inflate(R.layout.item_story, parent, false);
        final ViewHolder vh = new ViewHolder(storyView);
        storyView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new LoadContentEvent(entities.get(vh.getLayoutPosition()).getId()));
            }
        });


        return vh;
    }

    @Override
    public void onBindViewHolder(StoryListAdapter.ViewHolder holder, int position) {
        Contents.StoriesEntity entity = entities.get(position);

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
