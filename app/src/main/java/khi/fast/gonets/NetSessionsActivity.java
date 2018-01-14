package khi.fast.gonets;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
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
    ImageView Add;

    private MySessionAdapter MySessionAdapter;
    private MySessionAdapter MySessionAdapter2;
    private FirebaseDatabase mFirebaseDatabase;
    private LinearLayout main1;
    private LinearLayout li;
    private RelativeLayout  linearLayout;
    private DatabaseReference mMessageDatabaseReference;

    private ChildEventListener mChildEventListener;

    private String id1;
    private FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private ProgressBar progressBar2;
    private ListView mmessageListViewMOM;
    private ListView mmessageListViewMOM2;
    private TextView TextName;
    private String Name;
    private String Picture;
    private String Time;
    private String username;
    private String Username;
    private ImageView logout;
    private String emailFacebook;
    private LinearLayout hideLayout2;
    private LinearLayout reload;
    private String skills;
    String UID;

    private RelativeLayout HideLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_sessions_activity);
        hideLayout2=(LinearLayout)findViewById(R.id.hideLayout2);
        mmessageListViewMOM = (ListView) findViewById(R.id.messageListView);
        mmessageListViewMOM2 = (ListView) findViewById(R.id.messageListView2);
        linearLayout=(RelativeLayout)findViewById(R.id. linearLayout12);
        main1=(LinearLayout)findViewById(R.id.main1);
        li=(LinearLayout)findViewById(R.id.li);
        progressBar2=(ProgressBar)findViewById(R.id.progressBar2);
        reload=(LinearLayout) findViewById(R.id.reload);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        logout=(ImageView)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(NetSessionsActivity.this, logout);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.game_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {


                        AuthUI.getInstance().signOut(NetSessionsActivity.this);

                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }


        });
        if(isNetworkAvailable()){

            hideLayout2.setVisibility(View.GONE);
        }
        else {

            hideLayout2.setVisibility(View.VISIBLE);
        }
        TextName=(TextView)findViewById(R.id.TextName);
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("MySessions");

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reload.setVisibility(View.GONE);
                progressBar2.setVisibility(View.VISIBLE);
                if(isNetworkAvailable()){
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            //findViewById(R.id.layout1).setVisibility(View.GONE);

                            Intent i = new Intent(NetSessionsActivity.this,MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            progressBar2.setVisibility(View.GONE);
                        }
                    }, 300);

                }
                else{
                    Toast.makeText(NetSessionsActivity.this,"Check Your Internet Connection",Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            //findViewById(R.id.layout1).setVisibility(View.GONE);
                            //  p.setVisibility(View.INVISIBLE);
                            progressBar2.setVisibility(View.GONE);
                            reload.setVisibility(View.VISIBLE);
                        }
                    }, 500);
                }

            }
        });

        Add=(ImageView) findViewById(R.id.UserTeam);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NetSessionsActivity.this,CreateNewNetSessionActivity.class);
                i.putExtra("Username",username);
                i.putExtra("Activity","NetSessionsActivity");
                System.out.println("emailFacebook"+emailFacebook);
                i.putExtra("ID",emailFacebook);
                startActivity(i);
            }
        });

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                onSignedInInitialize("");
                System.out.println("user? "+user);
//                System.out.println("user? "+user.getDisplayName());
  //              System.out.println("user? "+user.getEmail());
                if(user!=null) {
                    Username = user.getDisplayName();
                    final Bundle extra = getIntent().getExtras();
                    if (extra != null) {
                        System.out.println("HERE =>");
                        if(extra.getString("Activity").equals("GettingStartedActivity")){
                            TextName.setText("Hello, "+extra.getString("Name"));
                            username=extra.getString("Name");
                            emailFacebook=extra.getString("ID");
                        }
                        else if(extra.getString("Activity").equals("MainActivity")){
                            username=extra.getString("NAME");
                            TextName.setText("Hello, "+extra.getString("NAME"));














                            System.out.println("Ghulam"+username);
                        }
                        else if (extra.getString("Activity").equals("CreateNewNetSessionActivity")){
                            Name = extra.getString("Name");
                            Picture = extra.getString("Picture");
                            Time = extra.getString("Time");
                            System.out.println("HI");

                            emailFacebook=extra.getString("ID");
                            System.out.println("wtf"+extra.getString("ID"));
                            //   username2=extra.getString("Username");
                            System.out.println("what is this?"+Username);
                            System.out.println("id?"+emailFacebook);
                            Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("MySessions");

                            mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        int count1=0;
                                        for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                            // do something with the individual "issues"
                                            System.out.println(issue.child("id").getValue()+""+emailFacebook);
                                            if(issue.child("id").getValue().equals(emailFacebook)) {

                                               count1++;
                                            }

                                        }
                                        UID=emailFacebook+""+count1;
                                        System.out.println("UIDD"+UID);
                                        MySessionClass MySessionClass = new MySessionClass(UID,emailFacebook,Username,Picture, Name, Time,"Write a Message! ",0);
                                        // Clear input box
                                        mMessageDatabaseReference.push().setValue(MySessionClass);

                                    }
                                }


                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }

                    }
                    Bundle extra1 = getIntent().getExtras();
                    if (extra1 != null) {
                        if(extra1.getString("Activity").equals("MainActivityFacebook")){
                            emailFacebook=extra1.getString("ID");
                            username=extra.getString("NAME");
                            System.out.println("Harmain pagal nahi hai Harmain kutta hai");
                            TextName.setText("Hello, "+extra.getString("NAME"));
                        }
                        else if(extra1.getString("Activity").equals("MainActivityGoogle")){
                            emailFacebook=user.getEmail();
                        }
                        else if(extra1.getString("Activity").equals("NetworkError")){
                            linearLayout.setVisibility(View.GONE);
                            li.setVisibility(View.GONE);
                            System.out.println("Harmain pagal hai");
                            mmessageListViewMOM2.setVisibility(View.GONE);
                            main1.setVisibility(View.GONE);
                        }
                        else if (extra.getString("Activity").equals("CreateNewNetSessionActivity1")){
                             emailFacebook=extra.getString("ID");
                              }
                        else if (extra.getString("Activity").equals("NetSessionDetailActivity")){
                            emailFacebook=extra.getString("ID");
                        }

                    }

                        System.out.println("user.getEmail(): "+user.getEmail());
                    final List<MySessionClass> momclasses = new ArrayList<>();
                    final List<MySessionClass> momclasses2 = new ArrayList<>();

                    System.out.println("AND NOW? "+emailFacebook);
                    MySessionAdapter = new MySessionAdapter(NetSessionsActivity.this, R.layout.item_net_sessions, momclasses,Username,1,emailFacebook);
                    MySessionAdapter2 = new MySessionAdapter(NetSessionsActivity.this, R.layout.item_net_sessions, momclasses2,Username,2,emailFacebook);

                    System.out.println("here => "+MySessionAdapter);
                    if(mmessageListViewMOM!=null)
                        mmessageListViewMOM.setAdapter(MySessionAdapter);
                    if(mmessageListViewMOM2!=null)
                        mmessageListViewMOM2.setAdapter(MySessionAdapter2);


                }
                else{
                    Intent i = new Intent(NetSessionsActivity.this,MainActivity.class);
                    startActivity(i);
                }
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
                        if(dataSnapshot.child("id").getValue().equals(emailFacebook)){

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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}