package dev.spikeysanju.expensetracker.view.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.spikeysanju.expensetracker.databinding.FragmentAccountBinding
import dev.spikeysanju.expensetracker.view.adapter.AccountAdapter
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AccountViewModel by viewModels()
    private val accountAdapter = AccountAdapter(
        onEditClick = { account ->
            val action = AccountFragmentDirections.actionAccountFragmentToEditAccountFragment(account)
            findNavController().navigate(action)
        },
        onDeleteClick = { account ->
            viewModel.deleteAccount(account)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeAccounts()

        binding.btnAddAccount.setOnClickListener {
            findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToAddAccountFragment())
        }
    }

    private fun setupRecyclerView() {
        binding.rvAccounts.apply {
            adapter = accountAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeAccounts() {
        lifecycleScope.launchWhenStarted {
            viewModel.accounts.collect {
                accountAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
