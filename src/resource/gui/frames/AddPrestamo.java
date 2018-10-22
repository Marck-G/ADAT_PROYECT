package resource.gui.frames;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import resource.controller.AddPrestamoController;
import resource.gui.frames.components.buttons.DefaultButton;
import resource.gui.frames.components.buttons.LigthButton;
import resource.gui.frames.components.inputs.DefaultInput;
import resource.model.beans.Estado;
import resource.utils.Formating;

public final class AddPrestamo extends JFrame {
	private static final long serialVersionUID = -330471827742038469L;
	private DefaultInput codigo;
	private DefaultInput dni;
	private DefaultInput fecha_alta;
	private DefaultInput fecha_dev;
	private JComboBox<Estado> estado;
	private DefaultComboBoxModel<Estado> model;
	private LigthButton	aceptar;
	private DefaultButton cancelar;
	
	public AddPrestamo() {
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setTitle("Agregar Prestamo");
		setLayout( new GridBagLayout() );
		elementos();
		setMinimumSize(new Dimension(500, 390));
		setLocationRelativeTo( null );
		setVisible( true );
	}
	
	private void elementos() {
		codigo	= new DefaultInput( getContentPane() );
		codigo.setName( "C&oacute;digo del libro" );
		codigo.setPlaceholderText("Código del libro");
		dni		= new DefaultInput( getContentPane() );
		dni.setName( "DNI" );
		dni.setPlaceholderText("XXXXXXXXA");
		fecha_alta = new DefaultInput( getContentPane() );
		fecha_alta.setName( "Fecha de alta" );
		fecha_alta.setPlaceholderText("dd/mm/yy");
		fecha_dev  = new DefaultInput( getContentPane() );
		fecha_dev.setName( "Fecha de devoluci&oacute;n" );
		fecha_dev.setPlaceholderText("dd/mm/yy");
		model = new DefaultComboBoxModel<Estado>( Estado.values() );
		estado = new JComboBox<Estado>( model );
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 0, 0, 10 );
		getContentPane().add( new JLabel( String.format( "%-30s", 
				"Código del libro:" ) ), c );
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;		
		getContentPane().add( new JLabel( String.format( "%-30s", 
				"DNI del alumno:" ) ), c );
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;		
		getContentPane().add( new JLabel( String.format( "%-30s", 
				"Fecha de alta:" ) ), c );
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;		
		getContentPane().add( new JLabel( String.format( "%-30s", 
				"Fecha de devolución:" ) ), c );
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;		
		getContentPane().add( new JLabel( String.format( "%-30s", 
				"Estado:" ) ), c );
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;		
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
		
		aceptar = new LigthButton("ACEPTAR");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 5;
		c.insets= new Insets(20, -5, 10, 20);
		getContentPane().add( aceptar, c );
		
		cancelar = new DefaultButton("CANCELAR");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		getContentPane().add( cancelar, c );
	}
	
	public static void main(String[] args) {
		AddPrestamoController.instancia()._init( new AddPrestamo() );
		
	}

	/**
	 * @return the codigo
	 */
	public DefaultInput getCodigo() {
		return codigo;
	}

	/**
	 * @return the dni
	 */
	public DefaultInput getDni() {
		return dni;
	}

	/**
	 * @return the fecha_alta
	 */
	public DefaultInput getFecha_alta() {
		return fecha_alta;
	}

	/**
	 * @return the fecha_dev
	 */
	public DefaultInput getFecha_dev() {
		return fecha_dev;
	}

	/**
	 * @return the estado
	 */
	public JComboBox<Estado> getEstado() {
		return estado;
	}

	/**
	 * @return the aceptar
	 */
	public LigthButton getAceptar() {
		return aceptar;
	}

	/**
	 * @return the cancelar
	 */
	public DefaultButton getCancelar() {
		return cancelar;
	}
	
	
	
	
	
	
}
