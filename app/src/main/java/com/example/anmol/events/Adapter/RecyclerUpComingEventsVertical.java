package com.example.anmol.events.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.anmol.events.Adapter.BookMyShow.BookMyShowAdapter;
import com.example.anmol.events.Adapter.EventsHigh.RecyclerOutDoorAdapter;
import com.example.anmol.events.Adapter.EventsHigh.RecyclerTechnologyAdapter;
import com.example.anmol.events.Adapter.EventsHigh.RecyclerTodayAdapter;
import com.example.anmol.events.Adapter.Insider.InsiderAdapter;
import com.example.anmol.events.Data.BookMyShow.bookMyShowData;
import com.example.anmol.events.Data.EventsHigh.OutDoorsEventsHigh;
import com.example.anmol.events.Data.EventsHigh.TechnologyEventsHigh;
import com.example.anmol.events.Data.EventsHigh.TodayEventsHigh;
import com.example.anmol.events.Data.Insider.InsiderData;
import com.example.anmol.events.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



public class RecyclerUpComingEventsVertical extends RecyclerView.Adapter<RecyclerUpComingEventsVertical.ViewHolder> {


    Context ctx;

    String s[]={"BookMyShow","Insider Events","Events High"};



    public RecyclerUpComingEventsVertical(Context ctx) {


        this.ctx=ctx;

    }


    class ViewHolder extends RecyclerView.ViewHolder{


        TextView tx;
        ImageView im;
        RelativeLayout relativeLayout;
        RecyclerView rv;
        RecyclerView.LayoutManager layoutManager;
        RecyclerView.Adapter adapter;

        public ViewHolder(View itemView)
        {
            super(itemView);

            relativeLayout=itemView.findViewById(R.id.AllEventRel);
            tx=itemView.findViewById(R.id.TextViewContent);
            im=itemView.findViewById(R.id.imagenewvertical);
            rv=itemView.findViewById(R.id.UpcomingEventsMainScreenSecondRecycler);
            layoutManager=new LinearLayoutManager(ctx,LinearLayoutManager.HORIZONTAL,false);

        }


    }


    @Override
    public RecyclerUpComingEventsVertical.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RecyclerUpComingEventsVertical.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_main_vertical,parent,false));

    }

    @Override
    public void onBindViewHolder(final RecyclerUpComingEventsVertical.ViewHolder holder, int position) {


        holder.tx.setText(s[position]);
        holder.rv.setLayoutManager(holder.layoutManager);

        // Horizontal Recycler View

        // Step By Step

        // Book My Show Event
        if (position==0){

            holder.rv.setVisibility(View.VISIBLE);
            holder.im.setVisibility(View.GONE);

            bookMyShowData bookMyShowData=new bookMyShowData(ctx);
            bookMyShowData.getData(new bookMyShowData.bookMyShowcallback() {
                @Override
                public void getData(List<List<String>> li) {

                    List<String> image=li.get(0);
                    List<String> titleL=li.get(1);
                    List<String> dateL=li.get(2);
                    List<String> tagL=li.get(3);
                    List<String> buyNowL=li.get(4);

                    System.out.println("");

                    holder.adapter=new BookMyShowAdapter(ctx,image,titleL,dateL,tagL,buyNowL);
                    holder.rv.setAdapter(holder.adapter);

                }
            });


        }

        //Insider Events Data
        else if (position==1){


            holder.rv.setVisibility(View.VISIBLE);
            holder.im.setVisibility(View.GONE);

            InsiderData insiderData=new InsiderData(ctx);
            insiderData.getDataInsider(new InsiderData.InsiderCallBack() {
                @Override
                public void result(List<List<String>> list) {

                    List<String> name=list.get(0);
                    List<String> image=list.get(1);
                    List<String> date=list.get(2);
                    List<String> place=list.get(3);
                    List<String> href=list.get(4);

                    holder.adapter=new InsiderAdapter(ctx,image,name,date,place,href);
                    holder.rv.setAdapter(holder.adapter);

                }
            });

        }

        else if (position==2){
            holder.rv.setVisibility(View.GONE);
            holder.im.setVisibility(View.VISIBLE);
            Picasso.with(ctx).load("http://www.teamboca.com/wp-content/uploads/2016/06/coming-soon.png.man_.png").fit().into(holder.im);
        }



    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

