package com.example.stud_ie_app.DashboardFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.stud_ie_app.LeaderboardRecyclerViewAdapter;
import com.example.stud_ie_app.R;
import com.example.stud_ie_app.SessionData;
import com.example.stud_ie_app.Users;

import java.util.ArrayList;
import java.util.List;

public class FragmentLeaderboard extends Fragment {

    View view;
    private RecyclerView leaderboardRecyclerView;
    private List<Users> mUsers;

    public FragmentLeaderboard() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.leaderboard_fragment, container, false);
        leaderboardRecyclerView = (RecyclerView) view.findViewById(R.id.leaderboard_recyclerview);
        LeaderboardRecyclerViewAdapter recyclerViewAdapter = new LeaderboardRecyclerViewAdapter(getContext(), mUsers);
        leaderboardRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        leaderboardRecyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUsers = SessionData.mUserDatabase.mUserDao().getAll();
        /*
        mUsers = new ArrayList<>();
        //TODO: Replace hard code with actual list
        mUsers.add(new Users("Kevin", "password", 1));
        mUsers.add(new Users("Sand Nigga", "muslim", 3));
        mUsers.add(new Users("Adam", "chew", 4));
        */

    }
}
