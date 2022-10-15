package services

import data.Top250Data
import retrofit2.Call
import retrofit2.http.GET
import webclient.IMDB_API_KEY

interface MovieService {
    @GET(IMDB_API_KEY)
    fun findTop250Movies(): Call<Top250Data>
}
