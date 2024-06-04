package com.example.androidlesson22.api.repositories

import com.example.androidlesson22.api.IApiManager
import com.example.androidlesson22.api.Resource
import com.example.androidlesson22.models.get.category.Category
import com.example.androidlesson22.models.get.category.CategoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class CategoryRepository  @Inject constructor(private val api: IApiManager) {


    suspend fun getAllCategory(): Resource<CategoryResponse> {
        return safeApiRequest {
            api.getAllCategory()
        }
    }





    suspend fun <T> safeApiRequest(request: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {

                val response = request.invoke()

                if (response.isSuccessful) {
                    Resource.Success(response.body())
                } else {
                    Resource.Error(response.message())
                }
            } catch (e: Exception) {
                Resource.Error(e.localizedMessage)
            }
        }
    }
}