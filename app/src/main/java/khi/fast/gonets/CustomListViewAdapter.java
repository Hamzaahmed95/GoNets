package khi.fast.gonets;

/**
 * Created by Hamza Ahmed on 02-Jan-18.
 */


import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomListViewAdapter extends ArrayAdapter<NetFacilityClass> {

    Context context;

    public CustomListViewAdapter(Context context, int resourceId,
                                 List<NetFacilityClass> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView Picture;
        TextView txtName;
        TextView txtAddress;
        LinearLayout linearLayout;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        NetFacilityClass rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_net_facilities, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.name);
            holder.txtAddress = (TextView) convertView.findViewById(R.id.address);
            holder.Picture = (ImageView) convertView.findViewById(R.id.picture);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.linearLayout);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtName.setText(rowItem.getName());
        holder.txtAddress.setText(rowItem.getAddress());
        holder.Picture.setImageResource(Integer.parseInt(rowItem.getPictures()));

        return convertView;
    }
}