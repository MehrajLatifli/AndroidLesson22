package com.example.producthub.views.fragments.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.producthub.R
import com.example.producthub.databinding.FragmentHomeBinding
import com.example.producthub.utilities.gone
import com.example.producthub.utilities.visible
import com.example.producthub.viewmodels.HomeViewModel
import com.example.producthub.views.adapters.CategoryAdapter
import com.example.producthub.views.adapters.ProductAdapter
import com.example.producthub.views.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val categoryAdapter = CategoryAdapter()
    private val productAdapter = ProductAdapter()
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()



            viewModel.getAllCategory()
            viewModel.getAllProduct()

            setUpRecyclerView()

            categoryAdapter.onClickItem = { categoryName ->

                viewModel.getProductsByCategory(categoryName)


                with(binding.editText) {
                    clearFocus()
                    clearComposingText()
                    text?.clear()
                }


            }





        binding.editText.addTextChangedListener {

            viewLifecycleOwner.lifecycleScope.launch {
                val searchText = binding.editText.text.toString().trim()
                viewModel.searchProducts(searchText)
                updateSearchDrawable(searchText.isNotEmpty())
            }

        }



            productAdapter.onClickItem = { productId ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(productId)
                findNavController().navigate(action)
            }

    }

    override fun onResume() {
        super.onResume()

        observeData()



        hideOtherWidgets()

            with(binding.editText) {
                clearFocus()
                clearComposingText()
                text?.clear()
            }

            categoryAdapter.resetSelectedItemPosition()

        binding.recycleViewCategories.scrollToPosition(0)


    }

    private fun observeData() {
        viewModel.categories.observe(viewLifecycleOwner) { item ->
            item?.let {
                categoryAdapter.updateList(it)
            }
        }

        viewModel.products.observe(viewLifecycleOwner) { item ->
            item?.let {
                productAdapter.updateList(it)
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBarContainer.progressBar2.visible()

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

    private fun setUpRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recycleViewCategories.layoutManager = linearLayoutManager
        binding.recycleViewCategories.adapter = categoryAdapter

        val spanCount = if (isTablet()) 4 else 2
        val gridLayoutManager = GridLayoutManager(context, spanCount)
        binding.recycleViewProduct.layoutManager = gridLayoutManager
        binding.recycleViewProduct.adapter = productAdapter
    }

    private fun isTablet(): Boolean {
        val configuration = resources.configuration
        return configuration.smallestScreenWidthDp >= 600
    }

    private fun showOtherWidgets() {

        binding.recycleViewProduct.visible()
    }

    private fun hideOtherWidgets() {
        binding.recycleViewProduct.gone()
    }
    private fun updateSearchDrawable(isSearchActive: Boolean) {
        val drawableId = if (isSearchActive) R.drawable.search else R.drawable.search
        val tintColorId = if (isSearchActive) R.color.PinkishOrange else R.color.BlackOrchid
        binding.editText.setCompoundDrawablesWithIntrinsicBounds(drawableId, 0, 0, 0)
        binding.editText.compoundDrawables[0].setTint(ContextCompat.getColor(requireContext(), tintColorId))
    }
}
