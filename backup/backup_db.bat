@echo off
for /f "skip=1" %%i in ('wmic os get localdatetime') do if not defined fulldate set fulldate=%%i
set year=%fulldate:~0,4%
set month=%fulldate:~4,2%
set day=%fulldate:~6,2%
set foldername=%day%.%month%.%year%
cd %~dp0%
mkdir data
cd %~dp0%data
mkdir %foldername%
set p=%~dp0%data
echo %p%
cd C:\Important\PostgreSQL\bin
pg_dump --dbname=postgresql://dbconnection:0000@127.0.0.1:5432/webdb --column-inserts --clean --file=%p%\%foldername%\webdb_backup.backup
xcopy %p%\%foldername%\webdb_backup.backup %p%\last_backup\webdb_backup.backup* /Y
pause
exit