@ECHO off
@chcp 65001

rem Сброка docker файла для backend и eureka
call docker-compose build

rem Запуск docker файла для backend и eureka
start docker-compose up

rem Сборка docker файла для frontend
call docker build -f DockerfileFront -t frontendshell .

rem Запуск docker файла для frontend
start docker run -it --name project_frontendshell_1 -e eureka.client.service-url.defaultZone=http://eureka:8001/eureka --network project_myproject frontendshell

rem Остановка процесса
:loop
set /p answer="Чтобы оставить процесс введите "stop":  "
if "%answer%"=="stop" ( 

call docker stop project_frontendWeb_1
call docker stop project_frontendshell_1
call docker stop project_postgres_1
call docker stop project_backend_1
call docker stop project_eureka_1

) else ( goto :loop )


