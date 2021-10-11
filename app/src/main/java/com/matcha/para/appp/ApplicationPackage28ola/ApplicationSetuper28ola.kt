package com.matcha.para.appp.ApplicationPackage28ola

import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.matcha.para.appp.URLStuff28ola.*
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApplicationSetuper28ola(private val applicationMethods28ola: ApplicationContract28ola.ApplicationMethods28ola) : ApplicationContract28ola.ApplicationSetuperMethods28ola{

    override fun appsFlyerSetup28ola() {
        MobileAds.initialize(applicationMethods28ola.getContext28ola())
        CoroutineScope(Dispatchers.IO).launch {
            try {
                AID28ola =
                    AdvertisingIdClient.getAdvertisingIdInfo(applicationMethods28ola.getContext28ola())?.id
            } catch (e28ola: Exception) {

            }
        }
    }

    override fun oneSignalSetup28ola() {
        OneSignal.initWithContext(applicationMethods28ola.getContext28ola())
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.setAppId(decodeString28ola(CODED_ONESIGNAL_28ola))
    }

    override fun mobileAdsSetup28ola() {
        val conversionListener28ola = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {
                p0?.run {
                    status28ola =
                        if (getValue(APPSFLYER_STATUS_TAG_28ola).toString() == "Organic") "Organic" else "Non-organic"

                    getValue(APPSFLYER_CAMPAIGN_TAG_28ola)
                        .toString()
                        .split("||").drop(1)
                        .map {
                            it.split(":")[1]
                        }.let { list28ola ->
                            key28ola =
                                if (list28ola.isNotEmpty()) list28ola[0] else "empty"
                            sub228ola =
                                if (list28ola.size >= 2) list28ola[1] else "empty"
                            sub328ola =
                                if (list28ola.size >= 3) list28ola[2] else "empty"
                        }


                    mediaSource28ola = getValue(APPSFLYER_MEDIA_SOURCE_TAG_28ola).toString()
                    afChannel28ola = getValue(APPSFLYER_AF_CHANNEL_TAG_28ola).toString()
                    adGroup28ola = getValue(APPSFLYER_ADGROUP_TAG_28ola).toString()
                    adSet28ola = getValue(APPSFLYER_ADSET_TAG_28ola).toString()

                }
            }

            override fun onConversionDataFail(p0: String?) {
            }

            override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
            }

            override fun onAttributionFailure(p0: String?) {
            }
        }
        AppsFlyerLib.getInstance().run {
            uid28ola = getAppsFlyerUID(applicationMethods28ola.getContext28ola())
            init(
                decodeString28ola(CODE_APPSFLYER_28ola),
                conversionListener28ola,
                applicationMethods28ola.getContext28ola()
            )
            start(applicationMethods28ola.getContext28ola())
        }
    }
}