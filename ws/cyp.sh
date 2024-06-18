rm -rf ../class/
javac --module-path /home/cyprien/javaSDK/javafx-sdk-22.0.1/lib --add-modules javafx.controls,javafx.fxml -d ../class -sourcepath ../src $(find ../src -name "*.java")
java --module-path /home/cyprien/javaSDK/javafx-sdk-22.0.1/lib --add-modules javafx.controls,javafx.fxml -cp "../class:/home/cyprien/javaSDK/mysql-connector-j-8.4.0/mysql-connector-j-8.4.0.jar:../src/assets" view.Main