package com.corphish.nightlight.UI.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.corphish.nightlight.Data.Constants;
import com.corphish.nightlight.Engine.Core;
import com.corphish.nightlight.Helpers.PreferenceHelper;
import com.corphish.nightlight.R;

/**
 * Created by Avinaba on 10/23/2017.
 * Master switch fragment
 */

public class MasterSwitchFragment extends Fragment {

    public interface MasterSwitchClickListener {
        void onSwitchClicked (boolean checkStatus);
    }

    MasterSwitchClickListener mCallback;
    boolean enabled;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enabled = PreferenceHelper.getBoolean(getContext(), Constants.PREF_MASTER_SWITCH);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (MasterSwitchClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement MasterSwitchClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.card_master_switch, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SwitchCompat masterSwitch = getView().findViewById(R.id.master_switch);
        masterSwitch.setChecked(enabled);
        masterSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Core.applyNightModeAsync(b, getContext());
                PreferenceHelper.putBoolean(getContext(), Constants.PREF_MASTER_SWITCH ,b);
                if (mCallback != null) mCallback.onSwitchClicked(b);
            }
        });

        AppCompatCheckBox preserveSwitch = getView().findViewById(R.id.kcal_preserve_switch);
        preserveSwitch.setChecked(PreferenceHelper.getBoolean(getContext(), Constants.KCAL_PRESERVE_SWITCH, true));
        preserveSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                PreferenceHelper.putBoolean(getContext(), Constants.KCAL_PRESERVE_SWITCH, b);
            }
        });
    }
}
