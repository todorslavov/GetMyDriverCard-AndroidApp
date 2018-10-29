package com.example.mihai.getmydrivercardapp.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.views.adapters.CardApplicationArrayAdapter;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.CardApplicationListView;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.BasePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.presenterInterfaces.CardApplicationListPresenter;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardApplicationListFragment extends Fragment implements CardApplicationListView {

    @BindView(R.id.lv_applications)
    ListView mListView;

    private CardApplicationArrayAdapter mAdapter;
    private CardApplicationListPresenter cardApplicationListPresenter;

    @Inject
    public CardApplicationListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_application_list, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new CardApplicationArrayAdapter(Objects.requireNonNull(getContext()), R.layout.custom_list_item);
        mListView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        cardApplicationListPresenter
                .loadCardApplications();
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        if ( presenter instanceof CardApplicationListPresenter) {
            this.cardApplicationListPresenter = (CardApplicationListPresenter) presenter;
        } else {
            throw new InvalidParameterException();
        }
    }

    @Override
    public void showApplications(List<CardApplication> cardApplications) {
        Objects.requireNonNull(getActivity())
                .runOnUiThread(() -> {
            mAdapter.clear();
            mAdapter.addAll(cardApplications);
        });
    }

    @Override
    public void showEmptyListMessage() {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            Toast.makeText(getContext(),
                    "The list is empty!",
                    Toast.LENGTH_SHORT)
                    .show();
        });
    }

    @Override
    public void showError(Exception e) {

        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            Toast.makeText(getContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_SHORT)
                    .show();
        });
    }
}