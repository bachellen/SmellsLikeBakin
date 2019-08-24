package com.example.smellslikebakin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ViewPagerFragment extends Fragment {

    public static final String KEY_RECIPE_INDEX = "recipe_index";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(KEY_RECIPE_INDEX);
        getActivity().setTitle(Recipes.names[index]);
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);

        final IngredientsFragments ingredientsFragments = new IngredientsFragments();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RECIPE_INDEX, index );
        ingredientsFragments.setArguments(bundle);
        final directionFragments directionFragments = new directionFragments();
        bundle = new Bundle();
        bundle.putInt(KEY_RECIPE_INDEX, index );
        directionFragments.setArguments(bundle);
        ViewPager viewPager = view.findViewById(R.id.ViewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return i == 0 ? ingredientsFragments : directionFragments;

            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return position == 0 ? "Ingredients": "Directions";

            }

            @Override
            public int getCount() { return 2; }
        });

        TabLayout tabLayout= view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }
}
