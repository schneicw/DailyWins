package com.schneider.dailywins.home

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.schneider.dailywins.data.DailyWin
import com.schneider.dailywins.data.state.ApplicationState
import com.schneider.dailywins.data.store.ApplicationStore
import java.lang.RuntimeException
import javax.inject.Inject

class HomeFragmentViewModel  @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val applicationStore: ApplicationStore
    ) : ViewModel() {

    private var winList = mutableListOf<DailyWin>()

    fun getAllUserWins() {
        val authState = applicationStore.applicationState.value as ApplicationState.AuthenticatedState
        db.collection("wins").document(authState.user?.uid ?:
            throw RuntimeException("Error Retrieving User Wins")
        ).collection("dailyWins").get().addOnSuccessListener { wins ->
            for (win in wins) {
                val win = win.toObject(DailyWin::class.java)
                winList.add(win)
                println("USER VAL WINS: ${win.winList?.size}")
            }
        }
    }

    fun deleteUserWin() {
    }
}