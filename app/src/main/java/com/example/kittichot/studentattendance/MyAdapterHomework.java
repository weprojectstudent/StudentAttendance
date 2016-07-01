package com.example.kittichot.studentattendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by kittichot on 17/4/2559.
 */
public class MyAdapterHomework extends BaseAdapter {
    private Context objContext;
    private String[] strID,strTitle,strDetails,strsavethedate, strdatesent;
    public MyAdapterHomework(Context context,String[] getstrID,String[] getstrTitle,String[] getstrDetails,String[] getstrsavethedate,String[] getstrdatesent) {
        this.objContext = context;
        this.strID = getstrID;
        this.strTitle = getstrTitle;
        this.strDetails = getstrDetails;
        this.strsavethedate = getstrsavethedate;
        this.strdatesent = getstrdatesent;
    }



    @Override
    public int getCount() {
        return strTitle.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = objLayoutInflater.inflate(R.layout.list_view_homework, parent, false);

        if (position % 2 == 0) {
            view.setBackgroundResource(R.color.colorbg0);
        } else {
            view.setBackgroundResource(R.color.colorbg1);
        }

        TextView txtViewTitle = (TextView) view.findViewById(R.id.txthomeName);
        txtViewTitle.setText(strTitle[position]);

        TextView txtViewDetail = (TextView) view.findViewById(R.id.txtDetail);
        txtViewDetail.setText(strDetails[position]);

        TextView txtViewSavedate = (TextView) view.findViewById(R.id.textView9);
        txtViewSavedate.setText(strsavethedate[position]);

        TextView txtViewDatesent = (TextView) view.findViewById(R.id.textView12);
        txtViewDatesent.setText(strdatesent[position]);

        TextView txtViewHideID = (TextView) view.findViewById(R.id.textView19);
        txtViewHideID.setText(strID[position]);
        return view;
    }
}
