package com.itg.jobcardmanagement.registration.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;

/**
 * Created by Android itg 8 on 8/7/2017.
 */

public class RegistrationModel  implements Parcelable {
    private int id;
    private String registrationNumber;
    private String chesisNumber;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String customerAddress;
    private String customerCity;
    private String customerState;
    private String carSeries;
    private String carModelCode;
    private String carVinNumber;
    private String carColorCode;
    private String carSaleDate;
    private String carDelearName;

    public String getCustomerTransfertFilePath() {
        return customerTransfertFilePath;
    }

    public void setCustomerTransfertFilePath(String customerTransfertFilePath) {
        this.customerTransfertFilePath = customerTransfertFilePath;
    }

    private String customerTransfertFilePath;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    private String customerEmail;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getChesisNumber() {
        return chesisNumber;
    }

    public void setChesisNumber(String chesisNumber) {
        this.chesisNumber = chesisNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public String getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries;
    }

    public String getCarModelCode() {
        return carModelCode;
    }

    public void setCarModelCode(String carModelCode) {
        this.carModelCode = carModelCode;
    }

    public String getCarVinNumber() {
        return carVinNumber;
    }

    public void setCarVinNumber(String carVinNumber) {
        this.carVinNumber = carVinNumber;
    }

    public String getCarColorCode() {
        return carColorCode;
    }

    public void setCarColorCode(String carColorCode) {
        this.carColorCode = carColorCode;
    }

    public String getCarSaleDate() {
        return carSaleDate;
    }

    public void setCarSaleDate(String carSaleDate) {
        this.carSaleDate = carSaleDate;
    }



    public RegistrationModel() {
    }

    protected RegistrationModel(Parcel in) {
        id = in.readInt();
        registrationNumber = in.readString();
        chesisNumber = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        contactNumber = in.readString();
        customerAddress = in.readString();
        customerCity = in.readString();
        customerState = in.readString();
        carSeries = in.readString();
        carModelCode = in.readString();
        carVinNumber = in.readString();
        carColorCode = in.readString();
        carSaleDate = in.readString();
        carDelearName = in.readString();
        customerEmail = in.readString();
        customerEmail = in.readString();
        customerTransfertFilePath = in.readString();
    }

    public static final Creator<RegistrationModel> CREATOR = new Creator<RegistrationModel>() {
        @Override
        public RegistrationModel createFromParcel(Parcel in) {
            return new RegistrationModel(in);
        }

        @Override
        public RegistrationModel[] newArray(int size) {
            return new RegistrationModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(registrationNumber);
        dest.writeString(chesisNumber);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(contactNumber);
        dest.writeString(customerAddress);
        dest.writeString(customerCity);
        dest.writeString(customerState);
        dest.writeString(carSeries);
        dest.writeString(carModelCode);
        dest.writeString(carVinNumber);
        dest.writeString(carColorCode);
        dest.writeString(carSaleDate);
        dest.writeString(carDelearName);
        dest.writeString(customerEmail);
        dest.writeString(customerTransfertFilePath);
    }

    public String getCarDelearName() {
        return carDelearName;
    }

    public void setCarDelearName(String carDelearName) {
        this.carDelearName = carDelearName;
    }
}
