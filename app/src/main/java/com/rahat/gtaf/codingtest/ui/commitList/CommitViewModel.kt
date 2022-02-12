package com.rahat.gtaf.codingtest.ui.commitList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rahat.gtaf.codingtest.models.CommitModel
import com.rahat.gtaf.codingtest.models.ErrorResponse
import com.rahat.gtaf.codingtest.utils.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class CommitViewModel @Inject constructor(
    repository: CommitListRepository
) : ViewModel() {

    private val repos = repository

    fun getCommitList(
        ownerName: String,
        repoName: String,
        per_page: Int = Const.PER_PAGE // defaulting to 10 as only 10 commit needed to be seen
    ): LiveData<Pair<List<CommitModel>?, ErrorResponse?>> {

        return liveData(Dispatchers.Main) {
            val response = repos.getCommitList(ownerName, repoName, per_page)
            emit(response)
        }


    }

}