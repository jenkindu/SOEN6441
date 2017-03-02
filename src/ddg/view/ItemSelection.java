package ddg.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;

import ddg.Config;
import ddg.item.entity.BaseItem;
import ddg.model.Fighter;
import ddg.model.FighterModel;
import ddg.model.ItemEditorModel;
import ddg.utils.UtilityStorage;
import ddg.utils.Utils;
import ddg.view.component.DComboBox;
import ddg.view.component.ListEntryCellRenderer;

public class ItemSelection extends JDialog implements ActionListener, ListSelectionListener{
    private final JButton selectBtn = new JButton("      Select      ");
    private final JButton cancelBtn = new JButton("    Cancel  ");
    
    private  ItemEditorModel itemListModel = new ItemEditorModel(); 
    private final JList itemJList = new JList();
    private final JScrollPane itemListPane = new JScrollPane(itemJList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
    
    private final JLabel nameLabel = new JLabel(" Equipment name ");
    private final JLabel typeLabel = new JLabel(" Equipment type ");
    private final JLabel attributeLabel = new JLabel(" Attribute ");
    private final JLabel bonusLabel = new JLabel(" Value ");


	ArrayList<BaseItem> al1 = new ArrayList<>();
	public int id = 100;
	public String fighterKeyName = "fighter111";
    private static CharacterEditLayout owner;
    public BaseItem selectedItem;

    public static void main(String[] args) 
    {
        //call the method to build the frame
    	ItemSelection frame1 = new ItemSelection();
//        createAndShowGUI();
    }//end of main()
    ///////////////////////////////////////////////////////////////////////////
    public static void createAndShowGUI(CharacterEditLayout ownerFrame) 
    {

        owner = (CharacterEditLayout) ownerFrame;
        System.out.println("========"+owner);
        //new up  this class, & call constructor, --due to extends, it is a frame
    	ItemSelection frame1 = new ItemSelection(); 
        frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame1.pack();
//        frame1.setResizable(false);
        frame1.setVisible(true);
    }//end of createAndShowGUI()
    ///////////////////////////////////////////////////////////////////////////
    ItemSelection()
    {
        //build the frame with a title and define layout
        super();
        setTitle("Select Item 2");
        setModal(true);
        
		String g = Utils.readFile(Config.ITEM_FILE);
		this.itemListModel = Utils.fromJson(g, ItemEditorModel.class);
		if (this.itemListModel == null) {
			this.itemListModel = new ItemEditorModel();
		}
		
        setLayout(new BorderLayout());
        JPanel backPanel= new JPanel(new BorderLayout());
        JPanel characterPanel= new JPanel(new BorderLayout());
        JPanel attributesPanel= new JPanel(new GridLayout(8,3,5,5));
        JPanel backpackPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel= new JPanel(new GridLayout(8,1,5,5));
        JPanel backpackListPanel = new JPanel(new BorderLayout());
        ImageIcon icon = new ImageIcon("icon1.jpg");  
        icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),  
                icon.getIconHeight(), Image.SCALE_DEFAULT)); 
//        id = 2;

        add(backPanel, BorderLayout.NORTH);
//        backPanel.setPreferredSize(new Dimension(600,300));
        backPanel.add(characterPanel, BorderLayout.WEST);
        backPanel.add(attributesPanel, BorderLayout.CENTER);
        backPanel.add(backpackPanel, BorderLayout.EAST);
        backpackPanel.add(buttonsPanel, BorderLayout.SOUTH);
