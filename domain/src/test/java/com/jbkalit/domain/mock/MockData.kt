package com.jbkalit.domain.mock

import com.jbkalit.domain.model.*

val user = User(
    "jbkalit",
    18681626,
    "MDQ6VXNlcjE4NjgxNjI2",
    "https://avatars.githubusercontent.com/u/18681626?v=4",
    "",
    "https://api.github.com/users/jbkalit",
    "https://github.com/jbkalit",
    "https://api.github.com/users/jbkalit/followers",
    "https://api.github.com/users/jbkalit/following{/other_user}",
    "https://api.github.com/users/jbkalit/gists{/gist_id}",
    "https://api.github.com/users/jbkalit/starred{/owner}{/repo}",
    "https://api.github.com/users/jbkalit/subscriptions",
    "https://api.github.com/users/jbkalit/orgs",
    "https://api.github.com/users/jbkalit/repos",
    "https://api.github.com/users/jbkalit/events{/privacy}",
    "https://api.github.com/users/jbkalit/received_events",
    "User",
    false,
    1.0
)

val feed = Feed(
    1,
    false,
    listOf(user)
)
