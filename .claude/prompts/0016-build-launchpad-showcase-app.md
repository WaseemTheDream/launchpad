# Build Launchpad Showcase App

[auto-commit]

> A modern, interactive guide app showcasing Launchpad's capabilities

## Overview

Transform the Launchpad template into a polished, production-quality showcase application that serves as both a demonstration of what's possible and a user-friendly guide for building Android apps with Claude Code.

**Use Ultrathink (extended thinking) throughout this implementation for thorough analysis and high-quality code.**

---

## App Vision

### Purpose
A beautiful, interactive app that:
1. **Demonstrates** Launchpad's capabilities through its own polished implementation
2. **Guides** users on building different types of Android applications
3. **Inspires** with modern design and smooth interactions

### Target Experience
- First-time user opens the app and thinks "Wow, this is professional"
- Navigation is intuitive and delightful
- Content is helpful and actionable
- App feels like a real product, not a template

---

## Design Requirements

### Theme & Colors

**Light Theme:**
- Primary: Deep Blue (#1565C0) - Trust, technology
- Secondary: Vibrant Orange (#FF6D00) - Energy, creativity (Launchpad accent)
- Background: Clean White (#FAFAFA)
- Surface: Pure White (#FFFFFF)
- On-Surface: Near Black (#1A1A1A)

**Dark Theme:**
- Primary: Lighter Blue (#42A5F5)
- Secondary: Bright Orange (#FF9100)
- Background: Deep Gray (#121212)
- Surface: Elevated Gray (#1E1E1E)
- On-Surface: Off White (#E0E0E0)

### Typography
- Headlines: Bold, impactful
- Body: Clean, readable
- Use Material 3 type scale

### Iconography
- Use Material Icons for navigation
- Create custom vector assets for guide categories
- Rocket/launch themed icons where appropriate

### Animations
- Shared element transitions between screens
- Smooth fade/slide for navigation
- Subtle micro-interactions on buttons
- Loading shimmer effects where appropriate
- Staggered list animations

---

## Screen Architecture

### 1. Splash Screen
**Duration**: 1.5 seconds with fade transition
**Content**:
- Launchpad logo (rocket icon + "Launchpad" text)
- Tagline: "Build Android Apps with AI"
- Subtle animation (rocket lift-off or pulse)

### 2. Home Screen (Main Hub)
**Layout**: Scrollable with cards
**Sections**:

**A. Hero Section**
- Welcome message with user's time of day greeting
- "What would you like to build today?"
- Quick action buttons: "Start New Project", "Continue Learning"

**B. Getting Started Card**
- Progress indicator for onboarding steps
- Steps: Install → Configure → Blueprint → Forge → Ship
- Visual checkmarks for completed steps

**C. Guide Categories Grid**
- 2x2 or 2x3 grid of category cards
- Each card: Icon + Title + Brief description
- Categories:
  1. 📱 Utility Apps
  2. 🎮 Games
  3. 🛒 E-Commerce
  4. 💬 Social Apps
  5. 📊 Productivity
  6. 🎨 Creative Tools

**D. Quick Tips Section**
- Horizontal scrolling cards with tips
- "Did you know?" style content

**E. Tech Stack Section**
- Horizontal scroll of technology badges
- Kotlin, Jetpack Compose, Material 3, Gradle, etc.

### 3. Guide Detail Screen
**For each category (Utility, Games, etc.)**
**Layout**: Long-form scrollable content

**Content Structure**:
- Hero image/illustration for category
- "What are {Category} Apps?"
- Key features to include
- Architecture recommendations
- Example apps in this category
- Step-by-step: "How to build with Launchpad"
- Code snippets (syntax highlighted)
- "Try it: /ship [example command]"
- Related guides

### 4. Commands Reference Screen
**Layout**: Searchable list with expandable items
**Content**:
- All Launchpad commands (/blueprint, /forge, /ship, etc.)
- Each command: Name, description, usage examples, options
- Copy-to-clipboard for command examples

### 5. About Screen
**Content**:
- Launchpad logo and version
- "Built with Claude Code" badge
- Technology stack with icons
- Links: GitHub, Documentation
- Credits/attribution
- Open source license info

### 6. Settings Screen
**Options**:
- Theme toggle (Light/Dark/System)
- Notification preferences (placeholder)
- Clear app data (placeholder)
- Version info

---

## Navigation Structure

```
┌─────────────────────────────────────────────────────────────┐
│                      Bottom Navigation                       │
├─────────────┬─────────────┬─────────────┬─────────────┬─────┤
│    Home     │   Guides    │  Commands   │   About     │ ⚙️  │
│     🏠      │     📚      │     💻      │     ℹ️      │     │
└─────────────┴─────────────┴─────────────┴─────────────┴─────┘

Home → Guide Category Card → Guide Detail Screen
Guides → Category List → Guide Detail Screen
Commands → Command List → Command Detail (expandable)
```

---

## Implementation Tasks

### Phase 1: Project Foundation

#### 1.1 Update App Identity
- [ ] Change app name to "Launchpad Guide" in strings.xml
- [ ] Update app icon (create simple rocket icon)
- [ ] Keep package as com.example.launchpad (it's a showcase)

#### 1.2 Add Dependencies
Add to app/build.gradle.kts:
```kotlin
// Navigation
implementation("androidx.navigation:navigation-compose:2.7.7")

// Icons extended
implementation("androidx.compose.material:material-icons-extended:1.6.1")

// Accompanist for system UI
implementation("com.google.accompanist:accompanist-systemuicontroller:0.34.0")

// Splash screen API
implementation("androidx.core:core-splashscreen:1.0.1")
```

#### 1.3 Create Color Scheme
Update Color.kt with the design colors:
- Light theme colors
- Dark theme colors
- Semantic colors (success, warning, error)

#### 1.4 Update Theme
- Configure Material 3 dynamic theming
- Set up light/dark theme switching
- Configure status bar colors

#### 1.5 Create Typography
- Define type scale
- Use default Material fonts or add custom

### Phase 2: Navigation & Structure

#### 2.1 Define Routes
Create sealed class for navigation routes:
- Splash
- Home
- GuideList
- GuideDetail(categoryId)
- Commands
- CommandDetail(commandId)
- About
- Settings

#### 2.2 Create NavHost
- Set up bottom navigation
- Configure transitions (fade + slide)
- Handle deep links

#### 2.3 Create Screen Scaffolds
Create placeholder composables for each screen

### Phase 3: Core Screens

#### 3.1 Splash Screen
- Animated logo
- Delay then navigate to Home
- Edge-to-edge design

#### 3.2 Home Screen
- Time-based greeting
- Getting started progress
- Category cards grid
- Quick tips carousel
- Tech stack badges

#### 3.3 Bottom Navigation Bar
- 5 items: Home, Guides, Commands, About, Settings
- Selected state animations
- Badge support (optional)

### Phase 4: Guide Screens

#### 4.1 Guide Categories
Create data model and content for:
1. **Utility Apps**: Calculator, Timer, Unit Converter, Notes
2. **Games**: Trivia, Puzzle, Casual, Card games
3. **E-Commerce**: Product catalog, Cart, Checkout flow
4. **Social Apps**: Feed, Profiles, Messaging, Notifications
5. **Productivity**: Task manager, Calendar, Reminders
6. **Creative Tools**: Drawing, Photo editing, Music

#### 4.2 Guide List Screen
- Vertical list with large cards
- Category icon and gradient
- Item count badge
- Search functionality (optional)

#### 4.3 Guide Detail Screen
- Hero header with parallax
- Markdown-style content rendering
- Code block syntax highlighting
- Interactive examples
- Related guides section

### Phase 5: Commands Reference

#### 5.1 Command Data
Create comprehensive command list:
- /blueprint - Product ideation
- /forge - MVP generation
- /ship - Change execution
- /queue - Task queue
- /burn - Process queue
- /reboot - Resume work
- /build - Compile app
- /install - Deploy to device
- /run - Build, install, launch
- /execute-prompt - Run prompt file

#### 5.2 Commands List Screen
- Searchable list
- Grouped by category (Dev, Build, Workflow)
- Quick-copy buttons

#### 5.3 Command Detail
- Expandable cards with full documentation
- Usage examples
- Options table
- Related commands

### Phase 6: About & Settings

#### 6.1 About Screen
- App logo and version
- Tech stack grid with icons
- GitHub link button
- "Built with Claude Code" section
- License info

#### 6.2 Settings Screen
- Theme selector (Radio buttons: Light/Dark/System)
- About app link
- Version display

### Phase 7: Polish & Animations

#### 7.1 Transitions
- Fade between bottom nav destinations
- Shared element for guide cards
- Slide up for modals/dialogs

#### 7.2 Micro-interactions
- Button press feedback
- Card elevation on hover/press
- Ripple effects

#### 7.3 Loading States
- Shimmer placeholder for content
- Skeleton screens

#### 7.4 Empty States
- Friendly illustrations
- Helpful messages

### Phase 8: Content Creation

#### 8.1 Guide Content
Write comprehensive guide content for each category:
- What it is
- Key features
- Architecture tips
- Example prompts for Claude
- Code snippets

#### 8.2 Command Documentation
Document each command with:
- Purpose
- Usage syntax
- Options
- Examples
- Tips

### Phase 9: Quality Assurance

#### 9.1 Build and Install
- Run /build to compile
- Run /install to deploy to emulator
- Run /run to launch

#### 9.2 Visual QA (using Mobile MCP if available)
- Take screenshots of each screen
- Verify:
  - Font sizes are readable (min 12sp body, 14sp for important)
  - Touch targets are adequate (min 48dp)
  - Colors have sufficient contrast
  - Images/icons are appropriately sized
  - Layout works on different screen sizes
  - Dark mode looks correct

#### 9.3 Navigation QA
- Test all navigation paths
- Verify back navigation
- Check deep link support

#### 9.4 Theme QA
- Toggle between light/dark
- Verify all screens respect theme
- Check system theme following

---

## File Structure

```
app/src/main/java/com/example/launchpad/
├── MainActivity.kt
├── LaunchpadApp.kt                    # Main app composable
├── ui/
│   ├── navigation/
│   │   ├── NavGraph.kt               # Navigation setup
│   │   ├── Routes.kt                 # Route definitions
│   │   └── BottomNavBar.kt           # Bottom navigation
│   ├── screens/
│   │   ├── splash/
│   │   │   └── SplashScreen.kt
│   │   ├── home/
│   │   │   ├── HomeScreen.kt
│   │   │   └── components/           # Home-specific components
│   │   ├── guides/
│   │   │   ├── GuideListScreen.kt
│   │   │   ├── GuideDetailScreen.kt
│   │   │   └── components/
│   │   ├── commands/
│   │   │   ├── CommandsScreen.kt
│   │   │   └── components/
│   │   ├── about/
│   │   │   └── AboutScreen.kt
│   │   └── settings/
│   │       └── SettingsScreen.kt
│   ├── components/                    # Shared components
│   │   ├── LaunchpadCard.kt
│   │   ├── CategoryIcon.kt
│   │   ├── TechBadge.kt
│   │   ├── CodeBlock.kt
│   │   └── GradientHeader.kt
│   └── theme/
│       ├── Color.kt
│       ├── Type.kt
│       ├── Shape.kt
│       └── Theme.kt
├── data/
│   ├── model/
│   │   ├── GuideCategory.kt
│   │   ├── Guide.kt
│   │   ├── Command.kt
│   │   └── TechStack.kt
│   └── repository/
│       ├── GuideRepository.kt        # Static guide content
│       └── CommandRepository.kt      # Command documentation
└── util/
    └── TimeGreeting.kt               # Time-based greeting helper
```

---

## Commit Strategy

Make atomic commits after each major component:

1. `[0017] feat: Set up project foundation with theme and navigation`
2. `[0018] feat: Implement splash screen with animation`
3. `[0019] feat: Add home screen with category grid`
4. `[0020] feat: Create guide list and detail screens`
5. `[0021] feat: Implement commands reference screen`
6. `[0022] feat: Add about and settings screens`
7. `[0023] feat: Add animations and polish`
8. `[0024] feat: Complete guide content`
9. `[0025] chore: Final polish and QA fixes`

---

## Success Criteria

The app is complete when:

1. ✅ All screens are implemented and navigable
2. ✅ Dark and light themes work correctly
3. ✅ Animations are smooth and delightful
4. ✅ Content is comprehensive and helpful
5. ✅ App builds without errors
6. ✅ App runs on emulator without crashes
7. ✅ Visual QA passes (readable fonts, proper sizing)
8. ✅ Navigation is intuitive
9. ✅ App feels professional and polished

---

## Notes for Implementation

- Use remember and LaunchedEffect appropriately
- Implement proper state hoisting
- Use meaningful preview annotations
- Handle configuration changes gracefully
- Consider accessibility (content descriptions)
- Use string resources for all user-facing text
- Create reusable components where possible
