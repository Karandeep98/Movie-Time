package codingblocks.com.networkokhttp

import com.google.gson.annotations.SerializedName

data class GithubResponse(

//	@field:SerializedName("login")
//	val login: String,
//
//	@field:SerializedName("avatar_url")
//	val avatar_url: String,
//
//	@field:SerializedName("id")
	val id: Int,
	val title:String,
	val vote_average:Float,
	val poster_path:String,
	val backdrop_path :String
)
		data class Github2(val results:ArrayList<GithubResponse>)