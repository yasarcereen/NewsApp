package com.example.newsapp.root.di

import com.example.newsapp.root.RootInteractor
import com.example.newsapp.root.RootRouter
import com.example.newsapp.root.RootView
import com.example.newsapp.root.units.article.builder.ArticleBuilder
import com.example.newsapp.root.units.newslist.NewsListRouter
import com.example.newsapp.root.units.newslist.builder.NewsListBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RootModule {
    @RootScope
    companion object {
        @Provides
        fun router(
            component: RootComponent,
            view: RootView,
            interactor: RootInteractor
        ): RootRouter {
            return RootRouter(view, interactor, component, NewsListBuilder(component), ArticleBuilder(component))
        }
    }

    @RootScope
    @Binds
    abstract fun presenter(view: RootView): RootInteractor.Presenter
}
