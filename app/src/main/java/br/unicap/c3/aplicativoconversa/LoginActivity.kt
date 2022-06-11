package br.unicap.c3.aplicativoconversa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etSenha: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegistrar: Button
    private lateinit var btnJogo: Button
    private lateinit var btnDesenvolvedores: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        etEmail = findViewById(R.id.et_email)
        etSenha = findViewById(R.id.et_senha)
        btnLogin = findViewById(R.id.btn_login)
        btnRegistrar = findViewById(R.id.btn_registrar)
        btnJogo = findViewById(R.id.btn_jogo)
        btnDesenvolvedores = findViewById(R.id.btn_desenvolvedores)

        btnRegistrar.setOnClickListener{
            val intent = Intent(this, RegistrarActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener{
            val email = etEmail.text.toString()
            val senha = etSenha.text.toString()

            login(email, senha)
        }

        btnJogo.setOnClickListener{
            val intent = Intent(this, JogadoresActivity::class.java)
            startActivity(intent)
        }

        btnDesenvolvedores.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }

    private fun login(email: String, senha: String){
        mAuth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "Usuario não existente!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}