package com.example.edudeep

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.edudeep.databinding.ItemChatGroupBinding

class ChatListAdapter(
    private val chatGroups: List<ChatGroup>,
    private val onItemClick: (ChatGroup) -> Unit
) : RecyclerView.Adapter<ChatListAdapter.ChatGroupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatGroupViewHolder {
        // Inflate the item layout for each chat group
        val binding = ItemChatGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatGroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatGroupViewHolder, position: Int) {
        val group = chatGroups[position]
        holder.bind(group)
    }

    override fun getItemCount() = chatGroups.size

    inner class ChatGroupViewHolder(private val binding: ItemChatGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(chatGroup: ChatGroup) {
            binding.chatGroupName.text = chatGroup.name
            binding.root.setOnClickListener { onItemClick(chatGroup) }
        }
    }
}
