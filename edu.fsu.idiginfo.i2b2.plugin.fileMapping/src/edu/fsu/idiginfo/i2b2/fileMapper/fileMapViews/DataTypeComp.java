package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DataTypeComp extends Composite {

	private DataBindingContext m_bindingContext;
	private edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataType dataType = new edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataType();
	private Button isDimensionButton;
	private Text nameText;
	private Text tableCDText;

	public DataTypeComp(
			Composite parent,
			int style,
			edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataType newDataType) {
		this(parent, style);
		setDataType(newDataType);
	}

	public DataTypeComp(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));

		new Label(this, SWT.NONE).setText("IsDimension:");

		isDimensionButton = new Button(this, SWT.CHECK);
		isDimensionButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false));

		new Label(this, SWT.NONE).setText("Name:");

		nameText = new Text(this, SWT.BORDER | SWT.SINGLE);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		new Label(this, SWT.NONE).setText("TableCD:");

		tableCDText = new Text(this, SWT.BORDER | SWT.SINGLE);
		tableCDText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		if (dataType != null) {
			m_bindingContext = initDataBindings();
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	private DataBindingContext initDataBindings() {
		IObservableValue isDimensionObserveWidget = SWTObservables
				.observeSelection(isDimensionButton);
		IObservableValue isDimensionObserveValue = PojoObservables
				.observeValue(dataType, "isDimension");
		IObservableValue nameObserveWidget = SWTObservables.observeText(
				nameText, SWT.Modify);
		IObservableValue nameObserveValue = PojoObservables.observeValue(
				dataType, "name");
		IObservableValue tableCDObserveWidget = SWTObservables.observeText(
				tableCDText, SWT.Modify);
		IObservableValue tableCDObserveValue = PojoObservables.observeValue(
				dataType, "tableCD");
		//
		DataBindingContext bindingContext = new DataBindingContext();
		//
		bindingContext.bindValue(isDimensionObserveWidget,
				isDimensionObserveValue,
				new org.eclipse.core.databinding.UpdateValueStrategy(
						UpdateValueStrategy.POLICY_NEVER), null);
		bindingContext.bindValue(nameObserveWidget, nameObserveValue,
				new org.eclipse.core.databinding.UpdateValueStrategy(
						UpdateValueStrategy.POLICY_NEVER), null);
		bindingContext.bindValue(tableCDObserveWidget, tableCDObserveValue,
				new org.eclipse.core.databinding.UpdateValueStrategy(
						UpdateValueStrategy.POLICY_NEVER), null);
		//
		return bindingContext;
	}

	public edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataType getDataType() {
		return dataType;
	}

	public void setDataType(
			edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataType newDataType) {
		setDataType(newDataType, true);
	}

	public void setDataType(
			edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataType newDataType,
			boolean update) {
		dataType = newDataType;
		if (update) {
			if (m_bindingContext != null) {
				m_bindingContext.dispose();
				m_bindingContext = null;
			}
			if (dataType != null) {
				m_bindingContext = initDataBindings();
			}
		}
	}

	@Override
	public String toString()
	{
		return dataType.getName();
	}
}
