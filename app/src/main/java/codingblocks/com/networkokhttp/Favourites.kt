//package codingblocks.com.networkokhttp
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.squareup.picasso.Picasso
//import kotlinx.android.synthetic.main.activity_favourites.*
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class Favourites : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_favourites)
//        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
////        val retrofitClient = Retrofit.Builder()
////            .baseUrl("https://api.themoviedb.org/3/")
////            .addConverterFactory(GsonConverterFactory.create())
////            .build()
////
////
////        val service = retrofitClient.create(GithubService::class.java)
//        var list=intent.getIntegerArrayListExtra("favlist")
//        for (i in 0 until list.size) {
//            tv.text = tv.text.toString()+"\n"+list[i]
////            service.overview(list[i]).enqueue(object : Callback<Overview> {
////                override fun onFailure(call: Call<Overview>, t: Throwable) {
////                    tv.text="Loading failed!"
////                    tv.text=tv.text.toString()+t.cause.toString()
////                }
////
////                override fun onResponse(
////                    call: Call<Overview>,
////                    response: Response<Overview>
////                ) {
////                    runOnUiThread {
////                        //                    tv.text=response.body().toString()
//////                        rview.layoutManager = LinearLayoutManager(this@Favourites, LinearLayoutManager.HORIZONTAL,false)
//////                        rview.adapter = FavAdapter(this@Favourites, response.body()!!.backdrop_path)
////                    Picasso.get().load("https://image.tmdb.org/t/p/original"+response.body()!!.backdrop_path).into(img)
////
////                    }
////                }
////            })
//        }
//    }
//}
