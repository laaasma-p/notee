package com.example.notee.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notee.R;
import com.example.notee.model.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private final Context context;
    private List<Note> notesList;
    private OnNoteItemClickListener onNoteItemClickListener;

    public NotesAdapter(Context context, List<Note> notesList, OnNoteItemClickListener onNoteItemClickListener) {
        this.context = context;
        this.notesList = notesList;
        this.onNoteItemClickListener = onNoteItemClickListener;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_note, parent, false);

        return new NotesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        holder.noteTitle.setText(notesList.get(holder.getAdapterPosition()).getTitle());
        holder.noteContent.setText(notesList.get(holder.getAdapterPosition()).getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                onNoteItemClickListener.onNoteItemClick(getItem(currentPosition), currentPosition);
            }
        });
    }


    public Note getItem(int position) {
        return notesList.get(position);
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Declare instance variables for the note layout
        TextView noteTitle, noteContent;

        ViewHolder(View itemView) {
            super(itemView);

            noteTitle = itemView.findViewById(R.id.titleEditText);
            noteContent = itemView.findViewById(R.id.content);
        }
    }

//    public void submitList(List<Note> noteList) {
//        this.notesList = noteList;
//        notifyDataSetChanged();
//    }

    public interface OnNoteItemClickListener {
        void onNoteItemClick(Note note, int position);
    }
}