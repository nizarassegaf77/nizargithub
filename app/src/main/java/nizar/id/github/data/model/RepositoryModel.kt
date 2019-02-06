package nizar.id.github.data.model


data class RepositoryModel(
        val id: Int? = 0, // 89257947
        val node_id: String? = "", // MDEwOlJlcG9zaXRvcnk4OTI1Nzk0Nw==
        val name: String? = "", // RecyclerViewFirebase
        val full_name: String? = "", // radhikayusuf/RecyclerViewFirebase
        val private: Boolean? = false, // false
        val owner: Owner? = Owner(),
        val html_url: String? = "", // https://github.com/radhikayusuf/RecyclerViewFirebase
        val description: String? = "", // Makes your models act as textiled
        val fork: Boolean? = false, // false
        val url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase
        val forks_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/forks
        val keys_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/keys{/key_id}
        val collaborators_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/collaborators{/collaborator}
        val teams_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/teams
        val hooks_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/hooks
        val issue_events_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/issues/events{/number}
        val events_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/events
        val assignees_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/assignees{/user}
        val branches_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/branches{/branch}
        val tags_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/tags
        val blobs_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/git/blobs{/sha}
        val git_tags_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/git/tags{/sha}
        val git_refs_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/git/refs{/sha}
        val trees_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/git/trees{/sha}
        val statuses_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/statuses/{sha}
        val languages_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/languages
        val stargazers_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/stargazers
        val contributors_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/contributors
        val subscribers_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/subscribers
        val subscription_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/subscription
        val commits_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/commits{/sha}
        val git_commits_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/git/commits{/sha}
        val comments_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/comments{/number}
        val issue_comment_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/issues/comments{/number}
        val contents_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/contents/{+path}
        val compare_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/compare/{base}...{head}
        val merges_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/merges
        val archive_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/{archive_format}{/ref}
        val downloads_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/downloads
        val issues_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/issues{/number}
        val pulls_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/pulls{/number}
        val milestones_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/milestones{/number}
        val notifications_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/notifications{?since,all,participating}
        val labels_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/labels{/name}
        val releases_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/releases{/id}
        val deployments_url: String? = "", // https://api.github.com/repos/radhikayusuf/RecyclerViewFirebase/deployments
        val created_at: String? = "", // 2017-04-24T15:38:14Z
        val updated_at: String? = "", // 2017-04-24T15:43:23Z
        val pushed_at: String? = "", // 2017-04-24T15:56:07Z
        val git_url: String? = "", // git://github.com/radhikayusuf/RecyclerViewFirebase.git
        val ssh_url: String? = "", // git@github.com:radhikayusuf/RecyclerViewFirebase.git
        val clone_url: String? = "", // https://github.com/radhikayusuf/RecyclerViewFirebase.git
        val svn_url: String? = "", // https://github.com/radhikayusuf/RecyclerViewFirebase
        val homepage: String? = "",
        val size: Int? = 0, // 129
        val stargazers_count: Int? = 0, // 0
        val watchers_count: Int? = 0, // 0
        val language: String? = "", // Java
        val has_issues: Boolean? = false, // true
        val has_projects: Boolean? = false, // true
        val has_downloads: Boolean? = false, // true
        val has_wiki: Boolean? = false, // true
        val has_pages: Boolean? = false, // false
        val forks_count: Int? = 0, // 0
        val mirror_url: Any? = Any(), // null
        val archived: Boolean? = false, // false
        val open_issues_count: Int? = 0, // 0
        val license: Any? = Any(), // null
        val forks: Int? = 0, // 0
        val open_issues: Int? = 0, // 0
        val watchers: Int? = 0, // 0
        val default_branch: String? = "" // master
) {

    data class Owner(
            val login: String? = "", // radhikayusuf
            val id: Int? = 0, // 19430472
            val node_id: String? = "", // MDQ6VXNlcjE5NDMwNDcy
            val avatar_url: String? = "", // https://avatars2.githubusercontent.com/u/19430472?v=4
            val gravatar_id: String? = "",
            val url: String? = "", // https://api.github.com/users/radhikayusuf
            val html_url: String? = "", // https://github.com/radhikayusuf
            val followers_url: String? = "", // https://api.github.com/users/radhikayusuf/followers
            val following_url: String? = "", // https://api.github.com/users/radhikayusuf/following{/other_user}
            val gists_url: String? = "", // https://api.github.com/users/radhikayusuf/gists{/gist_id}
            val starred_url: String? = "", // https://api.github.com/users/radhikayusuf/starred{/owner}{/repo}
            val subscriptions_url: String? = "", // https://api.github.com/users/radhikayusuf/subscriptions
            val organizations_url: String? = "", // https://api.github.com/users/radhikayusuf/orgs
            val repos_url: String? = "", // https://api.github.com/users/radhikayusuf/repos
            val events_url: String? = "", // https://api.github.com/users/radhikayusuf/events{/privacy}
            val received_events_url: String? = "", // https://api.github.com/users/radhikayusuf/received_events
            val type: String? = "", // User
            val site_admin: Boolean? = false // false
    )
}