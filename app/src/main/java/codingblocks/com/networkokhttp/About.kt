package codingblocks.com.networkokhttp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_about.*

class About : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        toolbar.title="About"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        feedbackbutton.setOnClickListener {
            val i = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:karandeep041998@gmail.com"))
//            i.putExtra(Intent.EXTRA_SUBJECT, "Hello There!")
//            i.putExtra(Intent.EXTRA_TEXT, "Hello Noobs!")
            startActivity(Intent.createChooser(i, "Send Email"))
        }
    }
}
