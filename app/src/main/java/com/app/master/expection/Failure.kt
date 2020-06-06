package com.app.master.expection

sealed class Failure {

    object NetWorkError : Failure()
    object ServerError : Failure()
    object TokenError : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}