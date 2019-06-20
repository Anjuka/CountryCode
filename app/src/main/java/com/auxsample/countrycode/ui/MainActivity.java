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
        Call<List<RetroCountry>> call = service.getAllDetail();
        call.enqueue(new Callback<List<RetroCountry>>() {
            @Override
            public void onResponse(Call<List<RetroCountry>> call, Response<List<RetroCountry>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroCountry>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                System.out.println("Error" + t.getMessage());
            }
        });
    }

    private void progressDialog() {
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
    }

    private void generateDataList(List<RetroCountry> photoList) {
        rvCountryData = findViewById(R.id.rvCountryData);
        adapter = new CustomAdapter(this, photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rvCountryData.setLayoutManager(layoutManager);
        rvCountryData.setAdapter(adapter);
    }
}

