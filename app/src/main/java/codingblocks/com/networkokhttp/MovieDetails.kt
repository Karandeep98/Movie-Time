package codingblocks.com.networkokhttp

data class MovieDetails (
    val character:String,
    val name:String,
    val profile_path:String
)
data class Cast(val cast:ArrayList<MovieDetails>)
data class SimilarMovieDetails (
    val title:String,
    val vote_average:Float,
    val poster_path:String
)

data class Similar(val results:ArrayList<SimilarMovieDetails>)
