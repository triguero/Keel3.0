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

/**
 * <p>
 * @author Written by Albert Orriols (La Salle, Ram�n Llull University - Barcelona) 28/03/2004
 * @author Modified by Xavi Sol� (La Salle, Ram�n Llull University - Barcelona) 03/12/2008
 * @version 1.1
 * @since JDK1.2
 * </p>
 */


package keel.Algorithms.Genetic_Rule_Learning.XCS;
import  keel.Algorithms.Genetic_Rule_Learning.XCS.KeelParser.Config;
import java.util.*;
import java.lang.*;
import java.io.*;


public class TFreeMutation implements TernaryMutation {
/**
 * <p>
 * This class implements the free mutation. So, a gene can be changed for
 * any valid value in the representation.
 * </p>
 */
	
	
  ///////////////////////////////////////
  // operations


/**
 * <p>
 * Mutates the character.
 * </p>
 * <p>
 * 
 * @param pos is the value of the classifier allele.
 * </p>
 * <p>
 * @param currentState is the value of that classifier position in the
 * environment. It in needed for the niched mutation.
 * </p>
 * <p>
 * @return a char with the mutated character
 * </p>
 */
    public char mutate(char pos, char currentState) {        
        char newPos = ' ';
        if (Config.rand() < Config.pM){
        	do{
        		newPos = Config.charVector[((int)(Config.numberOfCharacters * Config.rand())) % Config.numberOfCharacters];
        	}while (pos == newPos);
		return newPos;
	}
        return pos;
    } // end mutate        

} // end TFreeMutation




