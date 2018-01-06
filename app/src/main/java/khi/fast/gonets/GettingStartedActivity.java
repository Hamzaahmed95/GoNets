package khi.fast.gonets;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamza Ahmed on 30-Dec-17.
 */

public class GettingStartedActivity extends AppCompatActivity {


    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference mMessageDatabaseReference;

    private ChildEventListener mChildEventListener;

    private FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListner;
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
    TextView Name;
    EditText ScreenName;
    int skillBall=-1;
    int skillBat=-1;
    int skillwkeeper=-1;
    int checkEditText=0;
    private String id;
    CheckBox checkboxDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getting_started_layout);
        ball = (ImageView) findViewById(R.id.ball);
        bat = (ImageView) findViewById(R.id.bat);
        favouriteNetsName = (TextView) findViewById(R.id.favouriteNetsName);
        favouriteNetsName.setText("");
        icon = (ImageView) findViewById(R.id.icon);
        tick = (ImageView) findViewById(R.id.tick);
        Name = (TextView) findViewById(R.id.Name);
        checkboxDefault=(CheckBox)findViewById(R.id.checkboxDefault);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        // textHide=(TextView)findViewById(R.id.textHide);
        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("UserProfile");


        ScreenName = (EditText) findViewById(R.id.ScreenName);
        Bundle extra = this.getIntent().getExtras();
        if (extra != null) {
            Name.setText(extra.getString("NAME"));
           id=extra.getString("ID");
            System.out.println(extra.getString("ID"));


        }
        ScreenName.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().trim().length() == 0) {
                    checkEditText = 0;
                } else {
                    checkEditText = 1;
                }


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });


        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((skillBall == -1 && skillBat == -1 && skillwkeeper == 1) || (checkEditText == 0) || favouriteNetsName.getText().toString().equals("")) {
                    if (checkEditText == 0) {
                        Toast.makeText(GettingStartedActivity.this, "Please choose your name!", Toast.LENGTH_SHORT).show();

                    } else if (skillBall == -1 && skillBat == -1 && skillwkeeper == -1) {

                        Toast.makeText(GettingStartedActivity.this, "Please Select Your skills!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(GettingStartedActivity.this, "Please select your favourite Nets", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    System.out.println("Name: " + Name.getText());
                    System.out.println("ScreenName: " + ScreenName.getText());
                    System.out.println("Skills: " + skillBall + " " + skillBat + " " + skillwkeeper);
                    String skills = "";
                    if (skillBat == 1 && skillBall == 1 && skillwkeeper == 1) {
                        skills = "Baller Batsman and Wicket Keeper";
                    } else if (skillBall == 1 && skillBat == 1 && skillwkeeper != 1) {
                        skills = "Baller and Batsman";
                    } else if (skillBall == 1 && skillBat != 1 && skillwkeeper == 1) {
                        skills = "Baller and Wicket Keeper";
                    } else if (skillBall != 1 && skillBat == 1 && skillwkeeper == 1) {
                        skills = "Batsman and Wicket Keeper";
                    } else if (skillBall == 1 && skillBat != 1 && skillwkeeper != 1) {
                        skills = "Baller";
                    } else if (skillBall != 1 && skillBat == 1 && skillwkeeper != 1) {
                        skills = "Batsman";
                    } else if (skillBall != 1 && skillBat != 1 && skillwkeeper == 1) {
                        skills = "Wicket Keeper";
                    }
                    int checkbox=0;
                    if(checkboxDefault.isChecked()){
                        checkbox=1;
                    }
                    else{
                        checkbox=2;
                    }


                    System.out.println("Fav nets: " + favouriteNetsName.getText().toString());
                    UserProfile UserProfile = new UserProfile(id,Name.getText().toString(), ScreenName.getText().toString(), skills, favouriteNetsName.getText().toString(),checkbox);

                    // Clear input box
                    mMessageDatabaseReference.push().setValue(UserProfile);

                    Intent i = new Intent(GettingStartedActivity.this,NetSessionsActivity.class);
                    i.putExtra("Name",Name.getText().toString());
                    i.putExtra("Activity","GettingStartedActivity");
                    startActivity(i);

                }




            }
        });
        wkeeper = (ImageView) findViewById(R.id.wkeeper);
        chooseNets = (TextView) findViewById(R.id.chooseNets);
        chooseNets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog();
            }
        });

        ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ballcount % 2 == 0) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);
                    layoutParams.setMargins(15, 5, 15, 5);
                    ball.setLayoutParams(layoutParams);
                    skillBall = 1;
                } else {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
                    layoutParams.setMargins(15, 5, 15, 5);
                    ball.setLayoutParams(layoutParams);
                    skillBall = -1;
                }
                ballcount++;

            }
        });
        bat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (batcount % 2 == 0) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);
                    layoutParams.setMargins(15, 5, 15, 5);
                    bat.setLayoutParams(layoutParams);
                    skillBat = 1;
                } else {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
                    layoutParams.setMargins(15, 5, 15, 5);
                    bat.setLayoutParams(layoutParams);
                    skillBat = -1;
                }
                batcount++;

            }
        });
        wkeeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wkeepercount % 2 == 0) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);
                    layoutParams.setMargins(15, 5, 15, 5);
                    wkeeper.setLayoutParams(layoutParams);
                    skillwkeeper = 1;
                } else {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
                    layoutParams.setMargins(15, 5, 15, 5);
                    wkeeper.setLayoutParams(layoutParams);
                    skillwkeeper = -1;
                }
                wkeepercount++;
            }
        });


        ;
        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                onSignedInInitialize("");


                if (user != null) {
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                } else {
                    onSignedOutInitialize();
                    //user is signed out
                    //  onSignedOutInitialize();
                    /*startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setTheme(R.style.FirebaseLoginTheme)
                                    .setLogo(R.drawable.wb5)
                                    .setProviders(
                                            AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER
                                    ).build(),
                            RC_SIGN_IN);
                }
                */
                }
                ;
            }

            ;

        };
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
    }


    @Override
    protected void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

    private void  onSignedInInitialize(String username){
        attachDatabaseReadListener();

    }
    private void  onSignedOutInitialize(){

        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        //  textHide.setVisibility(View.GONE);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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



    private void showDialog() {
        // custom dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.pop_up_favourite_net);

        final EditText editQuery=(EditText)dialog.findViewById(R.id.favouriteNet);



        Button Close  =(Button) dialog.findViewById(R.id.send);
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
    }
}
