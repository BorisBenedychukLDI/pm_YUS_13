package com.matcha.para.appp.WebActivity28ola

import android.Manifest
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.core.animation.doOnEnd
import androidx.lifecycle.lifecycleScope
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.matcha.para.appp.R
import com.matcha.para.appp.URLStuff28ola.filePathCallBack28ola
import com.matcha.para.appp.URLStuff28ola.uriView28ola
import com.matcha.para.appp.databinding.ActivityWebActivity28olaBinding
import kotlinx.coroutines.launch

class WebActivity28ola : AppCompatActivity(), WebActivityContract28ola.WebActivityMethods28ola {

    private lateinit var binding28ola: ActivityWebActivity28olaBinding
    private lateinit var webViPresenterMethods28ola: WebActivityContract28ola.WebActivityPresenter28ola

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding28ola = ActivityWebActivity28olaBinding.inflate(layoutInflater)
        setContentView(binding28ola.root)
        webViPresenterMethods28ola = WebPresenter28ola(this)
        webViPresenterMethods28ola.setupWebView28ola()
        webViPresenterMethods28ola.setupWebViewClient28ola()
        webViPresenterMethods28ola.setupWebChromeClient28ola()
        webViPresenterMethods28ola.loadTheOne28ola()
        binding28ola.srl28ola.setOnRefreshListener {
            binding28ola.wv28ola.loadUrl(
                binding28ola.wv28ola.url ?: return@setOnRefreshListener
            )
            binding28ola.srl28ola.isRefreshing = false
        }

    }

    override fun onResume() {
        webViPresenterMethods28ola.internetCheckWork28ola()
        super.onResume()
    }

    override fun onPause() {
        webViPresenterMethods28ola.stopChecking28ola()
        super.onPause()
    }

    override fun giveWebView28ola(): WebView {
        return binding28ola.wv28ola
    }

    override fun giveSharedPreffs28ola(): SharedPreferences {
        return getSharedPreferences("SP_28ola", MODE_PRIVATE)
    }

    override fun checkForPermissionPresence28ola() {
        Dexter.withContext(this)
            .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {

                }
            }).check()
    }

    override fun contextGiver28ola(): Context {
        return this
    }

    override fun startActivityForResult28ola(intent28ola: Intent) {
        startActivityForResult(intent28ola, 0)
    }

    override fun getIntentURL28ola(): String? {
        return intent.getStringExtra("webURL28ola")
    }

    override fun handleNetworkMissing28ola() {
        lifecycleScope.launch {
            binding28ola.srl28ola.animate().alpha(0f).run {
                duration = 500
            }
            binding28ola.wv28ola.animate().alpha(0f).run {
                duration = 500
            }
            binding28ola.tvNoConnectionLabel28ola.animate().alpha(1f).run {
                startDelay = 500
                duration = 500
            }
            binding28ola.bInternet28ola.animate().alpha(1f).run {
                startDelay = 500
                duration = 500
            }
            binding28ola.lanimationInternet28ola.animate().alpha(1f).run {
                startDelay = 500
                duration = 500
            }
            ValueAnimator.ofFloat(0f, 1f).run {
                duration = 1000
                addUpdateListener {
                    binding28ola.lanimationInternet28ola.progress = it.animatedValue as Float
                }
                start()
            }
            binding28ola.bInternet28ola.setOnClickListener {
                if (webViPresenterMethods28ola.isNetworkIsPresent28ola()) {
                    ValueAnimator.ofArgb(
                        resources.getColor(R.color.yellow_28ola),
                        resources.getColor(R.color.green_button_28ola)
                    ).run {
                        duration = 500
                        repeatCount = 1
                        repeatMode = ValueAnimator.REVERSE
                        setEvaluator(ArgbEvaluator())
                        addUpdateListener {
                            binding28ola.root.setBackgroundColor(it.animatedValue as Int)
                        }
                        doOnEnd {
                            binding28ola.srl28ola.animate().alpha(1f).run {
                                startDelay = 300
                                duration = 500
                            }
                            binding28ola.wv28ola.animate().alpha(1f).run {
                                startDelay = 300
                                duration = 500
                            }
                            binding28ola.tvNoConnectionLabel28ola.animate().alpha(0f).run {
                                duration = 500
                            }
                            binding28ola.bInternet28ola.animate().alpha(0f).run {
                                duration = 500
                            }
                            binding28ola.lanimationInternet28ola.animate().alpha(0f).run {
                                duration = 500
                            }
                            webViPresenterMethods28ola.internetCheckWork28ola()
                        }
                        start()
                    }
                    ValueAnimator.ofFloat(1f, 0f).run {
                        duration = 1000
                        addUpdateListener {
                            binding28ola.lanimationInternet28ola.progress =
                                it.animatedValue as Float
                        }
                        start()
                    }
                } else {
                    ValueAnimator.ofArgb(
                        resources.getColor(R.color.yellow_28ola),
                        resources.getColor(R.color.red_button_28ola)
                    ).run {
                        duration = 500
                        repeatCount = 1
                        repeatMode = ValueAnimator.REVERSE
                        setEvaluator(ArgbEvaluator())
                        addUpdateListener {
                            binding28ola.root.setBackgroundColor(it.animatedValue as Int)
                        }
                        start()
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        return if (binding28ola.wv28ola.canGoBack() && binding28ola.wv28ola.isFocused)
            binding28ola.wv28ola.goBack() else
            super.onBackPressed()
    }

    override fun onActivityResult(requestCode28ola: Int, resultCode28ola: Int, data28ola: Intent?) {
        if (filePathCallBack28ola != null && requestCode28ola == 0) {
            val uriResult28ola =
                if (data28ola == null || resultCode28ola != RESULT_OK) null else data28ola.data
            if (uriResult28ola != null) {
                filePathCallBack28ola?.onReceiveValue(arrayOf(uriResult28ola))
            } else {
                filePathCallBack28ola?.onReceiveValue(arrayOf(uriView28ola))
            }
            filePathCallBack28ola = null
        }
        super.onActivityResult(requestCode28ola, resultCode28ola, data28ola)
    }
}