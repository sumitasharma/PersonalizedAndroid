package com.example.android.android_me.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;


public class MasterListFragment extends Fragment {
    private final static String TAG = "MasterListFragment";
    onImageClickListner mCallBack;

    public MasterListFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);
        GridView masterListView = (GridView) rootView.findViewById(R.id.master_list);
        MasterListAdapter masterListAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        masterListView.setAdapter(masterListAdapter);
        masterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mCallBack.onImageSelected(position);
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallBack = (onImageClickListner) context;
        } catch (ClassCastException e) {
            Log.i(TAG, "implement onImageClistListner");
        }
    }

    public interface onImageClickListner {
        void onImageSelected(int position);
    }
}
