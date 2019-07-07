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
import kotlinx.android.synthetic.main.item_github2.view.*

class MovieAdapter( val context: Context, private val arrayList: ArrayList<MovieDetails>)
    : RecyclerView.Adapter<GithubViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val inflater = LayoutInflater.from(context)
        return GithubViewHolder(inflater.inflate(R.layout.item_github2, parent, false))

    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val user = arrayList[position]
        holder.bind(user, position)
    }
}
     class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



        fun bind(user: MovieDetails, position: Int) {

            with(itemView) {
                charactertv.text =   user.character
                nametv.text =  user.name
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.profile_path).into(img)
                if(user.profile_path==null){
                    Picasso.get().load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQaYY" +
                            "JV4KmiKJQY19WvKPIcHGFTx_b4IF13cwjAGtnPx-r2Syl1").into(img)

                }
            }

        }
    }



