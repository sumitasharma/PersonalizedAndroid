package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.onImageClickListner {
    int headIndex;
    int legIndex;
    int bodyIndex;
    boolean twopane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.android_me_linear_layout) != null) {
            twopane = true;

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
        } else {
            twopane = false;
        }

    }

    @Override
    public void onImageSelected(int position) {
        int bodyPartNumber = position / 12;
        int indexNumber = position - bodyPartNumber * 12;

        if (twopane) {
            Log.i("Twopane value is :", "true");
            BodyPartFragment newFragment = new BodyPartFragment();
            switch (bodyPartNumber) {
                case 0:
                    newFragment.setmImages(AndroidImageAssets.getHeads());
                    newFragment.setmIndex(indexNumber);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .commit();
                    break;
                case 1:
                    newFragment.setmImages(AndroidImageAssets.getBodies());
                    newFragment.setmIndex(indexNumber);
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_container, newFragment).commit();
                    break;
                case 2:
                    newFragment.setmImages(AndroidImageAssets.getLegs());
                    newFragment.setmIndex(indexNumber);
                    getSupportFragmentManager().beginTransaction().replace(R.id.leg_container, newFragment).commit();
                    break;
            }

        } else {
            Log.i("Twopane value is :", "false");
            switch (bodyPartNumber) {
                case 0:
                    headIndex = indexNumber;
                    break;
                case 1:
                    bodyIndex = indexNumber;
                    break;
                case 2:
                    legIndex = indexNumber;
                    break;
            }
            Bundle b = new Bundle();
            b.putInt("HeadIndex", headIndex);
            b.putInt("BodyIndex", bodyIndex);
            b.putInt("LegIndex", legIndex);
            Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(b);
            startActivity(intent);

        }
    }
}
