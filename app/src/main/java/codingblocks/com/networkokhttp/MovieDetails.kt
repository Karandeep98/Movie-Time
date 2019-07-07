package codingblocks.com.networkokhttp

data class MovieDetails (
    val character:String,
    val name:String,
    val profile_path:String
)
data class Cast(val cast:ArrayList<MovieDetails>)

data class Trailers(
    val key:String,
    val name:String

)
data class Trailerarray(val results:ArrayList<Trailers>)

