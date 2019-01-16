package com.example.administrator.industrialmessagesbridge.fragment.becomeMasterFragment;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.activity.BecomeMaster;
import com.example.administrator.industrialmessagesbridge.activity.SettingPersonInfo;
import com.example.administrator.industrialmessagesbridge.activity.activityDao.BecomeMasterDao;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BecomeMasterThreeFragment extends Fragment {


      private  Button nextBn3;
      private LinearLayout person_introduce;
      private LinearLayout person_experience;
    private BecomeMasterDao becomeMasterDao;
    private TextView person_introduce_tv;
    private TextView person_experience_tv;
    public BecomeMasterThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_become_master_three, container, false);
        nextBn3=(Button)view.findViewById(R.id.nextBn3);
        person_introduce=(LinearLayout)view.findViewById(R.id.person_introduce);
        person_experience=(LinearLayout)view.findViewById(R.id.person_experience);
        person_introduce_tv=(TextView)view.findViewById(R.id.person_introduce_tv);
        person_experience_tv=(TextView)view.findViewById(R.id.person_experience_tv);
        person_introduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SettingPersonInfo.class);
                intent.putExtra("type",0);
                startActivityForResult(intent,0);
            }
        });
        person_experience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SettingPersonInfo.class);
                intent.putExtra("type",1);
                startActivityForResult(intent,1);
            }
        });
        becomeMasterDao.fragmentThreeOnclick(nextBn3);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       switch (requestCode){
           case 0:
               Bundle bundle0=data.getExtras();
               person_introduce_tv.setText(bundle0.getString("result"));
               break;
           case 1:
               Bundle bundle1=data.getExtras();
               person_experience_tv.setText(bundle1.getString("result"));
               break;
       }
    }

    public void setBecomeMasterDao(BecomeMasterDao becomeMasterDao) {
        this.becomeMasterDao = becomeMasterDao;
    }
}
