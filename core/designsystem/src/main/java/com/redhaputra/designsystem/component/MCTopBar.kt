package com.redhaputra.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.redhaputra.designsystem.theme.MCIcons
import com.redhaputra.designsystem.theme.MCTheme

/**
 * Movie Compose custom [TopAppBar]
 *
 * @param screenTitle The title to be displayed in the center of the TopAppBar
 * @param backgroundColor top bar background color, default to white
 * @param contentPadding top bar content padding, recommended to only set horizontal padding,
 * because default height of top bar is set.
 * Default to 8 (because icon touchable area is 8)
 * @param startActions The navigation icon displayed at the start of the TopAppBar
 * @param endActions The actions displayed at the end of the TopAppBar.
 */
@Composable
fun MCTopBar(
    screenTitle: String = "",
    backgroundColor: Color = MCTheme.primaryColors.dark,
    contentPadding: PaddingValues = PaddingValues(horizontal = 8.dp),
    /** TopBar Start Contents */
    startActions: @Composable RowScope.() -> Unit = {},
    /** TopBar End Contents */
    endActions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        backgroundColor = backgroundColor,
        elevation = 4.dp,
        contentPadding = contentPadding,
        modifier = Modifier.fillMaxWidth()
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (startIcons, title, endIcons) = createRefs()

            Row(
                modifier = Modifier.constrainAs(startIcons) {
                    linkTo(start = parent.start, end = title.start, bias = 0f)
                    centerVerticallyTo(parent)
                },
                horizontalArrangement = Arrangement.Start
            ) {
                startActions()
            }

            Text(
                text = screenTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MCTheme.typography.headingLargeM.copy(
                    color = MCTheme.primaryColors.neutral100
                ),
                modifier = Modifier.constrainAs(title) {
                    centerTo(parent)
                }
            )

            Row(
                modifier = Modifier.constrainAs(endIcons) {
                    linkTo(start = title.end, end = parent.end, bias = 1f)
                    centerVerticallyTo(parent)
                },
                horizontalArrangement = Arrangement.End
            ) {
                endActions()
            }
        }
    }
}

/**
 * Movie Compose reusable composable component for top bar that only have
 * back button and title
 */
@Composable
fun MCDefaultTopBarContent(
    title: String,
    onBackClick: () -> Unit
) {
    MCTopBar(
        screenTitle = title,
        startActions = {
            MCClickableIcon(
                icon = painterResource(id = MCIcons.icArrowLeft),
                iconColor = MCTheme.primaryColors.neutral700,
                clickAction = onBackClick
            )
        }
    )
}