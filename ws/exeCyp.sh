rm -rf ../class/
javac --module-path C:/Users/bibou/javafx/lib --add-modules javafx.controls,javafx.fxml -d ../class -sourcepath ../src $(find ../src -name "*.java")
java --module-path C:/Users/bibou/javafx/lib --add-modules javafx.controls,javafx.fxml -cp "../class;mysql-connector-j-8.4.0.jar;../src/assets" view.Main
/bin/bash