package com.example.newsapp.root.units.newslist

import com.example.newsapp.data.model.Article
import com.example.newsapp.data.repository.Repository
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewsListInteractor: Interactor<NewsListInteractor.Presenter, NewsListRouter>() {
    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        val repository = Repository()

        coroutineScope.launch {
            val articles = repository.getAllNews()
            articles.collect {
                injectedPresenter.articleList.emit(it)
            }
        }
    }

    interface Presenter {
        var articleList: MutableStateFlow<List<Article>?>
    }
}