package com.matcha.para.appp.SplashActiivity28ola

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
                finalString28ola = decodeString28ola(CODE_BINOM_28ola)
                splashActivityMethods28ola28Ola.startNewIntentWithURL28ola(
                    finalString28ola ?: return@launch
                )
            }
        }
    }

}
