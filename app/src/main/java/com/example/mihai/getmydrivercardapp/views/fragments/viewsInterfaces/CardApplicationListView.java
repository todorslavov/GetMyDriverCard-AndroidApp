package com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces;

import com.example.mihai.getmydrivercardapp.models.CardApplication;

import java.util.List;

public interface CardApplicationListView extends BaseView, ErrorView {
    void showApplications(List<CardApplication> cardApplications);
    void showEmptyListMessage();
}