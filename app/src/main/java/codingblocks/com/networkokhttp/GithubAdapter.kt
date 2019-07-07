package codingblocks.com.networkokhttp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_github.view.*

class GithubAdapter( val context: Context, private val arrayList: ArrayList<GithubResponse>)
    : RecyclerView.Adapter<GithubAdapter.GithubViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val inflater = LayoutInflater.from(context)
        return GithubViewHolder(inflater.inflate(R.layout.item_github, parent, false))

    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val user = arrayList[position]
        holder.bind(user, position)
    }

inner class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var currentuser: GithubResponse? = null
    var currentposition = 0

    init {
        itemView.setOnClickListener {
            Toast.makeText(context, currentuser!!.title + "clicked!",Toast.LENGTH_LONG ).show()
            val detail= Intent(context,Details::class.java)
            detail.putExtra("ID", currentuser!!.id)
            context.startActivity(detail)
        }
    }

    fun bind(user: GithubResponse, position: Int) {


        this.currentuser = user
        this.currentposition = position
        with(itemView) {
            titletv.text = "Title: " + user.title
            ratingtv.text = "⭐ " + user.vote_average.toString() + "/10"
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.poster_path).into(img)
        }
//        with(itemView){
//            streettv.text="\nID: "+user.id+"\nName: "+user.name+"\nUsername: "+
//                    user.username+"\nEmail: "+user.email+"\nStreet: "+user.street+
//                    "\nSuite: "+user.suite+"\nCity: "+user.city+"\nZipcode: "+user.zipcode+"\n"


//        }

    }
}}



