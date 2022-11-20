package com.schneider.dailywins.addwins

import com.schneider.dailywins.data.RandomPhoto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {

    @GET("/photos/random")
    suspend fun getRandomPhoto(@Query("client_id") client_id: String = "LyCKZgAqtiWPEk1y6scAYv2f7fLFeneoUijWXW98mUU") : Response<RandomPhoto>
}


//?client_id=LyCKZgAqtiWPEk1y6scAYv2f7fLFeneoUijWXW98mUU