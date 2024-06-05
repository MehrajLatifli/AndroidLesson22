package com.example.androidlesson22.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlesson22.R
import com.example.androidlesson22.databinding.ItemProductBinding
import com.example.androidlesson22.models.get.product.Product
import com.example.androidlesson22.views.fragments.home.HomeFragmentDirections


class ProductAdapter: RecyclerView.Adapter<ProductAdapter.UserViewHolder>() {

    private val List = arrayListOf<Product>()

    lateinit var onClickItem: (String) -> Unit



    inner class UserViewHolder(val itemBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val item = List[position]

        holder.itemBinding.product = item

        val rating = item.rating
        setStarRating(rating, holder.itemBinding.star1, holder.itemBinding.star2, holder.itemBinding.star3, holder.itemBinding.star4, holder.itemBinding.star5)

        holder.itemBinding.basketButton.setOnClickListener {
            onClickItem.invoke(item.id.toString())


        }

    }

    private fun setStarRating(rating: Double?, vararg starImageViews: ImageView) {
        val maxStars = starImageViews.size
        val filledStars = (rating ?: 0.0).coerceIn(0.0, maxStars.toDouble()).toInt()

        for (i in 0 until maxStars) {
            if (i < filledStars) {
                starImageViews[i].setImageResource(R.drawable.yellowstar)
            } else {
                starImageViews[i].setImageResource(R.drawable.greystar)
            }
        }
    }

    fun updateList(newList: List<Product>) {
        List.clear()
        List.addAll(newList)
        notifyDataSetChanged()
    }


}