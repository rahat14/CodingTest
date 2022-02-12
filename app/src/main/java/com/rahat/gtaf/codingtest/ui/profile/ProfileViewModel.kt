package com.rahat.gtaf.codingtest.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahat.gtaf.codingtest.models.ErrorResponse
import com.rahat.gtaf.codingtest.models.UserModel
import com.rahat.gtaf.codingtest.networking.ApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    repository: ProfileRepository, private val api: ApiInterface
) : ViewModel() {

    val repos = repository
    val profileDataResponse: MutableLiveData<Pair<UserModel?, ErrorResponse?>> = MutableLiveData()

    init {
        getProfile()
    }

    private fun getProfile() {

        CoroutineScope(Dispatchers.IO).launch {
            val reponse = repos.getProfile()
            withContext(Dispatchers.Main) {
                profileDataResponse.value = reponse
            }
        }
    }
}