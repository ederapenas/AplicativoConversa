package br.unicap.c3.aplicativoconversa

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class UsuarioAdapter(val context: Context, val listaUsuario: ArrayList<Usuario>):
    RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.usuario_layout, parent, false)
        return UsuarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuarioAtual = listaUsuario[position]

        holder.txtNome.text = usuarioAtual.nome

        holder.itemView.setOnClickListener{
            val intent = Intent(context, ConversaActivity::class.java)
            intent.putExtra("nome", usuarioAtual.nome)
            intent.putExtra("uid", usuarioAtual.uid)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return listaUsuario.size
    }

    class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtNome = itemView.findViewById<TextView>(R.id.txt_nome)
    }
}