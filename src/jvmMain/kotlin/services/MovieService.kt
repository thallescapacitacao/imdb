package services

import dtos.Search
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    @GET("k_...")
    fun findTop250Movies(): Call<Search>
}
