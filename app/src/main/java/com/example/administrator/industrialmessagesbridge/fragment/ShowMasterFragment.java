package com.example.administrator.industrialmessagesbridge.fragment;




import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.activity.BusinessNeed;
import com.example.administrator.industrialmessagesbridge.activity.MasterIntroduce;
import com.example.administrator.industrialmessagesbridge.activity.ShowNeedDetail;
import com.example.administrator.industrialmessagesbridge.adapter.MasterShowAdapter;
import com.example.administrator.industrialmessagesbridge.adapter.NeedShowAdapter;
import com.example.administrator.industrialmessagesbridge.adapter.adapterModel.NeedShow;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowMasterFragment extends Fragment {
private SmartRefreshLayout refreshLayout;
private RecyclerView show_need_rv;
    private List<NeedShow> needShowList;
    private NeedShowAdapter needShowAdapter;//需求展示的适配器
   private MasterShowAdapter masterShowAdapter;//专家适配器
    public ShowMasterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=null;
       final Bundle bundle=  getArguments();
        if (bundle.getInt("master")==0) {
           v= inflater.inflate(R.layout.fragment_show_master, container, false);
            refreshLayout = (SmartRefreshLayout) v.findViewById(R.id.refreshLayout);
            show_need_rv = (RecyclerView) v.findViewById(R.id.show_need_rv);
            initData0();
           // Toast.makeText(getActivity(),"标签1Id"+bundle.get("lableOneId")+"标签2Id"+bundle.get("lableTwoId"),Toast.LENGTH_SHORT).show();
            return v;
        }if (getArguments().getInt("master")==1){
            v= inflater.inflate(R.layout.master_show, container, false);
            refreshLayout = (SmartRefreshLayout) v.findViewById(R.id.refreshLayout);
            show_need_rv = (RecyclerView) v.findViewById(R.id.show_need_rv);
            masterShowAdapter=new MasterShowAdapter(null);
            masterShowAdapter.setItemOnclick(new MasterShowAdapter.ItemOnclick() {
                @Override
                public void onclick(int id) {
                    Intent intent=new Intent(getContext(), MasterIntroduce.class);
                    startActivity(intent);
                    Toast.makeText(getActivity(),"lableOneId"+bundle.get("lableOneId")+"lableTwoId"+bundle.get("lableTwoId"),Toast.LENGTH_SHORT).show();
                }
            });
            show_need_rv.setAdapter(masterShowAdapter);
            show_need_rv.setLayoutManager(new LinearLayoutManager(getActivity()));

            return v;
        }
        return v;
    }
public void initData0(){
    needShowList=new ArrayList<>();
    needShowList.add(new NeedShow(0,"抓电鳗大赛征集","华山之巅",
            "100000","池塘里水满了 雨也停了田边的稀泥里到处是泥鳅天天我等着你 等着你捉泥鳅大哥哥 好不好咱们去捉泥鳅小牛的哥哥 带着他捉泥鳅,大哥哥,好不好咱们去捉泥鳅池塘里水满了 雨也停了田边的稀泥里 到处是泥鳅天天我等着你 等着你捉泥鳅大哥哥 好不好" +
            "咱们去捉泥鳅小牛的哥哥 带着他捉泥鳅大哥哥 好不好咱们去捉泥鳅大哥哥 好不好咱们去捉泥鳅大哥哥 好不好咱们去捉泥鳅 大哥哥 好不好咱们去捉泥鳅 大哥哥 好不好 咱们去捉泥鳅 大哥哥 好不好 咱们去捉泥鳅池塘里水满了 雨也停了田边的稀泥里到处是泥鳅天天我等着你 等着你捉泥鳅大哥哥 好不好咱们去捉泥鳅小牛的哥哥 带着他捉泥鳅,大哥哥,好不好咱们去捉泥鳅池塘里水满了 雨也停了田边的稀泥里 到处是泥鳅天天我等着你 等着你捉泥鳅大哥哥 好不好\" +\n" +
            "                \"咱们去捉泥鳅小牛的哥哥 带着他捉泥鳅大哥哥 好不好咱们去捉泥鳅大哥哥 好不好咱们去捉泥鳅大哥哥 好不好咱们去捉泥鳅 大哥哥 好不好咱们去捉泥鳅 大哥哥 好不好 咱们去捉泥鳅 大哥哥 好不好 咱们去捉泥鳅池塘里水满了 雨也停了田边的稀泥里到处是泥鳅天天我等着你 等着你捉泥鳅大哥哥 好不好咱们去捉泥鳅小牛的哥哥 带着他捉泥鳅,大哥哥,好不好咱们去捉泥鳅池塘里水满了 雨也停了田边的稀泥里 到处是泥鳅天天我等着你 等着你捉泥鳅大哥哥 好不好\" +\n" +
            "                \"咱们去捉泥鳅小牛的哥哥 带着他捉泥鳅大哥哥 好不好咱们去捉泥鳅大哥哥 好不好咱们去捉泥鳅大哥哥 好不好咱们去捉泥鳅 大哥哥 好不好咱们去捉泥鳅 大哥哥 好不好 咱们去捉泥鳅 大哥哥 好不好 咱们去捉泥鳅",
            "81天","5","5","2019,1,1"));
    needShowList.add(new NeedShow(0,"华山论剑","华山之巅",
            "100000","争夺九阴真经",
            "81天","5","5","2019,1,1"));
    needShowList.add(new NeedShow(0,"侠客岛一游","侠客岛",
            "100000","研究武林绝学",
            "81天","5","5","2019,1,1"));
    needShowList.add(new NeedShow(0,"金盆洗手大会","不知道在哪",
            "100000","刘正风正名",
            "81天","5","5","2019,1,1"));
    needShowList.add(new NeedShow(0,"五岳剑派合并","嵩山",
            "100000","五岳剑派",
            "81天","5","5","2019,1,1"));
    needShowList.add(new NeedShow(0,"抓电鳗大赛征集","华山之巅",
            "100000","电鳗，是华山的电鳗，不是加拿大的电鳗，不是",
            "81天","5","5","2019,1,1"));
    needShowAdapter=new NeedShowAdapter(needShowList);
    needShowAdapter.setNeedItemOnclick(new NeedShowAdapter.NeedItemOnclick() {
        @Override
        public void onlick(int needId,NeedShow needshow) {
            Intent intent=new Intent(getActivity(),ShowNeedDetail.class);
            Bundle bundle=new Bundle();
            bundle.putParcelable("needShow",needshow);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    });
    show_need_rv.setAdapter(needShowAdapter);
    show_need_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    /*
     * 加载数据
     * **/
    refreshLayout.setOnRefreshListener(new OnRefreshListener() {
        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            refreshLayout.finishRefresh(2000);
            Toast.makeText(getActivity(),"刷新",Toast.LENGTH_SHORT).show();
        }
    });
    refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
        @Override
        public void onLoadMore(RefreshLayout refreshlayout) {
            Toast.makeText(getActivity(),"加载更多",Toast.LENGTH_SHORT).show();
            refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加再失败
        }
    });
}
}
