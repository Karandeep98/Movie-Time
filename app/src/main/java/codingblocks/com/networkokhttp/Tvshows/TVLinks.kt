package codingblocks.com.networkokhttp.Tvshows

import codingblocks.com.networkokhttp.Cast
import codingblocks.com.networkokhttp.Github2
import codingblocks.com.networkokhttp.Trailerarray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVLinks {
    @GET("airing_today?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US&page")
    fun airingtoday(@Query("page")p:Int):Call<OverviewTvarray>

    @GET("on_the_air?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US&page")
    fun onair(@Query("page")p:Int):Call<OverviewTvarray>

    @GET("popular?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US&page")
    fun popular(@Query("page")p:Int):Call<OverviewTvarray>

    @GET("top_rated?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US&page")
    fun toprated(@Query("page")p:Int):Call<OverviewTvarray>

    @GET("{Id}?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US")
    fun overview(@Path("Id")id:Int):Call<Basicdetails>

    @GET("{Id}/videos?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US")
    fun videos(@Path("Id")id:Int):Call<Trailerarray>

    @GET("{Id}/credits?api_key=b315d3231fba4b90ca67901413855fb7")
    fun cast(@Path("Id")id:Int): Call<Cast>

    @GET("{Id}/similar?api_key=b315d3231fba4b90ca67901413855fb7&language=en-US&page=1")
    fun similartvshows(@Path("Id")id:Int): Call<SimilarArray>
}
