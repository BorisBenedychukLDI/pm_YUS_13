package com.matcha.para.appp.WebActivity28ola

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.webkit.*
import androidx.core.content.FileProvider
import com.matcha.para.appp.URLStuff28ola.filePathCallBack28ola
import com.matcha.para.appp.URLStuff28ola.uriView28ola
import kotlinx.coroutines.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class WebPresenter28ola (private val webActivityMethods28ola: WebActivityContract28ola.WebActivityMethods28ola) : WebActivityContract28ola.WebActivityPresenter28ola {

    private var job28ola: Job? = null

    override fun setupWebView28ola() {
        webActivityMethods28ola.giveWebView28ola().run {
            settings.run {
                loadWithOverviewMode = true
                displayZoomControls = false
                useWideViewPort = true
                javaScriptEnabled = true
                loadsImagesAutomatically = true
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                builtInZoomControls = true
                displayZoomControls = false
                domStorageEnabled = true
                mediaPlaybackRequiresUserGesture = false
            }

            scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        }
    }

    override fun setupWebViewClient28ola() {
        webActivityMethods28ola.giveWebView28ola().webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view28ola: WebView?,
                request28ola: WebResourceRequest?
            ): Boolean {
                val prohibitedLinks28ola = listOf("instagram","facebook","youtube")
                val modifiedLinks28ola = listOf("mailto:", "tel:")
                return when {
                    prohibitedLinks28ola.filter {
                        request28ola?.url.toString().contains(it)
                    }.isNotEmpty() -> true
                    modifiedLinks28ola.filter {
                        request28ola
                            ?.url.toString().startsWith(it)
                    }.isNotEmpty() -> {
                        view28ola?.context?.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                request28ola?.url
                            )
                        )
                        true
                    }
                    else -> false
                }
            }

            override fun onPageFinished(view28ola: WebView?, url28ola: String?) {
                webActivityMethods28ola.giveSharedPreffs28ola().edit().putString("Last_Page_28ola", url28ola?: return).apply()
                super.onPageFinished(view28ola, url28ola)
            }

            override fun onReceivedSslError(
                view28ola: WebView?,
                handler28ola: SslErrorHandler?,
                error28ola: SslError?
            ) {
                handler28ola?.proceed()
                super.onReceivedSslError(view28ola, handler28ola, error28ola)
            }
        }
    }

    override fun setupWebChromeClient28ola() {
        webActivityMethods28ola.giveWebView28ola().webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView28ola: WebView?,
                filePathCallback28ola: ValueCallback<Array<Uri>>?,
                fileChooserParams28ola: FileChooserParams?
            ): Boolean {
                webActivityMethods28ola.checkForPermissionPresence28ola()
                filePathCallBack28ola = filePathCallback28ola
                val captureIntent28ola = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (captureIntent28ola.resolveActivity(webActivityMethods28ola.contextGiver28ola().packageManager) != null) {
                    val providedFile28ola = createTempFile28ola()
                    uriView28ola = FileProvider.getUriForFile(
                        webActivityMethods28ola.contextGiver28ola(),
                        "${webActivityMethods28ola.contextGiver28ola().packageName}.provider",
                        providedFile28ola
                    )
                    captureIntent28ola.run {
                        putExtra(MediaStore.EXTRA_OUTPUT, uriView28ola)
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    val actionIntent28ola = Intent(Intent.ACTION_GET_CONTENT).apply {
                        addCategory(Intent.CATEGORY_OPENABLE)
                        type = "image/*"
                    }
                    val intentChooser28ola = Intent(Intent.ACTION_CHOOSER).apply {
                        putExtra(Intent.EXTRA_INTENT, captureIntent28ola)
                        putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(actionIntent28ola))
                    }
                    webActivityMethods28ola.startActivityForResult28ola(intentChooser28ola)
                    return true

                }
                return super.onShowFileChooser(
                    webView28ola,
                    filePathCallback28ola, fileChooserParams28ola)
            }
        }
    }

    override fun loadTheOne28ola() {
        webActivityMethods28ola.giveSharedPreffs28ola().getString("Last_Page_28ola",null)?.let {
                lastpage28ola ->
            webActivityMethods28ola.giveWebView28ola().loadUrl(lastpage28ola)
            return
        }
        webActivityMethods28ola.giveWebView28ola().loadUrl(webActivityMethods28ola.getIntentURL28ola() ?: return)
    }

    override fun internetCheckWork28ola() {
        job28ola = CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                delay(500)
                isNetworkIsPresent28ola().run {
                    if (!this) {
                        webActivityMethods28ola.handleNetworkMissing28ola()
                        cancel()
                    }
                }
            }
        }
    }

    override fun isNetworkIsPresent28ola(): Boolean {
        val connectivityManager28ola = webActivityMethods28ola.contextGiver28ola().getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val networkCapabilities28ola = connectivityManager28ola.getNetworkCapabilities(connectivityManager28ola.activeNetwork) ?: return false
            return networkCapabilities28ola.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            for (network28ola in connectivityManager28ola.allNetworks) {
                connectivityManager28ola.getNetworkInfo(network28ola)?.let {
                    if (it.isConnected) return true
                }
            }
            return false
        }
    }

    override fun stopChecking28ola() {
        job28ola?.cancel()
    }

    private fun createTempFile28ola(): File {
        val date28ola = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
            .format(Date())
        val fileDir28ola = webActivityMethods28ola.contextGiver28ola().getExternalFilesDir(
            Environment.DIRECTORY_PICTURES)
        return File.createTempFile("TMP${date28ola}_28ola", ".jpg", fileDir28ola)
    }
}