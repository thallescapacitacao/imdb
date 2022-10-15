package webclient

import data.Top250Data
import data.toMovie
import models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieWebClient {

    private val service = RetrofitInit().movieService

    fun findTop250Movies(lambda: (List<Movie>?) -> Unit) {
        service.findTop250Movies().enqueue(object : Callback<Top250Data> {
            override fun onResponse(
                call: Call<Top250Data>,
                response: Response<Top250Data>
            ) {
                val data = response.body()
                data?.items?.map { it.toMovie() }?.let { movies ->
                    lambda(movies)
                }
            }

            override fun onFailure(
                call: Call<Top250Data>,
                t: Throwable
            ) {

            }
        })
    }
}
