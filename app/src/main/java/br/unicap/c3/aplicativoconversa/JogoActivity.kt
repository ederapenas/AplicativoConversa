package br.unicap.c3.aplicativoconversa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class JogoActivity : AppCompatActivity() {

    enum class Turno{
        BOLA,
        XIS
    }

    private var primeiroTurno = Turno.XIS
    private var turnoAtual = Turno.BOLA

    private var pontuacaoXis = 0
    private var pontuacaoBola = 0

    private lateinit var tvTurno: TextView

    private var listaTabuleiro = mutableListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)

        tabuleiroInicial()
    }

    private fun tabuleiroInicial(){
        listaTabuleiro.add(findViewById(R.id.a1))
        listaTabuleiro.add(findViewById(R.id.a2))
        listaTabuleiro.add(findViewById(R.id.a3))
        listaTabuleiro.add(findViewById(R.id.b1))
        listaTabuleiro.add(findViewById(R.id.b2))
        listaTabuleiro.add(findViewById(R.id.b3))
        listaTabuleiro.add(findViewById(R.id.c1))
        listaTabuleiro.add(findViewById(R.id.c2))
        listaTabuleiro.add(findViewById(R.id.c3))
    }

    fun clicaTabuleiro(view: View){
        if(view !is Button)
            return
        addAoTabuleiro(view)

        if(checaVitoria("O")){
            pontuacaoBola++
            resultado("O venceu!")
            
        }else if(checaVitoria("X")){
            pontuacaoXis++
            resultado("X venceu!")
        }

        else if(tabuleiroCheio()){
            resultado("Empate")
        }
    }

    private fun checaVitoria(s: String): Boolean {
        if(match(findViewById(R.id.a1),s) && match(findViewById(R.id.a2),s) && match(findViewById(R.id.a3),s)){
            return true
        }
        if(match(findViewById(R.id.b1),s) && match(findViewById(R.id.b2), s) && match(findViewById(R.id.b3), s)){
            return true
        }
        if(match(findViewById(R.id.c1),s) && match(findViewById(R.id.c2), s) && match(findViewById(R.id.c3), s)){
            return true
        }

        if(match(findViewById(R.id.a1),s) && match(findViewById(R.id.b1), s) && match(findViewById(R.id.c1), s)){
            return true
        }
        if(match(findViewById(R.id.a2),s) && match(findViewById(R.id.b2), s) && match(findViewById(R.id.c2), s)){
            return true
        }
        if(match(findViewById(R.id.a3),s) && match(findViewById(R.id.b3), s) && match(findViewById(R.id.c3), s)){
            return true
        }

        if(match(findViewById(R.id.a1),s) && match(findViewById(R.id.b2), s) && match(findViewById(R.id.c3), s)){
            return true
        }
        if(match(findViewById(R.id.a3),s) && match(findViewById(R.id.b2), s) && match(findViewById(R.id.c1), s)){
            return true
        }

        return false
    }

    private fun match(botao: Button, simbolo: String) = botao.text == simbolo

    private fun resultado(title: String){

        val mensagem = "\nX: $pontuacaoXis \n\nO: $pontuacaoBola"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(mensagem)
            .setPositiveButton("Reiniciar"){
                _,_ ->
                reiniciarTabuleiro()
            }
            .setCancelable(false)
            .show()
    }

    private fun reiniciarTabuleiro(){
        for(botao in listaTabuleiro){
            botao.text = ""
        }

        if(primeiroTurno == Turno.BOLA){
            primeiroTurno = Turno.XIS
        }else if(primeiroTurno == Turno.XIS){
            primeiroTurno = Turno.BOLA
        }

        turnoAtual = primeiroTurno
        setTurnLabel()
    }

    private fun tabuleiroCheio(): Boolean{
        for(botao in listaTabuleiro){
            if(botao.text == ""){
                return false
            }
        }
        return true
    }

    private fun addAoTabuleiro(botao: Button){
        if(botao.text != ""){
            return
        }
        if(turnoAtual == Turno.BOLA){
            botao.text = "O"
            turnoAtual = Turno.XIS
        }else if(turnoAtual == Turno.XIS){
            botao.text = "X"
            turnoAtual = Turno.BOLA
        }
        setTurnLabel()
    }

    private fun setTurnLabel() {
        var turnoTexto = ""
        if (turnoAtual == Turno.XIS) {
            turnoTexto = "Vez do X"
        } else if (turnoAtual == Turno.BOLA) {
            turnoTexto = "Vez do O"
        }



        tvTurno = findViewById(R.id.tvvez)
        tvTurno.text = turnoTexto

    }

}