package hr.amaurov.niamu.orm_presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import hr.amaurov.niamu.orm_presentation.utils.startActivity

private const val DELAY = 3000L

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        redirect()
    }

    private fun redirect() {
        Handler(Looper.getMainLooper()).postDelayed({ startActivity<HostActivity>() }, DELAY)
    }
}