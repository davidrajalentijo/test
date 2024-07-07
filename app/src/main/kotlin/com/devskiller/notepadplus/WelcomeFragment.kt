package com.devskiller.notepadplus

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class WelcomeFragment : Fragment() {

    companion object {

        fun newInstance(): WelcomeFragment = WelcomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        val welcomeMessage = view.findViewById<TextView>(R.id.tv_welcome)
        welcomeMessage.text = getString(R.string.welcome_message)

        val createNoteButton = view.findViewById<View>(R.id.btn_add_note)
        createNoteButton.setOnClickListener {
            changeNoteActivity()
        }
        return view
    }

    private fun changeNoteActivity() {
        val intent = Intent(context, ChangeNoteActivity::class.java)
        context?.startActivity(intent)
    }
}
