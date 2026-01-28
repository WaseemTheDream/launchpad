[no-commit]

# Add run_claude.py Script

## Original Request
Commit the script added in the working copy

## Description
Added a Python utility script to run Claude Code with prompts from various sources.

## Script: scripts/run_claude.py

A command-line tool to invoke Claude Code with prompts:

### Usage
```bash
python run_claude.py "Your prompt here"
python run_claude.py --file path/to/prompt.md
python run_claude.py --prompt-number 0035
```

### Features
- Direct prompt text via command line
- Load prompt from any file (`--file`)
- Find prompt by number in `.claude/prompts/` (`--prompt-number`)
- Stream output in real-time (default)
- Optional `--skip-permissions` flag for automation
- Iterative execution loop for repeated runs

### Options
- `--file, -f` - Path to prompt file
- `--prompt-number, -n` - Prompt number (e.g., 35 or 0035)
- `--skip-permissions` - Add --dangerously-skip-permissions flag
- `--quiet, -q` - Don't stream output

## Files Created
- `scripts/run_claude.py` - Main script

## Session Reference
- **Ship Session**: ship_0030_20260128_153114
