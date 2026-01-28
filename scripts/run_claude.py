#!/usr/bin/env python3
"""
Run Claude Code with a specific prompt.

Usage:
    python run_claude.py "Your prompt here"
    python run_claude.py --file path/to/prompt.md
    python run_claude.py --prompt-number 0035
"""

import argparse
import subprocess
import sys
import os
from pathlib import Path


def get_project_root() -> Path:
    """Get the project root directory."""
    script_dir = Path(__file__).parent
    return script_dir.parent


def find_prompt_file(prompt_number: str) -> Path | None:
    """Find a prompt file by its number prefix."""
    project_root = get_project_root()
    prompts_dir = project_root / ".claude" / "prompts"

    # Pad to 4 digits
    padded = prompt_number.zfill(4)

    # Find matching files
    for file in prompts_dir.glob(f"{padded}-*.md"):
        return file

    return None


def read_prompt_file(filepath: Path) -> str:
    """Read prompt content from a file."""
    with open(filepath, 'r', encoding='utf-8') as f:
        return f.read()


def run_claude_code(prompt: str, skip_permissions: bool = False, print_output: bool = True) -> int:
    """
    Run Claude Code with the given prompt.

    Args:
        prompt: The prompt to send to Claude Code
        skip_permissions: If True, adds --dangerously-skip-permissions flag
        print_output: If True, streams output to console

    Returns:
        Exit code from Claude Code
    """
    # Build command
    cmd = ["claude", "--print"]

    if skip_permissions:
        cmd.append("--dangerously-skip-permissions")

    # Add the prompt as positional argument (must be last)
    cmd.append(prompt)

    print(f"Running: claude --print {'--dangerously-skip-permissions ' if skip_permissions else ''}<prompt>")
    print("-" * 60)

    try:
        if print_output:
            # Stream output in real-time
            process = subprocess.Popen(
                cmd,
                stdout=subprocess.PIPE,
                stderr=subprocess.STDOUT,
                text=True,
                bufsize=1,
                universal_newlines=True
            )

            for line in process.stdout:
                print(line, end='')

            process.wait()
            return process.returncode
        else:
            # Capture output
            result = subprocess.run(cmd, capture_output=True, text=True)
            print(result.stdout)
            if result.stderr:
                print(result.stderr, file=sys.stderr)
            return result.returncode

    except FileNotFoundError:
        print("Error: 'claude' command not found. Is Claude Code installed?", file=sys.stderr)
        return 1
    except KeyboardInterrupt:
        print("\nInterrupted by user")
        return 130


def main():
    parser = argparse.ArgumentParser(
        description="Run Claude Code with a specific prompt",
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog="""
Examples:
    python run_claude.py "Fix the bug in main.py"
    python run_claude.py --file .claude/prompts/0035-improve-city-layout-coherence.md
    python run_claude.py --prompt-number 0035
    python run_claude.py --skip-permissions "Run tests"
        """
    )

    # Prompt source (mutually exclusive)
    prompt_group = parser.add_mutually_exclusive_group(required=True)
    prompt_group.add_argument(
        "prompt",
        nargs="?",
        help="The prompt text to send to Claude Code"
    )
    prompt_group.add_argument(
        "--file", "-f",
        type=Path,
        help="Path to a file containing the prompt"
    )
    prompt_group.add_argument(
        "--prompt-number", "-n",
        help="Prompt number (e.g., 0035 or 35) to find in .claude/prompts/"
    )

    # Options
    parser.add_argument(
        "--skip-permissions",
        action="store_true",
        help="Add --dangerously-skip-permissions flag (use with caution)"
    )
    parser.add_argument(
        "--quiet", "-q",
        action="store_true",
        help="Don't stream output in real-time"
    )

    args = parser.parse_args()

    n = 0
    while True:
        print(f"Starting iteration: {n}")
        n += 1

        # Get the prompt content
        if args.file:
            if not args.file.exists():
                print(f"Error: File not found: {args.file}", file=sys.stderr)
                sys.exit(1)
            prompt = read_prompt_file(args.file)
            print(f"Using prompt from: {args.file}")

        elif args.prompt_number:
            prompt_file = find_prompt_file(args.prompt_number)
            if not prompt_file:
                print(f"Error: No prompt file found for number: {args.prompt_number}", file=sys.stderr)
                sys.exit(1)
            prompt = read_prompt_file(prompt_file)
            print(f"Using prompt from: {prompt_file}")

        else:
            prompt = args.prompt

        # Run Claude Code
        exit_code = run_claude_code(
            prompt=prompt,
            skip_permissions=args.skip_permissions,
            print_output=not args.quiet
        )

    sys.exit(exit_code)


if __name__ == "__main__":
    main()
