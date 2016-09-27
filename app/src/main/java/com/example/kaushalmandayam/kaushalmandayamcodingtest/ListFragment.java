package com.example.kaushalmandayam.kaushalmandayamcodingtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kaushalmandayam.kaushalmandayamcodingtest.Adapters.RecyclerAdapter;
import com.example.kaushalmandayam.kaushalmandayamcodingtest.Constants.Config;
import com.example.kaushalmandayam.kaushalmandayamcodingtest.Helper.DividerItemDecoration;
import com.example.kaushalmandayam.kaushalmandayamcodingtest.Model.User;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


/**
 * Created by Kaushal.Mandayam on 9/24/2016.
 */
public class ListFragment extends Fragment {

    private static final String TAG = "ADAPTER";
    ArrayList<User> users = new ArrayList<>();
    RecyclerView rv;
    RecyclerAdapter adapter;
    Firebase fire;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(getContext());
        fire = new Firebase(Config.FIREBASE_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_2, container, false);


        rv = (RecyclerView) view.findViewById(R.id.RecyclerViewID);
        adapter = new RecyclerAdapter(this.getContext(), users);
        LinearLayoutManager llm = new LinearLayoutManager(this.getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        rv.addItemDecoration(new DividerItemDecoration(getActivity()));
        this.refreshData();
        return view;
    }

    private void getUpdates(DataSnapshot dataSnapshot) {
        users.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {

            User user = new User();
            user.setFirstName(ds.getValue(User.class).getFirstName());
            user.setLastName(ds.getValue(User.class).getLastName());
            user.setDob(ds.getValue(User.class).getDob());
            user.setZipCode(ds.getValue(User.class).getZipCode());
            user.setKey(ds.getKey());
            Log.i(TAG, "getUpdates: "+ds.getKey());

            users.add(user);

        }

        if (users.size() > 0) {
            adapter = new RecyclerAdapter(this.getContext(), users);
            rv.setAdapter(adapter);
            Log.i(TAG, "getUpdates: Adapter is set");
        } else {
            Toast.makeText(getContext(), "No data to be displayed", Toast.LENGTH_SHORT).show();
        }
    }

    private void refreshData() {
        fire.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        rv.setAdapter(adapter);
    }


}
