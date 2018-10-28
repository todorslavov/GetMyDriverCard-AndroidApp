package com.example.mihai.getmydrivercardapp.daggerconfig;

import com.example.mihai.getmydrivercardapp.views.fragments.ImageCaptureFragment;
import com.example.mihai.getmydrivercardapp.views.fragments.LogInFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ImageCaptureFragment imageCaptureFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract LogInFragment logInFragment();
}
