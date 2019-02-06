package nizar.id.github.data.model


data class UserModel(
        val login: String? = "", // mojombo
        val id: Int? = 0, // 1
        val node_id: String? = "", // MDQ6VXNlcjE=
        val avatar_url: String? = "", // https://avatars0.githubusercontent.com/u/1?v=4
        val gravatar_id: String? = "",
        val url: String? = "", // https://api.github.com/users/mojombo
        val html_url: String? = "", // https://github.com/mojombo
        val followers_url: String? = "", // https://api.github.com/users/mojombo/followers
        val following_url: String? = "", // https://api.github.com/users/mojombo/following{/other_user}
        val gists_url: String? = "", // https://api.github.com/users/mojombo/gists{/gist_id}
        val starred_url: String? = "", // https://api.github.com/users/mojombo/starred{/owner}{/repo}
        val subscriptions_url: String? = "", // https://api.github.com/users/mojombo/subscriptions
        val organizations_url: String? = "", // https://api.github.com/users/mojombo/orgs
        val repos_url: String? = "", // https://api.github.com/users/mojombo/repos
        val events_url: String? = "", // https://api.github.com/users/mojombo/events{/privacy}
        val received_events_url: String? = "", // https://api.github.com/users/mojombo/received_events
        val type: String? = "", // User
        val site_admin: Boolean? = false, // false
        val name: String? = "", // Tom Preston-Werner
        val company: Any? = Any(), // null
        val blog: String? = "", // http://tom.preston-werner.com
        val location: String? = "", // San Francisco
        val email: Any? = Any(), // null
        val hireable: Any? = Any(), // null
        val bio: Any? = Any(), // null
        val public_repos: Int? = 0, // 61
        val public_gists: Int? = 0, // 62
        val followers: Int? = 0, // 21337
        val following: Int? = 0, // 11
        val created_at: String? = "", // 2007-10-20T05:24:19Z
        val updated_at: String? = "" // 2018-12-18T19:52:12Z
)