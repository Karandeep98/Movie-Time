package codingblocks.com.networkokhttp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_github.view.*

class GithubAdapter(private val context: Context, private val arrayList: ArrayList<GithubResponse>)
    : RecyclerView.Adapter<GithubViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):GithubViewHolder {
        val inflater = LayoutInflater.from(context)
        return GithubViewHolder(inflater.inflate(R.layout.item_github, parent, false))

    }
    override fun getItemCount(): Int =arrayList.size

    override fun onBindViewHolder(holder: GithubViewHolder,position: Int){
        val user=arrayList[position]
        holder.bind(user)
    }
}
class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bind(user: GithubResponse) {

        with(itemView){
        logintv.text="Login :"+user.login
            idtv.text="Id: "+user.id.toString()
            Picasso.get().load(user.avatar_url).into(img)
        }
        with(itemView){
//            streettv.text="\nID: "+user.id+"\nName: "+user.name+"\nUsername: "+
//                    user.username+"\nEmail: "+user.email+"\nStreet: "+user.street+
//                    "\nSuite: "+user.suite+"\nCity: "+user.city+"\nZipcode: "+user.zipcode+"\n"


        }

    }

}
