package codingblocks.com.networkokhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
//import codingblocks.com.networkokhttp.Client.getUrl
//import codingblocks.com.networkokhttp.Client.okHttpClient
//import codingblocks.com.networkokhttp.Client.okhttpCallback
import codingblocks.com.networkokhttp.Client.service
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        val userList = arrayListOf<GithubResponse>()
        service.listUsers().enqueue(object : Callback<Github2> {
            override fun onFailure(call: Call<Github2>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<Github2>,
                response: Response<Github2>
            ) {
                runOnUiThread {
                    rview.layoutManager = LinearLayoutManager(this@MainActivity)
                    rview.adapter = GithubAdapter(this@MainActivity, response.body()!!.items)

//                    tv.text = response.body()
                }
            }
        })

    }
}
