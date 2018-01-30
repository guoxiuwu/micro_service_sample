@echo off
set b=%~dp0/build.xml
cd ../apache-ant-1.9.7/bin/
ant -f %b% generateFiles;
pause