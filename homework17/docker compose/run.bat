@ECHO off
@chcp 65001

rem Сброка docker файла
call docker-compose build

rem Запуск docker файла
start docker-compose up

rem Остановка процесса
:loop
set /p answer="Чтобы оставить процесс введите "stop":  "
if "%answer%"=="stop" ( 

call docker stop dockercompose_programm_1
call docker stop dockercompose_postgres_1

call docker rm dockercompose_programm_1
call docker rm dockercompose_postgres_1

) else ( goto :loop )


