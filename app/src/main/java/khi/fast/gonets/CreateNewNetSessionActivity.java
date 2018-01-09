package khi.fast.gonets;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Hamza Ahmed on 01-Jan-18.
 */

public class CreateNewNetSessionActivity extends AppCompatActivity {

    ImageButton selectDate;
    static TextView Time;
    static TextView Date1;
    Spinner BestTeam;
    Spinner BestTeam2;
    ImageButton chooseLocaion;
    ImageView GroundImage;
    TextView GroundName;
    ImageView tick;
    String Name;
    String Picture;
    ImageView backButtonMOM;
    private String username;
    static private String id1;
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_favourite_nets);
        GroundImage=(ImageView)findViewById(R.id.GroundImage);
        GroundName=(TextView)findViewById(R.id.GroundName);
        tick=(ImageView)findViewById(R.id.tick);

        Bundle extra = this.getIntent().getExtras();
        if (extra != null) {
            if(extra.getString("Activity").equals("CustomeListViewAdapter")) {
                Name = extra.getString("Name");
                Picture = extra.getString("Picture");
                GroundImage.setImageResource(Integer.parseInt(Picture));
                GroundName.setText(Name);
            }
            else{
                username=extra.getString("Username");
;
                id1=extra.getString("ID");
                System.out.println("CustomeListViewAdapter ID: "+id1);
            }
            }
        selectDate = (ImageButton) findViewById(R.id.selectDate);
        Time = (TextView) findViewById(R.id.time);
        Date1 = (TextView) findViewById(R.id.date);
        chooseLocaion=(ImageButton) findViewById(R.id.chooseLocaion);
        chooseLocaion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreateNewNetSessionActivity.this,NetFacilitiesNearYouActivity.class);
                startActivity(i);
            }
        });
        BestTeam = (Spinner)findViewById(R.id.favTeam);
        BestTeam2 = (Spinner)findViewById(R.id.favTeam2);
        final String[] items = new String[]{"1","2","3", "4", "5", "6", "7", "8"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_style, items);

        BestTeam.setAdapter(adapter);
        BestTeam.setPrompt("select");
        final String[] items2 = new String[]{"Beginner","Medium","Pro",};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_style, items2);

        BestTeam2.setAdapter(adapter2);
        BestTeam2.setPrompt("select");

        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("HERER???? "+id1);
                System.out.println("picture"+Picture);
                System.out.println("picture"+Name);
                System.out.println("Time"+Time.getText().toString());
                Intent i = new Intent(CreateNewNetSessionActivity.this,NetSessionsActivity.class);

                i.putExtra("Picture",Picture);
                i.putExtra("Name",Name);
                i.putExtra("Username",username);

                System.out.println("HERER???? "+id1);
                i.putExtra("Activity","CreateNewNetSessionActivity");
                i.putExtra("ID",id1);
                i.putExtra("Time",Time.getText().toString());
                startActivity(i);
            }
        });
        backButtonMOM=(ImageView)findViewById(R.id.backButtonMOM);
        backButtonMOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreateNewNetSessionActivity.this,NetSessionsActivity.class);
                i.putExtra("Picture",Picture);
                i.putExtra("Name",Name);
                i.putExtra("Username",username);

                System.out.println("HERER???? "+id1);
                i.putExtra("Activity","CreateNewNetSessionActivity1");
                i.putExtra("ID",id1);
                i.putExtra("Time",Time.getText().toString());
                startActivity(i);
            }
        });




    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */



    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            int hour = 00;
            int minute = 00;

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            if (hourOfDay > 12) {
                hourOfDay = hourOfDay % 12;
                String time = hourOfDay + ":" + minute + " PM";
                System.out.println(time);
                Time.setText(time);
            } else {
                String time = hourOfDay + ":" + minute + " AM";
                System.out.println(time);
                Time.setText(time);

            }
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getFragmentManager(), "datePicker");

        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            int year = 2018;
            int month = 0;
            int day = 1;

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user

            System.out.println(year + " " + month + " " + day);
            SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
            Date date1 = new Date(year, month, day - 1);
            String dayOfWeek = simpledateformat.format(date1);
            System.out.println(dayOfWeek);
            String date=" "+dayOfWeek+", "+String.valueOf(day - 1)+" "+getNameOfMonth(month)+", "+year;
            Date1.setText(date);
         }
    }
    public static String getNameOfMonth(int number){
        String name="";
        if(number==0){
            name="January";
        }
        else if(number==1){
            name="Febrary";
        }
        else if(number==2){
            name="March";
        }
        else if(number==3){
            name="April";
        }
        else if(number==4){
            name="May";
        }
        else if(number==5){
            name="June";
        }
        else if(number==6){
            name="July";
        }
        else if(number==7){
            name="August";
        }
        else if(number==8){
            name="September";
        }
        else if(number==9){
            name="October";
        }
        else if(number==10){
            name="November";
        }
        else if(number==11){
            name="December";
        }
        return  name;

    }

}
