# VolvoVehicleFinder

A modern Android application that helps users browse and search for Volvo vehicles. Built with Jetpack Compose and following MVVM architecture, this app provides a seamless experience for exploring Volvo's vehicle lineup.

## Features

- 🚗 Real-time vehicle search and filtering
- 🌓 Dark/Light theme support
- 📱 Modern Material 3 Design
- 🖼️ High-quality vehicle images with smooth loading
- ⚡ Fast and responsive UI
- 🔄 Pull-to-refresh for latest updates
- 💾 Offline support with local caching

## Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose with Material 3
- **Architecture**: MVVM (Model-View-ViewModel)
- **Dependency Injection**: Hilt
- **Image Loading**: Coil
- **Local Storage**: Room Database
- **Async Operations**: Kotlin Coroutines & Flow
- **Testing**: JUnit, Compose UI Testing

## Setup

### Prerequisites
- Android Studio Hedgehog or later
- JDK 17
- Android SDK 21 or higher

### Installation

1. Clone the repository
```bash
git clone https://github.com/GebrecherkosAbrha/VolvoVehicleFinder.git
```

2. Open in Android Studio
```bash
cd VolvoVehicleFinder
```

3. Build the project
```bash
./gradlew build
```

4. Run the app using Android Studio's emulator or a physical device

## Development

### Building
```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease
```

### Testing
```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/volvovehiclefinder/
│   │   │   ├── data/           # Repository & Data Sources
│   │   │   ├── di/            # Dependency Injection
│   │   │   ├── model/         # Data Models
│   │   │   ├── ui/            # Compose UI Components
│   │   │   └── viewmodel/     # ViewModels
│   │   └── res/               # Resources
│   ├── test/                  # Unit Tests
│   └── androidTest/           # UI Tests

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/addedFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/addedFeature`)
5. Open a Pull Request

## Contact

[![GitHub](https://img.shields.io/badge/GitHub-Gebrecherkos_Abrha-blue?style=for-the-badge&logo=github)](https://github.com/GebrecherkosAbrha)
