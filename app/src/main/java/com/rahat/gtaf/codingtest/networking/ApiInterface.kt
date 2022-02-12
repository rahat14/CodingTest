package com.rahat.gtaf.codingtest.networking

import com.rahat.gtaf.codingtest.models.CommitModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("repos/{owner}/{repo}/commits")
    suspend fun getAllNews(
        @Path("owner") OwnerName: String,
        @Path("repo") RepoName: String,
    ): Response<List<CommitModel>>


}