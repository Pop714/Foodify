package net.pop.foodify.ui.composables.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import net.pop.foodify.model.loacl.CartEntity
import net.pop.foodify.ui.theme.Background
import net.pop.foodify.ui.theme.PrimaryTextColor

@Composable
fun CartScreenContent(
    uiState: CartUiState,
    removeCartItem: (CartEntity) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().background(Background)) {
        when (uiState) {
            is CartUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is CartUiState.Empty -> {
                Text(
                    text = "Your cart is empty.",
                    color = PrimaryTextColor,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is CartUiState.Error -> {
                Text(
                    text = uiState.message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is CartUiState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 48.dp, horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        Text(
                            text = "Shopping Cart",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryTextColor,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    items(
                        items = uiState.items,
                        key = { cartItem -> cartItem.id }
                    ) { item ->
                        CartItemRow(
                            item = item,
                            onCheckClick = { removeCartItem(item) },
                            modifier = Modifier.animateItem()
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun CartItemRow(
    modifier: Modifier = Modifier,
    item: CartEntity,
    onCheckClick: () -> Unit
) {
    val safeIngredientName = item.ingredient.replace(" ", "%20")
    val imageUrl = "https://www.themealdb.com/images/ingredients/$safeIngredientName-Small.png"

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = item.ingredient,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = item.ingredient.trim(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        item.measure.trim(), style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            IconButton(
                onClick = onCheckClick,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircleOutline,
                    contentDescription = "Mark as acquired",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}