package com.corphish.nightlight.UI.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.corphish.nightlight.Helpers.ExternalLink;
import com.corphish.nightlight.R;

/**
 * Created by avinabadalal on 31/12/17.
 * Contributors fragment
 */

public class ContributorsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.card_contributors, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExternalLink.open(getContext(), "https://github.com/corphish/NightLight/graphs/contributors");
            }
        });
    }
}
