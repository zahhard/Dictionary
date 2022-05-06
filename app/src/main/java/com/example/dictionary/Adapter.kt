package com.example.dictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


typealias clickHandler = ((word: String) -> Unit)

class CustomAdapter(var dataList: ArrayList<WordEntity>,  var clickHandler: clickHandler) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvWord: TextView = view.findViewById(R.id.word)
        val tvMeaning: TextView = view.findViewById(R.id.meaning)
        val btnOnClick: Button = view.findViewById(R.id.button_detail)

        fun bind(wordEntity: String, clickHandler: clickHandler) {
            btnOnClick.setOnClickListener {
                clickHandler(wordEntity)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_recycler_view, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvWord.text = dataList[position].word.toString()
        holder.tvMeaning.text = dataList[position].meaning
        holder.bind(dataList[position].word, clickHandler)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}