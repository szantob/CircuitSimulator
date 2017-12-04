package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.PartArray;
import main.Main;

public class ProgramMenu extends JMenuBar{
	private static final long serialVersionUID = 1L;
	
	public ProgramMenu() {
		initialize();
	}
	private void initialize() {
		JMenu mnFile = new JMenu("File");
			JMenuItem mntmNew = new JMenuItem("New");
				mntmNew.addActionListener(new newActionListener());
			mnFile.add(mntmNew);
			JMenuItem mntmSave = new JMenuItem("Save");
				mntmSave.addActionListener(new saveActionListener());
			mnFile.add(mntmSave);
			JMenuItem mntmSaveAs = new JMenuItem("Save As..");
				mntmSaveAs.addActionListener(new saveAsActionListener());
			mnFile.add(mntmSaveAs);
			JMenuItem mntmOpen = new JMenuItem("Open");
				mntmOpen.addActionListener(new openActionListener());
			mnFile.add(mntmOpen);
		add(mnFile);
		JMenu mnEdit = new JMenu("Edit");
		JMenu mnAsd = new JMenu("Asd");
			JMenuItem mntmAsd = new JMenuItem("asd");
			mnAsd.add(mntmAsd);
		mnEdit.add(mnAsd);
		add(mnEdit);
		JMenu mnSimulate = new JMenu("Simulate");
			JMenuItem mnStart = new JMenuItem("Start");
			mnStart.addActionListener(new startsimActionListener());
			mnSimulate.add(mnStart);
			JMenuItem mnPause = new JMenuItem("Pause");
			mnSimulate.add(mnPause);
		add(mnSimulate);
	}
}
class saveAsActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser(Paths.get("").toAbsolutePath().toString());
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		  File file = fileChooser.getSelectedFile();
		  Main.partArray.saveAs(file.getPath());
		}
	}
}
class saveActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		Main.partArray.save();
	}
}
class openActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser(Paths.get("").toAbsolutePath().toString());
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			String returnStr = PartArray.loadAs(file.getPath());
			System.out.println(returnStr);
		}
	}
}
class newActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		PartArray.newPage();	
	}	
}
class startsimActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		Main.tokenContainer.startSim();
	}	
}



