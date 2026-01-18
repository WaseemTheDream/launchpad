# Mobile Interaction Skill

This skill provides best practices for interacting with Android apps via ADB during development and testing.

## Key Principles

1. **Never guess coordinates** - Always use UI inspection to find element bounds
2. **Account for variable screen sizes** - Different emulators have different resolutions
3. **Use semantic element finding** - Find by text/description, not position
4. **Allow UI transitions** - Add delays after taps for animations

## Screen Resolution

Before interacting, get the device's actual screen dimensions:

```bash
adb shell wm size
# Example output: Physical size: 1280x2856
```

The screenshot resolution matches this physical size.

## Finding UI Elements

### Method 1: UI Automator Dump (Recommended)

Dump the UI hierarchy to find element bounds:

```bash
adb shell "uiautomator dump /sdcard/window_dump.xml && cat /sdcard/window_dump.xml" 2>/dev/null
```

Parse the XML to find elements by:
- **text** - The visible text label
- **content-desc** - Accessibility description
- **class** - Element type (e.g., android.widget.Button)
- **bounds** - The coordinates `[left,top][right,bottom]`

### Method 2: Find Specific Elements

Search for specific text:

```bash
adb shell "uiautomator dump /sdcard/window_dump.xml && cat /sdcard/window_dump.xml" 2>/dev/null | grep -o 'text="YourText"[^>]*bounds="[^"]*"'
```

Search for clickable elements:

```bash
adb shell "uiautomator dump /sdcard/window_dump.xml && cat /sdcard/window_dump.xml" 2>/dev/null | grep -o 'clickable="true"[^>]*bounds="[^"]*"'
```

## Calculating Tap Coordinates

Given bounds `[left,top][right,bottom]`, calculate the center:

```
x = (left + right) / 2
y = (top + bottom) / 2
```

Example: For bounds `[521,2544][758,2784]`:
- x = (521 + 758) / 2 = 639
- y = (2544 + 2784) / 2 = 2664

## Performing Actions

### Tap

```bash
adb shell input tap <x> <y>
```

### Type Text

```bash
adb shell input text "your text here"
```

Note: Spaces must be encoded as `%s` or use quotes properly.

### Swipe

```bash
adb shell input swipe <x1> <y1> <x2> <y2> <duration_ms>
```

Example scroll down:
```bash
adb shell input swipe 640 2000 640 500 300
```

### Press Keys

```bash
adb shell input keyevent KEYCODE_BACK
adb shell input keyevent KEYCODE_HOME
adb shell input keyevent KEYCODE_ENTER
```

## Taking Screenshots

```bash
adb exec-out screencap -p > screenshot.png
```

Save to a temporary location first, then move to final destination if the screenshot looks correct.

## Waiting for UI

After any action that causes UI changes:

```bash
sleep 1 && adb exec-out screencap -p > screenshot.png
```

For longer transitions (app launch, navigation):
```bash
sleep 2
```

## Common Patterns

### Navigate to Tab in Bottom Navigation

1. Dump UI hierarchy
2. Find tab by text (e.g., "Commands", "Settings")
3. Get bounds from the search result
4. Calculate center and tap

```bash
# Find the Commands tab
adb shell "uiautomator dump /sdcard/window_dump.xml && cat /sdcard/window_dump.xml" 2>/dev/null | grep -o 'text="Commands"[^>]*bounds="[^"]*"'

# Result: text="Commands" ... bounds="[521,2544][758,2784]"
# Center: (639, 2664)

adb shell input tap 639 2664
```

### Tap a Button by Text

```bash
# Find the button
adb shell "uiautomator dump /sdcard/window_dump.xml && cat /sdcard/window_dump.xml" 2>/dev/null | grep -o 'text="View Details"[^>]*bounds="[^"]*"'

# Extract bounds and calculate center, then tap
```

### Expand an Accordion/Card

Look for elements with content-desc="Expand" or clickable cards containing the target text.

### Scroll to Find Element

If element not found, scroll and retry:

```bash
# Scroll down
adb shell input swipe 640 2000 640 1000 300
sleep 0.5

# Dump UI again and search
adb shell "uiautomator dump /sdcard/window_dump.xml && cat /sdcard/window_dump.xml" 2>/dev/null | grep "TargetElement"
```

## Troubleshooting

### Tap Not Working

1. Verify coordinates are within screen bounds
2. Check if element is actually clickable (`clickable="true"`)
3. Ensure no overlay is blocking (dialog, loading spinner)
4. Element may be in a different layer - check parent bounds

### Element Not Found in Dump

1. UI may still be loading - add sleep before dump
2. Element may be off-screen - scroll first
3. Element may be in a different activity - verify current screen

### Screenshot Shows Different Content

Screenshots are taken at native resolution. If your viewer scales the image, coordinates in the image won't match actual device coordinates. Always use `adb shell wm size` for the real dimensions.

## Best Practices Checklist

- [ ] Get screen size before calculating coordinates
- [ ] Use uiautomator dump to find elements
- [ ] Calculate tap coordinates from bounds, don't guess
- [ ] Add sleep after actions that cause UI transitions
- [ ] Verify actions with screenshots
- [ ] Handle scrolling for off-screen elements
- [ ] Use text/description to find elements, not hardcoded positions
