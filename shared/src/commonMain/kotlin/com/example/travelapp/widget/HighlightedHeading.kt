package com.example.travelapp.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HighlightedHeading(
    normalText: String,
    boldText: String,
    highlightedText: String,
    modifier: Modifier = Modifier,
    normalTextColor: Color = Color(0xFF2C3240),
    boldTextColor: Color = Color(0xFF1E232E),
    highlightColor: Color = Color(0xFFFF6D22),
    fontSize: TextUnit = 38.sp,
    lineHeight: TextUnit = 44.sp,
    curvePeakHeight: Dp = 15.dp,
    curveThickness: Dp = 7.dp,
    verticalGap: Dp = 4.dp
) {
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    // Build the styled string and tag the highlighted portion
    val annotatedString = remember(normalText, boldText, highlightedText, normalTextColor, boldTextColor, highlightColor) {
        buildAnnotatedString {
            withStyle(
                SpanStyle(
                    color = normalTextColor,
                    fontWeight = FontWeight.Normal
                )
            ) {
                append("$normalText\n")
            }

            withStyle(
                SpanStyle(
                    color = boldTextColor,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("$boldText ")
            }

            pushStringAnnotation(tag = "HIGHLIGHTED", annotation = highlightedText)
            withStyle(
                SpanStyle(
                    color = highlightColor,
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(highlightedText)
            }
            pop()
        }
    }

    Box(modifier = modifier.padding(bottom = curvePeakHeight + curveThickness)) {
        Text(
            text = annotatedString,
            fontSize = fontSize,
            lineHeight = lineHeight,
            onTextLayout = { textLayoutResult = it }
        )

        Canvas(modifier = Modifier.matchParentSize()) {
            val layout = textLayoutResult ?: return@Canvas

            // Locate the bounding box of the highlighted segment
            val annotations = annotatedString.getStringAnnotations(
                tag = "HIGHLIGHTED",
                start = 0,
                end = annotatedString.length
            )

            annotations.firstOrNull()?.let { annotation ->
                val startOffset = annotation.start
                val endOffset = annotation.end

                val startBox = layout.getBoundingBox(startOffset)
                val endBox = layout.getBoundingBox(endOffset - 1)

                // Precise bounds of the target word
                val startX = startBox.left
                val endX = endBox.right
                val baseY = endBox.bottom + verticalGap.toPx()

                val peak = curvePeakHeight.toPx()
                val thickness = curveThickness.toPx()
                val midX = (startX + endX) / 2f

                val crescentPath = Path().apply {
                    // Start at left tip
                    moveTo(startX, baseY)

                    // Upper arc bending upward toward the text
                    quadraticTo(
                        midX, baseY - peak - (thickness / 2f),
                        endX, baseY
                    )

                    // Lower arc returning to the left to form the crescent belly
                    quadraticTo(
                        midX, baseY - peak + (thickness / 2f),
                        startX, baseY
                    )
                    close()
                }

                drawPath(
                    path = crescentPath,
                    color = highlightColor,
                    style = Fill
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun HighlightedHeadingPrev(){
    HighlightedHeading(
        normalText = "Explore the",
        boldText = "Beautiful",
        highlightedText = "world!",
    )
}