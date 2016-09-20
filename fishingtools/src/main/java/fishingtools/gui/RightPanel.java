package fishingtools.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import fishingtools.gui.model.FishingRodsTableModel;
import fishingtools.gui.model.SqlFishingRodsTableModel;

public class RightPanel extends JPanel {

	public static JTable table;
	private TableFrame tableFrame;
	private String tittle = "TABLE";

	public static JTable getTable() {
		return table;
	}

	public static void setTable(JTable table) {
		RightPanel.table = table;
	}

	public RightPanel(TableFrame tableFrame) {
		super();
		this.tableFrame = tableFrame;

		setBorder(BorderFactory.createTitledBorder(tittle));
		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		addTable();

	}

	private void addTable() {

		// FishingRodsTableModel model = new FishingRodsTableModel();

		SqlFishingRodsTableModel model = new SqlFishingRodsTableModel();

		table = new JTable(model);
		table.setEditingRow(4);
		table.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		TableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {

			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				
//				if (value instanceof Date) {
					value = f.format(value);
//				} 
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		};

		table.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		table.getColumnModel().getColumn(6).setCellEditor(new DateEditor());
		table.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyReleased(e);
				if (table.getSelectedRow() != -1) {
					if (e.getKeyCode() == KeyEvent.VK_TAB) {
						System.out.println("TAB PRESSED");
						int row = table.getSelectedRow();// get mouse-selected
															// row
						int col = table.getSelectedColumn();// get
															// mouse-selected
															// col
						boolean isEditable = table.isCellEditable(row, col);

						table.editCellAt(row, (isEditable) ? col : col + 1);
						table.transferFocus();
					}
				}
			}

		});

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

	}

}
