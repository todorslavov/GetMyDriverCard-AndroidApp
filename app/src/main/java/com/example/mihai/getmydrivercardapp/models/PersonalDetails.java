package com.example.mihai.getmydrivercardapp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonalDetails implements Serializable{

    private int id;
    private String driverID;
    private String firstNameLatin;
    private String surNameLatin;
    private Date driverBirthDate;
    private String address;
    private String phoneNumber;
    private String email;
//    private byte[] selfie;
//    private byte[] idCardImage;
//    private byte[] drivingLicenseImage;
    private byte[] signature;
//    private byte[] previousCardImage;
    private String countryIssuedCard;
    private String authorityIssuedCard;
    private String cardNumber;
    private Date dateOfExpiry;
    private Date dateOfLoss;
    private String placeOfLoss;
    private List<ImageModel> images;

    public PersonalDetails () {

    }

    public String getDriverID() {
        return driverID;
    }

    public String getFirstNameLatin() {
        return firstNameLatin;
    }

    public String getSurNameLatin() {
        return surNameLatin;
    }

    public Date getDriverBirthDate() {
        return driverBirthDate;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public List<ImageModel> getImages() {
        if (images == null) {
            images = new ArrayList<>();
        }
        return images;
    }

    //    public byte[] getSelfie() {
//        return selfie;
//    }
//
//    public byte[] getIdCardImage() {
//        return idCardImage;
//    }
//
//    public byte[] getDrivingLicenseImage() {
//        return drivingLicenseImage;
//    }
//
    public byte[] getSignature() {
        return signature;
    }
//
//    public byte[] getPreviousCardImage() {
//        return previousCardImage;
//    }

    public String getCountryIssuedCard() {
        return countryIssuedCard;
    }

    public String getAuthorityIssuedCard() {
        return authorityIssuedCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Date getDateOfExpiry() {
        return dateOfExpiry;
    }

    public Date getDateOfLoss() {
        return dateOfLoss;
    }

    public String getPlaceOfLoss() {
        return placeOfLoss;
    }

//    public void setSelfie(byte[] selfie) {
//        this.selfie = selfie;
//    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public void setFirstNameLatin(String firstNameLatin) {
        this.firstNameLatin = firstNameLatin;
    }

    public void setSurNameLatin(String surNameLatin) {
        this.surNameLatin = surNameLatin;
    }

    public void setDriverBirthDate(Date driverBirthDate) {
        this.driverBirthDate = driverBirthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public void setIdCardImage(byte[] idCardImage) {
//        this.idCardImage = idCardImage;
//    }
//
//    public void setDrivingLicenseImage(byte[] drivingLicenseImage) {
//        this.drivingLicenseImage = drivingLicenseImage;
//    }
//
    public void setSignature(byte[] signature) {
        this.signature = signature;
    }
//
//    public void setPreviousCardImage(byte[] previousCardImage) {
//        this.previousCardImage = previousCardImage;
//    }

    public void setCountryIssuedCard(String countryIssuedCard) {
        this.countryIssuedCard = countryIssuedCard;
    }

    public void setAuthorityIssuedCard(String authorityIssuedCard) {
        this.authorityIssuedCard = authorityIssuedCard;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setDateOfExpiry(Date dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public void setDateOfLoss(Date dateOfLoss) {
        this.dateOfLoss = dateOfLoss;
    }

    public void setPlaceOfLoss(String placeOfLoss) {
        this.placeOfLoss = placeOfLoss;
    }

    public void setImages(ArrayList<ImageModel> images) {
        this.images = images;
    }
}
