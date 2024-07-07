package com.devskiller.notepadplus

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devskiller.notepadplus.databinding.ViewNoteListItemBinding

class NoteAdapter(private val mNotes: List<Note>) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    inner class NoteHolder(
        val mViewBinding: ViewNoteListItemBinding
    ) : RecyclerView.ViewHolder(mViewBinding.root), View.OnClickListener {

        private var mNote: Note? = null

        override fun onClick(view: View) {
            val context = view.context
            val intent = Intent(context, ChangeNoteActivity::class.java)
            intent.putExtra("note", mNote)
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

        val noteTitle = if (note.title.isEmpty()) {
            field_not_be_empty_error
        }
        holder.mViewBinding.tvNoteTitle.text = note.title
    }

    override fun getItemCount(): Int = mNotes.size
}
