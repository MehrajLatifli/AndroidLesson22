package com.example.producthub.source.remote.api.repositories

import android.util.Log
import com.example.producthub.source.remote.api.IApiManager
import com.example.producthub.source.remote.api.Resource
import com.example.producthub.models.get.product.Product
import com.example.producthub.models.get.product.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ProductRepository @Inject constructor(private val api: IApiManager) {


    suspend fun getAllProduct(): Resource<ProductResponse> {
        return safeApiRequest {
            api.getAllProduct()
        }
    }


    suspend fun getProductById(id: Int): Resource<Product> {
        return safeApiRequest {
            api.getProductById(id)
        }
    }

    suspend fun getProductsbyCategory(category: String): Resource<ProductResponse> {
        return safeApiRequest {
            api.getProductsbyCategory(category)
        }
    }


    suspend fun <T> safeApiRequest(request: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {

                val response = request.invoke()

                if (response.isSuccessful) {
                    Resource.Success(response.body())
                } else {
                    Log.e("gggg", response.toString())
                    Resource.Error(response.message())
                }
            } catch (e: Exception) {
                Log.e("xera", e.localizedMessage.toString())
                Resource.Error(e.localizedMessage)
            }
        }
    }
}