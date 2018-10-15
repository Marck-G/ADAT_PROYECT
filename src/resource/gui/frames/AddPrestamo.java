package resource.gui.frames;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import resource.gui.frames.components.inputs.DefaultInput;
import resource.model.beans.Estado;

public final class AddPrestamo extends JFrame {
	private static final long serialVersionUID = -330471827742038469L;
	private DefaultInput codigo;
	private DefaultInput dni;
	private DefaultInput fecha_alta;
	private DefaultInput fecha_dev;
	private JComboBox<Estado> estado;
	private DefaultComboBoxModel<Estado> model;
	
	private AddPrestamo() {
		elementos();
	}
	
	private void elementos() {
		codigo	= new DefaultInput( getContentPane() );
		dni		= new DefaultInput( getContentPane() );
		fecha_alta = new DefaultInput( getContentPane() );
		fecha_dev  = new DefaultInput( getContentPane() );
		model = new DefaultComboBoxModel<Estado>( Estado.values() );
		estado = new JComboBox<>( model );
	}
	
	
}
