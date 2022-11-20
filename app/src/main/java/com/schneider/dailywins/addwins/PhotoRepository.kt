package com.schneider.dailywins.addwins

import com.schneider.dailywins.data.RandomPhoto
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val photoService: PhotoService
) {

    suspend fun getRandomPhoto(): RandomPhoto? {
        val response = photoService.getRandomPhoto()
        if (response.isSuccessful) {
            println("PhotoRepository good: ${response.raw()}")
            return response.body()
        }
        else {
            println("PhotoRepository bad: ${response.body()}")
        }
        return null
    }
}