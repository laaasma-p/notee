package com.example.notee;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class NotesActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText contentEditText;
    private Button addButton;

    private NoteActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        viewModel = new ViewModelProvider(this).get(NoteActivityViewModel.class);

        titleEditText = findViewById(R.id.titleEditText);
        contentEditText = findViewById(R.id.contentEditText);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString().trim();
                String content = contentEditText.getText().toString().trim();
                if (!title.isEmpty() && !content.isEmpty()) {
                    Note note = new Note(0, title, content);
                    viewModel.addNoteToDatabase(note);
                    // clear the EditText fields to prepare for a new note
                    titleEditText.setText("");
                    contentEditText.setText("");
                } else {
                    Toast.makeText(NotesActivity.this, "Please enter a title and content for the note.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Observe isNoteAdded LiveData
        viewModel.getIsNoteAdded().observe(this, isNoteAdded -> {
            if (isNoteAdded) {
                Toast.makeText(NotesActivity.this, "Note added successfully.", Toast.LENGTH_SHORT).show();
                viewModel.setIsNoteAdded(false); // reset isNoteAdded to false
            }
        });

    }
}