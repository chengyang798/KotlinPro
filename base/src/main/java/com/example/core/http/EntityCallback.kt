package com.example.core.http

/**
 * @author  chy
 * @date    2020-04-04
 */

interface EntityCallback<T> {

    fun onSuccess(entity: T)
    fun onFailure(message: String)
}