call C:\Development\tasks\tasks\runcrud.bat
if "%ERRORLEVEL%" == "0" goto startbrowser
echo Cannot run runcrud.bat
goto fail

:startbrowser
set url="http://localhost:8080/crud/v1/task/getTasks"
start chrome %url%
goto end

:fail
echo.
echo There were errors

:end
echo Everything is ok