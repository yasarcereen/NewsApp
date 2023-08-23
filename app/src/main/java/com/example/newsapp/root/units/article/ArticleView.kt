package com.example.newsapp.root.units.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.data.model.Article
import com.example.newsapp.utils.compose.rib.Compose
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class ArticleView: Compose, ArticleInteractor.Presenter {
    override val article = MutableStateFlow<Article?>(null)

    @Composable
    override fun Content(modifier: Modifier) {
        val viewedArticle by article.collectAsState()

        viewedArticle?.let {
            SingleArticleView(article = it)
        }
    }
    
    @Composable
    private fun SingleArticleView(article: Article) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)) {
            Text(text = article.title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineLarge)

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Author: ${article.author}",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Published at: ${article.publishedAt}",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = rememberAsyncImagePainter(article.urlToImage),
                contentDescription = null,
                modifier = Modifier.aspectRatio(16f / 9f),
                contentScale = ContentScale.FillHeight
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = article.description,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = article.content,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium)
        }
    }
}