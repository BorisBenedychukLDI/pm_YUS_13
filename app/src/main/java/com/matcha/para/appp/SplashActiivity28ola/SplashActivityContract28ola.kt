package com.matcha.para.appp.SplashActiivity28ola

import android.annotation.SuppressLint

@SuppressLint("CustomSplashScreen")
interface SplashActivityContract28ola {

    interface  SplashActivityMethods28ola {
        fun onClickListener28ola (clickScope28ola: () -> Unit)
        fun startNewIntentWithURL28ola (url28ola : String)
        fun getPackageName28ola (): String
    }

    interface SplashActivityPresenter28ola {
        fun thisEndsHere28ola()
        fun setupFB28ola ()
    }
}