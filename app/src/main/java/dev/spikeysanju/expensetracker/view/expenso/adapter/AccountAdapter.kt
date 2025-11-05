package dev.spikeysanju.expensetracker.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.spikeysanju.expensetracker.databinding.ItemAccountLayoutBinding
import dev.spikeysanju.expensetracker.model.Account

class AccountAdapter(
    private val onEditClick: (Account) -> Unit,
    private val onDeleteClick: (Account) -> Unit
) : ListAdapter<Account, AccountAdapter.AccountViewHolder>(AccountDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val binding =
            ItemAccountLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AccountViewHolder(private val binding: ItemAccountLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(account: Account) {
            binding.apply {
                tvAccountName.text = account.name
                tvAccountBalance.text = "₹${account.balance}"
                tvAccountType.text = account.type

                btnEditAccount.setOnClickListener {
                    onEditClick(account)
                }

                btnDeleteAccount.setOnClickListener {
                    onDeleteClick(account)
                }
            }
        }
    }
}
