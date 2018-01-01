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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class NetSessionsAdapter extends ArrayAdapter<NetSessionsClass>{

    ArrayList al = new ArrayList();
    Dialog dialog;
    private Context context;
    int pos1;
    private FirebaseDatabase mFirebaseDatabase;
    ProgressBar mprogressBar;
    NetSessionsClass message;
    private int count=0;
    NetSessionsClass message2;
    public NetSessionsAdapter(Context context, int resource, List<NetSessionsClass> objects) {
        super(context, resource, objects);
        this.context=context;

        mFirebaseDatabase = FirebaseDatabase.getInstance();

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_net_sessions, parent, false);
        }
/*        final TextView Status=(TextView)convertView.findViewById(R.id.Status2);
        final ImageView StatusImage = (ImageView)convertView.findViewById(R.id.StatusImage);

        message2 = getItem(position);
        System.out.println(message2.getStatus());
        if(message2.getStatus().equals("1")){
            Status.setText("Accepted");
            //      System.out.println("HI");
            StatusImage.setBackgroundResource(R.drawable.done);
        } else if (message2.getStatus().equals("-1")) {

            Status.setText("Rejected");
            //      System.out.println("HI");
            StatusImage.setBackgroundResource(R.drawable.rejected);
        }
        else {
            Status.setText("Pending");
            //      System.out.println("HI");
            StatusImage.setBackgroundResource(R.drawable.pending);
        }


        pos1=position;

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  System.out.println("position: "+position);
                message = getItem(position);
                //  System.out.println("here-> "+pos1);
                showDialog(message.getLeaderName(),message.getMember1(),message.getMember2(),message.getStatus());

            }
        });

  */      return convertView;
    }

  /*  private void showDialog(String leader1,String member1,String member2,String status) {
        // custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.registeration_item_detail);

        // set the custom dialog components - text, image and button

        // Close Button

        // Buy Button
        //  System.out.println(leader1+" hahaha");
        TextView L=(TextView)dialog.findViewById(R.id.m2);
        TextView Member1=(TextView)dialog.findViewById(R.id.member1);
        TextView Member2=(TextView)dialog.findViewById(R.id.member2);
        TextView Status=(TextView)dialog.findViewById(R.id.Pending);

        L.setText(leader1);
        Member1.setText(member1);
        Member2.setText(member2);
        if(status.equals("0"))
            Status.setText("Pending!");
        else if(status.equals("1")){
            Status.setText("Accepted!");
        }
        else{
            Status.setText("Rejected");
        }
        Button Close  =(Button) dialog.findViewById(R.id.close2);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }*/
}