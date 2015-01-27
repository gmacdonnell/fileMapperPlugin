package fileMapper.workBench;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class Snippet106 {

public static void main (String [] args) {
  Display display = new Display ();
  Shell shell = new Shell (display);
  shell.setLayout (new RowLayout (SWT.VERTICAL));
  final Table table = new Table (shell, SWT.BORDER | SWT.MULTI);
  table.setHeaderVisible (true);
  for (int i=0; i<4; i++) {
    TableColumn column = new TableColumn (table, SWT.NONE);
    column.setText ("Column " + i);
  }
  final TableColumn [] columns = table.getColumns ();
  for (int i=0; i<12; i++) {
    TableItem item = new TableItem (table, SWT.NONE);
    for (int j=0; j<columns.length; j++) {
      item.setText (j, "Item " + i);
    }
  }
  for (int i=0; i<columns.length; i++) columns [i].pack ();
  Button button = new Button (shell, SWT.PUSH);
  final int index = 1;
  button.setText ("Insert Column " + index + "a");
  button.addListener (SWT.Selection, new Listener () {
    public void handleEvent (Event e) {
      TableColumn column = new TableColumn (table, SWT.NONE, index);
      column.setText ("Column " + index + "a");
      TableItem [] items = table.getItems ();
      for (int i=0; i<items.length; i++) {
        items [i].setText (index, "Item " + i + "a");
      }
      column.pack ();
    }
  });
  shell.pack ();
  shell.open ();
  while (!shell.isDisposed ()) {
    if (!display.readAndDispatch ()) display.sleep ();
  }
  display.dispose ();
}

}
