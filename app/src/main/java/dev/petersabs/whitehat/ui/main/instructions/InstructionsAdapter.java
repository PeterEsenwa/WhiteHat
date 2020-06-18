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
    private static InstructionsAdapterInterface adapterInterfaceListener;
    private final ArrayList<Step> mSteps;

    InstructionsAdapter(ArrayList<Step> mSteps, InstructionsAdapterInterface listener) {
        this.mSteps = mSteps;
        adapterInterfaceListener = listener;
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
        String videoURL = holder.mInstruction.getVideoURL();
        if (videoURL != null && !videoURL.trim().isEmpty()) {
            holder.mPlayInstructionVideoIV.setVisibility(View.VISIBLE);
        } else {
            Log.d("HOLDER", holder.mInstruction.getShortDescription());
        }
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public interface InstructionsAdapterInterface {
        void instructionPicked(Step step);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final View mView;
        final TextView mInstructionTitle;
        final TextView mInstructionText;
        final ImageView mPlayInstructionVideoIV;
        Step mInstruction;
        boolean isExpanded = false;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mView.setOnClickListener(this);
//            mView.setOnLongClickListener(this);
//            mView.setOnTouchListener(this);
            mInstructionText = itemView.findViewById(R.id.step_text);
            mInstructionTitle = itemView.findViewById(R.id.step_title);
            mPlayInstructionVideoIV = itemView.findViewById(R.id.play_step_video);
        }

        @Override
        public void onClick(View v) {
            adapterInterfaceListener.instructionPicked(mInstruction);
        }

//        @Override
//        public boolean onLongClick(View v) {
//            isExpanded = true;
//            mInstructionText.setMaxLines(Integer.MAX_VALUE);
////            mView.setElevation(8);
//            return true;
//        }
//
//        @SuppressLint("ClickableViewAccessibility")
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            if (event.getAction() == MotionEvent.ACTION_UP && isExpanded) {
//                isExpanded = false;
//                mInstructionText.setMaxLines(3);
////                mView.setElevation(0);
//            }
//            return false;
//        }
    }
}
