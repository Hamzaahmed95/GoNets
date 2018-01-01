package khi.fast.gonets;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    // The ListView

    ImageView facebook;
    ImageView google;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        facebook=(ImageView)findViewById(R.id.facebook);
        google=(ImageView)findViewById(R.id.google);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,GettingStartedActivity.class);
                startActivity(i);

            }
        });
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,GettingStartedActivity.class);
                startActivity(i);

            }
        });

        // Find the list view
    }

}