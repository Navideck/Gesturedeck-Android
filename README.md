# Gesturedeck SDK - Android

## Overview

Revolutionize your Android app's user experience with the extraordinary power of Gesturedeck! Seamlessly integrated into your Android application, Gesturedeck empowers users to effortlessly control their devices through intuitive touch gestures, without even needing to look at the screen.

Imagine enhancing your app with the ability to adjust volume, skip tracks, and perform various actions effortlessly, making interactions smoother and more natural than ever before. Whether users are driving, biking, or engaged in any activity that demands their full attention, Gesturedeck ensures a seamless experience that enhances productivity and safety.

## Gesturedeck

Gesturedeck is the low-level API that allows you to build custom functionalities on top of Gesturedeck's gestures. Without introducing any additional UI, your app gains access to powerful gesture controls that redefine user interactions.

### Getting Started with Gesturedeck

To integrate Gesturedeck into your Android app, you have two options:

#### Option 1: Subclass GesturedeckActivity

You can make your `MainActivity` subclass `GesturedeckActivity` to gain access to Gesturedeck functionality.

```kotlin
class MainActivity : GesturedeckActivity()
```

#### Option 2: Manually feed touch events to Gesturedeck

If you prefer manual control, you can manually feed touch events to Gesturedeck. Follow these steps:

1. Initialize Gesturedeck manually by passing the activity or context in the constructor:

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var gesturedeck: Gesturedeck

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gesturedeck = Gesturedeck(
            this,
            panSensitivity = PanSensitivity.MEDIUM,
            tapAction = { /* Handle tap gesture here */ },
            swipeRightAction = { /* Handle swipe right gesture here */ },
            swipeLeftAction = { /* Handle swipe left gesture here */ },
            panAction = { /* Handle pan gesture here */ },
            longPressAction = { /* Handle long press gesture here */ }
        )
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        // Feed all touchEvents to Gesturedeck
        gesturedeck.onTouchEvent(event)
        return super.dispatchTouchEvent(event)
    }
}
```

Alternatively, you can add this tag in the Manifest file:

```xml
<application android:name="com.navideck.gesturedeck_android.globalActivity.GlobalApplication" />
```

and initialize without passing the activity:

```kotlin
gesturedeck = Gesturedeck(
    panSensitivity = PanSensitivity.MEDIUM,
    tapAction = { /* Handle tap gesture here */ },
    swipeRightAction = { /* Handle swipe right gesture here */ },
    swipeLeftAction = { /* Handle swipe left gesture here */ },
    panAction = { /* Handle pan gesture here */ },
    longPressAction = { /* Handle long press gesture here */ }
)
```

### Listen to Individual Gestures

To listen to individual gestures, you can use the properties `tapAction`, `swipeRightAction`, `swipeLeftAction`, `panAction`, and `longPressAction` in GesturedeckMedia's constructor.

```kotlin
gesturedeckMedia.tapAction = { /* Handle tap gesture here */ }
gesturedeckMedia.swipeRightAction = { /* Handle swipe right gesture here */ }
gesturedeckMedia.swipeLeftAction = { /* Handle swipe left gesture here */ }
gesturedeckMedia.panAction = { /* Handle pan gesture here */ }
gesturedeckMedia.longPressAction = { /* Handle long press gesture here */ }
```

For detailed API reference, visit [Gesturedeck API Reference](https://navideck.github.io/Gesturedeck-Android/gesturedeck-android/com.navideck.gesturedeck_android/-gesturedeck/index.html).

### Customize Sensitivity

To manage the sensitivity of the pan gesture, you can pass the `PanSensitivity` enum to the constructor. The default sensitivity level is `PanSensitivity.MEDIUM`.

```kotlin
gesturedeck = Gesturedeck(
    this,
    panSensitivity = PanSensitivity.MEDIUM,
    tapAction = { /* Handle tap gesture here */ },
    swipeRightAction = { /* Handle swipe right gesture here */ },
    swipeLeftAction = { /* Handle swipe left gesture here */ },
    panAction = { /* Handle pan gesture here */ },
    longPressAction = { /* Handle long press gesture here */ }
)
```

#### Start and Stop Gesturedeck

To start and stop Gesturedeck's gesture detection, you can call the `start()` and `stop()` methods respectively.

```kotlin
// Start gesture detection
gesturedeck.start()

// Stop gesture detection
gesturedeck.stop()
```

## GesturedeckMedia - Media Controls

GesturedeckMedia is a specialized implementation built on top of Gesturedeck, tailored specifically for media apps. It provides default gesture actions that can be customized to suit your app's requirements.

### Getting Started with GesturedeckMedia

To use GesturedeckMedia for showing media controls UI, follow these steps:

1. Initialize `GesturedeckMedia` with `GesturedeckMediaOverlay`:

```kotlin
val gesturedeckMedia = GesturedeckMedia(
    context = this,
    gesturedeckMediaOverlay = GesturedeckMediaOverlay(
        activity = this@MainActivity,
    ),
    tapAction = { /* Handle tap gesture here */ },
    swipeRightAction = { /* Handle swipe right gesture here */ },
    swipeLeftAction = { /* Handle swipe left gesture here */ },
    panAction = { /* Handle pan gesture here */ },
    longPressAction = { /* Handle long press gesture here */ }
)
```

#### Customize Overlay Background

By default, GesturedeckMedia will render UI elements with a blur background. If you want to customize the GesturedeckMedia overlay background, you can pass your root view (`YOUR_VIEW_GROUP`) in `GesturedeckMediaOverlay`. The SDK will only render the relevant UI elements (e.g., volume bar and icons) without any background color on top of your own view. It is generally a good idea to set a semi-transparent color as the background.

```kotlin
val gesturedeckMedia = GesturedeckMedia(
    context = this,
    gesturedeckMediaOverlay = Gesturedeck

MediaOverlay(
        activity = this@MainActivity,
        rootView = YOUR_VIEW_GROUP,
    ),
    tapAction = { /* Handle tap gesture here */ },
    swipeRightAction = { /* Handle swipe right gesture here */ },
    swipeLeftAction = { /* Handle swipe left gesture here */ },
    panAction = { /* Handle pan gesture here */ },
    longPressAction = { /* Handle long press gesture here */ }
)
```

### Handling Volume Key Action

To handle the device's volume key action with GesturedeckMedia, feed key events as well:

```kotlin
class MainActivity : AppCompatActivity() {

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        // To hide the device's native volume Dialog and show only Gesturedeck UI
        return gesturedeckMedia?.onKeyEvents(event) ?: false

        // To show both the device's native dialog and Gesturedeck UI
        // gesturedeck?.onKeyEvents(event)
        // return false
    }
}
```

For detailed API reference, visit [GesturedeckMedia API Reference](https://navideck.github.io/Gesturedeck-Android/gesturedeck-android/com.navideck.gesturedeck_android/-gesturedeck-media/index.html).

## UniversalVolume - Unified Volume Control

An Android library for easy volume control on different devices. Integrates smoothly with GesturedeckMedia for intuitive volume adjustments using pan gestures.

You can use [UniversalVolume](https://github.com/Navideck/Universal-Volume) with GesturedeckMedia. Simply import UniversalVolume, and GesturedeckMedia will automatically use UniversalVolume for changing volume on pan gestures.
