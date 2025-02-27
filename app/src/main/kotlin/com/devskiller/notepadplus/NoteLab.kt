package com.devskiller.notepadplus

import androidx.annotation.VisibleForTesting
import java.util.UUID

object NoteLab {

    private val mNotes = mutableListOf<Note>()

    val notes: List<Note>
        get() = mNotes

    fun addNote(note: Note) {
        mNotes.add(note)
    }

    fun updateNote(newNote: Note) {
        mNotes.firstOrNull { note ->
            note.id == newNote.id
        }?.let { note ->
            mNotes.remove(note)
            mNotes.add(newNote)
        }
    }

    fun getNote(uuid: UUID): Note? = mNotes.firstOrNull { note ->
        note.id == uuid
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal fun clear() = mNotes.clear()
}
