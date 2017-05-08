package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UrJasWitK on 28-Apr-17.
 */

public class BodyPartFragment extends Fragment {

    private static final String TAG = BodyPartFragment.class.getCanonicalName();
    private static final String LIST_INDEX = "list index";
    private static final String IMAGE_LIST = "image list";

    private List<Integer> mImageIds;
    private int mListIndex;

    public BodyPartFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        final ImageView img = (ImageView)rootView.findViewById(R.id.body_part_image_view);

        if (savedInstanceState != null) {
            setmImageIds(savedInstanceState.getIntegerArrayList(IMAGE_LIST));
            setmListIndex(savedInstanceState.getInt(LIST_INDEX));
        }

        if (mImageIds != null){
            img.setImageResource(mImageIds.get(mListIndex));
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListIndex < mImageIds.size()-1)
                        mListIndex++;
                    else mListIndex=0;
                    img.setImageResource(mImageIds.get(mListIndex));
                }
            });
        }
        else Log.e(TAG, "the fragment has null list of image id's");
        return rootView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LIST_INDEX, mListIndex);
        outState.putIntegerArrayList(IMAGE_LIST, (ArrayList<Integer>)mImageIds);
    }

    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public void setmListIndex(int mListIndex) {
        this.mListIndex = mListIndex;
    }
}
