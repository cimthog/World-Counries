package com.divineventures.worldcountries;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadrosid.svgloader.SvgLoader;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.viewHolder> {

    private List<MyData> dataList;
    private Context context;

    public RvAdapter(Context context, List<MyData> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.country_list,viewGroup,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder viewHolder, int i) {
        viewHolder.tv_name.setText(dataList.get(i).getName());
        viewHolder.tv_region.setText("Region: "+dataList.get(i).getRegion());
        viewHolder.tv_pop.setText("Population: "+dataList.get(i).getPopulation());
        SvgLoader.pluck().with((Activity)context).load(dataList.get(i).getFlag(), viewHolder.flag);

        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = dataList.get(viewHolder.getAdapterPosition()).getName();
                String region = dataList.get(viewHolder.getAdapterPosition()).getRegion();

                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra("Name",name);
                intent.putExtra("Region",region);
                Toast.makeText(context,"You clicked "+dataList.get(viewHolder.getAdapterPosition()).getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ImageView flag;

        private TextView tv_name, tv_region, tv_pop;
        private LinearLayout layout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            flag = itemView.findViewById(R.id.image_flag);
            tv_name = itemView.findViewById(R.id.country_name);
            tv_pop = itemView.findViewById(R.id.country_population);
            tv_region = itemView.findViewById(R.id.country_region);
            layout = itemView.findViewById(R.id.item_layout);
        }

    }
}
