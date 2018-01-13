package khi.fast.gonets;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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

    private ListView mmessageListViewMOM2;
    ImageView wkeeper;
    private String UID;
    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference mMessageDatabaseReference;

    private ChildEventListener mChildEventListener;
    private String CurrentUser;
    private FirebaseAuth mAuth;
    private String id1;

    private noOfPeopleAdapter noOfPeopleAdapter;
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

        mmessageListViewMOM2 = (ListView) findViewById(R.id.messageListView2);
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
        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("PeopleGoingToNetSession");



















        tick = (ImageView) findViewById(R.id.tick);
        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println();
               Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("MySessions");

                mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int count1=0;
                            for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                // do something with the individual "issues"
                                System.out.println(issue.child("uid").getValue()+""+UID);
                                if(issue.child("uid").getValue().equals(UID)) {
                                    int count=Integer.valueOf(issue.child("noOfPeopleGoing").getValue().toString());
                                    System.out.println("Before?"+count);
                                    count=count+1;
                                    issue.getRef().child("noOfPeopleGoing").setValue(count);
                                    System.out.println("After! "+issue.child("noOfPeopleGoing").getValue().toString());
                                    NoOfPeopleGoing noOfPeopleGoing=new NoOfPeopleGoing(UID,CurrentUser);
                                    mMessageDatabaseReference.push().setValue(noOfPeopleGoing);



                                }

                            }

                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });






            }
        });
        Name = (TextView) findViewById(R.id.Name);
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            System.out.println("HERE =>");

                TextName.setText(extra.getString("Name"));
                Time.setText(extra.getString("Time"));
                Name.setText(extra.getString("Username"));
                MessageText.setText(extra.getString("Message"));
                id1=extra.getString("Id");
                UID=extra.getString("UID");
                CurrentUser=extra.getString("CurrentUser");
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

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                onSignedInInitialize("");
                System.out.println("user? " + user);
//                System.out.println("user? "+user.getDisplayName());
                //              System.out.println("user? "+user.getEmail());
                if (user != null) {

                    Bundle extra = getIntent().getExtras();
                    if (extra != null) {
                        System.out.println("Starting?"+UID);
                        UID=extra.getString("UID");
                        onSignedInInitialize(UID);
                    }

                        final List<NoOfPeopleGoing> NoOfPeopleGoing = new ArrayList<>();
                    noOfPeopleAdapter = new noOfPeopleAdapter(NetSessionDetailActivity.this, R.layout.item_net_sessions, NoOfPeopleGoing);
                    if(mmessageListViewMOM2!=null)
                        mmessageListViewMOM2.setAdapter(noOfPeopleAdapter);



                }
            };
        };
    }
    @Override
    protected void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mAuth.removeAuthStateListener(mAuthStateListner);
        }
        noOfPeopleAdapter.clear();
        detachDatabaseReadListener();
    }


    @Override
    protected void onResume(){
        super.onResume();
        mAuth.addAuthStateListener(mAuthStateListner);
    }

    private void  onSignedInInitialize(String uid){
        attachDatabaseReadListener(uid);

    }
    private void  onSignedOutInitialize(){

        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(final String uid){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    NoOfPeopleGoing NoOfPeopleGoing = dataSnapshot.getValue(NoOfPeopleGoing.class);
                    if(NoOfPeopleGoing!=null){
                        System.out.println(dataSnapshot.child("uid").getValue()+" Now Starting here? "+UID);
                        if(dataSnapshot.child("uid").getValue().equals(UID)){

                            noOfPeopleAdapter.add(NoOfPeopleGoing);
                            System.out.println("HI");
                        }

                    }

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    MySessionClass f =dataSnapshot.getValue(MySessionClass.class);

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            mMessageDatabaseReference.addChildEventListener(mChildEventListener);
            mMessageDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                        //  Log.d("mom ",""+mom.getPICTURE());

                    }






                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null)
            mMessageDatabaseReference.removeEventListener(mChildEventListener);
        mChildEventListener=null;
    }
}
