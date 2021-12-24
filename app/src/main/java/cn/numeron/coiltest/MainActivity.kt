package cn.numeron.coiltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import cn.numeron.okhttp.file.DlProgressCallback
import coil.load
import coil.request.CachePolicy

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.text_view)
        findViewById<ImageView>(R.id.image_view).load("https://eng.libretexts.org/@api/deki/files/16283/m0120_fStraightCoil.png") {
            diskCachePolicy(CachePolicy.DISABLED)
            memoryCachePolicy(CachePolicy.DISABLED)
            setParameter("DlProgressCallback", DlProgressCallback {
                val percent = (it * 100).toInt()
                runOnUiThread {
                    textView.text = String.format("%d%%", percent)
                }
            })
        }
    }

}