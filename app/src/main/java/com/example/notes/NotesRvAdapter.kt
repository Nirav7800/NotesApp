package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class NotesRvAdapter(private val context:Context, private val listner:INotedRvAdapter):RecyclerView.Adapter<NotesRvAdapter.NoteViewHolder>() {

    val allnotes=ArrayList<Note>()

    inner class  NoteViewHolder(itemView: View) : ViewHolder(itemView){

        val textView=itemView.findViewById<TextView>(R.id.textView)
        val deletebtn=itemView.findViewById<ImageView>(R.id.deletebtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
      val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deletebtn.setOnClickListener {
            listner.onitemClicked(allnotes[viewHolder.adapterPosition])
        }
        return  viewHolder

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       val current_note=allnotes[position]
        holder.textView.text=current_note.text
    }

    override fun getItemCount(): Int {
      return  allnotes.size
    }

    fun updateList(newList:List<Note>){
        allnotes.clear()
        allnotes.addAll(newList)
        notifyDataSetChanged()
    }
}
interface INotedRvAdapter{
    fun onitemClicked(note:Note)
}