package com.example.kaushalmandayam.kaushalmandayamcodingtest;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaushalmandayam.kaushalmandayamcodingtest.Model.User;
import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {


    private static final String TAG = "POSITION";
    private static final String TAG2 = "KEY";
    TextView firstName, LastName, dob, zipCode;
    EditText firstNameEdtTxt, LastNameEdtTx, dobEdtTx, zipCodeEdtTx;
    Button deleteButton, updateButton;
    String mFirstName, mLastName, mDateOfBirth, mZip;
    Firebase ref;
    String fName, lName, dateOfBirth, zip, key;

    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ref = new Firebase("https://service-fusion.firebaseio.com/");
        Firebase.setAndroidContext(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.Detailtoolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
                finish();
                //What to do on back clicked
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        firstName = (TextView) findViewById(R.id.firstNameTV);
        LastName = (TextView) findViewById(R.id.lastNameTV);
        dob = (TextView) findViewById(R.id.dobTV);
        zipCode = (TextView) findViewById(R.id.zipCodeTV);


        //RECEIVE DATA
        Intent i = this.getIntent();
        fName = i.getExtras().getString("FNAME_KEY");
        lName = i.getExtras().getString("LNAME_KEY");
        dateOfBirth = i.getExtras().getString("DOB_KEY");
        zip = i.getExtras().getString("ZIP_KEY");
        pos = i.getExtras().getInt("POS_KEY");
        Log.i(TAG, "onCreate: " + pos);
        key = i.getExtras().getString("KEY_KEY");
        Log.i(TAG2, "onCreate: " + key);


        //SET DATA
        firstName.setText(fName);
        LastName.setText(lName);
        dob.setText(dateOfBirth);
        zipCode.setText(zip);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayUpdateDialog();
            }
        });
    }

    private void displayUpdateDialog() {


        Dialog d = new Dialog(this);
        d.setTitle("UPDATE DATA");
        d.setContentView(R.layout.dialog_layout);

        firstNameEdtTxt = (EditText) d.findViewById(R.id.dialogFnameEditTxt);
        LastNameEdtTx = (EditText) d.findViewById(R.id.dialogLnameEditTxt);
        dobEdtTx = (EditText) d.findViewById(R.id.dialogDobEditTxt);
        zipCodeEdtTx = (EditText) d.findViewById(R.id.dialogZipCodeEditTxt);
        updateButton = (Button) d.findViewById(R.id.updateBtn);
        deleteButton = (Button) d.findViewById(R.id.deleteBtn);

        firstNameEdtTxt.setText(fName);
        LastNameEdtTx.setText(lName);
        dobEdtTx.setText(dateOfBirth);
        zipCodeEdtTx.setText(zip);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firebase userRef = ref.child("User").child(key);
                User user = new User();
                mFirstName = firstNameEdtTxt.getText().toString();
                mLastName = LastNameEdtTx.getText().toString();
                mDateOfBirth = dobEdtTx.getText().toString();
                mZip = zipCodeEdtTx.getText().toString();


                user.setFirstName(mFirstName);
                user.setLastName(mLastName);
                user.setDob(mDateOfBirth);
                user.setZipCode(mZip);

                Map<String, Object> userData = new HashMap<String, Object>();
                userData.put("dob", mDateOfBirth);
                userData.put("firstName", mFirstName);
                userData.put("lastName", mLastName);
                userData.put("zipCode", mZip);
                userRef.updateChildren(userData);
                Toast.makeText(getBaseContext(), "USER DATA UPDATED", Toast.LENGTH_SHORT).show();
                DetailActivity.this.finish();

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Firebase userRef = ref.child("User").child(key);
                userRef.removeValue();
                Toast.makeText(getBaseContext(), "USER DELETED", Toast.LENGTH_SHORT).show();
                DetailActivity.this.finish();

            }
        });
        d.show();
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
