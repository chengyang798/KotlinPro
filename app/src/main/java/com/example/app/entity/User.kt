package com.example.app.entity

/**
 * @author  chy
 * @date    2020-04-02
 */
class User{
    var username : String? = null
    var password : String? = null
    var code : String? = null



    constructor(username: String?, password: String?, code: String?) {
        this.username = username
        this.password = password
        this.code = code
    }

    constructor()


}