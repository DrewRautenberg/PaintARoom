/* PROJECT:  PaintARoom
 * AUTHOR:  Dr. Kaminski
 * MODIFIED BY: Drew Rautenberg
 * DESCRIPTION:  Program calculates how much paint is needed to paint a room.
 * INPUT:   size of room (length, width, height),
 *          number of doors & number of windows
 * OUTPUT:  number of gallons of paint needed (as a whole number)
 * USER INTERFACE:  INPUT:      5 DialogBoxes
 *                  OUTPUT:     IDE Output/"console" window
 * ASSUMPTIONS:  - square/rectangular room (no slanted walls/dormers/etc.),
 *      - sizes of doors & windows are average (default sizes),
 *      - doors/windows/floor/ceiling are NOT being painted
 * PROCESSING:  Do the algorithm in steps by calling methods to "do the work",
 *      rather than doing one big formula (for now).
 *****************************************************************************/

import javax.swing.JOptionPane;

public class PaintARoom {

    public static void main(String[] args) {

        //-------------------------------------------------------------- GIVENS
        // (final makes these int's NAMED CONSTANTS)
        final int SQ_FT_PER_GALLON = 350;    // for 1 coat
        final int NUM_COATS = 2;             // the default
        final int AVE_DOOR_AREA = 21;        // 7' x 3' on ave. (incl. molding)
        final int AVE_WINDOW_AREA = 9;       // 3' x 3' on ave. (incl. molding)

        //----------------------------------- OUTPUT VARIABLES FOR CALCULATIONS
        int totalArea;
        int winDoorArea;
        int paintableArea;
        int gallons;

        //---------------------------------------------------------- PROCESSING
        totalArea = findWallArea();                  // incl. doors & windows
        winDoorArea = findWinDoorArea(AVE_DOOR_AREA, AVE_WINDOW_AREA);
        paintableArea = totalArea - winDoorArea;
        gallons = findGallonsNeeded(paintableArea, NUM_COATS, SQ_FT_PER_GALLON);

        //-------------------------------------------------------------- OUTPUT
        System.out.println("Need " + gallons + " gallons of paint for " +
                NUM_COATS + " coats");

        //---------------------------------------------------------------------
        System.out.println("\nTHE END");
        System.exit(0);
    } // END OF main

    //*************************************************************************
    // This method returns the room's total wall area (in square feet)
    //      without considering windows or doors.
    private static int findWallArea() {
        String userString = JOptionPane.showInputDialog("Enter room height (8 is normal)?");
        int height = Integer.parseInt(userString);
        int length = Integer.parseInt(JOptionPane.showInputDialog("Enter length of room (a whole number)?"));
        int width = Integer.parseInt(JOptionPane.showInputDialog("Enter width of room (a whole number)?"));

        int area;
        area = 2 * ((length * height) + (width * height));
        return area;
    }

    //*************************************************************************
    // This method finds the room's total square footage for all the doors
    //      and windows (based on average sizes for doors/windows).
    private static int findWinDoorArea(int aveDoorArea, int aveWindowArea) {
        int numDoors = Integer.parseInt(JOptionPane.showInputDialog
                ("Enter number of doors"));
        int numWindows = Integer.parseInt(JOptionPane.showInputDialog
                ("Enter number of windows"));

        int doorArea;
        int winArea;
        int total;
        doorArea = numDoors * aveDoorArea;
        winArea = numWindows * aveWindowArea;
        total = doorArea + winArea;
        return total;
    }

    //*************************************************************************
    // This method finds the total number of gallons needed to paint the given
    // room (based on data user provided and constant values assumed in this
    // program
    private static int findGallonsNeeded(int area, int coats, int sqFtperGallon) {
        int totalGallons;
        int areaWithCoats;
        double numGallons;

        areaWithCoats = area * coats;
        numGallons = areaWithCoats / sqFtperGallon;

        if (numGallons % 1 == 0) {
            totalGallons = (int) numGallons;
        } else {
            totalGallons = (int) numGallons + 1;
        }
        return totalGallons;
    }
} // END OF CLASS (the whole program)