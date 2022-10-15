package utils

import config.RetrofitInit
import dtos.Search
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieWebClient {

    private val service = RetrofitInit().movieService

    fun findTop250Movies() {
        service.findTop250Movies().enqueue(object : Callback<Search> {
            override fun onResponse(
                call: Call<Search>,
                response: Response<Search>
            ) {
                println(response.body())
            }

            override fun onFailure(
                call: Call<Search>,
                t: Throwable
            ) {
                println("Erro durante a chamada à API.")
            }
        })
    }
}
