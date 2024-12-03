package com.frenkel.stockf.features.stock_details.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.frenkel.stockf.utils.toFormattedDateString
import com.frenkel.ui_kit.ui.theme.Success600
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLine
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.fill
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianLayerRangeProvider
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.patrykandpatrick.vico.core.cartesian.layer.LineCartesianLayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date

@Composable
fun Chart(
    data: Map<Date, Double>,
    modifier: Modifier = Modifier
) {
    val modelProducer = remember { CartesianChartModelProducer() }
    LaunchedEffect(data) {
        withContext(Dispatchers.Default) {
            modelProducer.runTransaction {
                lineSeries { series(
                    y = data.values
                ) }
            }
        }
    }

    CartesianChartHost(
        modifier = modifier,
        chart = rememberCartesianChart(
            rememberLineCartesianLayer(
                lineProvider = LineCartesianLayer.LineProvider.series(
                    LineCartesianLayer.rememberLine(
                        fill = remember { LineCartesianLayer.LineFill.single(fill(Success600)) },
                        areaFill = null,
                    )
                ),
                rangeProvider = CartesianLayerRangeProvider.fixed(
                    minY = data.values.min(),
                    maxY = data.values.max(),
                ),
            ),
            startAxis = VerticalAxis.rememberStart(
                line = null,
                tick = null,
                itemPlacer = VerticalAxis.ItemPlacer.count(count = { 5 }),
            ),
            bottomAxis = HorizontalAxis.rememberBottom(
                line = null,
                guideline = null,
                tick = null,
                valueFormatter = { _, x, _ ->
                    data.keys.elementAt(x.toInt()).toFormattedDateString()
                },
            ),
        ),
        modelProducer = modelProducer,
    )
}