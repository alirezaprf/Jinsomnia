@echo off
javac Main.java -d out
cd out
set /p inp="Enter Arguments: "
java Main %inp%
pause