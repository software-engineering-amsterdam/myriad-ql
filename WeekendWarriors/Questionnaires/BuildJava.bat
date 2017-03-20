@echo off
set pathToGrammar=%1
set temporaryDirectoryName=Java

For %%A in ("%pathToGrammar%") do (
    Set grammarName=%%~nA
)

java org.antlr.v4.Tool %pathToGrammar% -o .\%temporaryDirectoryName%
javac -d %temporaryDirectoryName%\%grammarName%\Grammar %temporaryDirectoryName%\%grammarName%\Grammar\*.java