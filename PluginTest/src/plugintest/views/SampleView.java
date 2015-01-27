package plugintest.views;

import plugintest.ui.*;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;


public class SampleView extends ViewPart {


  @Override
  public void createPartControl(Composite parent) {
    TableViewer viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
        | SWT.V_SCROLL);
    int operations = DND.DROP_COPY| DND.DROP_MOVE;
    Transfer[] transferTypes = new Transfer[]{TextTransfer.getInstance()};
    viewer.addDragSupport(operations, transferTypes , new TermDragListener(viewer));
    viewer.setContentProvider(new RequiredTermContentProvider());
    viewer.setLabelProvider(new TermLabelProvider());
    viewer.setInput(TermProvider.instance.getModel());
    
  }

  @Override
  public void setFocus() {

  }

} 