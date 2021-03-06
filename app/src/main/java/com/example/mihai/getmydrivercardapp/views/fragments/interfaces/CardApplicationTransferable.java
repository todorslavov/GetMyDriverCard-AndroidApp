package com.example.mihai.getmydrivercardapp.views.fragments.interfaces;

import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;

public interface CardApplicationTransferable {
    void setLoggedUser(User user);
    void setCurrentCardApplication(CardApplication cardApplication);
}
