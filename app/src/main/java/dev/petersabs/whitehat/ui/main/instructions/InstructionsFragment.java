package dev.petersabs.whitehat.ui.main.instructions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import dev.petersabs.whitehat.R;
import dev.petersabs.whitehat.models.Step;

public class InstructionsFragment extends Fragment implements InstructionsAdapter.InstructionsAdapterInterface {
    private static final String ARG_STEPS_LIST = "steps-list";
    private static final String ARG_IMAGE_URL = "image-url";
    private ArrayList<Step> mSteps;
    private String mImageUrl;
    private ConstraintLayout instructionBottomSheet;

    public InstructionsFragment() {
    }

    public static InstructionsFragment newInstance(@NonNull ArrayList<Step> steps, String imageUrl) {
        InstructionsFragment fragment = new InstructionsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_STEPS_LIST, steps);
        if (imageUrl != null) args.putString(ARG_IMAGE_URL, imageUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mSteps = getArguments().getParcelableArrayList(ARG_STEPS_LIST);
            mImageUrl = getArguments().getString(ARG_IMAGE_URL);
        }
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instructions_list, container, false);

        instructionBottomSheet = view.findViewById(R.id.instructions_bottomsheet);

        RecyclerView instructionsRV = view.findViewById(R.id.recipes_instructions_list);
        instructionsRV.setLayoutManager(new LinearLayoutManager(view.getContext()));
        instructionsRV.setAdapter(new InstructionsAdapter(mSteps, this));

        if (mImageUrl != null && !mImageUrl.trim().isEmpty()) {
            View appBarLayout = view.findViewById(R.id.instructions_appbar_layout);
            appBarLayout.setVisibility(View.VISIBLE);
            Picasso.get().load(mImageUrl)
                    .placeholder(R.color.primaryBackground)
                    .into((ImageView) appBarLayout.findViewById(R.id.recipe_image));
        }
        return view;
    }

    @Override
    public void instructionPicked(@NotNull Step step) {

    }
}
