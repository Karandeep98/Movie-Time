package codingblocks.com.networkokhttp

data class GithubUser(
val login:String,
val avatar_url:String
//val name:String,
//val username:String,
//val email:String,
//val street: String,
//val suite: String,
//val city: String,
//val zipcode:String
)
data class Github(val items:ArrayList<GithubUser>)