package codingblocks.com.networkokhttp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.*
import codingblocks.com.networkokhttp.Tvshows.TVMainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_github.*
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging
//import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.ArrayList

//import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var doubleBackPressed = false
    override fun onBackPressed() {

//        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
            if (doubleBackPressed) {
                super.onBackPressed()
            }
            else {
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                        drawer_layout.closeDrawer(GravityCompat.START)
                    }
                else{
                doubleBackPressed = true
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
                GlobalScope.launch {
                    delay(2000)
                    doubleBackPressed = false
                }
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        val searchitem=menu?.findItem(R.id.menu_search)
        val searchview=searchitem?.actionView as androidx.appcompat.widget.SearchView
        searchview.queryHint="Search Movies"
        searchview.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val a= Intent(this@MainActivity,Search::class.java)
                a.putExtra("searchtext",p0)
                startActivity(a)
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    val retrofitClient = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofitClient.create(GithubService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
//
        nav_view.setNavigationItemSelectedListener(this)

        //code for push notifications
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            nm.createNotificationChannel(
                NotificationChannel(
                    "first", "default",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        FirebaseMessaging.getInstance().subscribeToTopic("general")
            .addOnCompleteListener { task ->
                var msg = "Successful!"
                if (!task.isSuccessful) {
                    msg = "Failed!"
                }
//                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }

//        favbt.setOnClickListener {
//            val l=Intent(this@MainActivity,Room::class.java)
//            l.putExtra("favlist",1)
//            startActivity(l)
//        }
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
        service.nowShowing(1).enqueue(object : Callback<Github2> {
            override fun onFailure(call: Call<Github2>, t: Throwable) {
                prg.visibility=View.GONE
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause?.toString()
                Snackbar.make(root,"No Internet Connection",Snackbar.LENGTH_INDEFINITE).show()

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
                    val snapHelper = PagerSnapHelper()
                    snapHelper.attachToRecyclerView(rview)
                    prg.visibility=View.GONE
                    scrollview.visibility=View.VISIBLE


                }
            }
        })
        service.popularMovies(1).enqueue(object : Callback<Github2> {
            override fun onFailure(call: Call<Github2>, t: Throwable) {
                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<Github2>,
                response: Response<Github2>
            ) {
                runOnUiThread {

                    rview2.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    rview2.adapter = GithubAdapter2(this@MainActivity, response.body()!!.results)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })
        service.upcoming(1).enqueue(object : Callback<Github2> {
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
                    rview3.adapter = GithubAdapter(this@MainActivity, response.body()!!.results.filter {
                        it.backdrop_path != null
                    } as ArrayList<GithubResponse>)
                    val snapHelper = PagerSnapHelper()
                    snapHelper.attachToRecyclerView(rview3)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })
        service.toprated(1).enqueue(object : Callback<Github2> {
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
//        searchbutton.setOnClickListener {
//            val a=Intent(this,Search::class.java)
//            a.putExtra("searchtext",et.text.toString())
//            startActivity(a)
//
//        }
        viewAllNowShowing.setOnClickListener {
            val a=Intent(this,ViewAll::class.java)
            a.putExtra("category","nowshowing")
            startActivity(a)
        }
        viewAllPopular.setOnClickListener {
            val a=Intent(this,ViewAll::class.java)
            a.putExtra("category","popular")
            startActivity(a)
        }
        viewAllUpcoming.setOnClickListener {
            val a=Intent(this,ViewAll::class.java)
            a.putExtra("category","upcoming")
            startActivity(a)
        }
        viewAllToprated.setOnClickListener {
            val a=Intent(this,ViewAll::class.java)
            a.putExtra("category","toprated")
            startActivity(a)
        }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.movies -> {
//                val l = Intent(this, MainActivity::class.java)
//                startActivity(l)
            }
            R.id.fav -> {
                val l = Intent(this, Room::class.java)
//                l.putExtra("favlist", 1)
                startActivity(l)
            }
            R.id.about -> {
                val l = Intent(this, About::class.java)
                startActivity(l)
            }
            R.id.tvshows->{
                val l = Intent(this, TVMainActivity::class.java)
                startActivity(l)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
