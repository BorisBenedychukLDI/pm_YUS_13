package com.matcha.para.appp.ApplicationPackage28ola

import com.matcha.para.appp.URLStuff28ola.CODED_ONESIGNAL_28ola
import com.matcha.para.appp.URLStuff28ola.decodeString28ola
import com.onesignal.OneSignal

class ApplicationSetuper28ola(private val applicationMethods28ola: ApplicationContract28ola.ApplicationMethods28ola) : ApplicationContract28ola.ApplicationSetuperMethods28ola{

    override fun oneSignalSetup28ola() {
        OneSignal.initWithContext(applicationMethods28ola.getContext28ola())
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.setAppId(decodeString28ola(CODED_ONESIGNAL_28ola))
    }

}