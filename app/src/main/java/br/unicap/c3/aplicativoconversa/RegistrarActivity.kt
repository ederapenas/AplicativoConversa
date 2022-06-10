package br.unicap.c3.aplicativoconversa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrarActivity : AppCompatActivity() {

    private lateinit var etNome: EditText
    private lateinit var etEmail: EditText
    private lateinit var etSenha: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        etNome = findViewById(R.id.et_nome)
        etEmail = findViewById(R.id.et_email)
        etSenha = findViewById(R.id.et_senha)
        btnRegistrar = findViewById(R.id.btn_registrar)

        btnRegistrar.setOnClickListener{
            val nome = etNome.text.toString()
            val email = etEmail.text.toString()
            val senha = etSenha.text.toString()

            registrar(nome, email, senha)
        }
    }

    private fun registrar(nome: String, email: String, senha: String){
        mAuth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(nome, email, mAuth.currentUser?.uid!!)
                    val intent = Intent(this@RegistrarActivity, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@RegistrarActivity, "Um erro ocorreu!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(nome: String, email: String, uid: String){
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("usuario").child(uid).setValue(Usuario(nome, email, uid))
    }
}