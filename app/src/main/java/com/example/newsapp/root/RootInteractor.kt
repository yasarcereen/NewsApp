package com.example.newsapp.root

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor

class RootInteractor : Interactor<RootInteractor.Presenter, RootRouter>() {

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        router.attachNewsList()
    }

    interface Presenter
}