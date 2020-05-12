@echo off
javac Cli.java -d out
cd out
set /p inp="Enter Arguments: "
java Cli %inp%
pause