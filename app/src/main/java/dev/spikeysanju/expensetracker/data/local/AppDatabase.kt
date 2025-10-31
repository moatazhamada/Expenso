package dev.spikeysanju.expensetracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.spikeysanju.expenso.data.local.dao.AccountDao
import dev.spikeysanju.expenso.model.Account
import dev.spikeysanju.expensetracker.data.local.TransactionDao
import dev.spikeysanju.expensetracker.model.Transaction

@Database(
    entities = [Transaction::class, Account::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTransactionDao(): TransactionDao
    abstract fun getAccountDao(): AccountDao
}
