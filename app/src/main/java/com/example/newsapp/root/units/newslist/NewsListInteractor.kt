package com.example.newsapp.root.units.newslist

import android.util.Log
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.repository.Repository
import com.example.newsapp.root.BooleanState
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.coroutineScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsListInteractor :
    Interactor<NewsListInteractor.Presenter, NewsListRouter>() {

    @Inject
    lateinit var newsListEventFlowCollector: FlowCollector<@JvmSuppressWildcards NewsListEvents>

    @Inject
    lateinit var newsListBooleanFlowCollector: FlowCollector<@JvmSuppressWildcards BooleanState>

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        val repository = Repository()

        coroutineScope.launch {
            val articles = repository.getAllNews()
            articles.collect {
                injectedPresenter.articleList.emit(it)
            }
        }

        coroutineScope.launch {
            injectedPresenter.articleClicks.collect {
                newsListEventFlowCollector.emit(NewsListEvents.ArticleSelected(it))
            }
        }

        coroutineScope.launch {
            newsListBooleanFlowCollector.emit(BooleanState.CheckState)
        }
    }

    interface Presenter {
        var articleList: MutableStateFlow<List<Article>?>
        var articleClicks: MutableSharedFlow<Article>
    }
}