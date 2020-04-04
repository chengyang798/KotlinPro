package com.example.core.http

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

/**
 * @author  chy
 * @date    2020-04-04
 */
object HttpClient : OkHttpClient(){
    val gson = Gson();
    fun <T> convert(json:String?, type:Type):T{
        return gson.fromJson(json,type)
    }

   fun<T> get(path:String, type:Type, entityCallback: EntityCallback<T>){
        val builder = Request.Builder()
                .url("https://api.hencoder.com/$path")
                .build()
        this.newCall(builder).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                entityCallback!!.onFailure("网路异常")
            }

            override fun onResponse(call: Call, response: Response) {
                when(response.code()){
                   in 200..299 -> entityCallback!!.onSuccess(convert(response.body()!!.string(),type))
                   in 400..499 ->  entityCallback!!.onFailure("客户端错误")
                   in 500..599 ->  entityCallback!!.onFailure("服务器错误")
                   else ->  entityCallback!!.onFailure("未知错误")
                }
            }
        })

    }
}