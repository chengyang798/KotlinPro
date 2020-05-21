package com.example.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.app.entity.User
import com.example.app.widget.CodeView
import com.example.core.utils.CacheUtils
import com.example.core.utils.toast
import com.example.lesson.LessonActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author  chy
 * @date    2020-04-02
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val usernameKey = "username"
    private val passworkKey = "passwork"

    private lateinit var et_username:EditText
    private lateinit var et_password:EditText
    private lateinit var et_code:EditText
    private lateinit var et_code1:EditText
    private lateinit var et_code2:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_code = findViewById(R.id.et_code)

        et_username.setText(CacheUtils.get(usernameKey))
        et_password.setText(CacheUtils.get(passworkKey))

        val codeView = findViewById<CodeView>(R.id.code_view)
        btn_login.setOnClickListener{login()}
        codeView.setOnClickListener(this)
    }

    /**
     * 登陆
     */
    private fun login() {

        var username = et_username.text.toString()
        var password = et_password.text.toString()
        var code = et_code.text.toString()
        val user = User(username, password, code)

        if (verify(user)) {
            CacheUtils.save(usernameKey,username)
            CacheUtils.save(passworkKey,password)
            startActivity(Intent(this,LessonActivity::class.java))
        }
    }

    private fun verify(user: User) : Boolean{
        if (user.username != null && user.username!!.length < 4) {
            toast("用户名不合法")
            return false
        }
        if (user.password != null && user.password!!.length < 4) {
            toast("密码不合法")
            return false
        }
        return true
    }

    override fun onClick(v: View?) {

        if (v is CodeView) {
            v.updateCode()
        }

    }

}