import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CommonBottomNavigation(
    currentRoute: String,
    onNavigateToHome: () -> Unit,
    onNavigateToAttendance: () -> Unit,
    onNavigateToReports: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    NavigationBar(
        containerColor = Color(0xFF6200EE)
    ) {
        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = onNavigateToHome,
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White.copy(alpha = 0.7f)
            )
        )
        NavigationBarItem(
            selected = currentRoute == "attendance",
            onClick = onNavigateToAttendance,
            icon = { Icon(Icons.Default.CheckCircle, contentDescription = "Attendance") },
            label = { Text("Attendance") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White.copy(alpha = 0.7f)
            )
        )
        NavigationBarItem(
            selected = currentRoute == "reports",
            onClick = onNavigateToReports,
            icon = { Icon(Icons.Default.List, contentDescription = "Reports") },
            label = { Text("Reports") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White.copy(alpha = 0.7f)
            )
        )
        NavigationBarItem(
            selected = currentRoute == "settings",
            onClick = onNavigateToSettings,
            icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White.copy(alpha = 0.7f)
            )
        )
    }
}