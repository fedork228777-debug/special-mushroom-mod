@echo off
title Special Mushroom Mod - Сборка
color 0A
echo.
echo  =============================================
echo   Special Mushroom Mod - Автосборка
echo  =============================================
echo.

:: Проверка Java
java -version >nul 2>&1
if errorlevel 1 (
    echo  [ОШИБКА] Java не найдена!
    echo.
    echo  Скачай Java 17 здесь:
    echo  https://adoptium.net/
    echo.
    pause
    exit /b 1
)
echo  [OK] Java найдена

:: Скачать Gradle Wrapper если его нет
if not exist "gradlew.bat" (
    echo  [INFO] Скачиваю Gradle Wrapper...
    powershell -Command "Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-8.4-bin.zip' -OutFile '%TEMP%\gradle.zip'" >nul 2>&1
    powershell -Command "Expand-Archive '%TEMP%\gradle.zip' -DestinationPath '%TEMP%\gradle' -Force" >nul 2>&1
    set GRADLE_BIN=%TEMP%\gradle\gradle-8.4\bin\gradle.bat
) else (
    set GRADLE_BIN=gradlew.bat
)

echo  [INFO] Начинаю сборку мода...
echo.
call gradlew.bat build 2>&1

if errorlevel 1 (
    echo.
    echo  [ОШИБКА] Сборка не удалась. Попробуй запустить от имени администратора.
    pause
    exit /b 1
)

echo.
echo  =============================================
echo   Готово! JAR файл находится в:
echo   build\libs\special-mushroom-mod-1.0.0.jar
echo  =============================================
echo.
echo  Скопируй его в папку .minecraft\mods\
echo.
pause
