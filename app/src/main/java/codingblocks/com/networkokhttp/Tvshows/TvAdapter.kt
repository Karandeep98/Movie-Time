package codingblocks.com.networkokhttp.Tvshows


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.edit
import androidx.recyclerview.widget.RecyclerView
import codingblocks.com.networkokhttp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_github.view.*

class TvAdapter( val context: Context, private val arrayList: ArrayList<OverviewTv>)
    : RecyclerView.Adapter<TvAdapter.GithubViewHolder>() {

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
        var currentuser: OverviewTv? = null
        var currentposition = 0

        init {
            itemView.setOnClickListener {
//                            Toast.makeText(context, currentuser!!.name + "clicked!",Toast.LENGTH_LONG ).show()
                val detail = Intent(context, TvDetails::class.java)
                detail.putExtra("ID", currentuser!!.id)
                context.startActivity(detail)

            }

        }

        fun bind(user: OverviewTv, position: Int) {

            this.currentuser = user
            this.currentposition = position
            with(itemView) {

                titletv.text = user.name
                if (user.vote_average.toInt() != 0) {
                    ratingtv.text = "⭐ " + user.vote_average.toString() + "/10"
                }
                Picasso.get().load("https://image.tmdb.org/t/p/original" + user.backdrop_path).fit().centerCrop().into(img)
//                if(user.backdrop_path.isNullOrBlank()){
//                    Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.poster_path).into(img)
//                }
            }


        }
    }
}





