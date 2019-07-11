package codingblocks.com.networkokhttp

import android.preference.PreferenceManager
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    //    @GET("https://api.github.com/search/users?q=karandeep")
//    fun listUsers(): Call<Github2>


    @GET("now_playing?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US&page=1")
    fun nowShowing(): Call<Github2>

    @GET("popular?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US&page=1")
    fun popularMovies(): Call<Github2>

    @GET("upcoming?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US&page=1")
    fun upcoming(): Call<Github2>

    @GET("top_rated?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US&page=1")
    fun toprated(): Call<Github2>

    @GET("{Id}/credits?api_key=b315d3231fba4b90ca67901413855fb7")
    fun cast(@Path("Id")id:Int): Call<Cast>

    @GET("{Id}/similar?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US&page=1")
    fun similarmovies(@Path("Id")id:Int): Call<Github2>
    @GET("{Id}/videos?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US&page=1")
    fun trailers(@Path("Id")id:Int): Call<Trailerarray>

    @GET("{Id}?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US")
    fun overview(@Path("Id")id:Int):Call<Overview>

    @GET("person/{Id}?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US")
    fun castinfo(@Path("Id")id:Int):Call<Castinfo>

    @GET("person/{Id}/movie_credits?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US")
    fun moviecast(@Path("Id")id:Int):Call<Moviecastarray>

    @GET("search/movie?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US&query&page=1&include_adult=false")
    fun search(@Query("query")q:String):Call<Searcharray>





}
