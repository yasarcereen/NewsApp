package com.example.newsapp.root.units.newslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.newsapp.data.model.Article
import com.example.newsapp.utils.compose.rib.Compose
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.flow.MutableStateFlow

class NewsListView: Compose, NewsListInteractor.Presenter {
    override var articleList = MutableStateFlow<List<Article>?>(null)

    @Composable
    override fun Content(modifier: Modifier) {
        val articleList by articleList.collectAsState()

        articleList?.let { News(it) }
    }

    @Composable
    private fun SingleArticle(article: Article) {
        Column(modifier = Modifier.clip(shape = RoundedCornerShape(10.dp))
            .border(width = 2.dp, color = Color.LightGray)
            .padding(16.dp)
            .fillMaxWidth()) {
            Image(
                painter = rememberAsyncImagePainter(article.urlToImage),
                contentDescription = null,
                modifier = Modifier.aspectRatio(16f / 9f),
                contentScale = ContentScale.FillHeight
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = article.title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineLarge)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = article.description,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium)
        }
    }

    @Composable
    private fun News(news: List<Article>) {
        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(news) {article ->
                SingleArticle(article = article)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}