package dev.petersabs.whitehat.ui.main.instructions;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.petersabs.whitehat.R;
import dev.petersabs.whitehat.models.Step;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionsAdapter.ViewHolder> {
    private final ArrayList<Step> mSteps;

    InstructionsAdapter(ArrayList<Step> mSteps) {
        this.mSteps = mSteps;
    }

    @NonNull
    @Override
    public InstructionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.instruction_summary, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsAdapter.ViewHolder holder, int position) {
        holder.mInstruction = mSteps.get(position);
        holder.mInstructionTitle.setText(holder.mInstruction.getShortDescription());
        holder.mInstructionText.setText(holder.mInstruction.getDescription());
        if (holder.mInstruction.getVideoURL() != null) {
            holder.mPlayInstructionVideoIV.setVisibility(View.VISIBLE);
        } else {
            Log.d("HOLDER", holder.mInstruction.getShortDescription());
        }
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mInstructionTitle;
        final TextView mInstructionText;
        final ImageView mPlayInstructionVideoIV;
        Step mInstruction;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mInstructionText = itemView.findViewById(R.id.step_text);
            mInstructionTitle = itemView.findViewById(R.id.step_title);
            mPlayInstructionVideoIV = itemView.findViewById(R.id.play_step_video);
        }
    }
}
