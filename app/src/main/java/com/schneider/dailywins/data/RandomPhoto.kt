package com.schneider.dailywins.data



data class RandomPhoto(
    val urls : RandomPhotoURL
)

data class RandomPhotoURL(
    val raw : String,
    val full : String,
    val regular : String,
    val small : String,
    val thumb : String,
)