package khi.fast.gonets;

/**
 * Created by Hamza Ahmed on 30-Dec-17.
 */


/**
 * Created by Hamza Ahmed on 23-Dec-17.
 */


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class MySessionAdapter extends ArrayAdapter<MySessionClass>{

    ArrayList al = new ArrayList();
    Dialog dialog;
    private Context context;
    int pos1;
    private FirebaseDatabase mFirebaseDatabase;
    ProgressBar mprogressBar;
    private int count=0;
    MySessionClass message2;
    String Email;
    int flagg;
    String skillss;
    String skillss2;
    MySessionClass message;
    FirebaseUser user;
    String username;

    private FirebaseAuth mAuth;
    public MySessionAdapter(Context context, int resource, List<MySessionClass> objects,String name1,int flag,String email) {
        super(context, resource, objects);
        this.context=context;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        System.out.println("->Email"+email);
        username=name1;
        flagg=flag;
        mAuth.getCurrentUser();
        Email=email;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_net_sessions, parent, false);
        }

        ImageView Picture=(ImageView) convertView.findViewById(R.id.Picture);
        final TextView Name=(TextView)convertView.findViewById(R.id.Name);
        TextView Time=(TextView)convertView.findViewById(R.id.Time);
        TextView noOfPersonGoing=(TextView)convertView.findViewById(R.id.noOfPersonGoing);

        Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("UserProfile");

        mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int count1=0;
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"
                        System.out.println(issue.child("id").getValue()+""+Email);
                        if(issue.child("id").getValue().equals(Email)) {

                            skillss=issue.child("skills").getValue().toString();
                            System.out.println("SKILLS "+skillss);
                        }

                    }

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        message = getItem(position);
//        Picture.setImageResource(Integer.parseInt(message.getPicture()));
        Name.setText(message.getName());
        Time.setText(message.getTime());
        noOfPersonGoing.setText(String.valueOf(message.getNoOfPeopleGoing())+" going");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flagg==1) {
                    message = getItem(position);
                    Intent i = new Intent(getContext(), NetSessionDetailActivity.class);
                    System.out.println("=>flag1"+message.getName());
                    System.out.println("=>flag1"+message.getTime());
                    System.out.println("=>flag1"+message.getUsername());
                    System.out.println("=>flag1"+skillss);
                    System.out.println("=>flag1"+message.getID());
                    System.out.println("=>flag1"+message.getUID());
                    i.putExtra("Name", message.getName());
                    i.putExtra("Time", message.getTime());
                    i.putExtra("Username", username);
                    i.putExtra("Skills",skillss);
                    i.putExtra("Id",Email);
                    i.putExtra("Message",message.getMessage());
                    i.putExtra("UID",message.getUID());
                    i.putExtra("CurrentUser",Email);
                    getContext().startActivity(i);
                }
                else{
                    message = getItem(position);
                    Query mHouseDatabaseReference4 =mFirebaseDatabase.getReference().child("UserProfile");

                    mHouseDatabaseReference4.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                int count1=0;
                                for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                    // do something with the individual "issues"
                                 //   System.out.println(issue.child("id").getValue()+""+Email);
                                    if(issue.child("id").getValue().equals(message.getID())) {

                                        skillss2=issue.child("skills").getValue().toString();
                                      //  System.out.println("SKILLS "+skillss2);
                                    }

                                }

                                Intent i = new Intent(getContext(), NetSessionDetailActivity.class);
                                System.out.println("=>flag2"+message.getName());
                                System.out.println("=>flag2"+message.getTime());
                                System.out.println("=>flag2"+message.getUsername());
                                System.out.println("=>flag2"+skillss2);
                                System.out.println("=>flag2"+message.getID());
                                System.out.println("=>flag2"+message.getUID());
                                i.putExtra("Name", message.getName());
                                i.putExtra("Time", message.getTime());
                                i.putExtra("Username", message.getUsername());
                                i.putExtra("Skills",skillss2);
                                i.putExtra("Id",Email);
                                System.out.println("=>UID"+message.getUID());
                                i.putExtra("UID",message.getUID());
                                System.out.println("Message hai ?"+message.getMessage());
                                i.putExtra("Message",message.getMessage());
                                i.putExtra("CurrentUser",Email);
                                getContext().startActivity(i);

                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }
            }
        });

       return convertView;
    }
}