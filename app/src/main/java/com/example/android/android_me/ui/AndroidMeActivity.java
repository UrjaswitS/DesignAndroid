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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    private static final String HEAD = "head", BODY = "body", LEG ="leg";


    public static Intent makeIntent(Context context, int head, int body, int leg){
        Bundle data = new Bundle();
        data.putInt(HEAD, head);
        data.putInt(BODY, body);
        data.putInt(LEG, leg);
        return new Intent(context, AndroidMeActivity.class)
                .putExtras(data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        populate(getSupportFragmentManager(), savedInstanceState, this, false);
    }

    public static void populate(FragmentManager manager,
                                Bundle savedInstanceState,
                                Activity c, boolean twoPane)
    {
        BodyPartFragment headFragment = new BodyPartFragment(),
                bodyFragment = new BodyPartFragment(),
                legFragment = new BodyPartFragment();
        headFragment.setmImageIds(AndroidImageAssets.getHeads());
        bodyFragment.setmImageIds(AndroidImageAssets.getBodies());
        legFragment.setmImageIds(AndroidImageAssets.getLegs());

        if (savedInstanceState == null)
            if (!twoPane) {
                Bundle data = c.getIntent().getExtras();
                headFragment.setmListIndex(data.getInt(HEAD));
                bodyFragment.setmListIndex(data.getInt(BODY));
                legFragment.setmListIndex(data.getInt(LEG));
            }else {
                headFragment.setmListIndex(1);
                bodyFragment.setmListIndex(1);
                legFragment.setmListIndex(1);
            }

        manager.beginTransaction()
                .add(R.id.head_container, headFragment)
                .add(R.id.body_container, bodyFragment)
                .add(R.id.leg_container, legFragment)
                .commit();
    }
}
