package com.duycuong.weather.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DuyCương on 06/02/2018.
 */

public class AddressResponse {

    @SerializedName("results")
    @Expose
    private List<Result> mResults;
    @SerializedName("status")
    @Expose
    private String mStatus;

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public class AddressComponent {

        @SerializedName("long_name")
        @Expose
        private String mLongName;

        public String getLongName() {
            return mLongName;
        }

        public void setLongName(String longName) {
            mLongName = longName;
        }
    }

    public class Result {

        @SerializedName("address_components")
        @Expose
        private List<AddressComponent> mAddressComponents;

        public List<AddressComponent> getAddressComponents() {
            return mAddressComponents;
        }

        public void setAddressComponents(List<AddressComponent> addressComponents) {
            this.mAddressComponents = addressComponents;
        }
    }
}
