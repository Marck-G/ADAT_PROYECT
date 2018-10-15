package resource.gui.frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setLayout( new GridBagLayout() );
		elementos();
		pack();
		setVisible( true );
	}
	
	private void elementos() {
		codigo	= new DefaultInput( getContentPane() );
		dni		= new DefaultInput( getContentPane() );
		fecha_alta = new DefaultInput( getContentPane() );
		fecha_dev  = new DefaultInput( getContentPane() );
		model = new DefaultComboBoxModel<Estado>( Estado.values() );
		estado = new JComboBox<>( model );
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 10 );
		getContentPane().add( new JLabel( String.format( "%30s", "Código del libro:" ) ), c );
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;		
		getContentPane().add( new JLabel( String.format( "%30s", "DNI del alumno:" ) ), c );
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;		
		getContentPane().add( new JLabel( String.format( "%30s", "Fecha de alta:" ) ), c );
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;		
		getContentPane().add( new JLabel( String.format( "%30s", "Fecha de devolución:" ) ), c );
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;		
		getContentPane().add( new JLabel( String.format( "%30s", "Estado:" ) ), c );
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;		
		c.insets = new Insets(0, 0, 0, 0 );
		getContentPane().add( codigo, c );		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;		
		getContentPane().add( dni, c );
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;		
		getContentPane().add( fecha_alta, c );
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;		
		getContentPane().add( fecha_dev, c );
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		getContentPane().add( estado, c );
	}
	
	public static void main(String[] args) {
		new AddPrestamo();
	}
	
	
}
