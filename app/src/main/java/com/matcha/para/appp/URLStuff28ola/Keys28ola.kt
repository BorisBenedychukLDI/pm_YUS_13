package com.matcha.para.appp.URLStuff28ola

import android.util.Base64


const val APPSFLYER_CAMPAIGN_TAG_28ola = "campaign"
const val APPSFLYER_STATUS_TAG_28ola = "af_status"
const val APPSFLYER_ADGROUP_TAG_28ola = "adgroup"
const val APPSFLYER_ADSET_TAG_28ola = "adset"
const val APPSFLYER_AF_CHANNEL_TAG_28ola = "af_channel"
const val APPSFLYER_MEDIA_SOURCE_TAG_28ola = "media_source"

const val FB_DEFAULT_KEY_28ola = "paradefkey"
const val FB_BLACK_KEY_28ola = "parablackpa_"
const val FB_WHITE_KEY_28ola = "parawhitepa"
const val CODED_ONESIGNAL_28ola = "MDUwOWY1MzQtMDRmMy00MWIwLTg5NjktMDQwOTc1MWNiNjE2"
const val CODE_APPSFLYER_28ola = "OXVVd3Z0WnJibm01Q2lBczdMTXduaQ=="

fun decodeString28ola (str28ola: String) = String (Base64.decode(str28ola, Base64.DEFAULT))