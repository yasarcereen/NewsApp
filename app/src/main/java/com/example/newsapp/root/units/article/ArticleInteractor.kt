package com.example.newsapp.root.units.article

import com.example.newsapp.data.model.Article
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ArticleInteractor(private val selectedArticle: Article): Interactor<ArticleInteractor.Presenter, ArticleRouter>() {
    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        coroutineScope.launch {
            injectedPresenter.article.emit(selectedArticle)
        }
    }

    interface Presenter {
        val article: MutableStateFlow<Article?>
    }
}