package singledevapps.schoolcomtask.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import singledevapps.schoolcomtask.R;
import singledevapps.schoolcomtask.data.DataList;
import singledevapps.schoolcomtask.model.PostData;

/**
 * Created by prakash on 11/6/17.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    private Context mContext;
    private static   DataAdapter dataAdapter;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView bck;
        public ViewPager mPager;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            bck = (ImageView) view.findViewById(R.id.background);
            mPager = (ViewPager) view.findViewById(R.id.pager);


        }
    }

    public static DataAdapter instance(Context context){
        if (dataAdapter==null) {
            dataAdapter = new DataAdapter(context);
        }
        return dataAdapter;
    }


    public DataAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lay_post_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        PostData post = DataList.DataList().get(position);
        holder.title.setText(post.getPostText());
       // holder.bck.setImageURI(post.getPostUri());
        Toast.makeText(mContext,post.getPostUri().size()+"",Toast.LENGTH_SHORT).show();
        ImageSwipeAdapter adapter = new ImageSwipeAdapter(mContext,post.getPostUri());
        holder.mPager.setAdapter(adapter);
      //  Picasso.with(mContext).load(news.getUrlToImage()).transform(new BlurTransformation(mContext)).into(holder.bck);
    }

    @Override
    public int getItemCount() {
        return DataList.DataList().size();
    }
}
 