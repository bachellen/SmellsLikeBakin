package com.example.smellslikebakin;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListFragment.OnRecipeSelectedInterface {

    public static final String LIST_FRAGMENT = "list_fragment";
    public static final String VIEWPAGER_FRAGMENT = "viewpager_fragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment savedFragment = getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT);
       if(savedFragment == null) {
           Fragment fragment = new ListFragment();
           FragmentManager fragmentManager = getSupportFragmentManager();
           // FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
           fragmentManager.beginTransaction().replace(R.id.placeHolder, fragment, LIST_FRAGMENT).commit();
       }
    }

    @Override
    public void OnListRecipeSelected(int index) {
        Fragment fragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        // FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentManager.beginTransaction().replace(R.id.placeHolder, fragment, VIEWPAGER_FRAGMENT).addToBackStack(null).commit();

    }
}
