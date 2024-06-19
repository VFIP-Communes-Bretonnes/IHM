import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.File;

public class ImageZoomExample extends Application {

    private double startX, startY;
    private double startTranslateX, startTranslateY;
    private double zoomFactor = 1.1;
    private double minScale = 0.1;
    private double maxScale = 10.0;

    @Override
    public void start(Stage primaryStage) {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        if (f != null) {
            System.out.println(f.toURI().toString());
            Image image = new Image(f.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(400);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);

            imageView.setOnMousePressed(event -> {
                startX = event.getSceneX();
                startY = event.getSceneY();
                startTranslateX = imageView.getTranslateX();
                startTranslateY = imageView.getTranslateY();
            });

            imageView.setOnMouseDragged(event -> {
                double deltaX = event.getSceneX() - startX;
                double deltaY = event.getSceneY() - startY;
                imageView.setTranslateX(startTranslateX + deltaX);
                imageView.setTranslateY(startTranslateY + deltaY);
            });

            imageView.addEventFilter(ScrollEvent.ANY, event -> {
                if (event.getDeltaY() > 0) {
                    zoomIn(imageView);
                } else if (event.getDeltaY() < 0) {
                    zoomOut(imageView);
                }
                event.consume();
            });

            StackPane root = new StackPane();
            root.getChildren().add(imageView);

            Scene scene = new Scene(root, 600, 400);

            primaryStage.setTitle("Image Pan Example");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    private void zoomIn(ImageView imageView) {
        double currentScale = imageView.getScaleX();
        if (currentScale * zoomFactor <= maxScale) {
            imageView.setScaleX(currentScale * zoomFactor);
            imageView.setScaleY(currentScale * zoomFactor);
        }
    }

    private void zoomOut(ImageView imageView) {
        double currentScale = imageView.getScaleX();
        if (currentScale / zoomFactor >= minScale) {
            imageView.setScaleX(currentScale / zoomFactor);
            imageView.setScaleY(currentScale / zoomFactor);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
