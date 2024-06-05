package com.example.androidlesson22.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlesson22.R
import com.example.androidlesson22.databinding.ItemCategoryBinding
import com.example.androidlesson22.models.get.category.Category

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.UserViewHolder>() {

    private val categoryList = arrayListOf<Category>()
    private var selectedItemPosition = RecyclerView.NO_POSITION

    lateinit var onClickItem: (String) -> Unit

    inner class UserViewHolder(val itemBinding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = categoryList[position]
        holder.itemBinding.category = item


        if (position == selectedItemPosition) {
            holder.itemBinding.constraintLayout.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.PinkishOrange
                )
            )
            holder.itemBinding.nametextView.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.White
                )
            )
        } else {
            holder.itemBinding.constraintLayout.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.White
                )
            )

            holder.itemBinding.nametextView.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.PinkishOrange
                )
            )
        }

        holder.itemView.setOnClickListener {
            onClickItem.invoke(item.slug?.lowercase() ?: "")

            selectedItemPosition = holder.getBindingAdapterPosition()
            notifyDataSetChanged()
        }
    }

    fun updateList(newList: List<Category>) {
        categoryList.clear()
        categoryList.addAll(newList)
        notifyDataSetChanged()
    }

    fun resetSelectedItemPosition() {
        selectedItemPosition = RecyclerView.NO_POSITION
        notifyDataSetChanged()
    }
}
