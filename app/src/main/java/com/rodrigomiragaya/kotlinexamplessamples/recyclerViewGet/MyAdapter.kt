package com.rodrigomiragaya.kotlinexamplessamples.recyclerViewGet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rodrigomiragaya.kotlinexamplessamples.R
import com.rodrigomiragaya.kotlinexamplessamples.retrofitGet.Post
import kotlinx.android.synthetic.main.recycler_adapter_layout.view.*

class MyAdapter (val onClick : (Post) -> Unit) :  RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var data =  emptyList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data[position]

        holder.apply {
            userId.text = currentItem.userId.toString()
            id.text = currentItem.id.toString()
            title.text = currentItem.titulo.toString()
            body.text = currentItem.body.toString()
            setItem(currentItem)
        }
    }

    override fun getItemCount(): Int {
      return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userId: TextView = itemView.userId_txt
        val id: TextView = itemView.id_txt
        val title: TextView = itemView.title_txt
        val body: TextView = itemView.body_txt


        fun setItem (item: Post){
            itemView.setOnClickListener {
                onClick(item)
            }
        }
    }

    //metodo para actualizar la lista de elementos del recyclerView
    fun setData(newList : List<Post>){
        val diferencia = DiffUtil.calculateDiff( object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = data.size

            override fun getNewListSize(): Int =newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = data[oldItemPosition]
                val newItem = newList[newItemPosition]
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return data[oldItemPosition] == newList[newItemPosition]
            }
        })

        this.data = newList
        diferencia.dispatchUpdatesTo(this)
//        notifyDataSetChanged()
    }

}

