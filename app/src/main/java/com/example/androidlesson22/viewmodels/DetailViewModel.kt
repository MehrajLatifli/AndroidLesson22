package com.example.androidlesson22.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidlesson22.source.remote.api.repositories.ProductRepository
import com.example.androidlesson22.models.get.product.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.androidlesson22.source.remote.api.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class DetailViewModel @Inject constructor(private val repo: ProductRepository) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun getProductById(id: Int) {
        isLoading.value = true

        viewModelScope.launch(Dispatchers.Main) {


                val response = repo.getProductById(id.toInt())


                    when (response) {
                        is Resource.Success -> {
                            loading.value = false
                            val recipeResponse = response.data
                            if (recipeResponse != null) {
                                _product.value = recipeResponse!!

                            } else {
                                error.value = "No recipes found"
                            }
                        }
                        is Resource.Error -> {
                            error.value = "Failed to fetch recipes: ${response.message}"
                        }
                        else -> {
                            loading.value = false
                        }
                    }




        }
    }


}