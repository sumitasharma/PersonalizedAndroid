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
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    int headIndex = 0;
    int bodyIndex = 0;
    int legIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        if (getIntent() != null) {
            headIndex = b.getInt("HeadIndex", 0);
            bodyIndex = b.getInt("BodyIndex", 0);
            legIndex = b.getInt("LegIndex", 0);
        }
        if (savedInstanceState == null) {
            // Create a new head BodyPartFragment
            BodyPartFragment headFragment = new BodyPartFragment();
            headFragment.setmImages(AndroidImageAssets.getHeads());
            headFragment.setmIndex(headIndex);
            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setmImages(AndroidImageAssets.getBodies());
            bodyFragment.setmIndex(bodyIndex);
            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setmImages(AndroidImageAssets.getLegs());
            legFragment.setmIndex(legIndex);

            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();
            fragmentManager.beginTransaction().add(R.id.body_container, bodyFragment).commit();
            fragmentManager.beginTransaction().add(R.id.leg_container, legFragment).commit();
        }
    }
}
