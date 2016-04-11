package com.onecode.humam.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.florent37.glidepalette.GlidePalette;

import java.util.List;

/**
 * Created by humam on 11/04/2016.
 */
public class list1 extends RecyclerView.Adapter<list1.holder1> {

    List<item> items;
    // List<items>feed;
    Context ctx;


    public list1(Context ctx, List<item> items) {
        this.items = items;
        // this.feed=feed;
        this.ctx = ctx;
    }




    @Override
    public list1.holder1 onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);

        return new holder1(v);
    }

    @Override
    public void onBindViewHolder(final holder1 holder,  final int position) {
        holder.tdesc.setText(items.get(position).gettitle());
        Glide.with(ctx)
                .load(items.get(position).getimg())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .listener(GlidePalette.with(items.get(position).getimg())

                        .intoCallBack(new GlidePalette.CallBack() {

                            @Override
                            public void onPaletteLoaded(Palette palette) {


                                if (palette != null) {
                                    Palette.Swatch s = palette.getVibrantSwatch();
                                    if (s == null) {
                                        s = palette.getDarkVibrantSwatch();
                                    }
                                    if (s == null) {
                                        s = palette.getLightVibrantSwatch();
                                    }
                                    if (s == null) {
                                        s = palette.getMutedSwatch();
                                    }
                                    if (s != null){
                                        holder.tdesc.setTextColor(s.getTitleTextColor());
                                        holder.tdesc.setTextColor(s.getTitleTextColor());
                                        holder.tdesc.setBackgroundColor(s.getRgb());
                                        holder.card.setCardBackgroundColor(s.getRgb());
                                    }

                                }
                            }
                        }))
                .into(holder.imgthumbnail);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent io=new Intent(ctx,newsdetail.class);

                String  name=items.get(position).gettitle();
                Log.d("list1", name);
                String url=items.get(position).getauther();

                io.putExtra("Name",name);
                io.putExtra("url", url);

                ctx.startActivity(io);
                //ADD DATA TO OUR INTENT

                Toast.makeText(ctx, "Clicked on image" + position, Toast.LENGTH_LONG).show();

            }
        });


    }






    @Override
    public int getItemCount() {
        return items.size();
    }



    public  static class holder1 extends RecyclerView.ViewHolder {

        TextView tdesc;
        //  TextView tname;
        ImageView imgthumbnail;
        CardView card;

        holder1(View itemView) {
            super(itemView);
            tdesc = (TextView) itemView.findViewById(R.id.textView);
            // tname = (TextView) itemView.findViewById(R.id.t_name);
            card = (CardView) itemView.findViewById(R.id.cardview);
            imgthumbnail = (ImageView) itemView.findViewById(R.id.imageView);


        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}



