# Add Mobile Interaction Skill

[no-commit]

## Original Request

Update/create a mobile interaction skill based on friction points observed while navigating the Showcase app via ADB. Make it work well for apps built with this launchpad project, accounting for different emulator screen sizes.

## Implementation Summary

Created `.claude/skills/mobile-interaction.md` documenting best practices for ADB-based mobile app interaction.

### Friction Points Observed

1. **Screen coordinates vary** - Initial tap coordinates (640, 640) didn't work because screen was 1280x2856
2. **Screenshots are native resolution** - Displayed images are scaled, causing coordinate mismatch
3. **Guessing coordinates fails** - Must use UI inspection to find actual element bounds
4. **UI transitions need delays** - Taps need time for animations to complete

### Key Practices Documented

1. **Get screen size first**: `adb shell wm size`
2. **Use UI Automator dump**: Find element bounds programmatically
3. **Calculate coordinates from bounds**: `x = (left + right) / 2`
4. **Add delays after actions**: `sleep 1` for UI transitions
5. **Find by text/description**: Not hardcoded positions

### Skill Contents

- Screen resolution detection
- UI element finding methods
- Coordinate calculation
- Action commands (tap, type, swipe, keys)
- Screenshot capture
- Common navigation patterns
- Troubleshooting guide
- Best practices checklist

### Files Changed

**Created:**
- `.claude/skills/mobile-interaction.md` - Complete skill documentation

**Modified:**
- `CLAUDE.md` - Added skill to Key Skills table and project structure

## Session Reference

- **Commit**: [0031] @ 9a9dbd3
- **Date**: 2026-01-18
