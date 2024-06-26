package com.example.producthub.views.fragments.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.producthub.R
import com.example.producthub.databinding.FragmentDetailBinding
import com.example.producthub.utilities.gone
import com.example.producthub.utilities.loadImageWithoutBindingAdapter
import com.example.producthub.utilities.visible
import com.example.producthub.viewmodels.DetailViewModel
import com.example.producthub.views.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel by viewModels<DetailViewModel>()
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



            val guidelinePercent = if (isTablet()) 0.50f else 0.40f
            val scaleType = if (isTablet()) ImageView.ScaleType.FIT_XY else ImageView.ScaleType.CENTER_CROP
            binding.guideline5.setGuidelinePercent(guidelinePercent)
            binding.imageView.scaleType = scaleType



            val productId = args.id.toInt()
            viewModel.getProductById(productId)
            observeData()


    }

    private fun observeData() {
        viewModel.product.observe(viewLifecycleOwner) { item ->
            item?.let {

                binding.textView1.text = it.title?:"No description"
                binding.textView2.text = it.description?:"No description"
                binding.textView3.text = "Price: ${it.price?.toString() ?: ""} $"
                binding.textView4.text = "Discount: ${it.discountPercentage?.toString() ?: ""}"
                binding.textView5.text = "Stock: ${it.stock?.toString() ?: ""}"
                binding.imageView.loadImageWithoutBindingAdapter(it.thumbnail?:"")

                setStarRating(it.rating, binding.star1, binding.star2, binding.star3, binding.star4,binding.star5)
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBarContainer.progressBar2.visible()
                hideOtherWidgets()
            } else {
                binding.progressBarContainer.progressBar2.gone()
                showOtherWidgets()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun hideOtherWidgets() {
        binding.starConstraintLayout.gone()
        binding.detailConstraintLayout.gone()
    }

    private fun showOtherWidgets() {
        binding.starConstraintLayout.visible()
        binding.detailConstraintLayout.visible()
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


    private fun isTablet(): Boolean {

        val configuration = resources.configuration
        return configuration.smallestScreenWidthDp >= 600
    }

}