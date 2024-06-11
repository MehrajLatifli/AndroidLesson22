package com.example.producthub.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.producthub.R
import com.example.producthub.databinding.ItemCategoryBinding
import com.example.producthub.models.get.category.Category

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.UserViewHolder>() {

    private val categoryList = mutableListOf<Category>()
    private var lastSelectedItemPosition = RecyclerView.NO_POSITION

    lateinit var onClickItem: (String) -> Unit

    inner class UserViewHolder(val itemBinding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = categoryList[position]
        holder.itemBinding.category = item

        val isSelected = position == lastSelectedItemPosition

        holder.itemBinding.constraintLayout.setBackgroundColor(
            ContextCompat.getColor(
                holder.itemView.context,
                if (isSelected) R.color.PinkishOrange else R.color.White
            )
        )
        holder.itemBinding.nametextView.setTextColor(
            ContextCompat.getColor(
                holder.itemView.context,
                if (isSelected) R.color.White else R.color.PinkishOrange
            )
        )

        holder.itemView.setOnClickListener {
            val currentSelectedItemPosition = holder.bindingAdapterPosition

            if (currentSelectedItemPosition != lastSelectedItemPosition) {
                val previousSelectedItemPosition = lastSelectedItemPosition
                lastSelectedItemPosition = currentSelectedItemPosition
                notifyItemChanged(previousSelectedItemPosition)
                notifyItemChanged(currentSelectedItemPosition)
                onClickItem.invoke(categoryList[currentSelectedItemPosition].slug?.lowercase() ?: "")
            } else {
                lastSelectedItemPosition = RecyclerView.NO_POSITION
                notifyItemChanged(currentSelectedItemPosition)
                onClickItem.invoke("")
            }
        }
    }

    fun updateList(newList: List<Category>) {
        categoryList.clear()
        categoryList.addAll(newList)
        notifyDataSetChanged()
    }

    fun resetSelectedItemPosition() {
        lastSelectedItemPosition = RecyclerView.NO_POSITION
        notifyDataSetChanged()
    }
}
