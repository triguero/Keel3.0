package keel.Algorithms.Rule_Learning.AQ;

/**
 * <p>Title: Data-set</p>
 * <p>Description: It contains the methods for reading the training and test files</p>
 * @author Written by Alberto Fern�ndez (University of Granada) 11/25/2004
 * @version 1.0
 * @since JDK1.4
 */

import java.io.*;
import keel.Dataset.*;
import java.util.Arrays;

public class Dataset {

    private double[][] X = null;
    private boolean[][] missing = null;
    private int[] C = null;
    private double[] emaximo;
    private double[] eminimo;

    private int ndatos; // Number of examples
    private int nvariables; // Numer of variables
    private int nentradas; // Number of inputs
    private int nclases; // Number of classes

    final static boolean debug = false;

    private InstanceSet IS;

    private int[] comunes;

    /**
     * It returns the values of the input attributes
     * @return double[][] An array with the input attributes
     */
    public double[][] getX() {
        return X;
    }

    /**
     * It returns the values for the output (class)
     * @return int[] An array with the ouput values
     */
    public int[] getC() {
        int[] retorno = new int[C.length];
        for (int i = 0; i < C.length; i++) {
            retorno[i] = C[i];
        }
        return retorno;
    }

    /**
     * It returns an array with the maximum values of the input attributes
     * @return double[] an array with the maximum values of the input attributes
     */
    public double[] getemaximo() {
        return emaximo;
    }

    /**
     * It returns an array with the minimum values of the input attributes
     * @return double[] an array with the minimum values of the input attributes
     */
    public double[] geteminimo() {
        return eminimo;
    }

    /**
     * It returns the number of examples
     * @return int the number of examples
     */
    public int getndatos() {
        return ndatos;
    }

    /**
     * It returns the number of variables
     * @return int the number of variables (including input and output)
     */
    public int getnvariables() {
        return nvariables;
    }

    /**
     * It returns the number of input variables
     * @return int the number of input variables
     */
    public int getnentradas() {
        return nentradas;
    }

    /**
     * It returns the total number of classes
     * @return int the total number of classes
     */
    public int getnClasses() {
        return nclases;
    }

    /**
     * Comprueba si un atributo est� "perdido" o no
     * @param i int N�mero de ejemplo
     * @param j int N�mero de atributo
     * @return boolean True si falta, False en otro caso
     */
    public boolean isMissing(int i, int j) {
        // True is the value is missing (0 in the table)
        return missing[i][j];
    }

    /**
     * Builder. It creates a new instance set
     */
    public Dataset() {
        IS = new InstanceSet(); // Init a new set of instances
    }

    /**
     * It reads the examples file (training or test)
     * @param nfejemplos String Name of the exampes file
     * @param train boolean True if it refers to the training set. False if it is test
     * @throws IOException A possible I/O exception
     */
    public void readSet(String nfejemplos, boolean train) throws
            IOException {
        try {
            // Load in memory a dataset that contains a classification problem
            IS.readSet(nfejemplos, train);
            ndatos = IS.getNumInstances();
            nentradas = Attributes.getInputNumAttributes();
            nvariables = nentradas + Attributes.getOutputNumAttributes();

            // Check that there is only one output variable
            if (Attributes.getOutputNumAttributes() > 1) {
                System.out.println(
                        "This algorithm can not process MIMO datasets");
                System.out.println(
                        "All outputs but the first one will be removed");
                System.exit(1);
            }
            boolean noOutputs = false;
            if (Attributes.getOutputNumAttributes() < 1) {
                System.out.println(
                        "This algorithm can not process datasets without outputs");
                System.out.println("Zero-valued output generated");
                noOutputs = true;
                System.exit(1);
            }

            // Initialice and fill our own tables
            X = new double[ndatos][nentradas];
            missing = new boolean[ndatos][nentradas];
            C = new int[ndatos];

            // Maximum and minimum of inputs
            emaximo = new double[nentradas];
            eminimo = new double[nentradas];

            // All values are casted into double/integer
            nclases = 0;
            for (int i = 0; i < ndatos; i++) {
                keel.Dataset.Instance inst = IS.getInstance(i);
                for (int j = 0; j < nentradas; j++) {
                    X[i][j] = IS.getInputNumericValue(i, j); //inst.getInputRealValues(j);
                    missing[i][j] = inst.getInputMissingValues(j);
                    if (X[i][j] > emaximo[j] || i == 0) {
                        emaximo[j] = X[i][j];
                    }
                    if (X[i][j] < eminimo[j] || i == 0) {
                        eminimo[j] = X[i][j];
                    }
                }

                if (noOutputs) {
                    C[i] = 0;
                } else {
                    C[i] = (int) IS.getOutputNumericValue(i, 0); //(int)inst.getOutputRealValues(i);
                }
                if (C[i] > nclases) {
                    nclases = C[i];
                }
            }
            nclases++;
            System.out.println("Number of classes=" + nclases);

        } catch (Exception e) {
            System.out.println("DBG: Exception in readSet");
            e.printStackTrace();
        }
    }

