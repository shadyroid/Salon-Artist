package com.shady.salonartists;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.shady.salonartists.data.ArtistClient;
import com.shady.salonartists.pojo.Artist;
import com.shady.salonartists.pojo.Salon;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistViewModel extends ViewModel {
    MutableLiveData<List<Artist>> ArtistsMutableLiveData = new MutableLiveData<>();

    public void getArtists(String salon_id){
        ArtistClient.getINSTANCE().getArtists(salon_id).enqueue(new Callback<Salon>() {
            @Override
            public void onResponse(Call<Salon> call, Response<Salon> response) {
                ArtistsMutableLiveData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<Salon> call, Throwable t) {
                Log.v("Shit","noooooooooooooo");

            }
        });
    }



}
