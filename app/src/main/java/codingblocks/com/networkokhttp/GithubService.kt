package codingblocks.com.networkokhttp

import retrofit2.Call
import retrofit2.http.GET

interface GithubService {
//    @GET("https://api.github.com/search/users?q=karandeep")
//    fun listUsers(): Call<Github2>
    @GET("now_playing?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US&page=1")
fun nowShowing(): Call<Github2>
    @GET("popular?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US&page=3")
    fun popularMovies():Call<Github2>
    @GET("upcoming?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US&page=2")
    fun upcoming():Call<Github2>
    @GET("top_rated?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US&page=1")
    fun toprated():Call<Github2>
    @GET("credits?api_key=b315d3231fba4b90ca67901413855fb7")
    fun details():Call<Cast>
    @GET("similar?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US&page=1")
    fun similarmovies():Call<Similar>

}