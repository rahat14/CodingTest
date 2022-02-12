package com.rahat.gtaf.codingtest.ui.profile

import com.haroldadmin.cnradapter.NetworkResponse
import com.rahat.gtaf.codingtest.models.ErrorResponse
import com.rahat.gtaf.codingtest.models.UserModel
import com.rahat.gtaf.codingtest.networking.ApiInterface
import com.rahat.gtaf.codingtest.utils.Const

class ProfileRepository(val api: ApiInterface) {

    suspend fun getProfile(user: String = Const.PROFILE_USERNAME): Pair<UserModel?, ErrorResponse?> {

        return when (val response = api.getUser(user)) {
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