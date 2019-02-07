package com.example.sowmik.offline3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class HomeFragment extends Fragment implements View.OnClickListener {

    View view;
    private CardView SearchTrainCardView,LocateTrainCardView,TravelHistoryCardView,TrainInfoCardView;


    String u_name,u_pass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_home, container, false);


        Bundle bundle = getArguments();

        u_name = bundle.getString("name");
        u_pass = bundle.getString("pass");


        SearchTrainCardView = view.findViewById(R.id.searchtrainid);
        LocateTrainCardView = view.findViewById(R.id.locatetrainid);
        TravelHistoryCardView = view.findViewById(R.id.travelhistoryid);
        TrainInfoCardView = view.findViewById(R.id.traininfoid);

        SearchTrainCardView.setOnClickListener(this);
        LocateTrainCardView.setOnClickListener(this);
        TravelHistoryCardView.setOnClickListener(this);
        TrainInfoCardView.setOnClickListener(this);
        
        return view;
    }


    @Override
    public void onClick(View v) {
        
        if(v.getId()==R.id.searchtrainid)
        {
            Toast.makeText(getActivity(), "Search Train", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(),SearchTrainActivity.class);
            intent.putExtra("name",u_name);

            startActivity(intent);

        }
        else if(v.getId()==R.id.locatetrainid)
        {
            Toast.makeText(getActivity(), "Locate Train", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(),LocateTrainActivity.class);
            startActivity(intent);

        }
        else if(v.getId()==R.id.travelhistoryid)
        {
            Toast.makeText(getActivity(), "Travel History", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(),TravelHistoryActivity.class);
            intent.putExtra("name",u_name);

            startActivity(intent);

        }
        else if(v.getId()==R.id.traininfoid)
        {
            Toast.makeText(getActivity(), "Train Info", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(),TrainInfoActivity.class);
            intent.putExtra("name",u_name);

            startActivity(intent);

        }
        
    }
}
