package com.inditraining.room_implementation.fragments.list

import android.graphics.Path.Direction
import android.text.Layout.Directions
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.inditraining.room_implementation.R
import com.inditraining.room_implementation.databinding.FragmentListBinding
import com.inditraining.room_implementation.model.User

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()
    private lateinit var tvInitial: TextView
    private lateinit var tvFirstName: TextView
    private lateinit var tvLastName: TextView
    private lateinit var tvAge: TextView
    private lateinit var rowLayout: ConstraintLayout

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        tvInitial = holder.itemView.findViewById(R.id.tv_initial)
        tvFirstName = holder.itemView.findViewById(R.id.tv_first_name)
        tvLastName = holder.itemView.findViewById(R.id.tv_last_name)
        tvAge = holder.itemView.findViewById(R.id.tv_age)
        rowLayout =  holder.itemView.findViewById(R.id.row_layout)

        tvInitial.text = "${currentItem.firstName.first()}${currentItem.lastName.first()}"
        tvFirstName.text = currentItem.firstName
        tvLastName.text = currentItem.lastName
        tvAge.text = "${currentItem.age} Tahun"

        rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)

            holder.itemView.findNavController().navigate(action)

        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user:List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }
}