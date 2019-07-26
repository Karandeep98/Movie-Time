package codingblocks.com.networkokhttp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_youtubeplay.*

class Youtubeplay : YouTubeBaseActivity() {
    val apikey="AIzaSyBEKZxQX5xLB3ZM6kFhcjBsvGWxBGRfSvs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtubeplay)
        var path=intent.getStringExtra("path")
        player.initialize(apikey,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(provider: YouTubePlayer.Provider,
                                                     youTubePlayer: YouTubePlayer, b: Boolean) {
                    if(!b){
                        youTubePlayer.loadVideo(path)
                        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                    }
                }

                override fun onInitializationFailure(provider: YouTubePlayer.Provider,
                                                     youTubeInitializationResult: YouTubeInitializationResult) {
                    Toast.makeText(this@Youtubeplay,youTubeInitializationResult.toString(),Toast.LENGTH_LONG).show()
                }
            })
    }
}
