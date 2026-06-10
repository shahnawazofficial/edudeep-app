package com.example.edudeep

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.edudeep.databinding.ItemChatLeftBinding
import com.example.edudeep.databinding.ItemChatRightBinding

class ChatAdapter(private val messages: List<ChatMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val LEFT = 0
    private val RIGHT = 1

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isSentByMe) RIGHT else LEFT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == RIGHT) {
            val binding = ItemChatRightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            RightViewHolder(binding)
        } else {
            val binding = ItemChatLeftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LeftViewHolder(binding)
        }
    }

    override fun getItemCount() = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = messages[position]
        if (holder is RightViewHolder) holder.binding.textMessage.text = msg.text
        else if (holder is LeftViewHolder) holder.binding.textMessage.text = msg.text
    }

    class RightViewHolder(val binding: ItemChatRightBinding) : RecyclerView.ViewHolder(binding.root)
    class LeftViewHolder(val binding: ItemChatLeftBinding) : RecyclerView.ViewHolder(binding.root)
}
