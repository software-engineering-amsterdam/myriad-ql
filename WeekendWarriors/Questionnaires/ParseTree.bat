REM Generate a visual representation of the parsetree. Based on the example on https://github.com/antlr/antlr4/blob/master/doc/getting-started.md
REM to generate the parsetree as shown there put "hello parrt" into <file> and run ParseTree hello r <file>

@echo off
set pathToGrammar=%1
REM Update when we know how to call this
set nodeName=%2 
set fileToParse=%3
set startDirectory=%CD%

For %%A in ("%pathToGrammar%") do (
    Set grammarName=%%~nA
)

cd Java\%grammarName%\Grammar
REM java -cp ".;%classpath%" org.antlr.v4.gui.TestRig %grammarName% %nodeName% -gui %startDirectory%\%fileToParse%
REM cd %startDirectory%