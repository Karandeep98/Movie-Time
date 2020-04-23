package codingblocks.com.networkokhttp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class example {
    val retrofitClient = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofitClient.create(GithubService::class.java)

}