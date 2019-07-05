package codingblocks.com.networkokhttp

import okhttp3.*
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object Client {
    //okhttp
//    val okHttpClient = OkHttpClient()
//
//    fun getUrl(url: String): Request {
//        return Request.Builder()
//            .url("https://api.github.com/$url")
//            .build()
//    }
//
//    fun okhttpCallback(fn: (Response?, Throwable?) -> Unit): Callback {
//        return object : Callback {
//            override fun onFailure(call: Call, e: IOException) = fn(null, e)
//
//            override fun onResponse(call: Call, response: Response) = fn(response, null)
//
//        }
//    }
    //retrofit
    val retrofitClient = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofitClient.create(GithubService::class.java)

}