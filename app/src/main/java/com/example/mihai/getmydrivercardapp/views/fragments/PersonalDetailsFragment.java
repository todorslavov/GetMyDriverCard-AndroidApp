package com.example.mihai.getmydrivercardapp.views.fragments;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.constants.Formats;
import com.example.mihai.getmydrivercardapp.constants.IntentKeys;
import com.example.mihai.getmydrivercardapp.customannotations.dateformat.DateFormat;
import com.example.mihai.getmydrivercardapp.customannotations.latincharacters.LatinCharacters;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.User;
import com.example.mihai.getmydrivercardapp.views.activities.interfaces.Navigator;
import com.example.mihai.getmydrivercardapp.views.fragments.interfaces.PersonalDetailsView;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.BasePresenter;
import com.example.mihai.getmydrivercardapp.views.presenters.interfaces.PersonalDetailsPresenter;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalDetailsFragment extends Fragment implements PersonalDetailsView,
        DatePickerDialog.OnDateSetListener, Validator.ValidationListener {

    @BindView(R.id.tv_user_id) TextView mUserIdTV;
    @BindView(R.id.tv_first_name) TextView mFirstNameTV;
    @BindView(R.id.tv_last_name) TextView mLastNameTV;
    @BindView(R.id.tv_birth_date) TextView mBirthDateTV;

    @BindView(R.id.et_user_id)
    @NotEmpty
    EditText mUserIdET;

    @BindView(R.id.et_first_name)
    @NotEmpty(sequence = 1)
    @LatinCharacters
    EditText mFirstNameET;

    @BindView(R.id.et_last_name)
    @NotEmpty(sequence = 1)
    @LatinCharacters
    EditText mLastNameET;

    @BindView(R.id.btn_next) Button mButtonNext;
    @BindView(R.id.btn_pick_date) Button mPickDateButton;

    @BindView(R.id.tv_birth_date_preview)
    @DateFormat(format = Formats.STRING_DATE_FORMAT)
    TextView mBirthDatePreview;

    @BindView(R.id.tv_lost_date_preview)
    @DateFormat(format =  Formats.STRING_DATE_FORMAT)
    TextView mLostDatePreview;

    @BindView(R.id.btn_lost_date) Button mPickLostDateButton;
    @BindView(R.id.et_place_lost)
    @NotEmpty(sequence = 1)
    @LatinCharacters(sequence = 2)
    EditText mPlaceLostET;

    @BindView(R.id.et_country)
    @NotEmpty(sequence = 1)
    @LatinCharacters(sequence = 2)
    EditText mCountryIssuedCardET;

    @BindView(R.id.et_authority)
    @NotEmpty(sequence = 1)
    @LatinCharacters(sequence = 2)
    EditText mAuthorityIssuedET;

    @BindView(R.id.et_old_card_number)
    @NotEmpty(sequence = 1)
    EditText mEUcardNumberET;

    @BindView(R.id.btn_expiry_date_old) Button mPickDateOfExpiryEUcardButton;

    @BindView(R.id.tv_expiry_date_preview)
    @DateFormat(format = Formats.STRING_DATE_FORMAT)
    TextView mDateOfExpiryEUcardPreview;

    @BindView(R.id.btn_expiry_date_renewal) Button mPickExpiryDate_RenewalButton;

    @BindView(R.id.tv_expiry_date_renewal_preview)
    @DateFormat(format = Formats.STRING_DATE_FORMAT)
    TextView mExpiryDate_RenewalPreview;

    @BindView(R.id.rl_reason_lost) RelativeLayout mLostElements;
    @BindView(R.id.rl_reason_exchange) RelativeLayout mExchangeElements;
    @BindView(R.id.rl_reason_renewal) RelativeLayout mRenewalElements;

    private PersonalDetailsPresenter mPersonalDetailsPresenter;
    private CardApplication mCardApplication;
    private User mUser;
    private Navigator mNavigator;
    private TextView mTextViewToChange;

    @Inject
    public PersonalDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_details, container, false);
        ButterKnife.bind(this, view);

        mButtonNext.setOnClickListener(v -> {
          mPersonalDetailsPresenter.validate();
        });

        mPickDateButton.setOnClickListener(v -> {
                    mTextViewToChange = mBirthDatePreview;
                    mPersonalDetailsPresenter.handlePickDateButtonOnClick();
                });

        mPickLostDateButton.setOnClickListener(v -> {
            mTextViewToChange = mLostDatePreview;
            mPersonalDetailsPresenter.handlePickDateButtonOnClick();
        });

        mPickDateOfExpiryEUcardButton.setOnClickListener(v -> {
               mTextViewToChange = mDateOfExpiryEUcardPreview;
               mPersonalDetailsPresenter.handlePickDateButtonOnClick();
        });

        mPickExpiryDate_RenewalButton.setOnClickListener(v -> {
            mTextViewToChange = mExpiryDate_RenewalPreview;
            mPersonalDetailsPresenter.handlePickDateButtonOnClick();
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPersonalDetailsPresenter
                .checkReasonAndRevealElementsIfNeeded(mCardApplication.getCardApplicationReason());
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        if (presenter instanceof  PersonalDetailsPresenter) {
            mPersonalDetailsPresenter= (PersonalDetailsPresenter) presenter;
        } else {
            throw new InvalidParameterException();
        }
    }

    @Override
    public void setCardApplicationFields() {

        try {
            SimpleDateFormat df = new SimpleDateFormat(Formats.STRING_DATE_FORMAT);

            mCardApplication.getDetails()
                    .setDriverID(String.valueOf(mUserIdET.getText()));
            mCardApplication.getDetails()
                    .setFirstNameLatin(String.valueOf(mFirstNameET.getText()));
            mCardApplication.getDetails()
                    .setSurNameLatin(String.valueOf(mLastNameET.getText()));
            mCardApplication.getDetails()
                    .setDriverBirthDate(df.parse(String.valueOf(mBirthDatePreview.getText())));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showLostOrStolenFields() {
        mLostElements.setVisibility(View.VISIBLE);
        adjustMarginTop(50);
    }

    @Override
    public void showExchangeFields() {
        mExchangeElements.setVisibility(View.VISIBLE);
        adjustCardExchangeLayout(10, 16);
    }

    @Override
    public void showRenewalFields() {
        mRenewalElements.setVisibility(View.VISIBLE);
        adjustMarginTop(90);
    }

    @Override
    public void setOptionalExchangeFields() {
        SimpleDateFormat df = new SimpleDateFormat(Formats.STRING_DATE_FORMAT);

        try {
            mCardApplication.getDetails()
                    .setAuthorityIssuedCard(String.valueOf(mAuthorityIssuedET.getText()));
            mCardApplication.getDetails()
                    .setCountryIssuedCard(String.valueOf(mCountryIssuedCardET.getText()));
            mCardApplication.getDetails()
                    .setCardNumber(String.valueOf(mEUcardNumberET.getText()));
            mCardApplication.getDetails()
                    .setDateOfExpiry(df.parse(String.valueOf(mDateOfExpiryEUcardPreview.getText())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setOptionalLostFields() {
        SimpleDateFormat df = new SimpleDateFormat(Formats.STRING_DATE_FORMAT);

        try {
            mCardApplication.getDetails()
                    .setPlaceOfLoss(String.valueOf(mPlaceLostET.getText()));

            mCardApplication.getDetails()
                    .setDateOfLoss(df.parse(String.valueOf(mLostDatePreview.getText())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setOptionalExpireFields() {
        SimpleDateFormat df = new SimpleDateFormat(Formats.STRING_DATE_FORMAT);
        try {
            mCardApplication.getDetails()
                    .setDateOfExpiry(df.parse(String.valueOf(mExpiryDate_RenewalPreview.getText())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressLint({"SimpleDateFormat","DefaultLocale"})
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            String dateString = String.format("%d/%d/%d", dayOfMonth, ++month, year);
            mTextViewToChange.setText(dateString);
            mTextViewToChange.setTextSize(20);
            mTextViewToChange.setTypeface(Typeface.DEFAULT);

    }

    @Override
    public DatePickerDialog initializeDatePickerDialog(int year, int month, int day) {
        return new DatePickerDialog(
                Objects.requireNonNull(getContext()),
                android.R.style.Theme_DeviceDefault_Light_Dialog,
                this,
                year, month, day);
    }

    @Override
    public void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = initializeDatePickerDialog(year, month, day);

        Objects.requireNonNull(getActivity())
                .runOnUiThread(datePickerDialog::show);
    }

    @Override
    public void setLoggedUser(User user) {
        this.mUser = user;
    }

    @Override
    public void setCurrentCardApplication(CardApplication cardApplication) {
        this.mCardApplication = cardApplication;
    }

    @Override
    public void navigate() {
        Intent intent = prepareIntent();
        mNavigator.navigateWith(intent);
    }

    @Override
    public void setNavigator(Navigator navigator) {
        this.mNavigator = navigator;
    }


    @Override
    public Intent prepareIntent() {
        Intent intent = new Intent();
        intent.putExtra(IntentKeys.USER_KEY, mUser);
        intent.putExtra(IntentKeys.CARD_APPLICATION_KEY, mCardApplication);
        return intent;
    }

    @Override
    public void onValidationSucceeded() {
        try {
            mPersonalDetailsPresenter.handleOnClickNext(mCardApplication.getCardApplicationReason());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            Rule failedRule = error.getFailedRules().get(0);
            String message = failedRule.getMessage(getContext());

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void showError(Exception e) {
        Toast.makeText(getContext(),
                "Error: " + e.getMessage(),
                Toast.LENGTH_SHORT )
                .show();
    }

    //adjusts layout when optional fields are shown
    private void adjustCardExchangeLayout(int marginTop, int textSize) {
        adjustMarginTop(marginTop);
        mUserIdTV.setTextSize(textSize);
        mFirstNameTV.setTextSize(textSize);
        mLastNameTV.setTextSize(textSize);
        mBirthDateTV.setTextSize(textSize);
    }

    //adjust margin top when optional fields are shown
    private void adjustMarginTop(int marginTop){
        ViewGroup.MarginLayoutParams params =
                (ViewGroup.MarginLayoutParams) mUserIdTV.getLayoutParams();
        params.topMargin = marginTop;
    }
}
