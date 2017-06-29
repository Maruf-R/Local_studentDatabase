import javax.swing.JFrame;

import studentdata.Connector;
import studentdata.DataTable;
import UI.*;

/**
 * @author Maruf Rahman - k1461976*/

public class MainApp {

	public static void main(String[] args) {
		AppWindow window = new AppWindow();
		String temp[] = new String[4];
		Integer tempInt;
		
		// Create a Connector object and open the connection to the server
        Connector server = new Connector();
        boolean success = server.connect("koala_kontrol", "e773894209986374e90c3ad7776b7fbb");
        
        if (success == false) {
            System.out.println("Fatal error: could not open connection to server");
            System.exit(1);
        }
        
        DataTable data = server.getData();
        
        int rowCount = data.getRowCount();
        for (int row = 0; row < rowCount; ++row) {
            for (int col = 0; col < 4; ++col) {
                if (col > 0) {
                    System.out.print(",");
                }
                System.out.print(data.getCell(row,col));
                temp[col] = data.getCell(row, col);
            }
            tempInt = Integer.parseInt(temp[0]);
            window.setData(temp[1], tempInt, temp[2], temp[3]);
            System.out.println();
        }
	}
}
