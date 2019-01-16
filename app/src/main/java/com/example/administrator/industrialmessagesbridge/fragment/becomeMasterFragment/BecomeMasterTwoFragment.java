package com.example.administrator.industrialmessagesbridge.fragment.becomeMasterFragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.activity.activityDao.BecomeMasterDao;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BecomeMasterTwoFragment extends Fragment {


 private   Button nextBn2;
BecomeMasterDao becomeMasterDao;
    public BecomeMasterTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_become_master_two, container, false);
        nextBn2=(Button)view.findViewById(R.id.nextBn2);
        becomeMasterDao.fragmentTwoOnclick(nextBn2);
        return view;
    }

    public void setBecomeMasterDao(BecomeMasterDao becomeMasterDao) {
        this.becomeMasterDao = becomeMasterDao;
    }
}
