plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}
android {
    namespace = "com.scanmaster.app"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.scanmaster.app"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "0.1-stage-b"
    }
    buildTypes { release { isMinifyEnabled = false } }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
    kotlinOptions { jvmTarget = "17" }
    buildFeatures { compose = true }
}
dependencies {
    implementation(project(":engine"))
    implementation(platform("androidx.compose:compose-bom:2024.12.01"))
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.foundation:foundation")
}

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application android:allowBackup="false" android:label="Scan Master V1" android:supportsRtl="true" android:theme="@android:style/Theme.Material.Light.NoActionBar">
        <activity android:name=".MainActivity" android:exported="true">
            <intent-filter><action android:name="android.intent.action.MAIN"/><category android:name="android.intent.category.LAUNCHER"/></intent-filter>
        </activity>
    </application>
</manifest>

package com.scanmaster.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MaterialTheme { ScanMasterScreen() } }
    }
}

private val headers = listOf("STK", "DIR", "Opn", "5M", "VM", "IB", "MP", "H", "TRIGGER", "ADX")

@Composable
fun ScanMasterScreen() {
    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(16.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Scan Master V1", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Stage B engine • Android starter", style = MaterialTheme.typography.titleSmall)
        Text("DATA STATUS: NOT CONNECTED", color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
        Text("No live market feed is connected. No stock prices, signals or alerts are shown until a verified data adapter is added.")
        HorizontalDivider()
        Text("Recent Scans", style = MaterialTheme.typography.titleLarge)
        Text("No scans recorded")
        HorizontalDivider()
        Text("Live Scan Results", style = MaterialTheme.typography.titleLarge)
        Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(18.dp)) {
            headers.forEach { Text(it, fontWeight = FontWeight.Bold) }
        }
        Text("Connect a permitted read-only market data provider to enable real results.")
        Text("Bias+ / Bias− withheld from the screen pending TradingView validation.", style = MaterialTheme.typography.bodySmall)
        HorizontalDivider()
        Text("Today's Trigger & Scan Event Log", style = MaterialTheme.typography.titleLarge)
        Text("No confirmed events")
        Text("This is a genuine installable starter, not the finished scanner. It makes no trading decisions and does not place orders.", style = MaterialTheme.typography.bodySmall)
    }
}
