package com.example.androidlesson22.views.fragments.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlesson22.R
import com.example.androidlesson22.databinding.FragmentHomeBinding
import com.example.androidlesson22.databinding.FragmentSignupBinding
import com.example.androidlesson22.utilities.gone
import com.example.androidlesson22.utilities.visible
import com.example.androidlesson22.viewmodels.HomeViewModel
import com.example.androidlesson22.views.adapters.CategoryAdapter
import com.example.androidlesson22.views.adapters.ProductAdapter
import com.example.androidlesson22.views.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    private var categoryAdapter = CategoryAdapter()


    private var productAdapter = ProductAdapter()

    private val viewModel by viewModels<HomeViewModel>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpRecyclerView()
        observeData()
        viewModel.getAllCategory()
        viewModel.getAllProduct()

        categoryAdapter.onClickItem = { categoryName ->
            viewModel.getProductsByCategory(categoryName)
        }

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val searchText = s.toString().trim()
                viewModel.searchProducts(searchText)

                if (searchText.isNotBlank() && searchText.isNotEmpty()) {
                    binding.editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.search, 0, 0, 0)
                    binding.editText.compoundDrawables[0].setTint(ContextCompat.getColor(requireContext(), R.color.PinkishOrange))
                } else {
                    binding.editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.search, 0, 0, 0)
                    binding.editText.compoundDrawables[0].setTint(ContextCompat.getColor(requireContext(), R.color.BlackOrchid))
                }
            }
        })
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
                hideOtherWidgets()
            } else {
                binding.progressBarContainer.progressBar2.gone()
                showOtherWidgets()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                Log.e("responseError", it)
            }
        }


    }

    private fun setUpRecyclerView() {


        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recycleViewCategories.layoutManager = linearLayoutManager
        binding.recycleViewCategories.adapter = categoryAdapter

        val gridLayoutManager = GridLayoutManager(context, 2)
        binding.recycleViewProduct.layoutManager = gridLayoutManager
        binding.recycleViewProduct.adapter = productAdapter
    }

    private fun hideOtherWidgets() {


        binding.recycleViewCategories.gone()
        binding.recycleViewProduct.gone()

    }

    private fun showOtherWidgets() {

        binding.recycleViewCategories.visible()
        binding.recycleViewProduct.visible()

    }

}