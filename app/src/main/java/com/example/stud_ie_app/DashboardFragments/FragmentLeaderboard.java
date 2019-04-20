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
import com.example.stud_ie_app.User;

import java.util.ArrayList;
import java.util.List;

public class FragmentLeaderboard extends Fragment {

    View view;
    private RecyclerView leaderboardRecyclerView;
    private List<User> mUsers;

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
        mUsers = new ArrayList<>();
        //TODO: Replace hard code with actual list
        mUsers.add(new User("Kevin the carry Nguyen", "Intern", 1000, 1));
        mUsers.add(new User("Sand Nigga", "Graduate", 1500, 3));
        mUsers.add(new User("Adam big dick Chew", "Exec", 120300, 3));
        mUsers.add(new User("Kevin the carry Nguyen", "Senior", 1000, 4));
        mUsers.add(new User("Sand Nigga", "Graduate", 1500, 5));
        mUsers.add(new User("RM Williams Rob", "Director", 120300, 6));
        mUsers.add(new User("Curry Muncher", "Intern", 1000, 7));
        mUsers.add(new User("Uesless Fuck", "Graduate", 1500, 8));
        mUsers.add(new User("Joyce the hottie", "Intern", 1000, 1));
        mUsers.add(new User("Oscar bae", "Graduate", 1500, 3));
        mUsers.add(new User("Yenni Timmy Turner", "Exec", 120300, 3));
        mUsers.add(new User("Ali Ali Akhbar", "Intern", 1000, 4));
        mUsers.add(new User("BIG DICK ENERGY!!", "Graduate", 1500, 5));
        mUsers.add(new User("Osama Bin Suck my Dick", "Exec", 120300, 6));
        mUsers.add(new User("Sand Nigga #2", "Intern", 1000, 7));
        mUsers.add(new User("Halal", "Graduate", 1500, 8));

    }
}