//        backpackPanel.add(backpackListPanel, BorderLayout.CENTER);
        itemJList.setPreferredSize(new Dimension(200,560));
        characterPanel.add(backpackListPanel, BorderLayout.CENTER);
        attributesPanel.setPreferredSize(new Dimension(600,320));
        JLabel lb1 = new JLabel(" ");
        JLabel nameModiferL = new JLabel(" L ");
        JLabel levelModiferL = new JLabel(" L ");
        JLabel strengthModiferL = new JLabel(" L ");
        lb1.setBorder(new LineBorder(Color.BLACK));
        nameModiferL.setBorder(new LineBorder(Color.BLACK));
        levelModiferL.setBorder(new LineBorder(Color.BLACK));
        strengthModiferL.setBorder(new LineBorder(Color.BLACK));
        lb1.setPreferredSize(new Dimension(20,15));
        lb1.setBounds(0, 0, 20, 15);
        lb1.setIcon(icon);
        lb1.setText(" 2 ");
        attributesPanel.add(new JLabel("   1  "));
        attributesPanel.add(lb1);
        attributesPanel.add(new JLabel("   L  "));
        attributesPanel.add(new JLabel(" Name: "));
        attributesPanel.add(nameLabel);
        attributesPanel.add(new JLabel("   L  "));
        attributesPanel.add(new JLabel(" Type: "));
        attributesPanel.add(typeLabel);
        attributesPanel.add(new JLabel("  L  "));
        attributesPanel.add(new JLabel(" Attribute: "));
        attributesPanel.add(attributeLabel);
        attributesPanel.add(new JLabel("  L   "));
        attributesPanel.add(new JLabel(" Value  "));
        attributesPanel.add(bonusLabel);
        attributesPanel.add(new JLabel("  L   "));
        attributesPanel.add(new JLabel("  L   "));
        attributesPanel.add(new JLabel("  L   "));
        attributesPanel.add(new JLabel("  L   "));
        attributesPanel.add(new JLabel("  L   "));
        attributesPanel.add(new JLabel("  L   "));
        attributesPanel.add(new JLabel("  L   "));
        attributesPanel.add(new JLabel("  L   "));
        attributesPanel.add(new JLabel("  L   "));
        attributesPanel.add(new JLabel("  L   "));
        
        buttonsPanel.add(new JLabel("  L  "));
        buttonsPanel.add(selectBtn);
        buttonsPanel.add(cancelBtn);
        buttonsPanel.add(new JLabel("  L  "));
        buttonsPanel.setSize(300,500);
//        itemJList.addListSelectionListener(this);
    	addListView();

        

    	selectBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
//    			BaseItem tempItem = UtilityStorage.getItem();
    			BaseItem tempItem = new BaseItem("Helmet");
    			owner.selectedItem = tempItem;
    			if (owner.wearingType.equals(tempItem.getName())){
    				System.out.println("Not correct type=========");
    			}
    			owner.getOwner().fighter.helmetIsOn = true;
//    			owner.getOwner().fighter.getWorn().add(selectedItem); 
    			owner.getOwner().fighter.getBackpack().add(selectedItem);   			
    			System.out.println(tempItem.getId() + " " + tempItem.getName() + " " + tempItem.getIncrease() + " " + tempItem.getBonus());
    			dispose();
            }
        });
    	cancelBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			dispose();
            }
        });    	

        backpackListPanel.add(itemListPane, BorderLayout.CENTER);

//        focusManage();
    }//end of constructor
    
	private void addListView() {		
		DefaultListModel l = itemListModel.getListModel();
		itemJList.setModel(l);
		itemJList.setCellRenderer(new ListEntryCellRenderer());
		itemJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemJList.addListSelectionListener(this);
		itemJList.setVisibleRowCount(15);
		itemListPane.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT-3 * Config.BTN_HEIGHT));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
    public void valueChanged(ListSelectionEvent e)
    {
		if (e.getValueIsAdjusting() == false) {
			int index = itemJList.getSelectedIndex();
			if(index >= 0) {
				System.out.println("list select:"+index);
				BaseItem item = itemListModel.getItemByIndex(index);
				
				nameLabel.setText(item.getId());
				typeLabel.setText(item.getName());
				attributeLabel.setText(item.getIncrease());
				bonusLabel.setText(Integer.toString(item.getBonus()));
				selectedItem = item;
				UtilityStorage.setItem(item);
			}
		}
        System.out.println("value changed");
    }
	
	public ItemSelection getThisFrame(){
		return this;
	}
	
	public int getID(){
		return id;
	}
 }
