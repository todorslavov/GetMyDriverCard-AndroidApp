package com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;

public interface ContactDetailsView extends BaseView{
    void setUser(User user);
    void CardApplication(CardApplication cardApplication);
    void assignValues();

    void navigateToNextView();
}
