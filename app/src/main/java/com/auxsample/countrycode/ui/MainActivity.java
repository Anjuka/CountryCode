package com.auxsample.countrycode.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import com.auxsample.countrycode.R;
import com.auxsample.countrycode.adapter.CustomAdapter;
import com.auxsample.countrycode.data.GetDataService;
import com.auxsample.countrycode.model.RetroCountry;
import com.auxsample.countrycode.network.RetrofitClientInstance;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.observers.BlockingBaseObserver;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDoalog;
    private RecyclerView rvCountryData;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Observable<List<RetroCountry>> call = service.getAllDetail();

        call.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceObserver<List<RetroCountry>>() {
                    @Override
                    public void onNext(List<RetroCountry> retroCountries) {
                        progressDoalog.dismiss();
                        generateDataList(retroCountries);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDoalog.dismiss();
                        Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        System.out.println("Error" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void progressDialog() {
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
    }

    private void generateDataList(List<RetroCountry> countryList) {
        rvCountryData = findViewById(R.id.rvCountryData);
        adapter = new CustomAdapter(this, countryList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rvCountryData.setLayoutManager(layoutManager);
        rvCountryData.setAdapter(adapter);
    }
}

