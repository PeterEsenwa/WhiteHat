package dev.petersabs.whitehat.ui.main.instructions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import dev.petersabs.whitehat.models.Step;

public class InstructionsFragment extends Fragment {
    private static final String ARG_STEPS_LIST = "steps-list";

    public InstructionsFragment() {
    }

    public static InstructionsFragment newInstance(ArrayList<Step> steps) {
        InstructionsFragment fragment = new InstructionsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_STEPS_LIST, steps);
        fragment.setArguments(args);
        return fragment;
    }

    public static InstructionsFragment newInstance() {
        return new InstructionsFragment();
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return new ConstraintLayout(requireContext());
    }
}
