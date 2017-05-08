package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity
    implements MasterListFragment.OnImageClickListener{

    private int headIndex = -1, bodyIndex = -1, legIndex = -1;

    private Button mNextButton;
    private boolean mTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AndroidMeActivity
                        .makeIntent(getBaseContext(), headIndex, bodyIndex, legIndex));

            }
        });
        mNextButton.setVisibility(View.GONE);
        if (findViewById(R.id.two_pane_linear_layout) != null){
            mTwoPane = true;
            mNextButton.setVisibility(View.GONE);
            AndroidMeActivity.populate(getSupportFragmentManager(),
                    savedInstanceState, null, true);
        }

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "position selected : " + position, Toast.LENGTH_SHORT).show();
        int bodyPart = position/12;
        int listIndex = position - 12*bodyPart;

        BodyPartFragment fragment = new BodyPartFragment();
        int id;
        switch (bodyPart){
            case 0: headIndex = listIndex;
                fragment.setmImageIds(AndroidImageAssets.getHeads());
                id = R.id.head_container;
                //fragment.setmListIndex(headIndex);
                break;
            case 1: bodyIndex = listIndex;
                fragment.setmImageIds(AndroidImageAssets.getBodies());
                id = R.id.body_container;
                break;
            case 2: legIndex = listIndex;
                fragment.setmImageIds(AndroidImageAssets.getLegs());
                id = R.id.leg_container;
                break;
            default:id=0;
        }
        if (mTwoPane == true){
            fragment.setmListIndex(listIndex);
            getSupportFragmentManager().beginTransaction()
                    .replace(id, fragment).commit();
            return;
        }
        if (headIndex != -1 && bodyIndex != -1 && legIndex != -1)
            mNextButton.setVisibility(View.VISIBLE);
    }
}
