package com.azamovhudstc.movieappforpdp.repository

import com.azamovhudstc.movieappforpdp.data.network.response.ResultXX
import com.azamovhudstc.movieappforpdp.data.network.response.VideoResponse
import java.util.concurrent.Flow

interface WatchVideoRepository {
    fun watchVideoListById(id:Int):kotlinx.coroutines.flow.Flow<Result<List<ResultXX>>>
}