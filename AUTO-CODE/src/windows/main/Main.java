package windows.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jdbc.ColumnInfo;
import jdbc.DbMetaData;
import jdbc.SchemaInfo;
import jdbc.TableInfo;
import other.Utils;
import template.API;
import template.DAO;
import template.POJO;
import template.Template;
import template.java.JavaAPI;
import template.java.JavaDAO;
import template.java.JavaPOJO;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ListModel;
import java.awt.SystemColor;
import javax.swing.event.ListSelectionListener;

import io.WriteFile;

import javax.swing.event.ListSelectionEvent;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {
	private JPanel contentPane;
	private DbMetaData metaData;
	private List<SchemaInfo> schemas;
	
	private JList list;
	private JList list_1;
	
	/**
	 * Create the frame.
	 */
	public Main(DbMetaData metaData) {
		this.metaData = metaData;
		this.schemas  = metaData.getAllSchemaData();
		this.setTitle("Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		String[] schemaNames = new String[schemas.size() + 1];
		
		int i = 1;
		schemaNames[0] = "All Schema";
		for (SchemaInfo schema: schemas){
			schemaNames[i++] = schema.getScheamName();
		}
		
		list = new JList(schemaNames);
		scrollPane.setViewportView(list);
		list.setToolTipText("");
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				int index = list.getSelectedIndex();
				
				if (!list.getValueIsAdjusting()) return;
				if (index > 0){
					List<TableInfo> talbes = schemas.get(index - 1).getTables();
					String[] tableNames = new String[talbes.size() + 1];
					int i = 1;
					tableNames[0] = "All Table";
					for (TableInfo table: talbes){
						tableNames[i++] = table.getTableName();
					}
					
					list_1.setListData(tableNames);
					list_1.setSelectedIndex(0);
				}else {
					list_1.setListData(new String[0]);
				}
			}
		});
		
		list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		list_1.setToolTipText("");
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblSchema = new JLabel("\u3000Schema");
		lblSchema.setForeground(Color.WHITE);
		lblSchema.setBackground(SystemColor.textHighlight);
		lblSchema.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblSchema.setOpaque(true);
		
		JLabel lblTable = new JLabel("\u3000Table");
		lblTable.setForeground(Color.WHITE);
		lblTable.setBackground(SystemColor.textHighlight);
		lblTable.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblTable.setOpaque(true);
		
		JLabel lblOutput = new JLabel("\u3000Output Type");
		lblOutput.setOpaque(true);
		lblOutput.setForeground(Color.WHITE);
		lblOutput.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblOutput.setBackground(SystemColor.textHighlight);
		
		JCheckBox chckbxPojo = new JCheckBox("POJO");
		chckbxPojo.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		
		JCheckBox chckbxDao = new JCheckBox("DAO");
		chckbxDao.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		
		JCheckBox chckbxApi = new JCheckBox("API");
		chckbxApi.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		
		JButton btnOutput = new JButton("Output");
		btnOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle("Save To");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);

			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			    	int schemaIndex = list  .getSelectedIndex();
			    	int tableIndex  = list_1.getSelectedIndex();	
			    	String path = chooser.getSelectedFile() + "\\";
			    	boolean selectedPOJO = chckbxPojo.isSelected();
			    	boolean selectedDAO  = chckbxDao .isSelected();
			    	boolean selectedAPI  = chckbxApi .isSelected();
			    	List<SchemaInfo> selectedSchema;
			    	
			    	if (schemaIndex == 0){
			    		selectedSchema = schemas;
			    	} else {
			    		selectedSchema = new ArrayList<SchemaInfo>();
			    		
			    		try {
							selectedSchema.add((SchemaInfo) schemas.get(schemaIndex - 1).clone());
							
							if (tableIndex > 0){
						    	List<TableInfo> selectedTable = new ArrayList<TableInfo>();
						    	selectedTable.add((TableInfo) schemas.get(schemaIndex - 1).getTables().get(tableIndex - 1).clone());
						    	selectedSchema.get(0).setTables(selectedTable);
				    		}
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	}
			    	
			    	POJO pojo = new JavaPOJO();
			    	DAO  dao  = new JavaDAO();
			    	API  api  = new JavaAPI();
			    	String fileSuffix = ".java";
			    	String pojoSuffix = "Model";
			    	String daoSuffix  = "DAO";
			    	String apiSuffix  = "API";
			    	
			    	String pojoName = "";
			    	String daoName  = "";
			    	String apiName  = "";
			    	
			    	List<String> importName = new ArrayList<String>();
			    	
			    	for (SchemaInfo schema: selectedSchema){
						for (TableInfo table: schema.getTables()){
							if (selectedPOJO){
								pojoName = Utils.formatFileName(table.getTableName()) + pojoSuffix;
								
								WriteFile.write(
									path + pojoName + fileSuffix, 
									Template.getPOJO(pojo, pojoName, table, importName, pojoSuffix)
								);
							}
									
							if (selectedDAO){
								pojoName = Utils.formatFileName(table.getTableName()) + pojoSuffix;
								daoName  = Utils.formatFileName(table.getTableName()) + daoSuffix ;
								
								WriteFile.write(
									path + daoName + fileSuffix, 
									Template.getDAO(dao, pojoName, daoName, table, importName, pojoSuffix, daoSuffix)
								);
							} 
							
							if (selectedAPI){
								pojoName = Utils.formatFileName(table.getTableName()) + pojoSuffix;
								daoName  = Utils.formatFileName(table.getTableName()) + daoSuffix ;
								apiName  = Utils.formatFileName(table.getTableName()) + apiSuffix ;
								
								WriteFile.write(
									path + apiName + fileSuffix, 
									Template.getAPI(api, pojoName, daoName, apiName, table, importName)
								);
							} 
						}
					}
			    }
			}
		});
		btnOutput.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblSchema, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addComponent(chckbxPojo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxDao)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxApi, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblOutput, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOutput))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
								.addComponent(lblTable, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSchema)
						.addComponent(lblTable))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane_1)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblOutput, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(chckbxPojo)
								.addComponent(chckbxDao, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(chckbxApi, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOutput)
							.addContainerGap())))
		);
		
		contentPane.setLayout(gl_contentPane);
	}
}
