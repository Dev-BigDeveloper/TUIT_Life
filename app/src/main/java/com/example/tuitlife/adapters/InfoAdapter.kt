package com.example.tuitlife.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuitlife.databinding.StudentsItemBinding
import com.example.tuitlife.models.Rektorat
import com.squareup.picasso.Picasso


class InfoAdapter(private var list:List<Rektorat>) : RecyclerView.Adapter<InfoAdapter.Vh>(){
    inner class Vh(private var itemUserBinding: StudentsItemBinding):RecyclerView.ViewHolder(itemUserBinding.root){

        fun onBind(user: Rektorat){
            itemUserBinding.nameItem.text = user.name
            itemUserBinding.infoItem.text = user.info
            Picasso.get().load(user.image).into(itemUserBinding.imageItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(StudentsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}