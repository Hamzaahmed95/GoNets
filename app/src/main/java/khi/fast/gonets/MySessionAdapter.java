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

public class MySessionAdapter extends ArrayAdapter<MySessionClass>{

    ArrayList al = new ArrayList();
    Dialog dialog;
    private Context context;
    int pos1;
    private FirebaseDatabase mFirebaseDatabase;
    ProgressBar mprogressBar;
    MySessionClass message;
    private int count=0;
    MySessionClass message2;
    public MySessionAdapter(Context context, int resource, List<MySessionClass> objects) {
        super(context, resource, objects);
        this.context=context;

        mFirebaseDatabase = FirebaseDatabase.getInstance();

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_net_sessions, parent, false);
        }

        ImageView Picture=(ImageView) convertView.findViewById(R.id.Picture);
        TextView Name=(TextView)convertView.findViewById(R.id.Name);
        TextView Time=(TextView)convertView.findViewById(R.id.Time);


        MySessionClass message = getItem(position);
        Picture.setImageResource(Integer.parseInt(message.getPicture()));
        Name.setText(message.getName());
        Time.setText(message.getTime());

       return convertView;
    }
}