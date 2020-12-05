package com.example.value.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.value.Model.ValueItem
import com.example.value.R
import com.example.value.ui.FirstFragment
import kotlinx.android.synthetic.main.value.view.*

class ValueAdapter(var mPassValue: FirstFragment): RecyclerView.Adapter<ValueAdapter.TaskViewHolder>() {

    private var dataList = emptyList<ValueItem>()

    fun updateListValue(mDatalist: List<ValueItem>){

        dataList = mDatalist
        notifyDataSetChanged()
    }
    inner class TaskViewHolder (itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val valueName = itemView.tv_value
        val itemView = itemView.setOnClickListener(this)

        override fun onClick(p0: View?) {
            mPassValue.passValue(dataList[adapterPosition])
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValueAdapter.TaskViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.value, parent, false )
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ValueAdapter.TaskViewHolder, position: Int) {
        val mValue = dataList[position]
        holder.valueName.text = mValue.nombre



    }

    override fun getItemCount() = dataList.size

}

interface Value {

    fun passValue(mValue: ValueItem)

}