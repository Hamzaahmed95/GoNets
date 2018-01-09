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

        convertView.setOnClickListener(new View.OnClickListener() {
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
                    getContext().startActivity(i);
                }
            }
        });

       return convertView;
    }
}