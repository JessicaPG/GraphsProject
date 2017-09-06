/***********************************************************************************************************************
 * Copyright (c) July 2017 Jessica Pérez Guijarro
 * All rights reserved
 ***********************************************************************************************************************/
package org.graphstream;

import algorithmsExtension.DijkstraExtension;
import algorithmsExtension.KruskalExtension;
import algorithmsExtension.PrimExtension;
import org.graphstream.algorithm.Dijkstra;
import utils.CreationGraph;

;
import java.io.IOException;

public class App
{
    public static void main( String[] args ) {


        try {
            System.out.println("------------------------------");
            System.out.println("*     Choose an option       *");
            System.out.println("------------------------------\n");
            System.out.println("  1 - Dijkstra algorithm \n");
            System.out.println("  2 - Kruskal algorithm  \n");
            System.out.println("  3 - Prim algorithm \n");

           char message = (char) System.in.read();

           switch (message){
               case '1':
                   String [] arg = CreationGraph.readSourceAndDestinationNodes();
                   DijkstraExtension.main(arg);
                   break;
               case '2':
                   KruskalExtension.main();
                   break;
               case '3':
                   PrimExtension.main();
                   break;

           }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
