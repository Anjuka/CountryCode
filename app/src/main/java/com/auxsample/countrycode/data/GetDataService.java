package com.auxsample.countrycode.data;

import com.auxsample.countrycode.model.RetroCountry;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("all?fields=name;flag;callingCodes")
    Call<List<RetroCountry>> getAllDetail();
}
