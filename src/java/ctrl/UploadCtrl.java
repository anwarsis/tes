/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;

 
 
 

/**
 *
 * @author sim
 */
public class UploadCtrl implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

 private     static final String     SAVE_PATH = System.getProperty("user.home"); 
    @AfterCompose
    public void afterCompose(
            @ContextParam(ContextType.VIEW)
            Component view) {
        Selectors.wireComponents(view, this, false);
    }

    @Command
    public void onFileUpload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
        UploadEvent upEvent = null;
        Object objUploadEvent = ctx.getTriggerEvent();

        if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
            upEvent = (UploadEvent) objUploadEvent;

            Media media = upEvent.getMedia();

            //Initialize components
            String name = media.getName();
            String format = media.getFormat();

            byte[] bFile = media.getByteData();

            System.out.println(String.format("File Name: %s, Format: %s" , name, format ));
            saveFile(media);
        }
    }
     private void saveFile(Media media)
        {
          String xnamafile;
           File file;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			InputStream fin = media.getStreamData();
			in = new BufferedInputStream(fin);
                        
			
                       
                          File baseDir = new File(SAVE_PATH+"/DTS");
			  if (!baseDir.exists()) {
			     baseDir.mkdirs();
			  }
//                          xnamafile=tbVSep.getIdRegister()+"_"+naviUpload;    
                           file = new File(SAVE_PATH +"/DTS/jos_bro");  
                       

                        
                        
                        //--------------
			OutputStream fout = new FileOutputStream(file);
			out = new BufferedOutputStream(fout);
			byte buffer[] = new byte[1024];
			int ch = in.read(buffer);
			while (ch != -1) {
				out.write(buffer, 0, ch);
				ch = in.read(buffer);
			}

                 
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
                        try {
                                if (out != null) {
                                out.close();
                            }

                                if (in != null) {
                                in.close();
                            }

                        } catch (IOException e) {
                                throw new RuntimeException(e);
                        }
		}
	} 

}