package dev.spikeysanju.expensetracker.data.local.dao

import androidx.room.*
import dev.spikeysanju.expensetracker.model.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: Account)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAccount(account: Account)

    @Delete
    suspend fun deleteAccount(account: Account)

    @Query("SELECT * FROM account")
    fun getAllAccounts(): Flow<List<Account>>
}
