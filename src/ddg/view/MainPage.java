package ddg.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import ddg.Config;
import ddg.view.component.OButton;
/**
 * This class is show main page
 * 
 * @author Zhen Du
 * @date Feb 5, 2017
 */
public class MainPage extends JPanel implements ActionListener {

	private ActionListener listener;
	
	public MainPage(ActionListener a) {
		this.listener = a;
		initView();
	}

	private void initView() {
		BorderLayout l = new BorderLayout();
	    setLayout(l);
		JPanel contentPanel = new JPanel();
		JTextArea ddg = new JTextArea("DDG");
		ddg.setEditable(false);
		contentPanel.add(ddg);
	    add(contentPanel, BorderLayout.CENTER);
	    
	    addOption();
	}

	private void addOption() {
		JPanel optionPanel = new JPanel();
	    optionPanel.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT));
	    optionPanel.setBorder(Config.border);
	    JTextArea optionTitle = new JTextArea("OPTION");
	    optionTitle.setEditable(false);
	    OButton charactorBtn = new OButton("CHARACTOR", this);
	    charactorBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    OButton campaignBtn = new OButton("CAMPAIGN", this);
	    campaignBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    OButton mapBtn = new OButton("MAP", this);
	    mapBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    OButton itemBtn = new OButton("ITEM", this);
	    itemBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    OButton continueBtn = new OButton("CONTINUE", this);
	    continueBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    OButton startBtn = new OButton("START", this);
	    startBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    optionPanel.add(optionTitle);
	    optionPanel.add(charactorBtn);
	    optionPanel.add(campaignBtn);
	    optionPanel.add(mapBtn);
	    optionPanel.add(itemBtn);
	    optionPanel.add(continueBtn);
	    optionPanel.add(startBtn);
	    add(optionPanel, BorderLayout.EAST);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("CHARACTOR")) {
			System.out.println("OPEN CHARACTOR");
		} else if(e.getActionCommand().equals("CAMPAIGN")) {
			System.out.println("OPEN CAMPAIGN");
		} else if(e.getActionCommand().equals("MAP")) {
			System.out.println("OPEN MAP");
		} else if(e.getActionCommand().equals("ITEM")) {
			System.out.println("OPEN ITEM");
		} else if(e.getActionCommand().equals("CONTINUE")) {
			System.out.println("OPEN CONTINUE");
		} else if(e.getActionCommand().equals("START")) {
			System.out.println("OPEN START");
		}
		listener.actionPerformed(e);
	}
}
