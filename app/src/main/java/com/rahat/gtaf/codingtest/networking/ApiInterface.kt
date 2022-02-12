package com.rahat.gtaf.codingtest.networking

import com.haroldadmin.cnradapter.NetworkResponse
import com.rahat.gtaf.codingtest.models.CommitModel
import com.rahat.gtaf.codingtest.models.ErrorResponse
import com.rahat.gtaf.codingtest.models.UserModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("repos/{owner}/{repo}/commits")
    suspend fun getCommits(
        @Path("owner") OwnerName: String,
        @Path("repo") RepoName: String,
        @Query("per_page") PerPage: Int
    ): NetworkResponse<List<CommitModel>, ErrorResponse>

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username: String,
    ): NetworkResponse<UserModel, ErrorResponse>
}