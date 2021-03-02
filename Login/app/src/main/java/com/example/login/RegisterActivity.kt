package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.util.Patterns
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RegisterActivity : AppCompatActivity() {
    private lateinit var emailText: EditText
    private lateinit var signUpButton: FloatingActionButton
    private lateinit var nameText: EditText
    private lateinit var passwordText: EditText
    private lateinit var backButton: ImageButton
    private lateinit var showPasswordButton: CheckBox

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        signUpButton = findViewById(R.id.btn_signup)
        nameText = findViewById(R.id.input_name)
        emailText = findViewById(R.id.input_email)
        passwordText = findViewById(R.id.input_password)
        backButton = findViewById(R.id.backToMain)
        showPasswordButton = findViewById(R.id.checkPasswordRegister)

        signUpButton.setOnClickListener { signUp() ***REMOVED***
        backButton.setOnClickListener { onBackPressed() ***REMOVED***
        showPasswordButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                passwordText.transformationMethod = HideReturnsTransformationMethod.getInstance()
          ***REMOVED***
            else passwordText.transformationMethod = PasswordTransformationMethod.getInstance()
      ***REMOVED***
  ***REMOVED***

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
  ***REMOVED***

    private fun signUp() {
        if (!validate()) {
            onSignUpFailed()
            return
      ***REMOVED***

        signUpButton.isEnabled = false

        val username = nameText.text.toString()
        val password = passwordText.text.toString()
        val email = emailText.text.toString()
        val user = User(username = username, password = password, email)

        val progressDialog = LoadDialog(this, user, getString(R.string.url), Log.REGISTER)
        progressDialog.show()
  ***REMOVED***

    private fun onSignUpFailed() {
        Toast.makeText(baseContext, "Check credentials", Toast.LENGTH_LONG).show()
        signUpButton.isEnabled = true
  ***REMOVED***

    private fun validate(): Boolean {
        var valid = true
        val name = nameText.text.toString()
        val email = emailText.text.toString()
        val password = passwordText.text.toString()

        if (name.isEmpty() || name.length < 3) {
            nameText.error = "at least 3 characters"
            valid = false
      ***REMOVED*** else {
            nameText.error = null
      ***REMOVED***

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.error = "enter a valid email address"
            valid = false
      ***REMOVED*** else {
            emailText.error = null
      ***REMOVED***

        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            passwordText.error = "between 4 and 10 alphanumeric characters"
            valid = false
      ***REMOVED*** else {
            passwordText.error = null
      ***REMOVED***

        return valid
  ***REMOVED***
***REMOVED***