package me.chen_wei.zhihu.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zanlabs.widget.infiniteviewpager.InfinitePagerAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import me.chen_wei.zhihu.R;
import me.chen_wei.zhihu.event.LoadContentEvent;
import me.chen_wei.zhihu.network.model.Latest;

/**
 * Created by Hander on 16/2/27.
 * <p/>
 * Email : hander_wei@163.com
 */
public class TopStoriesAdapter extends InfinitePagerAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Latest.TopStoriesEntity> topStories;

    public TopStoriesAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setDataList(List<Latest.TopStoriesEntity> entities) {
        topStories = entities;
    }

    @Override
    public int getItemCount() {
        return topStories == null ? 0 : topStories.size();
    }

    @Override
    public View getView(int position, View view, ViewGroup container) {
        ViewHolder vh;
        if (view != null) {
            vh = (ViewHolder) view.getTag();
        } else {
            view = mInflater.inflate(R.layout.item_top_story, container, false);
            vh = new ViewHolder(view);
            view.setTag(vh);
        }
        final Latest.TopStoriesEntity entity = topStories.get(position);
        vh.title.setText(entity.getTitle());
        Picasso.with(mContext).load(entity.getImage()).into(vh.img);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new LoadContentEvent(entity.getId()));
            }
        });
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.img_top_story)
        ImageView img;
        @Bind(R.id.tv_top_story_title)
        TextView title;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
