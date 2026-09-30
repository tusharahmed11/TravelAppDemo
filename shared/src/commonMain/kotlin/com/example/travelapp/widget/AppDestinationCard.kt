package com.example.travelapp.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppDestinationCard(
    title: String,
    location: String,
    rating: Double,
    modifier: Modifier = Modifier,
    cardWidth: Dp = 260.dp,
    cardHeight: Dp = 380.dp,
    imageHeight: Dp = 280.dp,
    initialBookmarked: Boolean = false,
    gradientColors: List<Color> = listOf(Color(0xFF56CCF2), Color(0xFF2F80ED), Color(0xFFF2994A)),
    onCardClick: () -> Unit = {},
    onBookmarkClick: (Boolean) -> Unit = {}
) {
    var isBookmarked by remember { mutableStateOf(initialBookmarked) }

    Card(
        modifier = modifier
            .width(cardWidth)
            .height(cardHeight)
            .clickable(onClick = onCardClick),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
        ) {
            // Illustrated image box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                // Vector landscape illustration placeholder
                DestinationArtPlaceholder(
                    colors = gradientColors,
                    modifier = Modifier.fillMaxSize()
                )

                // Bookmark icon button on top-right with translucent circular background
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(14.dp)
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.25f))
                        .clickable {
                            isBookmarked = !isBookmarked
                            onBookmarkClick(isBookmarked)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Title & Rating row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B1E28),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFB800),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = rating.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1B1E28)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Location & Friends Stack row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(1f, fill = false)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFF7D848D),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = location,
                        fontSize = 13.sp,
                        color = Color(0xFF7D848D),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                AppAvatarStack(extraCount = 50)
            }
        }
    }
}

@Composable
fun DestinationArtPlaceholder(
    colors: List<Color>,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height

        // Sky background gradient
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(colors[0], colors.getOrElse(1) { colors[0] })
            )
        )

        // Sun / light circle
        drawCircle(
            color = Color.White.copy(alpha = 0.35f),
            radius = width * 0.28f,
            center = Offset(width * 0.75f, height * 0.32f)
        )

        // Distant Mountains / Hills
        val mountainPath = Path().apply {
            moveTo(0f, height * 0.65f)
            quadraticTo(width * 0.25f, height * 0.42f, width * 0.55f, height * 0.62f)
            quadraticTo(width * 0.8f, height * 0.48f, width, height * 0.68f)
            lineTo(width, height)
            lineTo(0f, height)
            close()
        }
        drawPath(mountainPath, color = Color(0xFF4A708B))

        // Architectural block / building
        val buildingPath = Path().apply {
            moveTo(width * 0.12f, height * 0.52f)
            lineTo(width * 0.72f, height * 0.48f)
            lineTo(width * 0.76f, height * 0.7f)
            lineTo(width * 0.12f, height * 0.72f)
            close()
        }
        drawPath(buildingPath, color = Color(0xFF90708C))

        // Foreground rocky terrain
        val rockPath = Path().apply {
            moveTo(0f, height * 0.7f)
            quadraticTo(width * 0.3f, height * 0.64f, width * 0.6f, height * 0.74f)
            quadraticTo(width * 0.85f, height * 0.68f, width, height * 0.78f)
            lineTo(width, height)
            lineTo(0f, height)
            close()
        }
        drawPath(rockPath, color = colors.getOrElse(2) { Color(0xFFE8B298) })

        // Rocky blobs in foreground
        drawOval(
            color = Color(0xFFDDA28A),
            topLeft = Offset(width * 0.05f, height * 0.82f),
            size = androidx.compose.ui.geometry.Size(width * 0.45f, height * 0.22f)
        )
        drawOval(
            color = Color(0xFFC78C74),
            topLeft = Offset(width * 0.4f, height * 0.8f),
            size = androidx.compose.ui.geometry.Size(width * 0.55f, height * 0.24f)
        )
    }
}
