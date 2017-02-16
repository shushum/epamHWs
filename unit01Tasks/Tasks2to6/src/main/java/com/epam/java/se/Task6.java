package com.epam.java.se;

import java.util.Arrays;

public class Task6 {
}

class Notebook{
    private Note[] notes;
    private int size;

    public Notebook() {
        notes = new Note[10];
        size = 0;
    }

    /**
     * Adds a new Note to the Notebook.
     *
     * Adds a new Note to the Notebook
     * @param note Note to add
     */
    public void addNote(Note note){
        ensureCapacity(size + 1);
        notes[size] = note;
        size += 1;
    }

    /**
     * Deletes specified Note from the Notebook.
     *
     * Deletes Note from the Notebook by it's index. The following Notes cover the deleted one
     * @param noteIndex index of the Note to be deleted
     */
    public void deleteNote(int noteIndex){
        if (noteIndex < 0 || noteIndex > size - 1) {
            return;
        }
        Note[] copy = new Note[getCapacity() - 1];
        System.arraycopy(notes,0,copy,0, noteIndex);
        System.arraycopy(notes, noteIndex + 1, copy, noteIndex, size - noteIndex - 1);
        notes = copy;
        size -= 1;
    }

    /**
     * Edits specified Note in the Notebook.
     *
     * Edits specified Note in the Notebook. Edited Note replaces the old one
     * @param noteIndex index of the Note to be edited
     * @param newNote new content of the edited Note
     */
    public void editNote(int noteIndex, String newNote){
        if (noteIndex < 0 || noteIndex > size - 1) {
            return;
        }
        notes[noteIndex] = new Note(newNote);
    }

    /**
     * Shows all Notes of the Notebook in console.
     *
     * Shows all Notes of the Notebook in console. Each Note has it's own index and content
     */
    public void checkAllNotes(){
        for (int i = 0; i < size; i++){
            System.out.println("Note entry #" + i +":  " + notes[i].getNote());
        }
    }

    /**
     * Checks is there enough space for new Note.
     *
     * Checks is there enough space for new Note. If not, copies current Notebook in the new one with
     * more capacity
     * @param requiredCapacity required capacity of Notebook
     */
    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity <= getCapacity()){
            return;
        }
        final int newCapacity = Math.max(requiredCapacity, getCapacity()*3/2 + 1);
        notes = Arrays.copyOf(notes, newCapacity);
    }

    private int getCapacity(){return notes.length;}
}

class Note{
    private String note;

    public Note(){
        this.note = "Empty page";
    }

    public Note(String note){
        this.note = note;
    }

    public String getNote(){
        return note;
    }
}
