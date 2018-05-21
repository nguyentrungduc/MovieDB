package com.ptit.btl.moviedb.screen.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.screen.BaseActivity;
import com.ptit.btl.moviedb.screen.cast.ImageDetailPageAdapter;
import com.ptit.btl.moviedb.util.Constant;

import java.util.List;

/**
 * Created by CuongHQ on 5/20/2018.
 */

public class MovieContainerActivity extends BaseActivity {

    private ViewPager viewPager;
    private ImageDetailPageAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        initToolbar();
        init();
    }

    private void initToolbar() {
        View includeToolbar = findViewById(R.id.toolbar_result);
        Toolbar toolbar = includeToolbar.findViewById(R.id.toolbar_detail);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
        setTitle(getString(R.string.title_loading));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setTitle(getIntent().getStringExtra(Constant.BUNDLE_PEOPLE_NAME));
    }

    protected void init(){
        viewPager = findViewById(R.id.viewpager);
        final List<String> image =getIntent().getStringArrayListExtra("image");
        final int pos = getIntent().getIntExtra("pos",0);
        adapter = new ImageDetailPageAdapter(image,this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(pos);
        getSupportActionBar().setTitle((pos+1)+"/"+image.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setTitle((position+1)+"/"+image.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
