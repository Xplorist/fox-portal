@echo off
E:
cd %~dp0
"D:\1ProgramInstall\pdi-ce-9.0.0.0-423\data-integration\Kitchen.bat" -file "E:\SystemPortal\database\KettleFile\kjb\SystemPortal.kjb" ^
-level=Debug ^
-logfile "E:\SystemPortal\database\KettleFile\klog\SystemPortal_kjb.log" ^
-param:"filename=SystemPortal_kjb" ^
-param:"extend=txt"