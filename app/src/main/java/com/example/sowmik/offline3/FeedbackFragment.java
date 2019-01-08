package com.example.sowmik.offline3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FeedbackFragment extends Fragment implements View.OnClickListener {

    View view;
    private Button sendButton, clearButton;
    private EditText nameEdittext, messagerEdittext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_feedback, container, false);

        sendButton = view.findViewById(R.id.sendid);
        clearButton = view.findViewById(R.id.clearid);

        nameEdittext = view.findViewById(R.id.nameid);
        messagerEdittext = view.findViewById(R.id.feedid);

        sendButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        try {
            String name = nameEdittext.getText().toString();
            String message = messagerEdittext.getText().toString();

            if (v.getId() == R.id.sendid) {

                if(name.equals("") || message.equals(""))
                {
                    Toast.makeText(getActivity(), "Please write your name & feedback", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/email");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"sowmiksarker.2355.csedu@gmail.com", "safwan.du16@gmail.com", "raihandewon.sh49.csedu@gmail.com"});

                    intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from android app");
                    intent.putExtra(Intent.EXTRA_TEXT, "Name: " + name + "\nMessage: " + message);
                    startActivity(Intent.createChooser(intent, "Feedback With"));

                    nameEdittext.setText("");
                    messagerEdittext.setText("");
                }

            }
            else if (v.getId() == R.id.clearid) {
                nameEdittext.setText("");
                messagerEdittext.setText("");
            }

        } catch (Exception e) {

            Toast.makeText(getActivity(), "Exception: "+e, Toast.LENGTH_SHORT).show();
        }


    }
}
