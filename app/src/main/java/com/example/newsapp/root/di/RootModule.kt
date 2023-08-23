package com.example.newsapp.root.di

import com.example.newsapp.root.BooleanState
import com.example.newsapp.root.RootInteractor
import com.example.newsapp.root.RootRouter
import com.example.newsapp.root.RootView
import com.example.newsapp.root.units.article.builder.ArticleBuilder
import com.example.newsapp.root.units.newslist.NewsListEvents
import com.example.newsapp.root.units.newslist.NewsListRouter
import com.example.newsapp.root.units.newslist.builder.NewsListBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow

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
            return RootRouter(view,
                interactor,
                component,
                NewsListBuilder(component),
                ArticleBuilder(component)
            )
        }

        @Provides
        fun newsListEventsMutableFlow(): MutableSharedFlow<@JvmSuppressWildcards NewsListEvents> {
            return MutableSharedFlow()
        }

        @Provides
        fun newsListBooleanMutableFlow(): MutableSharedFlow<@JvmSuppressWildcards BooleanState> {
            return MutableSharedFlow()
        }
    }

    @RootScope
    @Binds
    abstract fun presenter(view: RootView): RootInteractor.Presenter

    @RootScope
    @Binds
    abstract fun newsListEventsAsFlow(
        mutableFlow: MutableSharedFlow<@JvmSuppressWildcards NewsListEvents>
    ): Flow<@JvmSuppressWildcards NewsListEvents>

    @RootScope
    @Binds
    abstract fun newsListEventsAsFlowCollector(
        mutableFlow: MutableSharedFlow<@JvmSuppressWildcards NewsListEvents>
    ): FlowCollector<@JvmSuppressWildcards NewsListEvents>

    @RootScope
    @Binds
    abstract fun newsListBooleanAsFlow(
        mutableFlow: MutableSharedFlow<@JvmSuppressWildcards BooleanState>
    ): Flow<@JvmSuppressWildcards BooleanState>

    @RootScope
    @Binds
    abstract fun newsListBooleanAsFlowCollector(
        mutableFlow: MutableSharedFlow<@JvmSuppressWildcards BooleanState>
    ): FlowCollector<@JvmSuppressWildcards BooleanState>
}
