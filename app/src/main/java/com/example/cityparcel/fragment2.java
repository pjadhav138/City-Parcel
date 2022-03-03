package com.example.cityparcel;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class fragment2 extends Fragment {
    Button next;
    TextView skip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment2, container, false);

        next = view.findViewById(R.id.next);
        skip = view.findViewById(R.id.skip_fragment);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (  (PagerActivity) getActivity()).pager.setCurrentItem(2);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotonext_fragment = new Intent(getActivity(),LogIn_activity.class);
                startActivity(gotonext_fragment);
                getActivity().finish();
            }
        });


        return view;
    }
}