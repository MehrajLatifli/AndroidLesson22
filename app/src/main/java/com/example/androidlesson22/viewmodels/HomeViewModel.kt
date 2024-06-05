package com.example.androidlesson22.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidlesson22.api.Resource
import com.example.androidlesson22.api.repositories.CategoryRepository
import com.example.androidlesson22.api.repositories.ProductRepository
import com.example.androidlesson22.models.get.category.Category
import com.example.androidlesson22.models.get.product.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: CategoryRepository, private val repo2: ProductRepository) : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    private var originalCategoryList = listOf<Category>()

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    private var originalProductList = listOf<Product>()

    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String?>()

    fun getAllCategory() {
        loading.value = true
        viewModelScope.launch(Dispatchers.Main) {

                val response = repo.getAllCategory()


                    when (response) {
                        is Resource.Success -> {
                            loading.value = false
                            val itemresponse = response.data
                            if (itemresponse != null && itemresponse.isNotEmpty()) {
                                _categories.value = itemresponse!!
                                originalCategoryList = itemresponse
                            } else {
                                error.value = "No categories found"
                                _categories.value = emptyList()
                            }
                        }
                        is Resource.Error -> {
                            error.value = "Failed to fetch categories: ${response.message}"
                        }
                        else -> {
                            loading.value = false
                        }
                    }


        }
    }


    fun getAllProduct() {
        loading.value = true
        viewModelScope.launch(Dispatchers.Main) {

                val response = repo2.getAllProduct()

                    when (response) {
                        is Resource.Success -> {
                            loading.value = false
                            val itemResponse = response.data
                            if (itemResponse != null && itemResponse.products != null) {
                                _products.value = itemResponse.products.orEmpty()
                                originalProductList = itemResponse.products
                            } else {
                                error.value = "No products found"
                                _products.value = emptyList()
                            }
                        }
                        is Resource.Error -> {
                            error.value = "Failed to fetch products: ${response.message}"
                        }
                        else -> {
                            loading.value = false
                        }
                    }


        }
    }

    fun getProductsByCategory(categoryName: String) {
        loading.value = true
        viewModelScope.launch(Dispatchers.Main) {

                val response = repo2.getProductsbyCategory(categoryName)
                Log.e("TAG", "getProductsByCategory: ${response.toString()}")

                    when (response) {
                        is Resource.Success -> {
                            loading.value = false
                            val itemResponse = response.data
                            if (itemResponse != null && itemResponse.products!!.isNotEmpty()) {
                                _products.value = itemResponse.products.filterNotNull()
                                originalProductList = itemResponse.products.filterNotNull()
                            } else {
                                error.value = "No products found for this category"
                                _products.value = emptyList()
                            }
                        }
                        is Resource.Error -> {
                            error.value = "Failed to fetch products: ${response.message}"

                            Log.e("TAG", "getProductsByCategory: ${response.message}")
                        }
                        else -> {
                            loading.value = false
                        }
                    }


        }
    }


    fun searchProducts(query: String) {
        if (query.isBlank()) {
            _products.value = originalProductList
            return
        }

        val filtered = originalProductList.filter { item ->
            item.title?.contains(query, ignoreCase = true) ?: false
        }
        _products.value = filtered
    }


    fun searchCategories(query: String) {
        if (query.isBlank()) {
            _categories.value = originalCategoryList
            return
        }

        val filtered = originalCategoryList.filter { category ->
            category.name?.contains(query, ignoreCase = true) ?: false
        }
        _categories.value = filtered
    }
}
