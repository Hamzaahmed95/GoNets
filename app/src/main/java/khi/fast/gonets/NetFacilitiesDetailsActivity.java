package khi.fast.gonets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Hamza Ahmed on 02-Jan-18.
 */

public class NetFacilitiesDetailsActivity extends AppCompatActivity {


    TextView Name1;
    TextView PhoneNo1;
    TextView Email1;
    TextView Details1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_facilities_details);
        Name1=(TextView)findViewById(R.id.name);
        PhoneNo1=(TextView)findViewById(R.id.phone);
        Email1=(TextView)findViewById(R.id.email);
        Details1=(TextView)findViewById(R.id.details);

        Bundle extra = this.getIntent().getExtras();
        if (extra != null) {
            String Name = extra.getString("Name");
            String PhoneNo = extra.getString("PhoneNo");
            String Email = extra.getString("Email");
            String Details = extra.getString("Details");
            Name1.setText(Name);
            PhoneNo1.setText(PhoneNo);
            Email1.setText(Email);
            Details1.setText(Details);

        }
    }

}
