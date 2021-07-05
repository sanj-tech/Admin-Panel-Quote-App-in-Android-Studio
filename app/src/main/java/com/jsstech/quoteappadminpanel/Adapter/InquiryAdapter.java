package com.jsstech.quoteappadminpanel.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.jsstech.quoteappadminpanel.Model.ModelInquiry;
import com.jsstech.quoteappadminpanel.R;

import java.util.List;

public class InquiryAdapter extends ArrayAdapter {


    Activity activity;
    List<ModelInquiry> list;

    public InquiryAdapter(Activity activity, List<ModelInquiry> list) {
        super(activity, R.layout.layout_inquiry, list);
        this.activity = activity;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(R.layout.layout_inquiry, null, true);
        
        TextView name = (TextView) convertView.findViewById(R.id.viewInquiry_name);
        TextView email = (TextView) convertView.findViewById(R.id.viewInquiry_email);
        TextView contact = (TextView) convertView.findViewById(R.id.viewInquiry_contact);
        TextView ask = (TextView) convertView.findViewById(R.id.viewInquiry_ask);
        TextView feedback = (TextView) convertView.findViewById(R.id.viewInquiry_feedback);

        ModelInquiry modelInquiry = list.get(position);
      
        name.setText(modelInquiry.getName());
        email.setText(modelInquiry.getEmail());
        contact.setText(modelInquiry.getContact());
        ask.setText(modelInquiry.getAsk());
        feedback.setText(modelInquiry.getFeedback());

        return convertView;
    }
}
