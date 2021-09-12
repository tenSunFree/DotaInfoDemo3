package com.example.dotainfodemo3.bill.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.ImageLoader
import coil.compose.rememberImagePainter
import com.example.dotainfodemo3.R
import com.example.dotainfodemo3.bill.model.BillEvent
import com.example.dotainfodemo3.bill.model.BillState
import com.example.dotainfodemo3.common.composable.QueueContent
import com.example.dotainfodemo3.hero_datasource.local.bill.BillPhotoBean

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun BillContent(
    state: BillState,
    onTriggerEvent: (BillEvent) -> Unit,
    imageLoader: ImageLoader,
) {
    QueueContent(
        queue = state.errorQueue,
        onRemoveHeadFromQueue = { onTriggerEvent(BillEvent.OnRemoveHeadFromQueue) },
        state = state.loading,
    ) {
        AnimatedVisibility(visible = state.photos.isNotEmpty()) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                val (topBar, content, bottomBar) = createRefs()
                Image(
                    modifier = Modifier.constrainAs(topBar) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }, painter = painterResource(id = R.drawable.icon_top_bar),
                    contentDescription = ""
                )
                LazyVerticalGrid(
                    modifier = Modifier.constrainAs(content) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(topBar.bottom)
                        bottom.linkTo(bottomBar.top)
                        height = Dimension.fillToConstraints
                    },
                    cells = GridCells.Fixed(2),
                    contentPadding = PaddingValues(start = 16.dp, top = 5.dp)
                ) {
                    items(state.photos) { bean ->
                        BillListItem(
                            bean = bean,
                            loader = imageLoader,
                        )
                    }
                }
                Image(
                    modifier = Modifier.constrainAs(bottomBar) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    }, painter = painterResource(id = R.drawable.icon_bottom_bar),
                    contentDescription = ""
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun BillListItem(
    bean: BillPhotoBean,
    loader: ImageLoader,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(end = 16.dp, bottom = 16.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(16.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val painter = rememberImagePainter(
                bean.url,
                imageLoader = loader,
                builder = {
                    placeholder(
                        if (isSystemInDarkTheme()) {
                            R.drawable.black_background
                        } else {
                            R.drawable.white_background
                        }
                    )
                }
            )
            Image(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                painter = painter,
                contentDescription = bean.title,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = bean.title,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(fontSize = 16.sp),
                textAlign = TextAlign.Center,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}