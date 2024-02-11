package com.toadfrogson.clownhub.data.model.exception

sealed class ClownException: Exception() {
    data object NoContentFoundInCache: ClownException()
    data object NoContentFetchedFromNetwork: ClownException()
}
