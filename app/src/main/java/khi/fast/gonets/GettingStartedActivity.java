package khi.fast.gonets;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Hamza Ahmed on 30-Dec-17.
 */

public class GettingStartedActivity extends AppCompatActivity {


    ImageView ball;
    ImageView bat;
    ImageView wkeeper;
    static int ballcount=0;
    static int batcount=0;
    static int wkeepercount=0;
    TextView chooseNets;
    Dialog dialog;
    TextView favouriteNetsName;
    ImageView icon;
    ImageView tick;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getting_started_layout);
        ball=(ImageView)findViewById(R.id.ball);
        bat=(ImageView)findViewById(R.id.bat);
        favouriteNetsName=(TextView)findViewById(R.id.favouriteNetsName);
        icon=(ImageView)findViewById(R.id.icon);
        tick=(ImageView) findViewById(R.id.tick);
        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GettingStartedActivity.this,NetSessionsActivity.class);
                startActivity(i);
            }
        });
        wkeeper=(ImageView)findViewById(R.id.wkeeper);
        chooseNets=(TextView)findViewById(R.id.chooseNets);
        chooseNets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDialog();
            }
        });

        ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ballcount%2==0) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);
                    layoutParams.setMargins(15,5,15,5);
                    ball.setLayoutParams(layoutParams);
                }
                else{
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
                    layoutParams.setMargins(15,5,15,5);
                    ball.setLayoutParams(layoutParams);
                }
                ballcount++;

            }
        });
        bat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(batcount%2==0) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);
                    layoutParams.setMargins(15,5,15,5);
                    bat.setLayoutParams(layoutParams);
                }
                else{
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
                    layoutParams.setMargins(15,5,15,5);
                    bat.setLayoutParams(layoutParams);
                }
                batcount++;

            }
        });
        wkeeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wkeepercount%2==0) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);
                    layoutParams.setMargins(15,5,15,5);
                    wkeeper.setLayoutParams(layoutParams);
                }
                else{
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
                    layoutParams.setMargins(15,5,15,5);
                    wkeeper.setLayoutParams(layoutParams);
                }
                wkeepercount++;
            }
        });




    }
  /*  private void showDialog() {
        // custom dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.item_favourite_nets);

        final EditText editQuery=(EditText)dialog.findViewById(R.id.editQuery);



        Button Close  =(Button) dialog.findViewById(R.id.ask);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name="Posted By Hamza ";
                String Question=editQuery.getText().toString();
                favouriteNetsName.setText(Question);
                icon.setVisibility(View.VISIBLE);
               // QueryClass queryClass=new QueryClass(name,Question,"TeacherName","","0");
               // mMessageDatabaseReference.push().setValue(queryClass);
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }*/
}
