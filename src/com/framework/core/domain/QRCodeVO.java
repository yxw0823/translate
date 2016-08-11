package com.framework.core.domain;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import jp.sourceforge.qrcode.data.QRCodeImage;

public class QRCodeVO implements Serializable, QRCodeImage {
	BufferedImage bufImg;  
	public QRCodeVO(BufferedImage bufImg) {
		super();
		this.bufImg = bufImg;
	}

    public int getHeight() {  
        return bufImg.getHeight();  
    }  
  
    public int getPixel(int x, int y) {  
        return bufImg.getRGB(x, y);  
    }  
  
    public int getWidth() {  
        return bufImg.getWidth();  
    }  

}
