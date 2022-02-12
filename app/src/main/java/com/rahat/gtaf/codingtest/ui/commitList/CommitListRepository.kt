package com.rahat.gtaf.codingtest.ui.commitList

import com.haroldadmin.cnradapter.NetworkResponse
import com.rahat.gtaf.codingtest.models.CommitModel
import com.rahat.gtaf.codingtest.models.ErrorResponse
import com.rahat.gtaf.codingtest.networking.ApiInterface

class CommitListRepository(val api: ApiInterface) {


    suspend fun getCommitList(
        ownerName: String,
        repoName: String,
        per_page: Int
    ): Pair<List<CommitModel>?, ErrorResponse?> {

        return when (val response = api.getCommits(ownerName, repoName, per_page)) {
            is NetworkResponse.Success -> {
                // Handle successful response

                Pair(response.body, null)
            }

            is NetworkResponse.ServerError -> {
                // Handle server error
                Pair(null, ErrorResponse(400, "Server Error"))
            }
            is NetworkResponse.NetworkError -> {
                // Handle network error
                Pair(null, ErrorResponse(400, "Network Error"))
            }
            is NetworkResponse.UnknownError -> {
                // Handle other errors
                Pair(null, ErrorResponse(400, "Unknown Error"))
            }
        }

    }


}