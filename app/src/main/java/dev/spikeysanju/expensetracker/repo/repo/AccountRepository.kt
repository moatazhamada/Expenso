package dev.spikeysanju.expensetracker.repo

import dev.spikeysanju.expensetracker.data.local.dao.AccountDao
import dev.spikeysanju.expensetracker.model.Account
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountDao: AccountDao
) {

    suspend fun insertAccount(account: Account) {
        accountDao.insertAccount(account)
    }

    suspend fun updateAccount(account: Account) {
        accountDao.updateAccount(account)
    }

    suspend fun deleteAccount(account: Account) {
        accountDao.deleteAccount(account)
    }

    fun getAllAccounts(): Flow<List<Account>> {
        return accountDao.getAllAccounts()
    }
}
