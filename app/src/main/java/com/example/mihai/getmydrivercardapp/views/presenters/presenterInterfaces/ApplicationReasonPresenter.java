package com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces;

import com.example.mihai.getmydrivercardapp.models.CardApplication;

public interface ApplicationReasonPresenter extends BasePresenter{
    void handleOnCheckedChange(int id);
    void handlePositiveButtonOnclick(String reason, CardApplication cardApplication);
}
