package com.nhpatt.myconference.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.nhpatt.myconference.R;
import com.nhpatt.myconference.entities.TalkEvent;
import com.nhpatt.myconference.fragments.DayFragment;
import com.nhpatt.myconference.usecases.TalksUseCase;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class AgendaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda);

        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.agenda_drawerlayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.agenda_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        ViewPager viewPager = (ViewPager) findViewById(R.id.agenda_viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.agenda_tablayout);
        tabLayout.setupWithViewPager(viewPager);

        NavigationView navigationView = (NavigationView) findViewById(R.id.agenda_navigationview);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.agenda:
                        return true;
                    case R.id.explore:
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        DayFragment firstDayFragment = createDayFragment(R.color.accent_material_light);
        DayFragment secondDayFragment = createDayFragment(R.color.ripple_material_light);

        adapter.addFrag(firstDayFragment, "JUEVES 19");
        adapter.addFrag(secondDayFragment, "VIERNES 20");
        viewPager.setAdapter(adapter);
    }

    @NonNull
    private DayFragment createDayFragment(int color) {
        DayFragment dayFragment = new DayFragment();
        Bundle args = new Bundle();
        args.putInt("color", getResources().getColor(color));
        dayFragment.setArguments(args);
        return dayFragment;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void findTalks() {
        TalksUseCase talksUseCase = new TalksUseCase();
        talksUseCase.alternativeRun();
    }

    public void onEventMainThread(TalkEvent response) {
        Toast.makeText(this, response.getTalks().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}