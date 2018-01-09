package khi.fast.gonets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Hamza Ahmed on 06-Jan-18.
 */

public class NetSessionDetailActivity extends AppCompatActivity {

    ImageView backButtonMOM;
    TextView TextName;
    TextView Time;
    TextView MessageText;
    Button SendMessage;
    EditText MessageEditText;
    ImageView tick;
    TextView Name;
    ImageView ball;
    ImageView bat;
    ImageView wkeeper;
    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference mMessageDatabaseReference;

    private ChildEventListener mChildEventListener;

    private FirebaseAuth mAuth;
    private String id1;
    private FirebaseAuth.AuthStateListener mAuthStateListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_session_details);
        backButtonMOM = (ImageView) findViewById(R.id.backButtonMOM);
        backButtonMOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NetSessionDetailActivity.this, NetSessionsActivity.class);
                i.putExtra("Activity","NetSessionDetailActivity");
                i.putExtra("ID",id1);
                startActivity(i);
            }
        });
        TextName = (TextView) findViewById(R.id.TextName);
        Time = (TextView) findViewById(R.id.Time);
        MessageText = (TextView) findViewById(R.id.MessageText);
        MessageEditText = (EditText) findViewById(R.id.MessageEditText);
        SendMessage = (Button) findViewById(R.id.SendMessage);
        SendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ball=(ImageView)findViewById(R.id.ball);
        bat=(ImageView)findViewById(R.id.bat);
        wkeeper=(ImageView)findViewById(R.id.wkeeper);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("UserProfile");



















        tick = (ImageView) findViewById(R.id.tick);
        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Name = (TextView) findViewById(R.id.Name);
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            System.out.println("HERE =>");

                TextName.setText(extra.getString("Name"));
                Time.setText(extra.getString("Time"));
                Name.setText(extra.getString("Username"));
                id1=extra.getString("Id");
            if(extra.getString("Skills").equals("Baller Batsman and Wicket Keeper")){
                ball.setVisibility(View.VISIBLE);
                bat.setVisibility(View.VISIBLE);
                wkeeper.setVisibility(View.VISIBLE);
            }
            else if(extra.getString("Skills").equals("Baller and Batsman")){
                ball.setVisibility(View.VISIBLE);
                bat.setVisibility(View.VISIBLE);
            }

            else if(extra.getString("Skills").equals("Baller and Wicket Keeper")){
                ball.setVisibility(View.VISIBLE);
                wkeeper.setVisibility(View.VISIBLE);
            }
            else if(extra.getString("Skills").equals("Batsman and Wicket Keeper")){
                bat.setVisibility(View.VISIBLE);
                wkeeper.setVisibility(View.VISIBLE);
            }

            else if(extra.getString("Skills").equals("Baller")){
                ball.setVisibility(View.VISIBLE);
            }

            else if(extra.getString("Skills").equals("Batsman")){
                bat.setVisibility(View.VISIBLE);
            }

            else if(extra.getString("Skills").equals("Wicket Keeper")){
                wkeeper.setVisibility(View.VISIBLE);
            }
        }
    }
}
