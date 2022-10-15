package webclient

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import services.MovieService

class RetrofitInit {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://imdb-api.com/API/Top250Movies/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val movieService: MovieService
        get() = retrofit.create(MovieService::class.java)
}
