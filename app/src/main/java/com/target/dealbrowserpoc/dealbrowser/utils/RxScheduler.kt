package com.target.dealbrowserpoc.dealbrowser.utils

import io.reactivex.Scheduler

interface RxScheduler {
    fun io(): Scheduler
    fun mainThread(): Scheduler
}