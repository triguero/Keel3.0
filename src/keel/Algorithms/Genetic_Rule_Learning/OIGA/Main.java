/***********************************************************************

	This file is part of KEEL-software, the Data Mining tool for regression, 
	classification, clustering, pattern mining and so on.

	Copyright (C) 2004-2010
	
	F. Herrera (herrera@decsai.ugr.es)
    L. S�nchez (luciano@uniovi.es)
    J. Alcal�-Fdez (jalcala@decsai.ugr.es)
    S. Garc�a (sglopez@ujaen.es)
    A. Fern�ndez (alberto.fernandez@ujaen.es)
    J. Luengo (julianlm@decsai.ugr.es)

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see http://www.gnu.org/licenses/
  
**********************************************************************/

package keel.Algorithms.Genetic_Rule_Learning.OIGA;


/**
 * Main class
 * <p>
 * @author Written by Juli�n Luengo Mart�n 08/02/2007
 * @version 0.2
 * @since JDK 1.5
 * </p>
 */
public class Main {

	/**
	 * It runs the algorithm
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		Oiga oiga;
		if (args.length != 1)
            System.err.println("Error. Only one parameter is needed.");
		
		oiga = new Oiga(args[0]);
		
		oiga.run();
	}

}