package com.example.mihai.getmydrivercardapp.views.fragments.interfaces;

import com.example.mihai.getmydrivercardapp.models.User;

public interface ApplicationStatusView extends BaseView, Loadable, Navigable {
    void showStatus(String message, int drawable);
    void setUser(User user);

}
