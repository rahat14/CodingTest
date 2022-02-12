package com.rahat.gtaf.codingtest.models

data class CommitModel(
    val author: Author?,
    val comments_url: String?,
    val commit: Commit?,
    val committer: Committer?,
    val html_url: String?,
    val node_id: String?,
    val parents: List<Parent?>?,
    val sha: String?,
    val url: String?
) {
    data class Author(
        val avatar_url: String?,
        val events_url: String?,
        val followers_url: String?,
        val following_url: String?,
        val gists_url: String?,
        val gravatar_id: String?,
        val html_url: String?,
        val id: Int?,
        val login: String?,
        val node_id: String?,
        val organizations_url: String?,
        val received_events_url: String?,
        val repos_url: String?,
        val site_admin: Boolean?,
        val starred_url: String?,
        val subscriptions_url: String?,
        val type: String?,
        val url: String?
    )

    data class Commit(
        val author: Author?,
        val comment_count: Int?,
        val committer: Committer?,
        val message: String?,
        val tree: Tree?,
        val url: String?,
        val verification: Verification?
    ) {
        data class Author(
            val date: String?,
            val email: String?,
            val name: String?
        )

        data class Committer(
            val date: String?,
            val email: String?,
            val name: String?
        )

        data class Tree(
            val sha: String?,
            val url: String?
        )

        data class Verification(
            val payload: String?,
            val reason: String?,
            val signature: String?,
            val verified: Boolean?
        )
    }

    data class Committer(
        val avatar_url: String?,
        val events_url: String?,
        val followers_url: String?,
        val following_url: String?,
        val gists_url: String?,
        val gravatar_id: String?,
        val html_url: String?,
        val id: Int?,
        val login: String?,
        val node_id: String?,
        val organizations_url: String?,
        val received_events_url: String?,
        val repos_url: String?,
        val site_admin: Boolean?,
        val starred_url: String?,
        val subscriptions_url: String?,
        val type: String?,
        val url: String?
    )

    data class Parent(
        val html_url: String?,
        val sha: String?,
        val url: String?
    )
}