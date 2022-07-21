package br.com.exerciciofirebase.ui.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.exerciciofirebase.databinding.MsgItemBinding

class AdapterMsg(private var listMsg: MutableList<String>) :
    RecyclerView.Adapter<AdapterMsg.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MsgItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val msg = listMsg[position]
        holder.exibirInformacaoesNaTextView(msg)
    }

    override fun getItemCount(): Int {
        return listMsg.size
    }

    class ViewHolder(val binding: MsgItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun exibirInformacaoesNaTextView(msg: String) {
            binding.msgBase.text = msg
        }
    }

    fun updateMsgList(newListMsg: MutableList<String>) {
        if (listMsg.size == 0) {
            listMsg = newListMsg
        }
        else {
            listMsg = mutableListOf()
            listMsg.addAll(newListMsg)
        }
        notifyDataSetChanged()
    }
}
