package com.schneider.dailywins.data

import java.sql.Timestamp
import java.util.*

data class DailyWin(
    val winId: String = "",
    val winList: List<String>? = null,
    val date: Date? = null,
    val photoId: String? = null,
    var highlighted: Boolean = false
)