package com.example.dictionary

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class StartGameDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Not Found !")
                .setPositiveButton("ok",
                    DialogInterface.OnClickListener { _, _ ->
                       dismiss()
                    })
                .setNegativeButton("cancel",
                    DialogInterface.OnClickListener { _, _ ->
                        dismiss()
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}