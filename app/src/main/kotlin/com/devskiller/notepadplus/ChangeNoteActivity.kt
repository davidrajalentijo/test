package com.devskiller.notepadplus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devskiller.notepadplus.databinding.ActivityChangeNoteBinding
import java.util.UUID

class ChangeNoteActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_NOTE_ID = "com.devskiller.intent.note_id"
        private const val EXTRA_EDIT_NOTE_ACTION = "com.devskiller.intent.edit_note_action"

        fun newIntent(
            context: Context,
            uuid: UUID
        ): Intent = Intent(context, ChangeNoteActivity::class.java)
            .putExtra(EXTRA_NOTE_ID, uuid)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding = ActivityChangeNoteBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        val noteIdExtra = intent.getStringExtra(EXTRA_NOTE_ID)
        val action = intent.getBooleanExtra(EXTRA_EDIT_NOTE_ACTION, false)
        var note: Note? = null

        noteIdExtra?.let {
            note = NoteLab.getNote(UUID.fromString(it))
            note?.let { resultNote -> setupView(viewBinding, resultNote) }
        }

        viewBinding.bSave.setOnClickListener {
            if (viewBinding.etTitle.text.toString().isEmpty()) {
                showEmptyError()
            } else {
                if (action) {
                    editNote(viewBinding, note)
                } else {
                    saveNote(viewBinding)
                }
            }
        }
    }

    private fun setupView(viewBinding: ActivityChangeNoteBinding, note: Note) {
        viewBinding.etTitle.setText(note.title)
        viewBinding.etDescription.setText(note.description)
    }

    private fun showEmptyError() {
        Toast.makeText(
            this,
            getString(R.string.field_not_be_empty_error),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun editNote(viewBinding: ActivityChangeNoteBinding, note: Note?) {
        note?.let {
            it.title = getNoteTitle(viewBinding)
            it.description = getNoteDescription(viewBinding)
            NoteLab.updateNote(it.id)
        }

        finish()
    }

    private fun saveNote(viewBinding: ActivityChangeNoteBinding) {
        val note =
            Note(
                title = getNoteTitle(viewBinding),
                description = getNoteDescription(viewBinding)
            )

        NoteLab.addNote(note)
        finish()
    }

    private fun getNoteTitle(viewBinding: ActivityChangeNoteBinding): String =
        viewBinding.etTitle.text.toString()

    private fun getNoteDescription(viewBinding: ActivityChangeNoteBinding): String =
        viewBinding.etDescription.text.toString()

}
