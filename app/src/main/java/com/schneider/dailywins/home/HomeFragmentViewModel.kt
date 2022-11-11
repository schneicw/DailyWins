package com.schneider.dailywins.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthResult
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

    private val _winList : MutableLiveData<MutableList<DailyWin>?> = MutableLiveData()
    val winList: MutableLiveData<MutableList<DailyWin>?>
        get() = _winList

    fun getAllUserWins() {
        val authState = applicationStore.applicationState.value as ApplicationState.AuthenticatedState
        db.collection("wins").document(authState.user?.uid ?:
            throw RuntimeException("Error Retrieving User Wins")
        ).collection("dailyWins").get().addOnSuccessListener { wins ->
            var tempList = mutableListOf<DailyWin>()
            for (win in wins) {
                val win = win.toObject(DailyWin::class.java)
                tempList.add(win)
                println("WINLIST " )
                println("USER VAL WINS: ${tempList[0].winList}")
            }
            _winList.postValue(tempList)
        }
    }

    fun deleteUserWin() {
    }
}