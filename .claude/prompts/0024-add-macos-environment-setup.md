# Add macOS Environment Setup Guide

[no-commit]

## Original Request

Add a macOS environment setup guide derived from the Windows 11 setup guide. Make it user-friendly, not overly long or verbose.

## Implementation Summary

Created `ENVIRONMENT_SETUP_MACOS.md` with Homebrew-based installation instructions covering:

1. **Homebrew** - Package manager installation
2. **Android Studio** - Download and setup for Apple/Intel Macs
3. **Git** - Via Xcode Command Line Tools or Homebrew
4. **Claude Code** - Node.js + npm installation
5. **GitHub CLI** - Homebrew installation and authentication
6. **Python 3** - Homebrew installation
7. **Emulator Setup** - Device Manager configuration
8. **Mobile MCP** - Device automation setup

### Key Differences from Windows Guide

- Uses Homebrew instead of winget for package management
- macOS-specific paths and keyboard shortcuts
- Simpler Git installation (often pre-installed)
- Different Android Studio download (.dmg vs .exe)

### Files Changed

**Created:**
- `ENVIRONMENT_SETUP_MACOS.md` - Complete macOS setup guide

**Modified:**
- `README.md` - Updated environment setup table to link to macOS guide

## Session Reference

- **Commit**: [0027] @ 610a9a6
- **Date**: 2026-01-18
