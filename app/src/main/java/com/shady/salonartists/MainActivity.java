package com.shady.salonartists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import com.shady.salonartists.databinding.ActivityMainBinding;
import com.shady.salonartists.pojo.Artist;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    ArtistsAdapter mArtistsAdapter;
    ActivityMainBinding mBinding;
    ArtistViewModel artistViewModel;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        //RecyclerView Setup
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mBinding.rvArtistsList.setLayoutManager(layoutManager);
        mArtistsAdapter = new ArtistsAdapter();
        mBinding.rvArtistsList.setAdapter(mArtistsAdapter);

        //ViewModel Setup
        artistViewModel = ViewModelProviders.of(this).get(ArtistViewModel.class);
        artistViewModel.getArtists("35");
        artistViewModel.ArtistsMutableLiveData.observe(this, new Observer<List<Artist>>() {
            @Override
            public void onChanged(List<Artist> artists) {
                mArtistsAdapter.setArtistData(artists);

            }
        });


        //Date Piker Setup
        mBinding.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDate(2000, 0, 1, R.style.DatePickerSpinner);
            }
        });


        //Time Piker Setup
        mBinding.time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                showTime(hour, minute);

            }
        });


    }

    //Show Date Piker Dialog
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(this)
                .callback(MainActivity.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .build()
                .show();
    }

    //Set the value of date
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        mBinding.date.setText(simpleDateFormat.format(calendar.getTime()));

    }


    //Show Time Piker Dialog
    void showTime(int hour, int minute) {
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(MainActivity.this, this, hour, minute, true);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }

    //Set the value of time
    @Override
    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
        mBinding.time.setText(selectedHour + ":" + selectedMinute);

    }
}
