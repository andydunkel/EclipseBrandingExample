package com.da.branding.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.part.ViewPart;

import com.da.branding.branding.Branding;

public class View extends ViewPart {
	public View() {
	}

	public static final String ID = "com.da.branding.example.view";
	private Label labelImage;
	
	public void createPartControl(Composite parent) {
		Composite top = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		top.setLayout(layout);
		// top banner
		Composite banner = new Composite(top, SWT.NONE);
		banner.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
		layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 10;
		layout.numColumns = 2;
		banner.setLayout(layout);
		
		// setup bold font
		Font boldFont = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);    
		
		Label l = new Label(banner, SWT.WRAP);
		l.setText("Subject:");
		l.setFont(boldFont);
		l = new Label(banner, SWT.WRAP);
		l.setText("This is a message about the cool Eclipse RCP!");
		
		l = new Label(banner, SWT.WRAP);
		l.setText("From:");
		l.setFont(boldFont);
    
		final Link link = new Link(banner, SWT.NONE);
		link.setText("<a>nicole@mail.org</a>");
		link.addSelectionListener(new SelectionAdapter() {    
			public void widgetSelected(SelectionEvent e) {
				MessageDialog.openInformation(getSite().getShell(), "Not Implemented", "Imagine the address book or a new message being created now.");
			}    
		});
    
		l = new Label(banner, SWT.WRAP);
		l.setText("Date:");
		l.setFont(boldFont);
		l = new Label(banner, SWT.WRAP);
		l.setText("10:34 am");
		new Label(banner, SWT.NONE);
		
		Button btnDoSomething = new Button(banner, SWT.NONE);
		btnDoSomething.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openFile();
			}
		});
		btnDoSomething.setText("Open File");
		
		Composite composite = new Composite(top, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		labelImage = new Label(composite, SWT.NONE);
		labelImage.setImage(Branding.getInstance().getImage());
	}

	/**
	 * Open a file and display its content
	 */
	protected void openFile() {		
		try {
			String content = loadFileToString(Branding.getInstance().getFile());
			MessageDialog.openInformation(this.getSite().getShell(), "File content", content);
		} catch (Exception e) {
			e.printStackTrace();
		}						
	}

	public void setFocus() {
		
	}
	
	/**
	 * Loads a file to String
	 * @param fileName - file name for example "C:\\MeineFile.txt"
	 * @return
	 * @throws IOException 
	 */
	private String loadFileToString(String fileName) throws IOException {
	    File file = new File(fileName);
	    StringBuffer content = new StringBuffer();
	    BufferedReader reader = null;

	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String s = null;

	        while ((s = reader.readLine()) != null) {
	            content.append(s).append(System.getProperty("line.separator"));
	        }
	    } catch (FileNotFoundException e) {
	        throw e;
	    } catch (IOException e) {
	        throw e;
	    } finally {
	        try {
	            if (reader != null) {
	                reader.close();
	            }
	        } catch (IOException e) {
	            throw e;
	        }
	    }   
	    return content.toString();
	}
}
