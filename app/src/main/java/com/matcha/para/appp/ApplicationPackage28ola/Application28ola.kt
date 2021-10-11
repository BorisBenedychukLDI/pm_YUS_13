package com.matcha.para.appp.ApplicationPackage28ola

import android.app.Application
import android.content.Context

class Application28ola : Application(), ApplicationContract28ola.ApplicationMethods28ola {

    private lateinit var applicationContract28ola: ApplicationContract28ola.ApplicationSetuperMethods28ola

    override fun onCreate() {
        applicationContract28ola = ApplicationSetuper28ola(this)
        applicationContract28ola.appsFlyerSetup28ola()
        applicationContract28ola.mobileAdsSetup28ola()
        applicationContract28ola.oneSignalSetup28ola()
        super.onCreate()
    }

    override fun getContext28ola(): Context {
        return this
    }
}