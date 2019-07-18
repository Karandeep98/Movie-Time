//package codingblocks.com.networkokhttp
//
//import android.content.Context
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.squareup.picasso.Picasso
//import kotlinx.android.synthetic.main.item_github.view.*
//
//abstract class FavAdapter(val context: Context, backdrop_path: String) : RecyclerView.Adapter<FavAdapter.GithubViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
//        val inflater = LayoutInflater.from(context)
//        return GithubViewHolder(inflater.inflate(R.layout.item_github, parent, false))
//
//    }
//
//    abstract override fun getItemCount(): Int
//
//    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
//        val user = arrayList[position]
//        holder.bind(user, position)
//    }
//
//    inner class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var currentuser: GithubResponse? = null
//        var currentposition = 0
//
//        init {
//            itemView.setOnClickListener {
//                //            Toast.makeText(context, currentuser!!.title + "clicked!",Toast.LENGTH_LONG ).show()
//                val detail= Intent(context,Details::class.java)
//                detail.putExtra("ID", currentuser!!.id)
////            prefs.edit {
////                putString("ID", currentuser!!.id.toString())
////            }
//                context.startActivity(detail)
//            }
//
//        }
//
//        fun bind(user: GithubResponse, position: Int) {
//
//
//            this.currentuser = user
//            this.currentposition = position
//            with(itemView) {
////                titletv.text = user.title
////                ratingtv.text = "⭐ " + user.vote_average.toString() + "/10"
//                Picasso.get().load("https://image.tmdb.org/t/p/original" + user.backdrop_path).into(img)
//            }
////        with(itemView){
////            streettv.text="\nID: "+user.id+"\nName: "+user.name+"\nUsername: "+
////                    user.username+"\nEmail: "+user.email+"\nStreet: "+user.street+
////                    "\nSuite: "+user.suite+"\nCity: "+user.city+"\nZipcode: "+user.zipcode+"\n"
//
//
////        }
//
//        }
//    }}
//}
