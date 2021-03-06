package com.example.tuitlife.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuitlife.databinding.ItemGroupMessagesBinding


class GroupsAdapterList(private var list:List<String>, var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<GroupsAdapterList.Vh>(){
    inner class Vh(private var itemGroupMessagesBinding: ItemGroupMessagesBinding):RecyclerView.ViewHolder(itemGroupMessagesBinding.root){
        fun onBind(name:String){
            itemGroupMessagesBinding.nameGroup.text = name
            itemView.setOnClickListener{
                onItemClickListener.onItemClick(name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemGroupMessagesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener{
        fun onItemClick(name: String){

        }
    }
}