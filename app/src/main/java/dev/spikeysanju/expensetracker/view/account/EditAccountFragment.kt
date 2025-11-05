package dev.spikeysanju.expensetracker.view.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import dev.spikeysanju.expensetracker.R
import dev.spikeysanju.expensetracker.databinding.FragmentEditAccountBinding
import dev.spikeysanju.expensetracker.model.Account

@AndroidEntryPoint
class EditAccountFragment : Fragment() {

    private var _binding: FragmentEditAccountBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AccountViewModel by viewModels()
    private val args: EditAccountFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accountTypes = resources.getStringArray(R.array.account_types)
        val adapter = ArrayAdapter(requireContext(), R.layout.item_autocomplete_layout, accountTypes)
        binding.etAccountType.setAdapter(adapter)

        binding.etAccountName.setText(args.account.name)
        binding.etAccountBalance.setText(args.account.balance.toString())
        binding.etAccountType.setText(args.account.type, false)

        binding.btnSaveAccount.text = "Update"
        binding.btnSaveAccount.setOnClickListener {
            updateAccount()
        }
    }

    private fun updateAccount() {
        val name = binding.etAccountName.text.toString()
        val balance = binding.etAccountBalance.text.toString().toDoubleOrNull()
        val type = binding.etAccountType.text.toString()

        if (name.isNotEmpty() && balance != null && type.isNotEmpty()) {
            val account = Account(args.account.id, name, balance, type)
            viewModel.updateAccount(account)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
