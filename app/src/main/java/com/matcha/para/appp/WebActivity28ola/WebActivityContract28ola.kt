package com.matcha.para.appp.WebActivity28ola

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.webkit.WebView

interface WebActivityContract28ola {

    interface WebActivityMethods28ola {
        fun giveWebView28ola (): WebView
        fun giveSharedPreffs28ola (): SharedPreferences
        fun checkForPermissionPresence28ola ()
        fun contextGiver28ola (): Context
        fun startActivityForResult28ola (intent28ola: Intent)
        fun getIntentURL28ola (): String?
        fun handleNetworkMissing28ola ()
    }

    interface WebActivityPresenter28ola  {
        fun setupWebView28ola ()
        fun setupWebViewClient28ola ()
        fun setupWebChromeClient28ola ()
        fun loadTheOne28ola ()
        fun internetCheckWork28ola ()
        fun isNetworkIsPresent28ola (): Boolean
        fun stopChecking28ola ()
    }
}