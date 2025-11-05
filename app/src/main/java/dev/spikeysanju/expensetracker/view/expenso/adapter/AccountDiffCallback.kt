package dev.spikeysanju.expensetracker.view.adapter

import androidx.recyclerview.widget.DiffUtil
import dev.spikeysanju.expensetracker.model.Account

class AccountDiffCallback : DiffUtil.ItemCallback<Account>() {
    override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem == newItem
    }
}
