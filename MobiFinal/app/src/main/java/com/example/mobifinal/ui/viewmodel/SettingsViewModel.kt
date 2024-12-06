import androidx.lifecycle.ViewModel
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {
    private val _darkMode = MutableStateFlow(false)
    val darkMode: StateFlow<Boolean> = _darkMode

    private val _notifications = MutableStateFlow(true)
val notifications: StateFlow<Boolean> = _notifications

private val _fontSize = MutableStateFlow(16f)
val fontSize: StateFlow<Float> = _fontSize

private val _brightness = MutableStateFlow(0.5f)
val brightness: StateFlow<Float> = _brightness

init {
    loadSettings()
}

private fun loadSettings() {
    _darkMode.value = sharedPreferences.getBoolean("dark_mode", false)
    _notifications.value = sharedPreferences.getBoolean("notifications", true)
    _fontSize.value = sharedPreferences.getFloat("font_size", 16f)
    _brightness.value = sharedPreferences.getFloat("brightness", 0.5f)
}

fun saveSettings() {
    sharedPreferences.edit().apply {
        putBoolean("dark_mode", _darkMode.value)
        putBoolean("notifications", _notifications.value)
        putFloat("font_size", _fontSize.value)
        putFloat("brightness", _brightness.value)
        apply()
    }
}

fun updateDarkMode(enabled: Boolean) {
    _darkMode.value = enabled
}

fun updateNotifications(enabled: Boolean) {
    _notifications.value = enabled
}

fun updateFontSize(size: Float) {
    _fontSize.value = size
}

fun updateBrightness(value: Float) {
    _brightness.value = value
}
}
