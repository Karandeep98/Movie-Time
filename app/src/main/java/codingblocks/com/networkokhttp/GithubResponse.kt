package codingblocks.com.networkokhttp

import com.google.gson.annotations.SerializedName

data class GithubResponse(

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("avatar_url")
	val avatar_url: String,

	@field:SerializedName("id")
	val id: Int

)
		data class Github2(val items:ArrayList<GithubResponse>)