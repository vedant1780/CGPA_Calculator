# 🎓 CGPA Calculator App

A stylish Android app that calculates CGPA based on input grades and credits for multiple subjects and semesters. Built using Jetpack Compose, the app offers a clean UI, responsive feedback, and persistent semester tracking.

## 🚀 Features

- Input grade and credit for up to 4 subjects per session
- Add multiple semesters and calculate cumulative CGPA
- Dynamic results display for current CGPA and semester breakdown
- Clean, modern UI with custom styling and fonts

## 🛠 Tech Stack

| Technology        | Purpose                        |
|-------------------|-------------------------------|
| Kotlin            | App development language      |
| Jetpack Compose   | Declarative UI framework      |
| Material3         | Modern Android design system  |
| State Management  | `remember` + `mutableStateOf` |

## 📐 Grade Point Calculation

Grade points are mapped as:

| Grade | Point |
|-------|-------|
| A     | 4.0   |
| B     | 3.0   |
| C     | 2.0   |
| D     | 1.0   |
| Others| 0.0   |

> Final CGPA = (Total Grade Points) / (Total Credits)

## 🧮 Usage Instructions

1. Clone the repo:
   ```bash
   git clone https://github.com/vedant1780/cgpa-calculator.git
   cd cgpa-calculator
