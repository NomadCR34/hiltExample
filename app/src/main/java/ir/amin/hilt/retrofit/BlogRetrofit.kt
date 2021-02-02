package ir.amin.hilt.retrofit

import retrofit2.http.GET

interface BlogRetrofit {

    @GET("blogs")
    suspend fun getData():List<BlogNetworkEntity>

}