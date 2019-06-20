package com.auxsample.countrycode.data;

import com.auxsample.countrycode.model.RetroCountry;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("all?fields=name;flag;callingCodes")
    Observable<List<RetroCountry>> getAllDetail();
}
