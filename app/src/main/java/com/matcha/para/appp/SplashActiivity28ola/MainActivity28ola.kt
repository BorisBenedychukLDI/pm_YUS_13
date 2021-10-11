package com.matcha.para.appp.SplashActiivity28ola

import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.matcha.para.appp.R
import com.matcha.para.appp.WebActivity28ola.WebActivity28ola
import com.matcha.para.appp.databinding.ActivityMain28olaBinding
import kotlinx.coroutines.launch

class MainActivity28ola : AppCompatActivity(), SplashActivityContract28ola.SplashActivityMethods28ola {

    private lateinit var binding28ola: ActivityMain28olaBinding
    private lateinit var splashActivityPresenterMethods28Ola: SplashActivityContract28ola.SplashActivityPresenter28ola
    private var animator28ola: ValueAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_28ola)
        getSharedPreferences("SP_28ola", MODE_PRIVATE).getString("Last_Page_28ola", null)?.let {
            startActivity(Intent(this, WebActivity28ola::class.java))
            finish()
        }
        binding28ola = ActivityMain28olaBinding.inflate(layoutInflater)
        setContentView(binding28ola.root)
        splashActivityPresenterMethods28Ola = SplashPresenter28ola(this)
        splashActivityPresenterMethods28Ola.setupFB28ola()
        splashActivityPresenterMethods28Ola.thisEndsHere28ola()
    }

    override fun onClickListener28ola(clickScope28ola: () -> Unit) {
        binding28ola.b28ola.setOnClickListener {
            lifecycleScope.launch {
                animation28ola()
                binding28ola.b28ola.isClickable = false
                clickScope28ola()
            }
        }
    }

    override fun onStop() {
        animator28ola?.cancel()
        super.onStop()
    }

    private fun animation28ola() {
        animator28ola = ValueAnimator.ofInt(0,3).apply {
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener {
                var s = "Loading"
                for (i in 0..it.animatedValue as Int) {
                    s += "."
                }
                binding28ola.b28ola.text = s
            }
            duration = 500
            start()
        }

        binding28ola.pb28ola.animate().alpha(1f)
    }

    override fun startNewIntentWithURL28ola(url28ola: String) {
        startActivity(Intent(this, WebActivity28ola::class.java).apply { putExtra("webURL28ola", url28ola) })
        finish()
    }

    override fun getPackageName28ola(): String {
        return packageName
    }
}