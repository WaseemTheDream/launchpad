# Product Documentation

This folder is where you document **your** product idea. The more context you provide here, the better Claude Code can assist you in building your Android application.

---

## 📝 What to Document

You don't need to have everything figured out. Start with what you know and expand as your vision develops.

### Suggested Files

| File | Purpose | Priority |
|------|---------|----------|
| `vision.md` | What is your app? What problem does it solve? | High |
| `features.md` | List of features and functionality | High |
| `audience.md` | Who are your target users? | Medium |
| `design.md` | Visual style, colors, branding | Medium |
| `technical.md` | Technical requirements and constraints | Medium |
| `competitors.md` | Similar apps and how yours differs | Low |
| `roadmap.md` | Phased development plan | Low |

### References Folder

Create a `references/` subfolder for:
- Screenshots of inspiration apps
- Wireframes or mockups
- Brand assets (logos, colors)
- Any other visual references

---

## 📋 Templates

### vision.md

```markdown
# Product Vision

## App Name
[Your app name]

## One-Liner
[Describe your app in one sentence]

## Problem Statement
[What problem does your app solve?]

## Solution
[How does your app solve this problem?]

## Unique Value Proposition
[What makes your app different from alternatives?]
```

### features.md

```markdown
# Features

## MVP Features (Must Have)
- [ ] Feature 1: [Description]
- [ ] Feature 2: [Description]
- [ ] Feature 3: [Description]

## Phase 2 Features (Nice to Have)
- [ ] Feature 4: [Description]
- [ ] Feature 5: [Description]

## Future Features (Backlog)
- [ ] Feature 6: [Description]

## Out of Scope
- [Things you explicitly won't build]
```

### audience.md

```markdown
# Target Audience

## Primary Users
[Who is your main user?]

## User Personas

### Persona 1: [Name]
- **Age:**
- **Occupation:**
- **Tech Savviness:**
- **Pain Points:**
- **Goals:**

## Use Cases
1. [When/why would someone use your app?]
2. [Another use case]
```

### design.md

```markdown
# Design Guidelines

## Style
[Material You / Custom / Minimal / Other]

## Colors
- Primary: #
- Secondary: #
- Accent: #

## Typography
[Font preferences if any]

## Inspiration
[Apps or designs you like the style of]

## Brand Assets
[List any existing logos, icons, etc.]
```

---

## 🚀 After Documenting

Once you have your documentation ready:

1. Open Claude Code in your terminal
2. Run the Blueprint command:
   ```
   /blueprint
   ```
3. Answer the questions Claude asks
4. Let Claude set up your project

---

## 💡 Tips

- **Start Simple** — You can always add more detail later
- **Be Honest** — Include uncertainties; Claude can help you decide
- **Think User-First** — Focus on what users need, not just features
- **Include Constraints** — Budget, timeline, technical limitations
- **Add Examples** — Screenshots and references are incredibly helpful

---

## Example Structure

```
product-docs/
├── README.md           # This file
├── vision.md           # App concept and goals
├── features.md         # Feature list with priorities
├── audience.md         # Target users and personas
├── design.md           # Visual preferences
├── technical.md        # Technical requirements
└── references/
    ├── competitor-screenshot-1.png
    ├── wireframe-home.png
    └── color-palette.png
```

---

*Delete this README once you've created your own documentation, or keep it as a reference.*
