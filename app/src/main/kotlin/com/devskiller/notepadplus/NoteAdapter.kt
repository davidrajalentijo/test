package com.devskiller.notepadplus

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devskiller.notepadplus.databinding.ViewNoteListItemBinding

class NoteAdapter(private val mNotes: List<Note>) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    companion object {
        private const val EXTRA_NOTE_ID = "com.devskiller.intent.note_id"
        private const val EXTRA_EDIT_NOTE_ACTION = "com.devskiller.intent.edit_note_action"
    }
    inner class NoteHolder(
        private val mViewBinding: ViewNoteListItemBinding
    ) : RecyclerView.ViewHolder(mViewBinding.root), View.OnClickListener {

        private var mNote: Note? = null

        fun setNote(note: Note) {
            mNote = note
        }

        override fun onClick(view: View) {
            val context = view.context
            val intent = Intent(context, ChangeNoteActivity::class.java)
            intent.apply {
                putExtra(EXTRA_EDIT_NOTE_ACTION, true)
                putExtra(EXTRA_NOTE_ID, mNote?.id.toString())
            }
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteHolder = NoteHolder(ViewNoteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(
        holder: NoteHolder,
        position: Int
    ) {
        val note = mNotes[position]
        holder.setNote(note)
        val noteTitle = holder.itemView.findViewById<TextView>(R.id.tv_note_title)
        val container = holder.itemView.findViewById<LinearLayout>(R.id.ll_note_list_item)
        noteTitle.text = note.title

        container.setOnClickListener {
            holder.onClick(it)
        }
    }

    override fun getItemCount(): Int = mNotes.size
}
