package com.example.administrator.industrialmessagesbridge.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.adapter.adapterModel.HomeFunction;
import com.example.administrator.industrialmessagesbridge.myView.RoundImageView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragmentFunctionAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder> {
   private List<HomeFunction>homeFunctionList;
   private FunctionClick functionClick;

    public HomeFragmentFunctionAdapter(List<HomeFunction> homeFunctionList) {
        this.homeFunctionList = homeFunctionList;
    }

    public class HomeFragmentFunctionViewHoler extends RecyclerView.ViewHolder{
        CircleImageView function_bn;
        TextView need_tv;
       public HomeFragmentFunctionViewHoler(@NonNull View itemView) {
           super(itemView);
           function_bn=(CircleImageView)itemView.findViewById(R.id.function_bn);
           need_tv=(TextView)itemView.findViewById(R.id.need_tv);
       }
   }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,int i) {
        return new HomeFragmentFunctionViewHoler(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_fragment_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {
        HomeFragmentFunctionViewHoler holer=(HomeFragmentFunctionViewHoler)viewHolder;
        holer.function_bn.setImageResource(homeFunctionList.get(i).getFunctionPhoto());
        holer.function_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionClick.functionClick(i);
            }
        });
        holer.need_tv.setText(homeFunctionList.get(i).getFunctionText());
    }

    @Override
    public int getItemCount() {
        return homeFunctionList.size();
    }
    public interface FunctionClick{
        public void functionClick(int position);
    }

    public void setFunctionClick(FunctionClick functionClick) {
        this.functionClick = functionClick;
    }
}