    /**
     * It returns a string with the file header
     * @return String a string with the file header
     */
    public String copiaCabeceraTest() {
        // Header of the output file
        String p = new String("");
        p = "@relation " + Attributes.getRelationName() + "\n";
        p += Attributes.getInputAttributesHeader();
        p += Attributes.getOutputAttributesHeader();
        p += Attributes.getInputHeader() + "\n";
        p += Attributes.getOutputHeader() + "\n";
        p += "@data\n";
        return p;
    }

    /**
     * It converts all values of the data-set to the interval [0,1]
     */
    public void normaliza() {
        int atts = this.getnentradas();
        double maximos[] = new double[atts];
        for (int j = 0; j < atts; j++) {
            maximos[j] = 1.0 / (emaximo[j] - eminimo[j]);
        }
        for (int i = 0; i < this.getndatos(); i++) {
            for (int j = 0; j < atts; j++) {
                if (isMissing(i, j)) {
                    ; //no escojo este ejemplo
                } else {
                    X[i][j] = (X[i][j] - eminimo[j]) * maximos[j];
                }
            }
        }
    }

    /**
     * It returns the types of each input (NOMINAL[0] or NUMERICAL[1])
     * @return int[] An array that contains 0 or 1 wether the attributes are nominal or numerical
     */
    public int[] tiposVar() {
        int[] tipos = new int[this.nentradas];
        for (int i = 0; i < this.nentradas; i++) {
            tipos[i] = 1;
            if (Attributes.getAttribute(i).getType() == Attribute.NOMINAL) {
                tipos[i] = 0;
            }
        }
        return tipos;
    }

    /**
     * It computes the most common values for each attribute
     */
    public void calculaMasComunes() {
        comunes = new int[nentradas];
        int[] aux = new int[ndatos];
        for (int i = 0; i < nentradas; i++) {
            for (int j = 0; j < ndatos; j++) {
                if (this.isMissing(j, i)) {
                    aux[j] = -1;
                } else {
                    aux[j] = (int) X[j][i];
                }
            }
            Arrays.sort(aux);
            int mascomun = aux[0];
            int contador = 1, j;
            for (j = 1; (aux[j] == mascomun) && (j < ndatos - 1); j++, contador++) {
                ;
            }
            int contador2 = 1;
            int mascomun2 = aux[j];
            if (j + 1 < ndatos) {
                for (j = j + 1; j < ndatos; j++) {
                    if (aux[j] == mascomun2) {
                        contador2++;
                    } else {
                        mascomun2 = aux[j];
                        if (contador2 > contador) {
                            contador = contador2;
                            mascomun = mascomun2;
                            contador2 = 1;
                        }
                    }
                }
            }
            comunes[i] = mascomun;
        }
    }

    /**
     * It return the most common value for the i-th atribute
     * @param i int Attribute id
     * @return int most common value for the i-th atribute
     */
    public int masComun(int i) {
        return comunes[i];
    }

    /**
     * It checks if in the data-set there is any continous input
     * @return boolean True if there exists any continous input. False in other case
     */
    public boolean hayAtributosContinuos() {
        return Attributes.hasRealAttributes();
    }

    /**
     * It returns the name of the variables of the problem
     * @return String[] An Array the name of the variables of the problem
     */
    public String[] dameNombres() {
        String[] salida = new String[nvariables];
        for (int i = 0; i < nentradas; i++) {
            salida[i] = Attributes.getInputAttribute(i).getName();
        }
        salida[nentradas] = Attributes.getOutputAttribute(0).getName();
        return salida;
    }

    /**
     * It return the class values
     * @return String[] An array with the nominal values for the class "id"
     */
    public String[] dameClases() {
        String[] salida = new String[nclases];
        Attribute at = Attributes.getOutputAttribute(0);
        if (at.getType() == at.NOMINAL) {
            for (int i = 0; i < nclases; i++) {
                salida[i] = at.getNominalValue(i);
            }
        } else {
            salida = null;
        }
        return salida;
    }

}
