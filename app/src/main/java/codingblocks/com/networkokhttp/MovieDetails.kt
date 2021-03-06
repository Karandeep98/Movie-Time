package codingblocks.com.networkokhttp


data class MovieDetails (
    val character:String,
    val name:String,
    val profile_path:String,
    val id: Int
)
data class Cast(val cast:ArrayList<MovieDetails>)

data class Trailers(
    val key:String,
    val name:String

)
data class Trailerarray(val results:ArrayList<Trailers>)

data class Overview(
    val title:String,
    val overview:String,
    val release_date:String,
    val runtime:Int,
    val poster_path:String,
    val vote_average:Float,
    val backdrop_path:String,
    val id:Int,
    val imdb_id:String,
    val genres:ArrayList<Genres>,
val production_companies:ArrayList<ProductionCompanies>

)
data class ProductionCompanies(
    val name: String,
    val logo_path:String
)
data class Genres(
    val name: String
)

data class Castinfo(
    val name:String,
    val biography:String,
    val place_of_birth:String,
    val birthday:String,
    val profile_path:String
)
data class Moviecast(
    val character:String,
    val poster_path:String,
    val title:String,
    val id:Int
)
data class Moviecastarray(val cast:ArrayList<Moviecast>)
data class Tvcast(
    val name:String,
    val character: String,
    val poster_path:String,
    val id:Int
)
data class TVcastArray(val cast:ArrayList<Tvcast>)

data class Searchdetails(
    val title:String,
    val media_type:String,
    val name:String,
    val profile_path:String,
    val first_air_date:String,
    val poster_path:String,
    val overview: String,
    val release_date:String,
val id:Int
)
data class Searcharray(val results:ArrayList<Searchdetails>)

data class Reviewdetails(
    val author:String,
    val content:String
)
data class ReviewArray(val results:ArrayList<Reviewdetails>)