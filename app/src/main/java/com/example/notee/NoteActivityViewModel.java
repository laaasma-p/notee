package com.example.notee;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NoteActivityViewModel extends ViewModel {
    private NoteRepository repository;
    private MutableLiveData<Boolean> isNoteAdded = new MutableLiveData<>(false);

    public NoteActivityViewModel(Application application) {
        repository = new NoteRepository(application);
    }

    public LiveData<Boolean> getIsNoteAdded() {
        return isNoteAdded;
    }

    public void addNoteToDatabase(Note note) {
        repository.addNoteToDatabase(note);
        isNoteAdded.setValue(true);
    }

    public void setIsNoteAdded(boolean value) {
        isNoteAdded.setValue(value);
    }
}