package com.shady.salonartists.data;




import com.shady.salonartists.pojo.Salon;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ArtistInterface {


    @POST("salonArtists?salon_id=35")
    public Call<Salon> getArtists(@Query("salon_id") String salon_id);

}
