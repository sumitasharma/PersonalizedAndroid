/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {

    public final static String IMAGEID = "imageid";
    public final static String INDEXID = "indexid";


    public List<Integer> mImages;
    // ex. index = 0 is the first image id in the given list , index 1 is the second, and so on
    public Integer mIndex;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment
     */
    public BodyPartFragment() {
    }

    public void setmImages(List<Integer> mImages) {
        this.mImages = mImages;
    }

    public void setmIndex(Integer mIndex) {
        this.mIndex = mIndex;
    }

    /**
     * Inflates the fragment layout file and sets the correct resource for the image to display
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt(INDEXID);
            mImages = savedInstanceState.getIntegerArrayList(IMAGEID);
        }

        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        // Get a reference to the ImageView in the fragment layout
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);
        
        // Otherwise, create a Log statement that indicates that the list was not found
        if (mImages != null) {
            imageView.setImageResource(mImages.get(mIndex));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mIndex >= mImages.size() - 1) {
                        mIndex = -1;
                    }
                    mIndex = mIndex + 1;
                    imageView.setImageResource(mImages.get(mIndex));

                }

            });
        } else
            Log.i("", "error");

        // Return the rootView
        return rootView;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(INDEXID, mIndex);
        outState.putIntegerArrayList(IMAGEID, (ArrayList<Integer>) mImages);
    }
}
