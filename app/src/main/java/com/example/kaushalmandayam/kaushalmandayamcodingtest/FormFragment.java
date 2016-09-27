package com.example.kaushalmandayam.kaushalmandayamcodingtest;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kaushalmandayam.kaushalmandayamcodingtest.Constants.Config;
import com.example.kaushalmandayam.kaushalmandayamcodingtest.Model.User;
import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by Kaushal.Mandayam on 9/24/2016.
 */
public class FormFragment extends Fragment implements View.OnClickListener {


    private Button mSubmitButton;
    private Button mClearButton;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mDateofBirth;
    private EditText mZipCode;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    Firebase fire;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(getContext());
        fire = new Firebase(Config.FIREBASE_URL);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);

        mSubmitButton = (Button) view.findViewById(R.id.submitButton);
        mClearButton = (Button) view.findViewById(R.id.clearButton);
        mFirstName = (EditText) view.findViewById(R.id.firstName);
        mLastName = (EditText) view.findViewById(R.id.lastName);
        mDateofBirth = (EditText) view.findViewById(R.id.dateOfBirth);
        mZipCode = (EditText) view.findViewById(R.id.zipCode);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirstName.setText("");
                mLastName.setText("");
                mDateofBirth.setText("");
                mZipCode.setText("");

            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firebase ref = new Firebase(Config.FIREBASE_URL);

                String firstName = mFirstName.getText().toString().trim();
                String lastName = mLastName.getText().toString().trim();
                String dateOfBirth = mDateofBirth.getText().toString().trim();
                String zipCode = mZipCode.getText().toString().trim();

                User user = new User();
                if (!Objects.equals(firstName, "") && !Objects.equals(lastName, "")
                        && !Objects.equals(dateOfBirth, "") && !Objects.equals(zipCode, "")) {
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setDob(dateOfBirth);
                    user.setZipCode(zipCode);
                    fire.child("User").push().setValue(user);
                    Toast.makeText(getActivity(), "User added to database", Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(getActivity(), "Please enter all the details", Toast.LENGTH_SHORT).show();
            }
        });
        setDateTimeField();

        return view;

    }

    private void setDateTimeField() {

        mDateofBirth.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mDateofBirth.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View view) {
        if (view == mDateofBirth) {
            fromDatePickerDialog.show();

        }
    }

    
}
