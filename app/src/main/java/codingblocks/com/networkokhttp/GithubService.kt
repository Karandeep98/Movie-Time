package codingblocks.com.networkokhttp

import retrofit2.Call
import retrofit2.http.GET

interface GithubService {
    @GET("search/users?q=karandeep")
    fun listUsers(): Call<Github2>

}
