package khi.fast.gonets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Hamza Ahmed on 06-Jan-18.
 */

public class NetSessionDetailActivity extends AppCompatActivity {

    ImageView backButtonMOM;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_session_details);
        backButtonMOM=(ImageView)findViewById(R.id.backButtonMOM);
        backButtonMOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NetSessionDetailActivity.this,NetSessionsActivity.class);
                startActivity(i);
            }
        });


    }
}
