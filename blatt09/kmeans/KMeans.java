import java.util.Arrays;
import java.util.Random;

public class KMeans {

    //Generator für Zufallszahlen
    private static Random random = new Random();

    //Referenz auf die GUI
    private static KMeansViewer viewer;

    public static void main(String[] args) {

        //Data data = new Data();
        Data data = new Data("iris.csv"); //Wenn Sie die Bonusaufgabe geloest haben sollten.

        //b)
        data.view();

        //Intialisierung der GUI, Features 0 und 2 werden angezeigt
        viewer = new KMeansViewer(data, 0, 2);

        //Die GUI anzeigen
        viewer.show();

        //Fuehre kmeans aus
        kmeans(data, new int[]{0, 2}, 3); //kmeans fuer Features 0,2
//        kmeans(data, new int[]{0, 1, 2, 3}, 3); //kmeans fuer alle Features
//        kmeans(data, new int[]{0, 1, 2, 3}, 2); //kmeans fuer alle Features, zu wenige Zentren

        //g)
        data.analyse();
    }

    private static void kmeans(Data data, int[] activeFeatures, int nrCenters) {

        double[][] centers = new double[nrCenters][data.values[0].length];

        //Create starting centers at random

        for (int centerId = 0; centerId < centers.length; centerId++) {
            for (int featureId = 0; featureId < activeFeatures.length; featureId++) {

                int randomId = random.nextInt(data.values.length);
                centers[centerId][activeFeatures[featureId]] = data.values[randomId][activeFeatures[featureId]];
            }
        }

        /*
        *   Die Center werden jeweils nach ID im Feld centers[] sortiert und haben dort fuer jedes Feature den Feature-Wert eines zufaellig gewaehlten
        *   Datensatzes.
        */


        viewer.touch(centers);

        boolean swapOccured = true;

        while (swapOccured) {

            int[] classification = new int[data.classification.length];

            for (int i = 0; i < classification.length; i++) {

                classification[i] = classify(data.values[i], centers, activeFeatures);

            }

            if (Arrays.equals(data.classification, classification)) {
                //TODO: Schleife beenden
                break;
            } else {
                data.classification = classification;
                recalcCenters(data, classification, centers, activeFeatures);
            }

            //Aktualisiere die GUI
            viewer.touch(centers);

            //Warte fuer 1000ms bis zur naechsten Berechnung
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private static int classify(double[] value, double[][] centers, int[] activeFeatures) {

        double[] distanceCenters = new double[centers.length];

        for (int i = 0; i < centers.length ; i++) {
            for (int feature : activeFeatures) {
                // Rechenvorschrift
              distanceCenters[i] += Math.pow(value[feature] - centers[i][feature], 2);
            }
        }

        int cluster = 0;

        for (int i = 1; i < distanceCenters.length; i++) {
            cluster = distanceCenters[cluster] > distanceCenters[i] ? i : cluster;
        }


        return cluster;
    }


    private static void recalcCenters(Data data, int[] classifications, double[][] centers, int[] activeFeatures) {
        //TODO: f)
    
        for (int center = 0; center < centers.length; center++) {

            // Berechne Anzahl der Werte

            int countAllElements = 0;
            double[] valueAllElements = new double[activeFeatures.length]; // Werte des neuen Clusters abhaengig vom Feature

            // Anzahl der zugeordneten Elemente
            for (int i = 0; i < data.values.length ; i++) {
                //if (classify(data.values[i], centers, activeFeatures) == center) {
                if (classifications[i] == center) {
                    countAllElements++ ;

                    // Die Features-Values werden noch aufaddiert
                    for (int j = 0; j < activeFeatures.length; j++) {
                        valueAllElements[j] += data.values[i][activeFeatures[j]];

                    }
                }
            }

            // Neuzuordnung der Centers
            for (int j = 0; j < activeFeatures.length; j++) {
                centers[center][activeFeatures[j]] = (double) (valueAllElements[j] / countAllElements);
            }



        }

    }
}
