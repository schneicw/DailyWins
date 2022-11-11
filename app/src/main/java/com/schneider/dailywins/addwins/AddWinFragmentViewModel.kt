package com.schneider.dailywins.addwins

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.schneider.dailywins.data.DailyWin
import com.schneider.dailywins.data.state.ApplicationState
import com.schneider.dailywins.data.store.ApplicationStore
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

class AddWinFragmentViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val applicationStore: ApplicationStore
) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun addWin(wins: List<String>, date: String = "null") {
        println("AddWinFragment: addWinFunction")
        val dattime = LocalDate.now()
        val date = Date.from(dattime.atStartOfDay(ZoneId.systemDefault()).toInstant())
        val win = DailyWin(winList = wins, date = date)
        val authState = applicationStore.applicationState.value as ApplicationState.AuthenticatedState
        db.collection("wins").document(authState.user?.uid ?:
        throw RuntimeException("Error Retrieving User Wins")
        ).collection("dailyWins").add(win).addOnSuccessListener {
            println("wins: $win")
        }
//
//        get().addOnSuccessListener { wins ->
////            var tempList = mutableListOf<DailyWin>()
////            for (win in wins) {
////                val win = win.toObject(DailyWin::class.java)
////                tempList.add(win)
////                println("WINLIST " )
////                println("USER VAL WINS: ${tempList[0].winList}")
////            }
////            _winList.postValue(tempList)
////        }
    }
}