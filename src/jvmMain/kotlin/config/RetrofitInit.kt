package config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import services.MovieService

class RetrofitInit {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://imdb-api.com/API/Top250Movies/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieService: MovieService
        get() = retrofit.create(MovieService::class.java)
}
