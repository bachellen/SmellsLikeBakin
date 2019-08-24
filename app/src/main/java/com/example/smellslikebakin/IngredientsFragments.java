package com.example.smellslikebakin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class IngredientsFragments extends Fragment {
    private static final String KEY_CHECKED_BOXES = "key_checked_boxes";
    private  CheckBox[] mCheckBox;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);
        View view = inflater.inflate(R.layout.fragments_ingrediens, container,
                false);

        LinearLayout linearLayout = view.findViewById(R.id.ingredientsLayout);
        String [] ingredients = Recipes.ingredients[index].split("`");
        mCheckBox = new CheckBox[ingredients.length];
        boolean[] checkedBoxes = new boolean[mCheckBox.length];
        if(savedInstanceState!=null&& savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES)!=null){
            checkedBoxes = savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES)
;        }
        this.setUpCheckBoxes(ingredients, linearLayout, checkedBoxes);


        return view;
    }

    private void setUpCheckBoxes(String[] ingredients, ViewGroup container, boolean[] checkedBoxes){
        int i =0;
        for (String ingredient : ingredients) {
            mCheckBox[i] = new CheckBox(getActivity());
            mCheckBox[i].setPadding(8, 16, 8, 16);
            mCheckBox[i].setTextSize(20f);
            mCheckBox[i].setText(ingredient);
            container.addView(mCheckBox[i]);
            if (checkedBoxes[i]){
                mCheckBox[i].toggle();
            }
            i++;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        boolean[] stateOfCheckBoxes = new boolean[mCheckBox.length];
        int i =0;
        for(CheckBox checkBox: mCheckBox){
            stateOfCheckBoxes[i++]= checkBox.isChecked();
        }
        outState.putBooleanArray(KEY_CHECKED_BOXES, stateOfCheckBoxes);
        super.onSaveInstanceState(outState);
    }
}
