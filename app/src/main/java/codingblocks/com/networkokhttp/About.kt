package codingblocks.com.networkokhttp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import kotlinx.android.synthetic.main.activity_about.*
import java.util.*

class About : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setupHyperlink()
        tb.title="About"
        setSupportActionBar(tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bt.setOnClickListener {
            val i = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:karandeep041998@gmail.com"))
           startActivity(Intent.createChooser(i, "Send Email"))
        }
    }
    private fun setupHyperlink() {

        tv.setMovementMethod(LinkMovementMethod.getInstance())
        //        linkTextView.setLinkTextColor(Color.);
    }
}
