package br.unicap.c3.aplicativoconversa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class JogadoresActivity : AppCompatActivity() {

    lateinit var etJogador1: EditText
    lateinit var etJogador2: EditText
    lateinit var btnJogar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogadores)

        etJogador1 = findViewById(R.id.et_jg1)
        etJogador2 = findViewById(R.id.et_jg2)
        btnJogar = findViewById(R.id.btn_jogar)

        btnJogar.setOnClickListener {
            val jogadorUm = etJogador1.text.toString()
            val jogadorDois = etJogador2.text.toString()

            if(jogadorUm.isEmpty() || jogadorDois.isEmpty() || jogadorUm == "" || jogadorDois == ""){
                Toast.makeText(this, "Os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this@JogadoresActivity, JogoActivity::class.java)
                intent.putExtra("jogadorUm", jogadorUm)
                intent.putExtra("jogadorDois", jogadorDois)
                startActivity(intent)
            }
        }
    }
}