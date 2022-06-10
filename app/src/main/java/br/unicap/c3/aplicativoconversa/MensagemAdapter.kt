package br.unicap.c3.aplicativoconversa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MensagemAdapter(val context: Context, val listaMensagens: ArrayList<Mensagem>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVE = 1
    val ITEM_SENT = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType == 1){
            val view: View = LayoutInflater.from(context).inflate(R.layout.recebida, parent, false)
            return RecebidaViewHolder(view)
        }else{
            val view: View = LayoutInflater.from(context).inflate(R.layout.enviado, parent, false)
            return EnviadaViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val mensagemAtual = listaMensagens[position]

        if(holder.javaClass == EnviadaViewHolder::class.java){
            val viewHolder = holder as EnviadaViewHolder
            holder.mensagemEnviada.text = mensagemAtual.mensagem
        }else{
            val viewHolder = holder as RecebidaViewHolder
            holder.mensagemRecebida.text = mensagemAtual.mensagem
        }

    }

    override fun getItemViewType(position: Int): Int {
        val mensagemAtual = listaMensagens[position]

        if(FirebaseAuth.getInstance().currentUser?.uid.equals(mensagemAtual.senderId)){
            return ITEM_SENT
        }else{
            return ITEM_RECEIVE
        }
    }

    override fun getItemCount(): Int {
        return listaMensagens.size
    }

    class EnviadaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val mensagemEnviada = itemView.findViewById<TextView>(R.id.txt_mensagem_enviada)
    }

    class RecebidaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val mensagemRecebida = itemView.findViewById<TextView>(R.id.txt_mensagem_recebida)
    }
}