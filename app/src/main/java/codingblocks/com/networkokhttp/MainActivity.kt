package codingblocks.com.networkokhttp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
//import codingblocks.com.networkokhttp.Client.getUrl
//import codingblocks.com.networkokhttp.Client.okHttpClient
//import codingblocks.com.networkokhttp.Client.okhttpCallback
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_github.*
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity() {
    val retrofitClient = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofitClient.create(GithubService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        favbt.setOnClickListener {
            val l=Intent(this@MainActivity,Room::class.java)
            l.putExtra("favlist",1)
            startActivity(l)
        }
//        okhttp with normal callback
//        val client = OkHttpClient()
//        val request = Request.Builder()
//            .url("https://api.github.com/search/users?q=karandeep")
//            .build()
//
//
//        client.newCall(request).enqueue(object:okhttp3.Callback{
//            override fun onFailure(call: okhttp3.Call, e: IOException) {
//                tv.text="Loading failed!"
//            }
//
//            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
//                val gson = Gson().fromJson(response.body?.string(), Github::class.java)
//                runOnUiThread {
//                    tv.text = gson.items.toString()
//                }            }
//
//        })
//        client.newCall(request).enqueue(object : Callback {
//            override fun onResponse(call: Call, response: okhttp3.Response) {
//                val gson = Gson().fromJson(response.body?.string(), Github::class.java)
//                runOnUiThread {
//                    tv.text = gson.items.toString()
//                }            }
//
//
//            override fun onFailure(call: Call, e: IOException) {
//                tv.text="Loading failed!"
//            }
//        })
        //okhttp with helper function and singleton object
//        okHttpClient.newCall(getUrl("search/users?q=karandeep"))
//            .enqueue(okhttpCallback { response, throwable ->
//                throwable?.let {
//                    tv.text="Loading failed!"
//                }
//                response?.let {
//                    val gson = Gson().fromJson(it.body?.string(), Github::class.java)
//                    runOnUiThread {
//                        tv.text = gson.items.toString()
//                    }
//                }
//            })
            //Retrofit
//        val userList = arrayListOf<GithubResponse>()
        service.nowShowing().enqueue(object : Callback<Github2> {
            override fun onFailure(call: Call<Github2>, t: Throwable) {
                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<Github2>,
                response: Response<Github2>
            ) {
                runOnUiThread {
//                    tv.text=response.body().toString()
                    rview.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    rview.adapter = GithubAdapter(this@MainActivity, response.body()!!.results)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })
        service.popularMovies().enqueue(object : Callback<Github2> {
            override fun onFailure(call: Call<Github2>, t: Throwable) {
                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<Github2>,
                response: Response<Github2>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    rview2.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    rview2.adapter = GithubAdapter2(this@MainActivity, response.body()!!.results)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })
        service.upcoming().enqueue(object : Callback<Github2> {
            override fun onFailure(call: Call<Github2>, t: Throwable) {
                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<Github2>,
                response: Response<Github2>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    rview3.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    rview3.adapter = GithubAdapter(this@MainActivity, response.body()!!.results)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })
        service.toprated().enqueue(object : Callback<Github2> {
            override fun onFailure(call: Call<Github2>, t: Throwable) {
                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<Github2>,
                response: Response<Github2>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    rview4.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    rview4.adapter = GithubAdapter2(this@MainActivity, response.body()!!.results)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })
//        searchview.OnQueryTextListener=searchview. {
//            val a=Intent(this,Search::class.java)
//            a.putExtra("searchtext",searchview.query.toString())
//            startActivity(a)
//        }
        searchbutton.setOnClickListener {
            val a=Intent(this,Search::class.java)
            a.putExtra("searchtext",et.text.toString())
            startActivity(a)

        }

    }
}

