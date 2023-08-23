package com.example.newsapp.root

import android.util.Log
import com.example.newsapp.root.units.newslist.NewsListEvents
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RootInteractor : Interactor<RootInteractor.Presenter, RootRouter>() {

    @Inject
    lateinit var newsListEventsFlow: Flow<@JvmSuppressWildcards NewsListEvents>

    @Inject
    lateinit var newsListBoolean: Flow<@JvmSuppressWildcards BooleanState>

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        router.attachNewsList()

        coroutineScope.launch {
            newsListEventsFlow.collect{
                Log.d("deneme", "collecting $it.")
//                when (it) {
//                    is NewsListEvents.ArticleSelected -> {
//                        router.detachNewsList()
//                        router.attachArticle(it.article)
//                    }
//                }
            }
        }
        coroutineScope.launch {
            while (true){
                Log.d("deneme", "collecting")
                delay(2000)
            }
        }

        coroutineScope.launch {
            newsListBoolean.collect {
                Log.d("deneme", "Boolean Değer: $it")
            }
        }
    }

    interface Presenter
}