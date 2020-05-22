package com.shady.salonartists.data;

import com.shady.salonartists.pojo.Salon;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ArtistClient {
    private static final String ARTIST_BASE_URL = "http://81.10.36.145/vt/show_web/api/";

    private ArtistInterface artistInterface;
    private static ArtistClient INSTANCE;



    public ArtistClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ARTIST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        artistInterface = retrofit.create(ArtistInterface.class);
    }

    public static ArtistClient getINSTANCE() {
        if (null == INSTANCE){
            INSTANCE = new ArtistClient();
        }
        return INSTANCE;
    }



    public Call<Salon> getArtists(String salon_id) {
        return artistInterface.getArtists(salon_id);
    }


}
