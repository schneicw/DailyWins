package com.schneider.dailywins.addwins

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.schneider.dailywins.data.DailyWin
import com.schneider.dailywins.data.RandomPhoto
import com.schneider.dailywins.data.state.ApplicationState
import com.schneider.dailywins.data.store.ApplicationStore
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

class AddWinFragmentViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val applicationStore: ApplicationStore,
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private val _randomPhoto : MutableLiveData<RandomPhoto> = MutableLiveData()
    val randomPhoto: MutableLiveData<RandomPhoto>
        get() = _randomPhoto

    @RequiresApi(Build.VERSION_CODES.O)
    fun addWin(wins: List<String>, photoId: String = "null") {
        println("AddWinFragment: addWinFunction")
        val dattime = LocalDate.now()
        val date = Date.from(dattime.atStartOfDay(ZoneId.systemDefault()).toInstant())
        val newUUID = UUID.randomUUID().toString()
        val win = DailyWin(winId = newUUID, winList = wins, date = date, photoId = photoId)
        val authState = applicationStore.applicationState.value as ApplicationState.AuthenticatedState
        db.collection("wins").document(authState.user?.uid ?:
        throw RuntimeException("Error Retrieving User Wins")
        ).collection("dailyWins").document(win.winId).set(win).addOnSuccessListener {
            println("wins: $win")
        }
    }

    fun getRandomPhoto() {
        viewModelScope.launch {
            _randomPhoto.postValue(photoRepository.getRandomPhoto())
        }
    }

}