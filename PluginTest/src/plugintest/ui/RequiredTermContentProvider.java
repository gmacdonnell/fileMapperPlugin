package plugintest.ui;





import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;



public class RequiredTermContentProvider implements IStructuredContentProvider {

 // @Override
  public Object[] getElements(Object inputElement) {
    List<RequiredTerm> list = (List<RequiredTerm>) inputElement;
    return list.toArray();
  }

 // @Override
  public void dispose() {
    
  }

 // @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }

} 