package com.da.branding.branding;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;
import org.osgi.framework.Bundle;

/**
 * Central class for branding logic
 * @author Andy Dunkel
 *
 */
public class Branding {
	
	private static Branding _instance = null;
	
	private enum Brands {
		CATASTIC, FAT_CAT
	}
	
	private Brands _currentBrand;
	private String _brandingPlugin;;


	//consts for products
	private final static String CATASTIC_PRODUCT = "com.da.branding.branding.catastic.product";
	private final static String FATCAT_PRODUCT = "com.da.branding.branding.fatcat.product";
	public final static String CATTASTIC_BRANDING_PLUGIN = "com.da.branding.branding.catastic";
	public final static String FATCAT_BRANDING_PLUGIN = "com.da.branding.branding.fatcat";
	
	
	/**
	 * Constructor
	 */
	private Branding() {
		String product = Platform.getProduct().getId();		
		
		if (product.equals(CATASTIC_PRODUCT)) {
			doDefaultBranding();
		}
		
		if (product.equals(FATCAT_PRODUCT)) {
			doFatCatBranding();
		}
		
		if (_currentBrand == null) {
			doDefaultBranding();
		}
	}
	
	/**
	 * Returns the branding instance
	 * @return
	 */
	public static Branding getInstance() {
		if (_instance == null) {
			_instance = new Branding();
		}
		
		return _instance;
	}
	
	
	/**
	 * Returns an image depending on the
	 * @return
	 */
	public Image getImage() {		
		return ResourceManager.getPluginImage(_brandingPlugin, "res/cat.png");
	}	
	
	/**
	 * Returns a path to a branded file
	 * @return
	 * @throws IOException 
	 * @throws URISyntaxException 
	 */
	public String getFile() throws URISyntaxException, IOException {
		return getPluginFilePath("res/text.txt", _brandingPlugin);
	}
	
	/**
	 * Returns the current branding
	 * @return
	 */
	public Brands getCurrentBrand() {
		return _currentBrand;
	}
	
	/**
	 * Returns the plugin file path
	 * @param fileName
	 * @param bundleName
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	private static String getPluginFilePath(String fileName, String bundleName) throws URISyntaxException, IOException {
		Bundle bundle = Platform.getBundle(bundleName);
		URL fileURL = bundle.getEntry(fileName);
		File file = null;
		
		file = new File(FileLocator.toFileURL(fileURL).getPath());
		
		return file.getPath();
	}

		
	private void doDefaultBranding() {
		_currentBrand = Brands.CATASTIC;
		_brandingPlugin = CATTASTIC_BRANDING_PLUGIN;
	}
	
	private void doFatCatBranding() {
		_currentBrand = Brands.FAT_CAT;
		_brandingPlugin = FATCAT_BRANDING_PLUGIN;
	}	

}
