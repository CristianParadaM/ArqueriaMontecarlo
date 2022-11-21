package view.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import view.JFrameMain;

/**
 * @author CRISTIAN DAVID PARADA MARTINEZ
 * @date 8/08/2022
 */
public class JTableResult extends JScrollPane {

	private static final long serialVersionUID = 1L;
	private JTable jTable;
	private ArrayList<Object[]> infoTable;
	private String[] columnNames;
	private int style;
	private TableModel dataModel;
	private DefaultTableCellRenderer cellRender;
	private int rowHeight;

	/**
	 * Constructor de JTableResult
	 */
	public JTableResult(ArrayList<Object[]> info, String[] columnNames, int style) {
		super();
		this.infoTable = info;
		this.columnNames = columnNames;
		this.style = style;
		this.rowHeight = 42 * JFrameMain.HEIGHT_SCREEN / 1080;
		init();
	}

	/**
	 * Metodo que inicia esta tabla
	 * 
	 */
	private void init() {
		this.setOpaque(false);
		this.getViewport().setOpaque(false);
		this.getVerticalScrollBar().setOpaque(false);
		this.getHorizontalScrollBar().setOpaque(false);
		this.setBorder(new LineBorder(Color.BLACK));
		this.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		this.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = style == 0? new Color(91, 155, 213): new Color(255, 153, 153);
			}

		});
		createRenderCell();
		createModel();
		createTable();
		this.setViewportView(jTable);
	}

	private void createTable() {
		jTable = new JTable(dataModel);
		jTable.getTableHeader().setFont(new Font(Constants.FONT_APP, Font.BOLD, 22 * JFrameMain.WIDTH_SCREEN / 1920));
		jTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jTable.getTableHeader().setPreferredSize(new Dimension(0, 40 * JFrameMain.HEIGHT_SCREEN / 1080));
		jTable.getTableHeader().setBackground(style == 0? new Color(91, 155, 213) : new Color(255, 153, 153));
		jTable.getTableHeader().setForeground(Color.WHITE);
		jTable.getTableHeader().setReorderingAllowed(false);
		jTable.getTableHeader().setResizingAllowed(false);

		for (int i = 0; i < columnNames.length; i++) {
			TableColumn tableColumn = jTable.getColumn(columnNames[i]);
			tableColumn.setCellRenderer(cellRender);

		}
		
		jTable.setShowGrid(false);
		jTable.setRowHeight(rowHeight);
	}

	private void createRenderCell() {
		cellRender = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void setValue(Object value) {
				setFont(new Font(Constants.FONT_APP, Font.PLAIN, 25 * JFrameMain.WIDTH_SCREEN / 1920));
				setForeground(Color.BLACK);
				setBackground(new Color(80, 78, 79));
				if (value instanceof Integer) {
					if ((int)value == -1) {
						super.setValue("-");
						return;
					}
				}
				if (value instanceof String) {
					if (value.equals("MALE")) {
						super.setValue("Masculino");
						return;
					}else if(value.equals("FEMALE")){
						super.setValue("Femenino");
						return;
					}
				}
				super.setValue(value);
			}

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean selected,
					boolean focused, int row, int column) {
				super.getTableCellRendererComponent(table, value, selected, focused, row, column);
				if (row % 2 == 0) {
					this.setBackground(new Color(240, 240, 240));
				} else {
					this.setBackground(new Color(255, 255, 255));
				}
				return this;
			}
		};
		cellRender.setHorizontalAlignment(JLabel.CENTER);
	}

	private void createModel() {
		dataModel = new AbstractTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public int getColumnCount() {
				return columnNames.length;
			}

			@Override
			public int getRowCount() {
				return infoTable.size();
			}

			@Override
			public Object getValueAt(int row, int col) {
				return infoTable.get(row)[col];
			}

			@Override
			public String getColumnName(int column) {
				return columnNames[column];
			}

			@Override
			public Class<? extends Object> getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}

			@Override
			public void setValueAt(Object aValue, int row, int column) {
				infoTable.get(row)[column] = aValue;
			}
		};
	}
	
	@Override
	public void setPreferredSize(Dimension preferredSize) {
		int measure = 42 * JFrameMain.WIDTH_SCREEN / 1920;
		int error = 6* JFrameMain.WIDTH_SCREEN / 1920;
		if (measure * infoTable.size() < preferredSize.height) {
			rowHeight = (measure * preferredSize.height)/ (measure * infoTable.size())-error;
			setInfoTable(infoTable, columnNames);
		}
		super.setPreferredSize(preferredSize);
	}
	
	public void setInfoTable(ArrayList<Object[]> infoTable, String[] columNames) {
		this.infoTable = infoTable;
		this.columnNames = columNames;
		createTable();
		this.setViewportView(jTable);
	}
	
}
