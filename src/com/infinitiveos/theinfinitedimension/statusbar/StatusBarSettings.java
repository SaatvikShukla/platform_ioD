/*
* Copyright (C) 2017 Infinitive OS
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.infinitiveos.theinfinitedimension.statusbar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.support.v7.preference.PreferenceScreen;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infinitiveos.theinfinitedimension.PagerSlidingTabStrip;
import com.infinitiveos.theinfinitedimension.statusbar.tabs.ClockSettings;
import com.infinitiveos.theinfinitedimension.statusbar.tabs.StatusbarGestures;
import com.android.settings.R;
import com.android.settings.Utils;
import com.android.settings.SettingsPreferenceFragment;

import com.android.internal.logging.MetricsProto.MetricsEvent;

import java.util.ArrayList;
import java.util.List;

public class StatusBarSettings extends SettingsPreferenceFragment {

    ViewPager mViewPager;
    String titleString[];
    ViewGroup mContainer;
    PagerSlidingTabStrip mTabs;

    static Bundle mSavedState;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContainer = container;
	final ActionBar actionBar = getActivity().getActionBar();
        //actionBar.setIcon(R.drawable.ic_settings_io);

        View view = inflater.inflate(R.layout.theinfinitedimension_ui, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
	mTabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);

        StatusBarAdapter StatusBarAdapter = new StatusBarAdapter(getFragmentManager());
        mViewPager.setAdapter(StatusBarAdapter);
       
	mTabs.setViewPager(mViewPager);
        setHasOptionsMenu(true);
        return view;
    }

   @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle saveState) {
        super.onSaveInstanceState(saveState);
    }

    @Override
    public void onResume() {
        super.onResume();
            mContainer.setPadding(0, 0, 0, 0);
    }

    class StatusBarAdapter extends FragmentPagerAdapter {
        String titles[] = getTitles();
        private Fragment frags[] = new Fragment[titles.length];

        public StatusBarAdapter(FragmentManager fm) {
            super(fm);
	    frags[0] = new ClockSettings();
	    frags[1] = new StatusbarGestures();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return frags[position];
        }

        @Override
        public int getCount() {
            return frags.length;
        }
    }

    private String[] getTitles() {
        String titleString[];
        titleString = new String[]{
		    getString(R.string.clock_tab_title),
		    getString(R.string.statusbar_gestures_title)};
        return titleString;
    }

    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.THEINFINITEDIMENSION;
    }
}
