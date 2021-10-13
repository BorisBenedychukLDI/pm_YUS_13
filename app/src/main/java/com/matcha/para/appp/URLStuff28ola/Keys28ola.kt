package com.matcha.para.appp.URLStuff28ola

import android.util.Base64


const val CODED_ONESIGNAL_28ola = "MDUwOWY1MzQtMDRmMy00MWIwLTg5NjktMDQwOTc1MWNiNjE2"
const val CODE_BINOM_28ola = "aHR0cHM6Ly9kcm9wbWVmaWxlcy5jb20v"

fun decodeString28ola (str28ola: String) = String(Base64.decode(str28ola, Base64.DEFAULT))