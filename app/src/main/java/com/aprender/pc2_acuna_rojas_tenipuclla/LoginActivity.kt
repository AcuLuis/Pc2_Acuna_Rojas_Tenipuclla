package com.aprender.pc2_acuna_rojas_tenipuclla

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etEmailLogin: EditText = findViewById(R.id.txt_email_log)
        val etPasswordLogin: EditText = findViewById(R.id.txt_pass_log)
        val btLogin: Button = findViewById(R.id.btn_acceso_log)
        val btRegistro: Button = findViewById(R.id.btn_registro_log)

        val auth = FirebaseAuth.getInstance()

        btLogin.setOnClickListener {
            val email = etEmailLogin.text.toString()
            val password = etPasswordLogin.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //Inicio de sesión exitoso
                        Snackbar
                            .make(
                                findViewById(android.R.id.content),
                                "Inicio de sesión exitoso",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        startActivity(Intent(this, interes::class.java))
                        finish()
                    } else {
                        //Error en el inicio de sesión
                        Snackbar
                            .make(
                                findViewById(android.R.id.content),
                                "Error con el inicio de sesión",
                                Snackbar.LENGTH_SHORT
                            ).show()
                    }

                }
    }
        btRegistro.setOnClickListener {
            startActivity(Intent(this, registro::class.java))
        }
    }

}