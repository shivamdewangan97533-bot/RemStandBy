package com.remstandby

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

private val Night = Color(0xFF05060B)
private val Panel = Color(0xFF111521)
private val Muted = Color(0xFF8E98AC)
private val Blue = Color(0xFF83A8FF)
private val Violet = Color(0xFFB9A0FF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Night) {
                    StandByApp()
                }
            }
        }
    }
}

@Composable
private fun StandByApp() {
    val pager = rememberPagerState(pageCount = { 4 })
    HorizontalPager(
        state = pager,
        modifier = Modifier.fillMaxSize(),
        beyondViewportPageCount = 1
    ) { page ->
        when (page) {
            0 -> MainDashboard()
            1 -> FullClock()
            2 -> RemRoom()
            else -> WidgetDashboard()
        }
    }
}

@Composable
private fun MainDashboard() {
    var now by remember { mutableStateOf(LocalTime.now()) }
    LaunchedEffect(Unit) {
        while (true) {
            now = LocalTime.now()
            delay(1_000)
        }
    }
    val date = LocalDate.now()
    val timeText = now.format(DateTimeFormatter.ofPattern("HH:mm"))
    val dayText = date.format(DateTimeFormatter.ofPattern("EEEE, d MMMM"))

    Row(
        modifier = Modifier.fillMaxSize().padding(horizontal = 34.dp, vertical = 26.dp),
        horizontalArrangement = Arrangement.spacedBy(22.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1.15f), verticalArrangement = Arrangement.Center) {
            Text("GOOD EVENING", color = Muted, fontSize = 12.sp, letterSpacing = 3.sp)
            Spacer(Modifier.height(12.dp))
            Text(timeText, color = Color.White, fontSize = 88.sp, lineHeight = 92.sp, fontWeight = FontWeight.Light)
            Text(dayText, color = Muted, fontSize = 17.sp)
            Spacer(Modifier.height(28.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.BatteryFull, contentDescription = null, tint = Blue, modifier = Modifier.size(18.dp))
                Text("  STAND BY", color = Blue, fontSize = 11.sp, letterSpacing = 2.sp)
            }
        }
        Column(modifier = Modifier.weight(0.95f), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            CalendarCard(date)
            MusicCard()
        }
    }
}

@Composable
private fun CalendarCard(date: LocalDate) {
    Column(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp))
            .background(Panel).padding(18.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.CalendarMonth, tint = Violet, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text(date.format(DateTimeFormatter.ofPattern("MMMM yyyy")).uppercase(), color = Color.White, fontSize = 12.sp, letterSpacing = 1.5.sp)
        }
        Spacer(Modifier.height(14.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            listOf("M", "T", "W", "T", "F", "S", "S").forEach {
                Text(it, color = Muted, fontSize = 11.sp, modifier = Modifier.width(24.dp), textAlign = TextAlign.Center)
            }
        }
        Spacer(Modifier.height(8.dp))
        val first = date.withDayOfMonth(1)
        val offset = first.dayOfWeek.value - 1
        val days = date.lengthOfMonth()
        val cells = (0 until 42).map { index -> index - offset + 1 }
        cells.chunked(7).forEach { week ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                week.forEach { day ->
                    val valid = day in 1..days
                    val selected = day == date.dayOfMonth
                    Box(
                        modifier = Modifier.size(24.dp).clip(CircleShape)
                            .background(if (selected) Blue else Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        if (valid) Text(
                            day.toString(),
                            color = if (selected) Night else Color(0xFFD8DCE8),
                            fontSize = 10.sp,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
            Spacer(Modifier.height(5.dp))
        }
    }
}

@Composable
private fun MusicCard() {
    var playing by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp))
            .background(Panel).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.size(48.dp).clip(RoundedCornerShape(14.dp)).background(Color(0xFF242B42)), contentAlignment = Alignment.Center) {
            Icon(Icons.Default.MusicNote, tint = Violet, contentDescription = null, modifier = Modifier.size(25.dp))
        }
        Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
            Text("NOW PLAYING", color = Muted, fontSize = 9.sp, letterSpacing = 1.5.sp)
            Text("Your music", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Text("Connect a music app", color = Muted, fontSize = 11.sp)
        }
        IconButton(onClick = { playing = !playing }) {
            Icon(if (playing) Icons.Default.Pause else Icons.Default.PlayArrow, contentDescription = "Demo play control", tint = Blue)
        }
    }
}

@Composable
private fun FullClock() {
    var now by remember { mutableStateOf(LocalTime.now()) }
    LaunchedEffect(Unit) {
        while (true) { now = LocalTime.now(); delay(1_000) }
    }
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(now.format(DateTimeFormatter.ofPattern("HH:mm")), color = Color.White, fontSize = 112.sp, fontWeight = FontWeight.ExtraLight)
        Text(LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, d MMMM")), color = Muted, fontSize = 18.sp)
        Spacer(Modifier.height(22.dp))
        Text("Swipe to meet Rem", color = Violet, fontSize = 12.sp, letterSpacing = 2.sp)
    }
}

@Composable
private fun RemRoom() {
    Column(Modifier.fillMaxSize().padding(28.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        RemPlaceholder()
        Spacer(Modifier.height(24.dp))
        Text("Rem is resting", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Medium)
        Text("Her animated character sprites will be added next.", color = Muted, fontSize = 13.sp, textAlign = TextAlign.Center)
    }
}

@Composable
private fun RemPlaceholder() {
    // Temporary pixel-style placeholder; replace with the approved Rem sprite sheet.
    val pixels = listOf(
        listOf(0,0,1,1,1,1,0,0),
        listOf(0,1,2,2,2,2,1,0),
        listOf(1,2,3,3,3,3,2,1),
        listOf(1,2,3,4,4,3,2,1),
        listOf(0,1,2,2,2,2,1,0),
        listOf(0,0,1,5,5,1,0,0),
        listOf(0,1,1,0,0,1,1,0)
    )
    val colors = mapOf(1 to Blue, 2 to Color(0xFFDDE7FF), 3 to Color(0xFF75A9FF), 4 to Color(0xFF18294E), 5 to Violet)
    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        pixels.forEach { line ->
            Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                line.forEach { pixel ->
                    Box(Modifier.size(15.dp).background(colors[pixel] ?: Color.Transparent))
                }
            }
        }
    }
}

@Composable
private fun WidgetDashboard() {
    Column(Modifier.fillMaxSize().padding(30.dp), verticalArrangement = Arrangement.Center) {
        Text("YOUR NIGHT DESK", color = Muted, fontSize = 11.sp, letterSpacing = 3.sp)
        Spacer(Modifier.height(18.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            InfoTile("WEATHER", "—°", "Weather access coming soon", Modifier.weight(1f))
            InfoTile("BATTERY", "—%", "Live battery status coming soon", Modifier.weight(1f))
            InfoTile("REM", "♡", "Your tiny companion", Modifier.weight(1f))
        }
        Spacer(Modifier.height(18.dp))
        Text("Swipe left or right to explore", color = Violet, fontSize = 12.sp)
    }
}

@Composable
private fun InfoTile(title: String, value: String, subtitle: String, modifier: Modifier = Modifier) {
    Column(modifier.clip(RoundedCornerShape(22.dp)).background(Panel).padding(18.dp)) {
        Text(title, color = Muted, fontSize = 10.sp, letterSpacing = 1.5.sp)
        Spacer(Modifier.height(10.dp))
        Text(value, color = Color.White, fontSize = 30.sp)
        Text(subtitle, color = Muted, fontSize = 10.sp)
    }
}
