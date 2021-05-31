package com.jbkalit.domain.model

import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("total_count") var totalCount: Int? = null,
    @SerializedName("incomplete_results") var incompleteResults: Boolean? = null,
    @SerializedName("items") var items: List<User>? = null
)
