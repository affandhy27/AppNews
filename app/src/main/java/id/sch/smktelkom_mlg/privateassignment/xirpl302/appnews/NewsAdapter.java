package id.sch.smktelkom_mlg.privateassignment.xirpl302.appnews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Affandhy on 14/05/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> newses;
    private Context context;

    public NewsAdapter(List<News> newses, Context context) {
        this.newses = newses;
        this.context = context;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.berita, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, final int position) {
        final News news = newses.get(position);

        holder.title.setText(news.getJudul());
        holder.caption.setText(news.getDeskrip());
        holder.section.setText(news.getBuat());

        Glide
                .with(context)
                .load(news.getGambar())
                .into(holder.url);


    }

    @Override
    public int getItemCount() {
        return newses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView section;
        public TextView title;
        public ImageView url;
        public TextView caption;


        public ViewHolder(View itemView) {
            super(itemView);


            section = (TextView) itemView.findViewById(R.id.tvc);
            title = (TextView) itemView.findViewById(R.id.tva);
            url = (ImageView) itemView.findViewById(R.id.gambar);
            caption = (TextView) itemView.findViewById(R.id.tvb);

        }

    }
}
