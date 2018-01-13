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

public class noOfPeopleAdapter extends ArrayAdapter<NoOfPeopleGoing>{

    ArrayList al = new ArrayList();
    Dialog dialog;
    private Context context;
    int pos1;
    private FirebaseDatabase mFirebaseDatabase;
    ProgressBar mprogressBar;
    private int count=0;
  //  NoOfPeopleGoing message2;
    String Email;
    int flagg;
    String skillss;
    String skillss2;
    NoOfPeopleGoing message;
    FirebaseUser user;
    String username;

    private FirebaseAuth mAuth;
    public noOfPeopleAdapter(Context context, int resource, List<NoOfPeopleGoing> objects) {
        super(context, resource, objects);
        this.context=context;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();



    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_no_of_people_going, parent, false);
        }

        final TextView Name=(TextView)convertView.findViewById(R.id.name);
        message=getItem(position);
        if(!message.equals(null))
        Name.setText(message.getId());

        /*        Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("UserProfile");

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





/*        message = getItem(position);
        Picture.setImageResource(Integer.parseInt(message.getPicture()));
        Name.setText(message.getName());
        Time.setText(message.getTime());
        noOfPersonGoing.setText(String.valueOf(message.getNoOfPeopleGoing())+"going");

     /*   convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flagg==1) {
                    message = getItem(position);
                    Intent i = new Intent(getContext(), NetSessionDetailActivity.class);
                    System.out.println("=>"+message.getName());
                    System.out.println("=>"+message.getTime());
                    System.out.println("=>"+username);
                    System.out.println("=>"+skillss);
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
                                System.out.println("=>"+message.getName());
                                System.out.println("=>"+message.getTime());
                                System.out.println("=>"+message.getUsername());
                                System.out.println("=>"+skillss2);
                                System.out.println("=>"+message.getID());
                                i.putExtra("Name", message.getName());
                                i.putExtra("Time", message.getTime());
                                i.putExtra("Username", message.getUsername());
                                i.putExtra("Skills",skillss2);
                                i.putExtra("Id",message.getID());
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
*/
        return convertView;
    }
}