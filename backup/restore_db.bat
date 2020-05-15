@echo off
cd C:\Important\PostgreSQL\bin
psql --dbname=postgresql://dbconnection:0000@127.0.0.1:5432/webdb --file=%~dp0%data\last_backup\webdb_backup.backup
exit