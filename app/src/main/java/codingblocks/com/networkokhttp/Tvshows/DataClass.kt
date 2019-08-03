package codingblocks.com.networkokhttp.Tvshows

data class OverviewTv(
    val name:String,
    val backdrop_path:String,
    val id:Int,
    val vote_average:Float,
    val poster_path:String,
    val original_language:String
    )
data class OverviewTvarray(val results:ArrayList<OverviewTv>)

data class Basicdetails(
    val name:String,
    val homepage:String,
    val backdrop_path:String,
    val poster_path: String,
    val first_air_date:String,
    val genres:ArrayList<Genres>,
    val id:Int,
    val number_of_episodes:Int,
    val number_of_seasons:Int,
    val overview:String,
    val vote_average:Float,
    val production_companies:ArrayList<ProductionCompanies>
)
data class Genres(
    val name:String
)
data class ProductionCompanies(
    val name:String,
    val logo_path:String
)
data class Similar(
    val id: Int,
    val name:String,
    val vote_average:Float,
    val poster_path:String,
    val backdrop_path :String
)
data class SimilarArray(val results:ArrayList<Similar>)