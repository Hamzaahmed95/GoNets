package khi.fast.gonets;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamza Ahmed on 02-Jan-18.
 */

public class NetFacilitiesNearYouActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {

    public static final String[] name = new String[]{"Rashid Latif Cricket Academy",
            "NCA Ground", "UBL Complex", "Customs Cricket Academy","PIA Cricket Training Center","Johar Cricket Academy"};

    public static final String[] phone_no = new String[]{
            "+92 345 8005198","+92 336 7007407","+92 336 7007407","+92 336 7007407","+92 336 7007407","+92 336 7007407"
    };
    public static final String[] address = new String[]{
            "5A/5B Nazimabad","5A/5B Nazimabad","5A/5B Nazimabad","5A/5B Nazimabad","5A/5B Nazimabad","5A/5B Nazimabad"
    };

    public static final String[] email = new String[]{
            "abc123@gmail.com","abc123@gmail.com","abc123@gmail.com","abc123@gmail.com","abc123@gmail.com","abc123@gmail.com"
    };
    public static final String[] details = new String[]{

      "Rashid Latif Cricket Academy is a cricket academy in Karachi, Pakistan, managed by the former Pakistan Test captain Rashid Latif. It is currently responsible for arranging the 2017 event of the PCCL. ",
            "Rashid Latif Cricket Academy is a cricket academy in Karachi, Pakistan, managed by the former Pakistan Test captain Rashid Latif. It is currently responsible for arranging the 2017 event of the PCCL. ",
            "Rashid Latif Cricket Academy is a cricket academy in Karachi, Pakistan, managed by the former Pakistan Test captain Rashid Latif. It is currently responsible for arranging the 2017 event of the PCCL. ",
            "Rashid Latif Cricket Academy is a cricket academy in Karachi, Pakistan, managed by the former Pakistan Test captain Rashid Latif. It is currently responsible for arranging the 2017 event of the PCCL. ",
            "Rashid Latif Cricket Academy is a cricket academy in Karachi, Pakistan, managed by the former Pakistan Test captain Rashid Latif. It is currently responsible for arranging the 2017 event of the PCCL. ",
            "Rashid Latif Cricket Academy is a cricket academy in Karachi, Pakistan, managed by the former Pakistan Test captain Rashid Latif. It is currently responsible for arranging the 2017 event of the PCCL. "
    };

    public static final Integer[] images = {R.drawable.netpicture, R.drawable.netpicture,
            R.drawable.netpicture,
            R.drawable.netpicture,
            R.drawable.netpicture,
            R.drawable.netpicture,};

    ListView listView;
    List<NetFacilityClass> netFacilityClass;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
     /**
     * Called when the activity is first created.
      *
      *
     */
     ImageView backButtonMOM;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_facilities_near_you);
        backButtonMOM=(ImageView)findViewById(R.id.backButtonMOM);
        backButtonMOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NetFacilitiesNearYouActivity.this,CreateNewNetSessionActivity.class);
                startActivity(i);

            }
        });

        netFacilityClass = new ArrayList<NetFacilityClass>();
        for (int i = 0; i < name.length; i++) {
            NetFacilityClass item = new NetFacilityClass(name[i],"4",String.valueOf(images[i]),phone_no[i],email[i],details[i],address[i],"karachi");
            netFacilityClass.add(item);
        }

        listView = (ListView) findViewById(R.id.list);
        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.net_facilities_near_you, netFacilityClass);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        NetFacilityClass netFacilityClass1 =netFacilityClass.get(position);
        Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + netFacilityClass1.getName() + ": " + netFacilityClass.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        System.out.println("HI");
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */



}
