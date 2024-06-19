package controller;

import java.lang.reflect.*;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.dao.ReadWriteDatabase;
import model.data.Aeroport;
import model.data.Annee;
import model.data.Commune;
import model.data.Departement;
import model.data.DonneesAnnuelles;
import model.data.Gare;

public class ComboBoxTableCell extends TableCell<Object, String> {
    private final ComboBox<String> comboBox;
    private final Field field;
    private final ReadWriteDatabase database;
    private final String getterName;
    private ArrayList<Object> choice;
    private ArrayList<Object> choiceVoisin;
    private VBox neighborContainer;

    public ComboBoxTableCell(Field field, ReadWriteDatabase database, String getterName) {
        this.field = field;
        this.database = database;
        this.getterName = getterName;
        this.comboBox = new ComboBox<>();
        this.choice = new ArrayList<>();
        this.choiceVoisin = new ArrayList<>();
        this.neighborContainer = new VBox();

        comboBox.setEditable(false);
        comboBox.getStyleClass().add("table-view-combo-box");

        neighborContainer.getStyleClass().add("neighbor-container");

        try {
            Method getter = database.getAllObjectsData().getClass().getMethod(getterName);
            Object listResult = getter.invoke(database.getAllObjectsData());
            for (Object obj : (ArrayList<?>) listResult) {
                comboBox.getItems().add(obj.toString());
                this.choice.add(obj);
            }
        } catch (Exception e) {
            try{
                Object listResult = database.getAllObjectsData().getCommunesList();
                for (Object obj : (ArrayList<?>) listResult) {
                    comboBox.getItems().add(obj.toString());
                    this.choice.add(obj);
                }
            }
            catch (Exception e2) {
                //e2.printStackTrace();
            }
        }

        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Object rowObject = getTableRow().getItem();
                if (rowObject != null) {
                    try {
                        field.setAccessible(true);
                        updateMainObject(rowObject, newValue);
                    } catch (Exception e) {
                        //e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            comboBox.setValue(item);
            if (getTableRow().getItem() instanceof Commune && isNeighborField()) {
                updateNeighborContainer((Commune) getTableRow().getItem());
                setGraphic(neighborContainer);
            } else {
                setGraphic(comboBox);
            }
        }
    }

    private boolean isNeighborField() {
        return field.getName().equalsIgnoreCase("listeVoisins");
    }

    private void updateMainObject(Object rowObject, String newValue) throws Exception {
        if (rowObject instanceof Departement) {
            ((Departement) rowObject).setNomDep(Departement.NomDepartement.valueOf(newValue));
        } else if (rowObject instanceof Aeroport) {
            Object dep = choice.get(comboBox.getItems().indexOf(comboBox.getSelectionModel().getSelectedItem()));
            ((Aeroport) rowObject).setLeDepartement((Departement) dep);
        } else if (rowObject instanceof Commune) {
            // No need to update here, handled by UI changes
            try{
                Object dep = choice.get(comboBox.getItems().indexOf(comboBox.getSelectionModel().getSelectedItem()));
                if(dep instanceof Departement){
                    ((Commune) rowObject).setLeDepartement((Departement) dep);
                }
            }
            catch (Exception e){
                // mdr
            }
        } else if (rowObject instanceof DonneesAnnuelles) {
            Object newO = choice.get(comboBox.getItems().indexOf(comboBox.getSelectionModel().getSelectedItem()));
            if (newO instanceof Annee) {
                ((DonneesAnnuelles) rowObject).setAnnee((Annee) newO);
            } else if (newO instanceof Commune) {
                ((DonneesAnnuelles) rowObject).setLaCommune((Commune) newO);
            }
        } else if (rowObject instanceof Gare) {
            Object newO = choice.get(comboBox.getItems().indexOf(comboBox.getSelectionModel().getSelectedItem()));
            ((Gare) rowObject).setLaCommune((Commune) newO);
        }
    }

    private void updateNeighborContainer(Commune commune) {
        neighborContainer.getChildren().clear();
        ArrayList<Commune> neighbors = commune.getListeVoisins();

        for (Commune neighbor : neighbors) {
            HBox neighborBox = new HBox();
            ComboBox<String> neighborComboBox = new ComboBox<>();
            Button deleteButton = new Button("Supprimer");

            deleteButton.getStyleClass().add("delete-button");

            for (Object obj : choice) {
                if (obj instanceof Commune) {
                    neighborComboBox.getItems().add(((Commune) obj).toString());
                }
            }

            neighborComboBox.setValue(neighbor.toString());
            neighborComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                Commune newNeighbor = (Commune) choice.get(neighborComboBox.getItems().indexOf(newValue));
                int index = neighbors.indexOf(neighbor);
                if (index >= 0) {
                    neighbors.set(index, newNeighbor);
                }
            });

            deleteButton.setOnAction(event -> {
                neighbors.remove(neighbor);
                neighborContainer.getChildren().remove(neighborBox);
            });

            neighborBox.getChildren().addAll(neighborComboBox, deleteButton);
            neighborContainer.getChildren().add(neighborBox);
        }

        Button addButton = new Button("Ajouter");

        addButton.getStyleClass().add("add-button");

        addButton.setOnAction(event -> {
            HBox newNeighborBox = new HBox();
            ComboBox<String> newNeighborComboBox = new ComboBox<>();
            Button newDeleteButton = new Button("Supprimer");

            newDeleteButton.getStyleClass().add("delete-button");

            for (Object obj : choice) {
                if (obj instanceof Commune) {
                    newNeighborComboBox.getItems().add(((Commune) obj).toString());
                }
            }

            newNeighborComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                Commune newNeighbor = (Commune) choice.get(newNeighborComboBox.getItems().indexOf(newValue));
                if (!neighbors.contains(newNeighbor)) {
                    neighbors.add(newNeighbor);
                }
            });

            newDeleteButton.setOnAction(e -> {
                neighbors.removeIf(c -> c.toString().equals(newNeighborComboBox.getValue()));
                neighborContainer.getChildren().remove(newNeighborBox);
            });

            newNeighborBox.getChildren().addAll(newNeighborComboBox, newDeleteButton);
            neighborContainer.getChildren().add(newNeighborBox);
        });

        neighborContainer.getChildren().add(addButton);
    }
}
