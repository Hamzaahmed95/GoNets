package khi.fast.gonets;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
 * Created by Hamza Ahmed on 23-Dec-17.
 */

public class NetSessionsActivity extends AppCompatActivity {
    private Dialog dialog;
    ImageButton Add;

    private MySessionAdapter MySessionAdapter;
    private MySessionAdapter MySessionAdapter2;
    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference mMessageDatabaseReference;

    private ChildEventListener mChildEventListener;

    private FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListner;

    private ListView mmessageListViewMOM;
    private ListView mmessageListViewMOM2;
    private TextView TextName;
    private String Name;
    private String Picture;
    private String Time;
    private String username;
    private String Username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_sessions_activity);

        mmessageListViewMOM = (ListView) findViewById(R.id.messageListView);
        mmessageListViewMOM2 = (ListView) findViewById(R.id.messageListView2);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        TextName=(TextView)findViewById(R.id.TextName);
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("MySessions");


        Add=(ImageButton) findViewById(R.id.UserTeam);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NetSessionsActivity.this,CreateNewNetSessionActivity.class);
                i.putExtra("Username",username);
                i.putExtra("Activity","NetSessionsActivity");
                startActivity(i);
            }
        });

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                onSignedInInitialize("");
                if(user!=null) {
                    Username = user.getDisplayName();
                    Bundle extra = getIntent().getExtras();
                    if (extra != null) {
                        if(extra.getString("Activity").equals("GettingStartedActivity")){
                            TextName.setText(extra.getString("Name"));
                            username=extra.getString("Name");
                        }
                        else {
                            Name = extra.getString("Name");
                            Picture = extra.getString("Picture");
                            Time = extra.getString("Time");
                            //   username2=extra.getString("Username");
                            System.out.println("what is this?"+Username);
                            MySessionClass MySessionClass = new MySessionClass(Username,Picture, Name, Time);
                            // Clear input box
                            mMessageDatabaseReference.push().setValue(MySessionClass);
                        }

                    }
                }
                System.out.println("Username2: "+Username);
                final List<MySessionClass> momclasses = new ArrayList<>();
                final List<MySessionClass> momclasses2 = new ArrayList<>();
                MySessionAdapter = new MySessionAdapter(NetSessionsActivity.this, R.layout.item_net_sessions, momclasses);
                MySessionAdapter2 = new MySessionAdapter(NetSessionsActivity.this, R.layout.item_net_sessions, momclasses2);

                System.out.println("here => "+MySessionAdapter);
                if(mmessageListViewMOM!=null)
                    mmessageListViewMOM.setAdapter(MySessionAdapter);
                if(mmessageListViewMOM2!=null)
                    mmessageListViewMOM2.setAdapter(MySessionAdapter2);


           /*     if(user!=null){
                    //user is signed in
                }else{
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
            };
        };
        mmessageListViewMOM.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
             //   System.out.println("hamza is this click?");
           //     Toast.makeText(NetSessionsActivity.this, position, Toast.LENGTH_SHORT).show();
            }
        });
        mmessageListViewMOM2.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
               // System.out.println("hamza is this click?");
                //Toast.makeText(NetSessionsActivity.this, position, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
        MySessionAdapter.clear();
        MySessionAdapter2.clear();
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
        MySessionAdapter.clear();
        MySessionAdapter2.clear();
        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    MySessionClass momclass = dataSnapshot.getValue(MySessionClass.class);
                    if(momclass!=null){
                        if(dataSnapshot.child("username").getValue().equals(Username)){

                            MySessionAdapter.add(momclass);
                            System.out.println("HI");
                        }
                        else {
                            MySessionAdapter2.add(momclass);
                            System.out.println("Bye");
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
   /* private void showDialog() {
        // custom dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.item_net_sessions);

        // set the custom dialog components - text, image and button

        // Close Button

        // Buy Button

        final EditText leaderName =(EditText) dialog.findViewById(R.id.leaderName);
        final EditText member1 =(EditText) dialog.findViewById(R.id.member1);
        final EditText member2 =(EditText) dialog.findViewById(R.id.member2);


        dialog.setCanceledOnTouchOutside (false);
        Button Close = (Button) dialog.findViewById(R.id.close1);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String LeaderName= leaderName.getText().toString();
                String Member1= member1.getText().toString();
                String Member2= member2.getText().toString();

                // TODO: Send messages on click
                MySessionClass MySessionClass = new MySessionClass(LeaderName,Member1,Member2,"0");
                // Clear input box
                mMessageDatabaseReference.push().setValue(MySessionClass);
                leaderName.setText("");
                member1.setText("");
                member2.setText("");
                textHide.setVisibility(view.GONE);

               /* Intent i = new Intent(PlatinumPlayers.this,SelectedTeams.class);
                i.putExtra("NAME",NAME);
                startActivity(i);
                mTeamDatabaseReference.push().setValue(usersFantacyTeam);


                dialog.dismiss();

            }
        });
        Button Close1 = (Button) dialog.findViewById(R.id.close2);
        Close1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                //Intent i = new Intent(Sil,GoldPlayers.class);
                //getContext().startActivity(i);

            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
*/
}