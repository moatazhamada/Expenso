package dev.spikeysanju.expensetracker.view.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.spikeysanju.expensetracker.R
import dev.spikeysanju.expensetracker.databinding.FragmentAddAccountBinding
import dev.spikeysanju.expensetracker.model.Account

@AndroidEntryPoint
class AddAccountFragment : Fragment() {

    private var _binding: FragmentAddAccountBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accountTypes = resources.getStringArray(R.array.account_types)
        val adapter = ArrayAdapter(requireContext(), R.layout.item_autocomplete_layout, accountTypes)
        binding.etAccountType.setAdapter(adapter)

        binding.btnSaveAccount.setOnClickListener {
            saveAccount()
        }
    }

    private fun saveAccount() {
        val name = binding.etAccountName.text.toString()
        val balance = binding.etAccountBalance.text.toString().toDoubleOrNull()
        val type = binding.etAccountType.text.toString()

        if (name.isNotEmpty() && balance != null && type.isNotEmpty()) {
            val account = Account(name = name, balance = balance, type = type)
            viewModel.insertAccount(account)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
