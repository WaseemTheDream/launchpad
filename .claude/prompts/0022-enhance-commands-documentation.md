# Enhance Commands Documentation with Architecture Details

[no-commit]

## Original Request

Update the Commands glossary and Showcase app to include detailed architecture descriptions for each command, enabling users to understand HOW commands work internally and ideate more deeply about leveraging them.

## Implementation Summary

### Data Model Enhancements

**Modified: `app/src/main/java/com/example/launchpad/data/model/Command.kt`**

Added new data classes to support rich architecture documentation:

```kotlin
data class CommandArchitecture(
    val philosophy: String,           // Core design philosophy
    val workflow: List<String>,       // Step-by-step execution flow
    val keyBehaviors: List<KeyBehavior>, // Notable behaviors/capabilities
    val bestFor: List<String>,        // Ideal use cases
    val notFor: List<String>,         // Anti-patterns
    val relatedCommands: List<String>, // Linked commands
    val proTips: List<String>         // Expert tips
)

data class KeyBehavior(
    val title: String,
    val description: String
)
```

Added `sourceFileName` field to Command for referencing raw .md source files.

### Architecture Documentation Added

Comprehensive architecture details added for all 13 commands:

**Development Commands:**
- `/blueprint` - Architect before builder philosophy, 7-phase workflow, context file persistence
- `/forge` - Proposal-first approach, Ultrathink-powered, destructive by design
- `/ship` - Autonomous delivery, progress logging for resumption, voice-friendly
- `/pilot` - Conversation-first, build-test loop, deferred commits
- `/land` - Commit phase separation, automatic session detection
- `/flight-plan` - Deep brainstorming, MoSCoW prioritization, executable plans

**Workflow Commands:**
- `/queue` - Minimal interruption task capture, simple markdown storage
- `/burn` - Batch processing with Ship workflow, resumable progress
- `/reboot` - Safety net for interrupted sessions, state verification

**Build Commands:**
- `/build` - Simplest compile, error analysis
- `/install` - Device check before build
- `/run` - Complete development cycle
- `/release` - Full release management with semantic versioning

### New CommandDetailScreen

**Created: `app/src/main/java/com/example/launchpad/ui/screens/commands/CommandDetailScreen.kt`**

Three-tab detail view:
1. **Overview** - Usage, examples, options, pro tips
2. **Architecture** - Philosophy, workflow, key behaviors, best for/not for
3. **Source** - Placeholder for raw markdown source display

### Navigation Updates

**Modified: `Routes.kt`**
- Added `CommandDetail` screen route with URL-encoded command name

**Modified: `NavGraph.kt`**
- Added navigation to CommandDetailScreen with URL decoding

**Modified: `CommandsScreen.kt`**
- Added `onCommandClick` callback
- Added "View Architecture & Details" button in expanded card view

## Files Changed

### Created
- `app/src/main/java/com/example/launchpad/ui/screens/commands/CommandDetailScreen.kt`

### Modified
- `app/src/main/java/com/example/launchpad/data/model/Command.kt`
- `app/src/main/java/com/example/launchpad/ui/navigation/Routes.kt`
- `app/src/main/java/com/example/launchpad/ui/navigation/NavGraph.kt`
- `app/src/main/java/com/example/launchpad/ui/screens/commands/CommandsScreen.kt`

## User Experience Flow

1. User navigates to Commands tab
2. User expands a command card to see quick reference
3. User taps "View Architecture & Details" button
4. CommandDetailScreen opens with three tabs:
   - **Overview**: Usage syntax, examples, options, pro tips
   - **Architecture**: Design philosophy, workflow steps, key behaviors, use cases
   - **Source**: File reference (raw source display ready for future implementation)

## Technical Notes

- Architecture data is embedded in CommandRepository for simplicity
- Source file loading deferred to future implementation (requires asset bundling)
- URL encoding used for command names with special characters (/)
- AutoMirrored icons used for RTL support

## Session Reference

- **Ship Session**: ship_0022_20260118_001742
- **Date**: 2026-01-18
