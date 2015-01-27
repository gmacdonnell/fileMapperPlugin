package plugintest.ui;

import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;



public class TermLabelProvider extends BaseLabelProvider implements
    ITableLabelProvider {

 // @Override
  public Image getColumnImage(Object element, int columnIndex) {
    return null;
  }

 // @Override
  public String getColumnText(Object element, int columnIndex) {
    RequiredTerm todo = (RequiredTerm) element;
    switch (columnIndex) {
    case 0:
      return todo.getColumn_name();
    case 1:
      return todo.getTable_name();
    }
    return "Not supported";
  }

} 